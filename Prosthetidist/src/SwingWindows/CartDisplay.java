package SwingWindows;

import java.awt.Color;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCDeliveryManager;
import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;

public class CartDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField creditCard;
	private JRadioButton standard;
	private JRadioButton premium;
	private JButton buy;
	private JButton back;
	private JTable table;
	private DefaultTableModel model;
	private JLabel total;

	private JDBCInvoiceManager im;
	private JDBCProstheticManager pm;
	private JDBCDeliveryManager dm;

	public CartDisplay(JFrame patientMenuDisplay, Patient patient, JDBCManager manager) {
		im = new JDBCInvoiceManager(manager);
		pm = new JDBCProstheticManager(manager);
		dm = new JDBCDeliveryManager(manager);

		patientMenuDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 636, 360);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.clearSelection();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 247, 247));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("YOUR SELECTION :");
		lblNewLabel.setBounds(25, 25, 113, 14);
		contentPane.add(lblNewLabel);

		standard = new JRadioButton("STANDARD");
		standard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (standard.isSelected()) {
					premium.setSelected(false);
					Float totalPrice = getTotalPrice(im.getPatientSelection(patient));
					total.setText(String.valueOf(totalPrice));
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		standard.setBounds(142, 177, 111, 23);
		standard.setBackground(new Color(247, 247, 247));
		contentPane.add(standard);

		premium = new JRadioButton("PREMIUM");
		premium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (premium.isSelected()) {
					standard.setSelected(false);
					Float totalPrice = getTotalPrice(im.getPatientSelection(patient)) + 5f;
					total.setText(String.valueOf(totalPrice));
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		premium.setBounds(306, 177, 111, 23);
		premium.setBackground(new Color(247, 247, 247));
		contentPane.add(premium);

		JLabel lblNewLabel_2 = new JLabel("CREDIT CARD NUMBER :");
		lblNewLabel_2.setBounds(54, 224, 159, 14);
		contentPane.add(lblNewLabel_2);

		creditCard = new JTextField();
		creditCard.setBackground(new Color(247, 247, 247));
		creditCard.setBorder(null);
		creditCard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (creditCard.getText().length() > 15) {
					e.consume(); // deja de procesar este evento
					Toolkit.getDefaultToolkit().beep();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() == 16)) { 
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		creditCard.setBounds(230, 221, 133, 20);
		contentPane.add(creditCard);
		creditCard.setColumns(10);

		buy = new JButton("BUY");
		buy.setEnabled(false);
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Long cardNumber = Long.valueOf(creditCard.getText());
					table.selectAll();
					int [] selection = table.getSelectedRows();
					for (int i : selection) {
						Integer prosCode = Integer.valueOf(table.getValueAt(i, 0).toString());
						if (premium.isSelected()) {
							im.updateInvoice(patient, prosCode, cardNumber, "Premium");
						}
						if (standard.isSelected()) {
							im.updateInvoice(patient, prosCode, cardNumber, "Standard");
						}
					}
					JOptionPane.showMessageDialog(CartDisplay.this, "Thank you for buying with us !", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					patientMenuDisplay.setEnabled(true);
					CartDisplay.this.setVisible(false);
					
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(CartDisplay.this, "Invalid credit card number", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		buy.setBounds(488, 278, 89, 23);
		contentPane.add(buy);

		back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				CartDisplay.this.setVisible(false);
			}
		});
		back.setBounds(10, 269, 32, 32);
		contentPane.add(back);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		model = new DefaultTableModel() {
			public boolean isCellEditable(int fil, int col) {
				return false;
			}
		};
		model.addColumn("Code");
		model.addColumn("Price");
		model.addColumn("Functionalities");
		model.addColumn("Type");
		model.addColumn("Model");
		model.addColumn("Length");
		model.addColumn("Width");
		model.addColumn("Weight");
		model.addColumn("Plastic");
		model.addColumn("Carbon Fiber");
		model.addColumn("Aluminium");

		for (Prosthetic p : im.getPatientSelection(patient)) {
			JLabel plasticImg = new JLabel();
			plasticImg.setIcon(new ImageIcon(p.hasPlastic()));
			JLabel carbonFiberImg = new JLabel();
			carbonFiberImg.setIcon(new ImageIcon(p.hasCarbonFiber()));
			JLabel aluminiumImg = new JLabel();
			aluminiumImg.setIcon(new ImageIcon(p.hasAluminium()));

			Object[] datos = new Object[] { p.getCode(), p.getPrice(), p.getFunctionalities(), p.getType(),
					p.getModel(), p.getMeasurement().getLengthiness(), p.getMeasurement().getWidth(),
					p.getMeasurement().getWeight(), plasticImg, carbonFiberImg, aluminiumImg };
			model.addRow(datos);
		}
		table.setModel(model);
		table.getColumn("Plastic").setCellRenderer(new LabelRenderer()); // sets the renderer implemented at the end of this class
		table.getColumn("Carbon Fiber").setCellRenderer(new LabelRenderer());
		table.getColumn("Aluminium").setCellRenderer(new LabelRenderer());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(54, 55, 463, 91);
		scrollPane.setBackground(new Color(247, 247, 247));
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Choose delivery");
		lblNewLabel_1.setBounds(50, 157, 121, 14);
		contentPane.add(lblNewLabel_1);

		JButton delete = new JButton("REMOVE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					int [] selection = table.getSelectedRows();
					for (int i : selection) {
						Integer prosCode = Integer.parseInt(table.getValueAt(i, 0).toString());
						im.deleteProstheticFromCart(patient, prosCode);
						model.removeRow(i);
					}
					total.setText(String.valueOf(getTotalPrice(im.getPatientSelection(patient))));
				} else {
					JOptionPane.showMessageDialog(CartDisplay.this, "Select a prosthetic", "Message",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		delete.setBounds(514, 15, 96, 23);
		contentPane.add(delete);

		total = new JLabel(String.valueOf(getTotalPrice(im.getPatientSelection(patient))));
		total.setBounds(500, 259, 53, 14);
		contentPane.add(total);

		JLabel dollar = new JLabel("");
		Image dollarImg = new ImageIcon(this.getClass().getResource("/dollar.png")).getImage();
		dollar.setIcon(new ImageIcon(dollarImg));
		dollar.setBounds(561, 257, 16, 16);
		contentPane.add(dollar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(230, 241, 133, 5);
		contentPane.add(separator);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				manager.disconnect();
			}
		};
		this.addWindowListener(exitListener);
	}

	private Float getTotalPrice(ArrayList<Prosthetic> cart) {
		Float totalPrice = 0f;
		for (Prosthetic p : cart) {
			totalPrice += p.getPrice();
		}
		return totalPrice;
	}
}

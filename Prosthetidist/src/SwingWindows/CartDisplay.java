package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CartDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField creditCard;
	private JRadioButton standard;
	private JRadioButton premium;
	private JButton buy;
	private JButton back;
	private JTable table;
	private DefaultTableModel model;
	
    private JDBCInvoiceManager im;
    private JDBCProstheticManager pm;


	
	public CartDisplay(JFrame patientMenuDisplay, Patient patient, JDBCManager manager) {
		im = new JDBCInvoiceManager(manager);
		pm = new JDBCProstheticManager(manager);
		patientMenuDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOUR SELECTION :");
		lblNewLabel.setBounds(24, 11, 113, 14);
		contentPane.add(lblNewLabel);
		
		standard = new JRadioButton("STANDARD");
		standard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (standard.isSelected()) {
					premium.setSelected(false);
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		standard.setBounds(41, 177, 111, 23);
		contentPane.add(standard);
		
		premium = new JRadioButton("PREMIUM");
		premium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (premium.isSelected()) {
					standard.setSelected(false);
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		premium.setBounds(195, 177, 111, 23);
		contentPane.add(premium);
		
		JLabel lblNewLabel_2 = new JLabel("CREDIT CARD NUMBER :");
		lblNewLabel_2.setBounds(24, 224, 128, 14);
		contentPane.add(lblNewLabel_2);
		
		creditCard = new JTextField();
		creditCard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (creditCard.getText().length() >= 16) {
					e.consume(); //deja de procesar este evento
					Toolkit.getDefaultToolkit().beep();
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) { //pq 15 y no 16?
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		creditCard.setBounds(173, 221, 133, 20);
		contentPane.add(creditCard);
		creditCard.setColumns(10);
		
		buy = new JButton("BUY");
		buy.setEnabled(false);
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				int[] selection = table.getSelectedRows();
				for(int i : selection) {
					Prosthetic p = pm.getProstheticByCode(Integer.valueOf(model.getValueAt(i, 1).toString()));
					if(premium.isSelected()) {
						im.updateInvoice(patient, p, Integer.valueOf(creditCard.getText()), premium.toString());
					}
					if(standard.isSelected()) {
						im.updateInvoice(patient, p, Integer.valueOf(creditCard.getText()), standard.toString());
					}
				}
				JOptionPane.showMessageDialog(CartDisplay.this, "Thank you for buying with us !", "Message", 
						JOptionPane.INFORMATION_MESSAGE);
				CartDisplay.this.setVisible(false);
			}
		});
		buy.setBounds(377, 282, 89, 23);
		contentPane.add(buy);
		
		back = new JButton("BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				CartDisplay.this.setVisible(false);
			}
		});
		back.setBounds(24, 282, 89, 23);
		contentPane.add(back);
		
		table = new JTable();
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

			Object[] datos = new Object[] { p.getCode(), p.getPrice(), p.getFunctionalities(), p.getType(),
					p.getModel(), p.getMeasurements().getLengthiness(), p.getMeasurements().getWidth(),
					p.getMeasurements().getWeight(), p.hasPlastic(), p.hasCarbonFiber(), p.hasAluminium() };
			model.addRow(datos);
		}
		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 450, 89);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Choose delivery");
		lblNewLabel_1.setBounds(24, 157, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		
	}
}

package SwingWindows;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exceptions.CartException;
import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.pojos.User;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ListSelectionModel;

public class PatientMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JDBCInvoiceManager im;
	private JDBCProstheticManager pm;
	private JTable table;

	public PatientMenuDisplay(JFrame pLogInDisplay, Patient patient, JDBCManager manager, JPAUserManager um) {
		pLogInDisplay.setEnabled(false);
		im = new JDBCInvoiceManager(manager);
		pm = new JDBCProstheticManager(manager);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 360);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.clearSelection();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton logOut = new JButton("LOG OUT");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				um.disconnect();
				pLogInDisplay.setEnabled(true);
				PatientMenuDisplay.this.setVisible(false);
			}
		});
		Image logOutImg = new ImageIcon(this.getClass().getResource("/logOut.png")).getImage();
		logOut.setIcon(new ImageIcon(logOutImg));
		logOut.setBounds(27, 288, 103, 23);
		contentPane.add(logOut);

		JLabel userLabel = new JLabel("");
		Image userImg = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		userLabel.setIcon(new ImageIcon(userImg));
		userLabel.setBounds(27, 15, 45, 39);
		contentPane.add(userLabel);

		JButton design = new JButton("Design My Own");
		design.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame designProsthetic = new DesignProsthetic(PatientMenuDisplay.this, manager);
				designProsthetic.setVisible(true);
			}
		});
		design.setBounds(368, 29, 119, 23);
		contentPane.add(design);

		JButton cart = new JButton("");
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cartDisplay = new CartDisplay(PatientMenuDisplay.this, patient, manager);
				cartDisplay.setVisible(true);
			}
		});
		cart.setBounds(540, 15, 70, 37);
		contentPane.add(cart);
		Image img = new ImageIcon(this.getClass().getResource("/cart.png")).getImage();
		cart.setIcon(new ImageIcon(img));

		JLabel username = new JLabel(patient.getName());
		username.setBounds(70, 29, 89, 14);
		contentPane.add(username);

		JButton addToCart = new JButton("ADD TO CART");
		addToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					int i = table.getSelectedRow();
					Integer prosCode = Integer.parseInt(table.getValueAt(i, 0).toString());
					try {
						im.addProstheticToCart(patient, prosCode);
						JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Your selection was added to your cart",
								"Message", JOptionPane.PLAIN_MESSAGE);
					} catch (CartException ce) {
						JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Already in your cart", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Select a prosthetic first", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		addToCart.setBounds(476, 288, 111, 23);
		contentPane.add(addToCart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(39, 79, 548, 177);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel() {
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

		for (Prosthetic p : pm.getProstheticsWithCompanyId()) {
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
		table.getColumn("Plastic").setCellRenderer(new LabelRenderer()); // sets the renderer implemented at the end of
																			// this class
		table.getColumn("Carbon Fiber").setCellRenderer(new LabelRenderer());
		table.getColumn("Aluminium").setCellRenderer(new LabelRenderer());
		scrollPane.setViewportView(table);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				manager.disconnect();

			}
		};
		this.addWindowListener(exitListener);
	}

}

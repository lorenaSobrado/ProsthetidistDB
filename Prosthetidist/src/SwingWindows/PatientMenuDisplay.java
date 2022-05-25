package SwingWindows;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.ui.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PatientMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JDBCInvoiceManager im;
	private JDBCProstheticManager pm;
	private JTable table;

	public PatientMenuDisplay(JFrame pLogInDisplay, Patient patient, JDBCManager manager) {
		pLogInDisplay.setEnabled(false);
		im = new JDBCInvoiceManager(manager);
		pm = new JDBCProstheticManager(manager);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton logOut = new JButton("LOG OUT");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		design.setBounds(253, 25, 119, 23);
		contentPane.add(design);

		JButton btnNewButton_3 = new JButton("Filters");
		btnNewButton_3.setBounds(382, 24, 89, 23);
		contentPane.add(btnNewButton_3);

		JButton cart = new JButton("");
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cartDisplay = new CartDisplay(PatientMenuDisplay.this, patient, manager);
				cartDisplay.setVisible(true);
			}
		});
		cart.setBounds(491, 17, 70, 37);
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
					int[] selection = table.getSelectedRows();
					for (int i : selection) {
						Integer prosCode = Integer.parseInt(table.getValueAt(i, 0).toString());
						im.addProstheticToCart(patient, prosCode);
					}
					JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Your selection was added to your cart",
							"Message", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Select a prosthetic first", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		addToCart.setBounds(424, 288, 111, 23);
		contentPane.add(addToCart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(39, 79, 488, 177);
		contentPane.add(scrollPane);

		table = new JTable();
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

		for (Prosthetic p : pm.listProstheticsWithCompanyId()) {

			Object[] datos = new Object[] { p.getCode(), p.getPrice(), p.getFunctionalities(), p.getType(),
					p.getModel(), p.getMeasurements().getLengthiness(), p.getMeasurements().getWidth(),
					p.getMeasurements().getWeight(), p.hasPlastic(), p.hasCarbonFiber(), p.hasAluminium() };
			model.addRow(datos);
		}
		table.setModel(model);

	}
}

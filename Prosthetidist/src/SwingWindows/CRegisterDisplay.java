package SwingWindows;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.ifaces.CompanyManager;
import prosthetidist.ifaces.UserManager;
import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.pojos.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private UserManager um;
	private CompanyManager cm;

	public CRegisterDisplay(JFrame companyDisplay, JDBCManager manager) {

		companyDisplay.setEnabled(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("NAME :");
		lblNewLabel.setBounds(41, 43, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PHONE NUMBER :");
		lblNewLabel_1.setBounds(41, 86, 93, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EMAIL :");
		lblNewLabel_2.setBounds(41, 127, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PASSWORD :");
		lblNewLabel_3.setBounds(41, 171, 93, 14);
		contentPane.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(183, 40, 110, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(183, 83, 110, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(183, 124, 110, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(183, 168, 110, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyDisplay.setEnabled(true);
				CRegisterDisplay.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Company company = new Company();

				company.setName(textField.getText());
				company.setPhone(Integer.parseInt(textField_1.getText()));
				String email = textField_2.getText();
				company.setEmail(email);
				String password = textField_3.getText();

//				// for the password
//				try {
//					MessageDigest md = MessageDigest.getInstance("MD5");
//					md.update(password.getBytes());
//					byte[] digest = md.digest();
//					User u = new User(email, digest);
//					Role role = um.getRole("Company");
//					u.setRole(role);
//					role.addUser(u);
//					um.newUser(u);
//
//				} catch (NoSuchAlgorithmException e1) {
//
//					e1.printStackTrace();
//
//				}
				cm = new JDBCCompanyManager (manager);
				cm.addCompany(company);
				JOptionPane.showMessageDialog(CRegisterDisplay.this, "Register successfull", "Message", JOptionPane.PLAIN_MESSAGE);
				companyDisplay.setEnabled(true);
				CRegisterDisplay.this.setVisible(false);

			}
		});
		btnNewButton_1.setBounds(236, 227, 89, 23);
		contentPane.add(btnNewButton_1);
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

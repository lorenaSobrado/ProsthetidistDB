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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField email;
	private JTextField passwordReadable;
	private JButton register;

	private UserManager um;
	private CompanyManager cm;
	private JPasswordField passwordHide;

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

		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		name.setBounds(183, 40, 110, 20);
		contentPane.add(name);
		name.setColumns(10);

		phone = new JTextField();
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		phone.setBounds(183, 83, 110, 20);
		contentPane.add(phone);
		phone.setColumns(10);

		email = new JTextField();
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		email.setBounds(183, 124, 110, 20);
		contentPane.add(email);
		email.setColumns(10);

		passwordReadable = new JTextField();
		passwordReadable.setVisible(false);
		passwordReadable.setBounds(183, 168, 110, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyDisplay.setEnabled(true);
				CRegisterDisplay.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);

		register = new JButton("Register");
		register.setEnabled(false);
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Company company = new Company();

				company.setName(name.getText());
				company.setPhone(Integer.parseInt(phone.getText()));
				company.setEmail(email.getText());
				String password = passwordHide.getText();

//				// for the password
//				try {
//					MessageDigest md = MessageDigest.getInstance("MD5");
//					md.update(password.getBytes());
//					byte[] digest = md.digest();
//					User u = new User(email.getText(), digest);
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
		register.setBounds(236, 227, 89, 23);
		contentPane.add(register);
		
		passwordHide = new JPasswordField();
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		passwordHide.setBounds(183, 168, 110, 20);
		contentPane.add(passwordHide);
		
		JCheckBox showPassword = new JCheckBox("");
		showPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPassword.setSelected(true);
				passwordReadable.setVisible(true);
				passwordHide.setVisible(false);
				passwordReadable.setText(passwordHide.getText());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				showPassword.setSelected(false);
				passwordReadable.setVisible(false);
				passwordHide.setVisible(true);
				passwordHide.setText(passwordReadable.getText());
			}
		});
		showPassword.setBounds(302, 167, 30, 23);
		contentPane.add(showPassword);
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

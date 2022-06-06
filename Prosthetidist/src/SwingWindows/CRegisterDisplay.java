package SwingWindows;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.ifaces.CompanyManager;
import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JSeparator;

public class CRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField email;
	private JTextField passwordReadable;
	private JButton register;
	private JButton back;

	private JPAUserManager um;
	private CompanyManager cm;
	private JPasswordField passwordHide;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;

	public CRegisterDisplay(JFrame cLogInDisplay, JDBCManager manager) {
		cLogInDisplay.setEnabled(false);
		cm = new JDBCCompanyManager(manager);
		um = new JPAUserManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 247, 247));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("NAME :");
		lblNewLabel.setBounds(41, 43, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PHONE NUMBER :");
		lblNewLabel_1.setBounds(41, 86, 131, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EMAIL :");
		lblNewLabel_2.setBounds(41, 127, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PASSWORD :");
		lblNewLabel_3.setBounds(41, 171, 93, 14);
		contentPane.add(lblNewLabel_3);

		name = new JTextField();
		name.setBackground(new Color(247, 247, 247));
		name.setBorder(null);
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()
						&& !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		name.setBounds(183, 40, 110, 20);
		contentPane.add(name);
		name.setColumns(10);

		phone = new JTextField();
		phone.setBackground(new Color(247, 247, 247));
		phone.setBorder(null);
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()
						&& !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		phone.setBounds(183, 83, 110, 20);
		contentPane.add(phone);
		phone.setColumns(10);

		email = new JTextField();
		email.setBackground(new Color(247, 247, 247));
		email.setBorder(null);
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()
						&& !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else 
					register.setEnabled(false);
			}
		});
		email.setBounds(183, 124, 110, 20);
		contentPane.add(email);
		email.setColumns(10);

		passwordReadable = new JTextField();
		passwordReadable.setBackground(new Color(247, 247, 247));
		passwordReadable.setBorder(null);
		passwordReadable.setVisible(false);
		passwordReadable.setBounds(183, 168, 110, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);

		back = new JButton("");
		back.setForeground(new Color(240, 240, 240));
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				um.disconnect();
				cLogInDisplay.setEnabled(true);
				CRegisterDisplay.this.setVisible(false);
			}
		});
		back.setBounds(10, 218, 32, 32);
		contentPane.add(back);

		register = new JButton("Register");
		register.setEnabled(false);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateEmail(email.getText())) {
					JOptionPane.showMessageDialog(CRegisterDisplay.this, "Invalid email", "Message",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {

						Company company = new Company();
						company.setName(name.getText());
						company.setPhone(Integer.parseInt(phone.getText()));
						company.setEmail(email.getText());
						String password = passwordHide.getText();

						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(password.getBytes());
						byte[] digest = md.digest();
						User u = new User(email.getText(), digest);
						Role role = um.getRole("Company");
						u.setRole(role);
						role.addUser(u);
						um.newUser(u);

						cm.addCompany(company);

						JOptionPane.showMessageDialog(CRegisterDisplay.this, "Register successfull", "Message",
								JOptionPane.PLAIN_MESSAGE);
						um.disconnect();
						cLogInDisplay.setEnabled(true);
						CRegisterDisplay.this.setVisible(false);
					} catch (NoSuchAlgorithmException | SQLException ex) {
						JOptionPane.showMessageDialog(CRegisterDisplay.this, "This account already exists. Try to log in or use a different email", "Message",
								JOptionPane.WARNING_MESSAGE);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(CRegisterDisplay.this, "Invalid phone number", "Message",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		register.setBounds(302, 227, 110, 23);
		contentPane.add(register);

		passwordHide = new JPasswordField();
		passwordHide.setBackground(new Color(247, 247, 247));
		passwordHide.setBorder(null);
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()
						&& !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
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
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(183, 60, 110, 5);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(182, 103, 110, 5);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(183, 144, 110, 5);
		contentPane.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(183, 188, 110, 5);
		contentPane.add(separator_3);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				manager.disconnect();
			}
		};
		this.addWindowListener(exitListener);
	}

	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+"); //String of available characters and pattern 
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
}

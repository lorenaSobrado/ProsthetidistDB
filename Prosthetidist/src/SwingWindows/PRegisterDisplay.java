package SwingWindows;

import prosthetidist.jdbc.JDBCManager;

import prosthetidist.jdbc.JDBCPatientManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.*;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;
import com.toedter.calendar.JCalendar;

import exceptions.PatientIdException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;

public class PRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField id;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private JTextField medicalCond;
	private LocalDate dob;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;
	private JTextField passwordReadable;
	private JButton register;
	private JButton back;
	private JCalendar calendar;

	private JPAUserManager um;
	private JDBCPatientManager pm;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private JSeparator separator_6;

	public PRegisterDisplay(JFrame patientDisplay, JDBCManager manager) {
		patientDisplay.setEnabled(false);
		pm = new JDBCPatientManager(manager);
		um = new JPAUserManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 349);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(247, 247, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 35, 61, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 62, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 94, 61, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(10, 122, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 153, 61, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Medical Condition");
		lblNewLabel_5.setBounds(10, 185, 112, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setBounds(376, 28, 85, 27);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Password");
		lblNewLabel_6_1.setBounds(10, 212, 85, 16);
		contentPane.add(lblNewLabel_6_1);

		name = new JTextField();
		name.setBackground(new Color(247, 247, 247));
		name.setBorder(null);
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& (phone.getText().length() == 9) && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);

			}
		});
		name.setBounds(121, 31, 123, 19);
		contentPane.add(name);
		name.setColumns(10);

		id = new JTextField();
		id.setBackground(new Color(247, 247, 247));
		id.setBorder(null);
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& !phone.getText().isEmpty() && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		id.setBounds(121, 59, 123, 20);
		contentPane.add(id);
		id.setColumns(10);

		email = new JTextField();
		email.setBackground(new Color(247, 247, 247));
		email.setBorder(null);
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& (phone.getText().length() == 9) && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		email.setBounds(121, 90, 123, 19);
		contentPane.add(email);
		email.setColumns(10);

		phone = new JTextField();
		phone.setBackground(new Color(247, 247, 247));
		phone.setBorder(null);
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (phone.getText().length() > 8) {
					e.consume(); // deja de procesar este evento
					Toolkit.getDefaultToolkit().beep();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& (phone.getText().length() == 9) && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		phone.setBounds(121, 120, 123, 19);
		contentPane.add(phone);
		phone.setColumns(10);

		address = new JTextField();
		address.setBackground(new Color(247, 247, 247));
		address.setBorder(null);
		address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& (phone.getText().length() == 9) && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		address.setBounds(121, 150, 123, 19);
		contentPane.add(address);
		address.setColumns(10);

		medicalCond = new JTextField();
		medicalCond.setBackground(new Color(247, 247, 247));
		medicalCond.setBorder(null);
		medicalCond.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& !phone.getText().isEmpty() && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		medicalCond.setBounds(121, 180, 123, 19);
		contentPane.add(medicalCond);
		medicalCond.setColumns(10);

		passwordHide = new JPasswordField();
		passwordHide.setBackground(new Color(247, 247, 247));
		passwordHide.setBorder(null);
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty()
						&& (phone.getText().length() == 9) && !address.getText().isEmpty()
						&& !medicalCond.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					register.setEnabled(true);
				} else
					register.setEnabled(false);
			}
		});
		passwordHide.setBounds(121, 210, 123, 20);
		contentPane.add(passwordHide);

		passwordReadable = new JTextField();
		passwordReadable.setBackground(new Color(247, 247, 247));
		passwordReadable.setBorder(null);
		passwordReadable.setBounds(121, 210, 123, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);
		passwordReadable.setVisible(false);

		register = new JButton("Register");
		register.setEnabled(false);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateEmail(email.getText())) {
					JOptionPane.showMessageDialog(PRegisterDisplay.this, "Invalid email", "Message",
							JOptionPane.ERROR_MESSAGE);
				} else if(dob == null){
					JOptionPane.showMessageDialog(PRegisterDisplay.this, "Select your date of birth", "Message",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {

						Patient patient = new Patient();
						patient.setName(name.getText());
						patient.setId(Integer.parseInt(id.getText()));
						patient.setEmail(email.getText());
						patient.setPhone(Integer.parseInt(phone.getText()));
						patient.setAddress(address.getText());
						patient.setNotes(medicalCond.getText());
						patient.setDob(dob);
						String password = passwordHide.getText();

						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(password.getBytes());
						byte[] digest = md.digest();
						User u = new User(email.getText(), digest);
						Role role = um.getRole("Patient");
						u.setRole(role);
						role.addUser(u);
						um.newUser(u);

						pm.addPatient(patient);
						JOptionPane.showMessageDialog(PRegisterDisplay.this, "Register successfull", "Message",
								JOptionPane.PLAIN_MESSAGE);
						um.disconnect();
						patientDisplay.setEnabled(true);
						PRegisterDisplay.this.setVisible(false);

					} catch (NoSuchAlgorithmException | SQLException ex) {
						JOptionPane.showMessageDialog(PRegisterDisplay.this, "This account already exists. Try to log in or use a different email", "Message",
								JOptionPane.WARNING_MESSAGE);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(PRegisterDisplay.this, "Invalid id or pone number", "Message",
								JOptionPane.ERROR_MESSAGE);
					} catch (PatientIdException px) {
						JOptionPane.showMessageDialog(PRegisterDisplay.this, "Id already used", "Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		register.setBounds(398, 278, 85, 21);
		contentPane.add(register);

		back = new JButton("");
		Image backImg = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(backImg));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				um.disconnect();
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		back.setBounds(10, 267, 32, 32);
		contentPane.add(back);

		showPassword = new JCheckBox("");
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
				passwordHide.setVisible(true);
				passwordReadable.setVisible(false);
				passwordHide.setText(passwordReadable.getText());
			}
		});
		showPassword.setBounds(251, 210, 21, 23);
		contentPane.add(showPassword);
		
		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue() != null) {
					int year = calendar.getYearChooser().getYear();
					int month = calendar.getMonthChooser().getMonth();
					int day = calendar.getDayChooser().getDay();
					dob = LocalDate.of(year, month, day);
				}
			}
		});
		calendar.setBounds(322, 55, 198, 153);
		contentPane.add(calendar);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.BLACK);
		separator.setBounds(121, 50, 123, 5);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(121, 230, 123, 5);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(121, 199, 123, 5);
		contentPane.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.WHITE);
		separator_3.setBounds(121, 169, 123, 5);
		contentPane.add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.WHITE);
		separator_4.setBounds(121, 79, 123, 5);
		contentPane.add(separator_4);
		
		separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.WHITE);
		separator_5.setBounds(121, 109, 123, 5);
		contentPane.add(separator_5);
		
		separator_6 = new JSeparator();
		separator_6.setForeground(Color.BLACK);
		separator_6.setBackground(Color.WHITE);
		separator_6.setBounds(121, 139, 123, 5);
		contentPane.add(separator_6);
		
		

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
		Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+"); // String of available characters and pattern
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
}

package SwingWindows;

import java.awt.BorderLayout;



import prosthetidist.ifaces.PatientManager;
import prosthetidist.ifaces.UserManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class PRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField name;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private JTextField medicalCond;
	private JTextField dob;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JButton register;
	
	private JPAUserManager um;
	private PatientManager pm;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;
	private JTextField passwordReadable;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PRegisterDisplay frame = new PRegisterDisplay();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PRegisterDisplay(JFrame patientDisplay) {
		patientDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 23, 61, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 51, 61, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(10, 75, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 112, 61, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Medical Condition");
		lblNewLabel_5.setBounds(10, 143, 112, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setBounds(10, 170, 85, 27);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password");
		lblNewLabel_6_1.setBounds(10, 208, 85, 27);
		contentPane.add(lblNewLabel_6_1);

		
		name = new JTextField(); 
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
					
			}
		});
		name.setBounds(121, 19, 96, 19);
		contentPane.add(name);
		name.setColumns(10);

		email = new JTextField();
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		email.setBounds(121, 47, 96, 19);
		contentPane.add(email);
		email.setColumns(10);

		phone = new JTextField();
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		phone.setBounds(121, 77, 96, 19);
		contentPane.add(phone);
		phone.setColumns(10);

		address = new JTextField();
		address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		address.setBounds(121, 109, 96, 19);
		contentPane.add(address);
		address.setColumns(10);

		medicalCond = new JTextField();
		medicalCond.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		medicalCond.setBounds(121, 141, 96, 19);
		contentPane.add(medicalCond);
		medicalCond.setColumns(10);

		dob = new JTextField();
		dob.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		dob.setBounds(121, 173, 96, 19);
		contentPane.add(dob);
		dob.setColumns(10);
		
		passwordHide = new JPasswordField();
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
//				if(!passwordHide.getText().isEmpty()) {
//					showPassword.setEnabled(true);
//				} else showPassword.setEnabled(false);
				
			}
		});
		passwordHide.setBounds(121, 211, 96, 20);
		contentPane.add(passwordHide);
		
		passwordReadable = new JTextField();
		passwordReadable.setBounds(121, 211, 96, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);
		passwordReadable.setVisible(false);

		register= new JButton("Register");
		register.setEnabled(false);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				
				patient.setName(name.getText());
				patient.setEmail(email.getText());
				patient.setPhone(Integer.parseInt(phone.getText()));
				patient.setAddress(address.getText());
				patient.setNotes(medicalCond.getText());
				LocalDate date = LocalDate.parse(dob.getText()); //year-mes-dia
				patient.setDob(date); 
				String password = passwordHide.getText();
				
//				try {
//					
//					MessageDigest md= MessageDigest.getInstance ("MD5");
//					md.update(password.getBytes());
//					byte [] digest= md.digest();
//					User u = new User (email, digest);
//					Role role = um.getRole("Patient");
//					u.setRole(role);
//					role.addUser(u);
//					um.newUser(u);
//
//				} catch (NoSuchAlgorithmException e1) { 
//					
//					e1.printStackTrace();
//				}
//				pm.addPatient(patient);
				JOptionPane.showMessageDialog(PRegisterDisplay.this, "Register successfull", "Message", JOptionPane.PLAIN_MESSAGE);
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		register.setBounds(246, 232, 85, 21);
		contentPane.add(register);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		cancel.setBounds(341, 232, 85, 21);
		contentPane.add(cancel);
		
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
		showPassword.setBounds(223, 210, 21, 23);
		contentPane.add(showPassword);
		
		
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

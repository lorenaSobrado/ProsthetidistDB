package SwingWindows;

import java.awt.BorderLayout;



import prosthetidist.ifaces.PatientManager;
import prosthetidist.ifaces.UserManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCPatientManager;
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
	private JTextField name;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private JTextField medicalCond;
	private JTextField dob;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;
	private JTextField passwordReadable;
	private JButton register;
	
	private JPAUserManager um;
	private JDBCManager m;
	private JDBCPatientManager pm;
	private JTextField id;
	

	public PRegisterDisplay(JFrame patientDisplay, JDBCManager manager) {
		patientDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 23, 61, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 52, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 83, 61, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(10, 111, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 144, 61, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Medical Condition");
		lblNewLabel_5.setBounds(10, 169, 112, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setBounds(10, 196, 85, 27);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password");
		lblNewLabel_6_1.setBounds(10, 234, 85, 27);
		contentPane.add(lblNewLabel_6_1);

		
		name = new JTextField(); 
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
					
			}
		});
		name.setBounds(121, 19, 96, 19);
		contentPane.add(name);
		name.setColumns(10);
		
		id = new JTextField();
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		id.setBounds(121, 49, 96, 20);
		contentPane.add(id);
		id.setColumns(10);

		email = new JTextField();
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		email.setBounds(121, 79, 96, 19);
		contentPane.add(email);
		email.setColumns(10);

		phone = new JTextField();
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		phone.setBounds(121, 109, 96, 19);
		contentPane.add(phone);
		phone.setColumns(10);

		address = new JTextField();
		address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		address.setBounds(121, 139, 96, 19);
		contentPane.add(address);
		address.setColumns(10);

		medicalCond = new JTextField();
		medicalCond.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		medicalCond.setBounds(121, 167, 96, 19);
		contentPane.add(medicalCond);
		medicalCond.setColumns(10);

		dob = new JTextField();
		dob.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		dob.setBounds(121, 202, 96, 19);
		contentPane.add(dob);
		dob.setColumns(10);
		
		passwordHide = new JPasswordField();
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!name.getText().isEmpty() && !id.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() &&
						!medicalCond.getText().isEmpty() && !dob.getText().isEmpty() && !passwordHide.getText().isEmpty()){
					register.setEnabled(true);
				} else register.setEnabled(false);
			}
		});
		passwordHide.setBounds(121, 237, 96, 20);
		contentPane.add(passwordHide);
		
		passwordReadable = new JTextField();
		passwordReadable.setBounds(121, 237, 96, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);
		passwordReadable.setVisible(false);

		register= new JButton("Register");
		register.setEnabled(false);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				
				patient.setName(name.getText());
				patient.setId(Integer.parseInt(id.getText()));
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
//					byte [] digest = md.digest();
//					User u = new User (email.getText(), digest);
//					um = new JPAUserManager(); nose si este bien
//					Role role = um.getRole("Patient");
//					u.setRole(role);
//					role.addUser(u);
//					um.newUser(u);
//
//				} catch (NoSuchAlgorithmException e1) { 
//					
//					e1.printStackTrace();
//				}
				pm = new JDBCPatientManager(manager);
				pm.addPatient(patient);
				JOptionPane.showMessageDialog(PRegisterDisplay.this, "Register successfull", "Message", JOptionPane.PLAIN_MESSAGE);
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		register.setBounds(330, 264, 85, 21);
		contentPane.add(register);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		cancel.setBounds(425, 264, 85, 21);
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
		showPassword.setBounds(223, 234, 21, 23);
		contentPane.add(showPassword);
		
		
		
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

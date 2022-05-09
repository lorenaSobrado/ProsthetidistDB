package SwingWindows;

import java.awt.BorderLayout;


<<<<<<< HEAD
import prosthetidist.ifaces.PatientManager;
import prosthetidist.ifaces.UserManager;
=======


>>>>>>> branch 'master' of https://github.com/lorenaSobrado/ProsthetidistDB.git
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

public class PRegisterDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JTextField textField_7;
	
	private UserManager um;
	private PatientManager pm;

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
		lblNewLabel_1.setBounds(10, 27, 61, 13);
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

		
		textField_1 = new JTextField(); //PQ TE HACE DE UNA EL 1 Y NO EL QUE ES SIN NUMERO??
		textField_1.setBounds(121, 23, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(121, 47, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(121, 77, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(121, 109, 96, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(121, 141, 96, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(121, 173, 96, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
			
		textField_7 = new JTextField();
		textField_7.setBounds(121, 211, 96, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseReleased(MouseEvent e) {
				if (textField_1.getText().length() > 0 && textField_2.getText().length() > 0
						&& textField_3.getText().length() > 0 && textField_4.getText().length() > 0
						&& textField_5.getText().length() > 0 && textField_6.getText().length() > 0) {
					
					boolean Validar = true; 
					JFrame patientMenuDisp = new PatientMenuDisplay();
					patientMenuDisp.setVisible(Validar);
					
					Patient patient = new Patient();
					
					patient.setName(textField_1.getText());
					String email= textField_2.getText();
					patient.setEmail(email);
					patient.setPhone(Integer.parseInt(textField_3.getText()));
					patient.setAddress(textField_4.getText());
					patient.setNotes(textField_5.getText());
					LocalDate date = LocalDate.parse(textField_6.getText(), formatter);
					patient.setDob(Date.valueOf(date)); //it must be a date 
					String password= textField_7.getText();
					
					try {
						
						MessageDigest md= MessageDigest.getInstance ("MD5");
						md.update(password.getBytes());
						byte [] digest= md.digest();
						User u= new User (email, digest);
						Role role = um.getRole("Patient");
						u.setRole(role);
						role.addUser(u);
						um.newUser(u);
		
					}catch (NoSuchAlgorithmException e1) { 
						
						e1.printStackTrace();
						
					}
					pm.addPatient(patient);
					
					

				} else {
					validarDatos();
				}
=======
				patientDisplay.setEnabled(true);
				JOptionPane.showMessageDialog(PRegisterDisplay.this, "Register successfull", "Message", JOptionPane.PLAIN_MESSAGE);
				PRegisterDisplay.this.setVisible(false);
>>>>>>> branch 'master' of https://github.com/lorenaSobrado/ProsthetidistDB.git
			}
		});
		btnNewButton.setBounds(225, 232, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientDisplay.setEnabled(true);
				PRegisterDisplay.this.setVisible(false);
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		btnNewButton_1.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_1);
		
	
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCPatientManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.User;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class PLogInDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;
	private JButton logIn;
	private JPAUserManager um;
	private JDBCPatientManager pm;
	private User user;
	private Patient patient;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PLogInDisplay frame = new PLogInDisplay();
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
	public PLogInDisplay(JFrame patientDisplay) {
		patientDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		email = new JTextField();
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!email.getText().isEmpty() && !password.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else logIn.setEnabled(false);
			}
		});
		email.setBounds(146, 63, 164, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!email.getText().isEmpty() && !password.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else logIn.setEnabled(false);
			}
		});
		password.setBounds(146, 94, 164, 20);
		contentPane.add(password);

		logIn = new JButton("Log in");
		logIn.setEnabled(false);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				//After jpa log in
//				user = um.checkPassword(email.getText(), password.getText());
//				patient = pm.getPatientByEmail(user.getEmail());
				patient = new Patient();
				JFrame patientMenuDisplay = new PatientMenuDisplay(PLogInDisplay.this, patient);
				patientMenuDisplay.setVisible(true);
			}
		});
		logIn.setBounds(221, 230, 89, 23);
		contentPane.add(logIn);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientDisplay.setEnabled(true);
				PLogInDisplay.this.setVisible(false);
			}
		});
		cancel.setBounds(320, 231, 85, 21);
		contentPane.add(cancel);

		JLabel lblNewLabel = new JLabel("EMAIL :");
		lblNewLabel.setBounds(28, 67, 65, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PASSWORD :");
		lblNewLabel_1.setBounds(28, 104, 79, 13);
		contentPane.add(lblNewLabel_1);
		
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

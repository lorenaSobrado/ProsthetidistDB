package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCPatientManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.User;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
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
import javax.swing.JCheckBox;

public class PLogInDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JPasswordField passwordHide;
	private JButton logIn;
	private JPAUserManager um;
	private JDBCPatientManager pm;
	private User user;
	private Patient patient;
	private JTextField passwordReadable;
	private JCheckBox showPassword;
	private JLabel imgLabel;

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
	public PLogInDisplay(JFrame patientDisplay, JDBCManager manager) {
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
				if(!email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else logIn.setEnabled(false);
			}
		});
		email.setBounds(146, 137, 164, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		passwordHide = new JPasswordField();
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!email.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else logIn.setEnabled(false);
			}
		});
		passwordHide.setBounds(146, 168, 164, 20);
		contentPane.add(passwordHide);
		
		passwordReadable = new JTextField();
		passwordReadable.setBounds(146, 168, 164, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);

		logIn = new JButton("Log in");
		logIn.setEnabled(false);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				//After jpa log in. 
//				um = new JPAUserManager();
//				pm = new JDBCPatientManager(manager);
//				user = new User();
//				user = um.checkPassword(email.getText(), passwordHide.getText());
//				patient = pm.getPatientByEmail(user.getEmail());
				patient = new Patient();
				JFrame patientMenuDisplay = new PatientMenuDisplay(PLogInDisplay.this, patient, manager);
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
		lblNewLabel.setBounds(28, 141, 65, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PASSWORD :");
		lblNewLabel_1.setBounds(28, 172, 79, 13);
		contentPane.add(lblNewLabel_1);
		
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
		showPassword.setBounds(317, 165, 29, 23);
		contentPane.add(showPassword);
		
		imgLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logIn.png")).getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(146, 11, 164, 103);
		contentPane.add(imgLabel);
		
		
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

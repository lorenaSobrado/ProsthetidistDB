package SwingWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCPatientManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.User;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
	private JTextField userName;
	private JTextField passwordReadable;
	private JButton back;
	private JButton logIn;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;
	private JPAUserManager um;
	private JDBCPatientManager pm;

	public PLogInDisplay(JFrame appDisplay, JDBCManager manager) {
		appDisplay.setEnabled(false);
		um = new JPAUserManager();
		pm = new JDBCPatientManager(manager);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userName = new JTextField();
		userName.setBounds(140, 59, 149, 20);
		contentPane.add(userName);
		userName.setColumns(10);

		passwordHide = new JPasswordField();
		passwordHide.setBackground(Color.WHITE);
		passwordHide.setBounds(140, 101, 149, 20);
		contentPane.add(passwordHide);

		passwordReadable = new JTextField();
		passwordReadable.setBounds(140, 101, 149, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);

		JLabel lblNewLabel = new JLabel("USERNAME :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(42, 61, 88, 17);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PASSWORD :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(42, 103, 88, 17);
		contentPane.add(lblNewLabel_1);

		back = new JButton("");
		back.setForeground(new Color(240, 240, 240));
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appDisplay.setEnabled(true);
				PLogInDisplay.this.setVisible(false);
			}
		});
		back.setBounds(10, 218, 32, 32);
		contentPane.add(back);

		logIn = new JButton("LOG IN");
		logIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		logIn.setBackground(new Color(0, 191, 255));
		logIn.setVerticalAlignment(SwingConstants.CENTER);
		logIn.setForeground(Color.WHITE);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = um.checkPassword(userName.getText(), passwordHide.getText());
				if (u != null) {
					Patient patient = pm.getPatientByEmail(u.getEmail());
					JFrame patientMenuDisplay = new PatientMenuDisplay(PLogInDisplay.this, patient, manager);
					patientMenuDisplay.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(PLogInDisplay.this, "Your password is incorrect or this account does not exist", "Message", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		logIn.setBounds(107, 142, 213, 23);
		contentPane.add(logIn);

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
				passwordReadable.setVisible(false);
				passwordHide.setVisible(true);
				passwordHide.setText(passwordReadable.getText());
			}
		});
		showPassword.setBounds(295, 101, 25, 23);
		contentPane.add(showPassword);

		JLabel separation = new JLabel("-----------------------------------------------------\r\n");
		separation.setBounds(107, 170, 213, 14);
		contentPane.add(separation);

		JButton register = new JButton("Create New Account");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame pRegisterDisplay = new PRegisterDisplay(PLogInDisplay.this, manager);
				pRegisterDisplay.setVisible(true);
			}
		});
		register.setBackground(Color.BLACK);
		register.setForeground(Color.WHITE);
		register.setBounds(107, 191, 213, 23);
		contentPane.add(register);

	}
}

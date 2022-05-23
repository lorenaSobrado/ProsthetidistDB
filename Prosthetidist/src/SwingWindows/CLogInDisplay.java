package SwingWindows;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.pojos.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class CLogInDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField passwordReadable;
	private JButton cancel;
	private JButton logIn;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CLogInDisplay frame = new CLogInDisplay();
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
	public CLogInDisplay(JDBCManager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userName = new JTextField();
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!userName.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else {
					logIn.setEnabled(false);
				}
			}
		});
		userName.setBounds(165, 73, 124, 20);
		contentPane.add(userName);
		userName.setColumns(10);
		
		passwordHide = new JPasswordField();
		passwordHide.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!userName.getText().isEmpty() && !passwordHide.getText().isEmpty()) {
					logIn.setEnabled(true);
				} else {
					logIn.setEnabled(false);
				}
			}
		});
		passwordHide.setBounds(165, 134, 124, 20);
		contentPane.add(passwordHide);
		
		passwordReadable = new JTextField();
		passwordReadable.setBounds(165, 134, 124, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USERNAME :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(42, 76, 88, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(42, 137, 88, 17);
		contentPane.add(lblNewLabel_1);
		
		cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CLogInDisplay.this.setVisible(false);
			}
		});
		cancel.setBounds(231, 227, 89, 23);
		contentPane.add(cancel);
		
		logIn = new JButton("LOG IN");
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Company company = new Company();
				JFrame companyMenuDisplay = new CompanyMenuDisplay(company, manager);
				companyMenuDisplay.setVisible(true);
			}
		});
		logIn.setBounds(335, 227, 89, 23);
		logIn.setEnabled(false);
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
		showPassword.setBounds(295, 133, 25, 23);
		contentPane.add(showPassword);
		
	}
	
	
}

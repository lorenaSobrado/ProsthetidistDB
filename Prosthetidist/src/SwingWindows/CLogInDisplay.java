package SwingWindows;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import java.awt.Font;

public class CLogInDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField passwordReadable;
	private JButton back;
	private JButton logIn;
	private JPasswordField passwordHide;
	private JCheckBox showPassword;
	private JSeparator separator;
	private JSeparator separator_1;
	
	private JPAUserManager um;
	private JDBCCompanyManager cm;

	public CLogInDisplay(JFrame appDisplay, JDBCManager manager) {
		appDisplay.setEnabled(false);
		cm = new JDBCCompanyManager(manager);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 247, 247));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userName = new JTextField();
		userName.setBackground(new Color(247, 247, 247));
		userName.setBorder(null);
		userName.setBounds(140, 59, 149, 20);
		contentPane.add(userName);
		userName.setColumns(10);

		passwordHide = new JPasswordField();
		passwordHide.setBackground(new Color(247, 247, 247));
		passwordHide.setBorder(null);
		passwordHide.setBounds(140, 101, 149, 20);
		contentPane.add(passwordHide);

		passwordReadable = new JTextField();
		passwordReadable.setBackground(new Color(247, 247, 247));
		passwordReadable.setBorder(null);
		passwordReadable.setBounds(140, 101, 149, 20);
		contentPane.add(passwordReadable);
		passwordReadable.setColumns(10);

		JLabel lblNewLabel = new JLabel("EMAIL:");
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
				CLogInDisplay.this.setVisible(false);
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
				um = new JPAUserManager();
				User u = um.checkPassword(userName.getText(), passwordHide.getText());
				if (u != null) {
					Company company = cm.getCompanyByEmail(u.getEmail());
					JFrame companyMenuDisplay = new CompanyMenuDisplay(CLogInDisplay.this, company, manager, um);
					companyMenuDisplay.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(CLogInDisplay.this,
							"Your password is incorrect or this account does not exist", "Message",
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
				JFrame cRegisterDisplay = new CRegisterDisplay(CLogInDisplay.this, manager);
				cRegisterDisplay.setVisible(true);
			}
		});
		register.setBackground(Color.BLACK);
		register.setForeground(Color.WHITE);
		register.setBounds(107, 191, 213, 23);
		contentPane.add(register);
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(140, 79, 149, 5);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(140, 121, 149, 5);
		contentPane.add(separator_1);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				manager.disconnect();

			}
		};
		this.addWindowListener(exitListener);
	}
}

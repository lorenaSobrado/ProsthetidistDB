package SwingWindows;

import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class AppDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField txtProsthetidist;

	public AppDisplay(JDBCManager manager) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton_1 = new JButton("PATIENT");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(277, 243, 172, 43);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame pLogInDisplay = new PLogInDisplay(AppDisplay.this, manager);
				pLogInDisplay.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("COMPANY");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(33, 243, 166, 43);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.inactiveCaption);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cLogInDisplay = new CLogInDisplay(AppDisplay.this, manager);
				cLogInDisplay.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);

		txtProsthetidist = new JTextField();
		txtProsthetidist.setForeground(new Color(0, 0, 0));
		txtProsthetidist.setBounds(57, 156, 377, 36);
		txtProsthetidist.setCaretColor(SystemColor.inactiveCaptionBorder);
		txtProsthetidist.setBackground(SystemColor.inactiveCaptionBorder);
		txtProsthetidist.setHorizontalAlignment(SwingConstants.CENTER);
		txtProsthetidist.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 40));
		txtProsthetidist.setText("PROSTHETIDIST\r\n");
		txtProsthetidist.setBorder(null);
		contentPane.add(txtProsthetidist);
		txtProsthetidist.setColumns(30);

		JLabel imgLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/prosthetic.png")).getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(117, 16, 205, 129);
		contentPane.add(imgLabel);

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

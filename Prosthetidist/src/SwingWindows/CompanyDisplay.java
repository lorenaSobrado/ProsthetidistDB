package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyDisplay extends JFrame {

	private JPanel contentPane;


	public CompanyDisplay(JFrame appDisplay, JDBCManager manager) {
		appDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cLogInDisplay = new CLogInDisplay(manager);
				cLogInDisplay.setVisible(true);
			}
		});
		btnNewButton.setBounds(153, 55, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cRegisterDisplay = new CRegisterDisplay(CompanyDisplay.this, manager);
				cRegisterDisplay.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(153, 123, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appDisplay.setEnabled(true);
				CompanyDisplay.this.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(291, 227, 89, 23);
		contentPane.add(btnNewButton_2);
	}

}

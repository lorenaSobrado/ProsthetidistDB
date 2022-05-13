package SwingWindows;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PatientDisplay frame = new PatientDisplay(); 
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
	public PatientDisplay(JFrame appDisplay) {
		appDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appDisplay.setEnabled(true);
				PatientDisplay.this.setVisible(false);
			}
		});
		back.setBounds(339, 231, 89, 23);
		contentPane.add(back);
		
		JButton logIn = new JButton("Log in");
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame pLogInDisplay = new PLogInDisplay(PatientDisplay.this);
				pLogInDisplay.setVisible(true);
			}
		});
		logIn.setBounds(161, 97, 89, 23);
		contentPane.add(logIn);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame pRegisterDisplay = new PRegisterDisplay(PatientDisplay.this);
				pRegisterDisplay.setVisible(true);
			}
		});
		register.setBounds(161, 144, 89, 23);
		contentPane.add(register);
	}
}

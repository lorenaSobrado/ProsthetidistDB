package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class CompanyOptions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyOptions frame = new CompanyOptions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyOptions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("UPLOAD PROSTHETIC");
		btnNewButton.setBounds(149, 55, 178, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DELETE PROSTHETIC");
		btnNewButton_1.setBounds(149, 96, 178, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("OFFER DESIGN");
		btnNewButton_2.setBounds(149, 142, 178, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("OK");
		btnNewButton_3.setBounds(193, 237, 117, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("CANCEL");
		btnNewButton_4.setBounds(322, 237, 117, 29);
		contentPane.add(btnNewButton_4);
	}
}

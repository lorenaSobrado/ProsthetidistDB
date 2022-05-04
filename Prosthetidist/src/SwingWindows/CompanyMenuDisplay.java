package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyMenuDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyMenuDisplay frame = new CompanyMenuDisplay();
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
	public CompanyMenuDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("UPLOAD PROSTHETIC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame uploadProsthetic = new UploadProsthetic();
				uploadProsthetic.setVisible(true);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFrame uploadprost = new UploadProsthetic();
				uploadprost.setVisible(true);
			}
		});
		btnNewButton.setBounds(149, 55, 178, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DELETE PROSTHETIC");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame deleteProsthetic = new DeleteProsthetic();
				deleteProsthetic.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(149, 96, 178, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("OFFER DESIGN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame offerProsthetic = new OfferOProsthetic();
				offerProsthetic.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(149, 142, 178, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("LOG OUT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompanyMenuDisplay.this.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(322, 237, 117, 29);
		contentPane.add(btnNewButton_4);
	}
}

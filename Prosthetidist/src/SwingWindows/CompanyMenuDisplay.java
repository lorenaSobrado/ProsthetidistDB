package SwingWindows;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.jpa.JPAUserManager;
import prosthetidist.pojos.Company;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CompanyMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JDBCCompanyManager cm;
	private JDBCProstheticManager pm;

	public CompanyMenuDisplay(JFrame cLogInDisplay, Company company, JDBCManager manager, JPAUserManager um) {
		cLogInDisplay.setEnabled(false);
		cm = new JDBCCompanyManager(manager);
		pm = new JDBCProstheticManager(manager);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("UPLOAD PROSTHETIC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame uploadProsthetic = new UploadProsthetic(CompanyMenuDisplay.this, company, manager);
				uploadProsthetic.setVisible(true);
			}
		});
		btnNewButton.setBounds(125, 74, 178, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("DELETE PROSTHETIC");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm.listProstheticsOfCompany(company).isEmpty()) {
					JOptionPane.showMessageDialog(CompanyMenuDisplay.this, "No prosthetics to delete", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JFrame deleteProsthetic = new DeleteProsthetic(CompanyMenuDisplay.this, company, manager);
					deleteProsthetic.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(125, 114, 178, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("OFFER DESIGN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pm.getProstheticsWithoutCompanyId().isEmpty()) {
					JOptionPane.showMessageDialog(CompanyMenuDisplay.this, "No prosthetics to offer", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JFrame offerProsthetic = new OfferProsthetic(CompanyMenuDisplay.this, company, manager);
					offerProsthetic.setVisible(true);
				}
			}
		});
		btnNewButton_2.setBounds(125, 154, 178, 29);
		contentPane.add(btnNewButton_2);

		JButton logOut = new JButton("LOG OUT");
		Image logOutImg = new ImageIcon(this.getClass().getResource("/logOut.png")).getImage();
		logOut.setIcon(new ImageIcon(logOutImg));
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				um.disconnect();
				cLogInDisplay.setEnabled(true);
				CompanyMenuDisplay.this.setVisible(false);
			}
		});
		logOut.setBounds(322, 237, 117, 29);
		contentPane.add(logOut);

		JLabel user = new JLabel("");
		Image userImg = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		user.setIcon(new ImageIcon(userImg));
		user.setBounds(28, 23, 45, 39);
		contentPane.add(user);

		JLabel username = new JLabel(company.getName());
		username.setBounds(71, 35, 89, 14);
		contentPane.add(username);

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

package SwingWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class OfferProstheticByCompany extends JFrame {

	private JPanel contentPane;
	private JTextField price;
	private JTextField model;
	private JButton ok;
	private JButton back;
	private JDBCCompanyManager cm;
	private JSeparator separator;
	private JSeparator separator_1;

	public OfferProstheticByCompany(JFrame offerProsthetic, JFrame companyMenuDisplay, Company company, Prosthetic prosthetic,
			JDBCManager manager) {
		cm = new JDBCCompanyManager(manager);
		offerProsthetic.setEnabled(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 247, 247));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		price = new JTextField();
		price.setBounds(178, 102, 112, 20);
		price.setBackground(new Color(247, 247, 247));
		price.setBorder(null);
		contentPane.add(price);
		price.setColumns(10);

		model = new JTextField();
		model.setBounds(178, 160, 112, 20);
		model.setBackground(new Color(247, 247, 247));
		model.setBorder(null);
		contentPane.add(model);
		model.setColumns(10);

		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prosthetic.setPrice(Float.valueOf(price.getText()));
					prosthetic.setModel(model.getText());
					cm.offerDesign(prosthetic, company.getId());
					JOptionPane.showConfirmDialog(OfferProstheticByCompany.this, "Prosthetic has been made!", "Message",
							JOptionPane.PLAIN_MESSAGE);
					offerProsthetic.setEnabled(true);
					OfferProstheticByCompany.this.setVisible(false);
					offerProsthetic.setVisible(false);
					companyMenuDisplay.setEnabled(true);
					companyMenuDisplay.setVisible(true);
				} catch(NumberFormatException nfe) {
					JOptionPane.showConfirmDialog(OfferProstheticByCompany.this, "Invalid price", "Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ok.setBounds(357, 258, 89, 23);
		contentPane.add(ok);

		back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				offerProsthetic.setEnabled(true);
				OfferProstheticByCompany.this.setVisible(false);
			}
		});
		back.setBounds(10, 268, 32, 32);
		contentPane.add(back);

		JLabel lblNewLabel = new JLabel("Price");
		lblNewLabel.setBounds(82, 105, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(82, 163, 46, 14);
		contentPane.add(lblModel);
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(178, 122, 112, 5);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(178, 180, 112, 5);
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

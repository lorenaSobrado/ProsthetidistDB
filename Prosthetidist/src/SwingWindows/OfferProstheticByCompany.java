package SwingWindows;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class OfferProstheticByCompany extends JFrame {

	private JPanel contentPane;
	private JTextField price;
	private JTextField model;
	private JButton ok;
	private JButton back;
	private JDBCCompanyManager cm;

	public OfferProstheticByCompany(JFrame offerProsthetic, Company company, Prosthetic prosthetic, JDBCManager manager) {
		cm = new JDBCCompanyManager(manager);
		offerProsthetic.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		price = new JTextField();
		price.setBounds(146, 67, 112, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		model = new JTextField();
		model.setBounds(146, 133, 112, 20);
		contentPane.add(model);
		model.setColumns(10);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prosthetic.setPrice(Float.valueOf(price.getText()));
				prosthetic.setModel(model.getText());
				cm.offerDesign(prosthetic);
				offerProsthetic.setEnabled(true);
				OfferProstheticByCompany.this.setVisible(false);
			}
		});
		ok.setBounds(254, 206, 89, 23);
		contentPane.add(ok);
		
		back = new JButton("BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				offerProsthetic.setEnabled(true);
				OfferProstheticByCompany.this.setVisible(false);
			}
		});
		back.setBounds(69, 206, 89, 23);
		contentPane.add(back);
		
		JLabel lblNewLabel = new JLabel("price");
		lblNewLabel.setBounds(45, 70, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblModel = new JLabel("model");
		lblModel.setBounds(45, 136, 46, 14);
		contentPane.add(lblModel);
	}
}

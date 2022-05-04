package SwingWindows;

import java.awt.BorderLayout;
import prosthetidist.jdbc.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.pojos.Measurements;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UploadProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */

	private JDBCCompanyManager cm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadProsthetic frame = new UploadProsthetic();
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
	public UploadProsthetic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Enter the following characteristics:");
		lblNewLabel.setBounds(58, 16, 328, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(20, 59, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Functionalities");
		lblNewLabel_2.setBounds(20, 87, 96, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setBounds(20, 115, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Model");
		lblNewLabel_4.setBounds(20, 143, 61, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Materials");
		lblNewLabel_5.setBounds(20, 171, 61, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Measurements");
		lblNewLabel_6.setBounds(20, 199, 96, 16);
		contentPane.add(lblNewLabel_6);

		textField = new JTextField();
		textField.setBounds(160, 54, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(160, 82, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(160, 110, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(160, 138, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Plastic");
		chckbxNewCheckBox.setBounds(104, 167, 113, 23);
		contentPane.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Aluminium");
		chckbxNewCheckBox_2.setBounds(333, 168, 99, 23);
		contentPane.add(chckbxNewCheckBox_2);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Carbon Fiber");
		chckbxNewCheckBox_1.setBounds(224, 168, 107, 23);
		contentPane.add(chckbxNewCheckBox_1);

		textField_4 = new JTextField();
		textField_4.setBounds(136, 197, 96, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(290, 197, 96, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(455, 197, 96, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prosthetic p = new Prosthetic();
				Measurements m = new Measurements();
				p.setPrice(Float.parseFloat(textField.getText()));
				p.setFunctionalities(textField_1.getText());
				p.setType(textField_2.getText());
				p.setModel(textField_3.getText());
				p.setMaterials(null);
				m.setLength(Float.parseFloat(textField_4.getText()));
				m.setWidth(Float.parseFloat(textField_5.getText()));
				m.setWeight(Float.parseFloat(textField_6.getText()));
				p.setMeasurements(m);

				cm.uploadProsthetics(p);
				// TODO SABER COMPANY ID

				UploadProsthetic.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(473, 313, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UploadProsthetic.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(343, 313, 89, 23);
		contentPane.add(btnNewButton_1);

	}
}

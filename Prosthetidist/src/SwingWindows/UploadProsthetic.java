package SwingWindows;

import java.awt.BorderLayout;
import prosthetidist.jdbc.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UploadProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JComboBox typeOptions;

	/**
	 * Launch the application.
	 */

	private JDBCProstheticManager pm;
	private JDBCMaterialManager matm;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UploadProsthetic frame = new UploadProsthetic();
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
	public UploadProsthetic(Company company, JDBCManager manager) {
		pm = new JDBCProstheticManager(manager);
		matm = new JDBCMaterialManager(manager);
		
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

		textField_3 = new JTextField();
		textField_3.setBounds(160, 138, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JCheckBox plastic = new JCheckBox("Plastic");
		plastic.setBounds(104, 167, 113, 23);
		contentPane.add(plastic);

		JCheckBox aluminium = new JCheckBox("Aluminium");
		aluminium.setBounds(333, 168, 99, 23);
		contentPane.add(aluminium);

		JCheckBox carbonFiber = new JCheckBox("Carbon Fiber");
		carbonFiber.setBounds(224, 168, 107, 23);
		contentPane.add(carbonFiber);

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
				
				Float price = Float.parseFloat(textField.getText());
				String functionalities= textField_1.getText();
				String model =textField_3.getText();
				String type = typeOptions.getSelectedItem().toString();
				p.setMaterials(null);
				m.setLengthiness(Float.parseFloat(textField_4.getText()));
				m.setWidth(Float.parseFloat(textField_5.getText()));
				m.setWeight(Float.parseFloat(textField_6.getText()));
				p.setMeasurements(m);
				pm.uploadProsthetics(company, p);
				
				if(plastic.isSelected()) {
					Material material = matm.getMaterialByName("Plastic");
					matm.uploadMaterialsOfProsthetic(material, p);
				}
				if(carbonFiber.isSelected()) {
					Material material = matm.getMaterialByName("Carbon Fiber");
					matm.uploadMaterialsOfProsthetic(material, p);
				}
				if(aluminium.isSelected()) {
					Material material = matm.getMaterialByName("Aluminium");
					matm.uploadMaterialsOfProsthetic(material, p);
				}
			

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
		
		JLabel lblNewLabel_7 = new JLabel("Length");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(136, 235, 96, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Width");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setBounds(290, 235, 96, 14);
		contentPane.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Weight");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setBounds(455, 235, 96, 14);
		contentPane.add(lblNewLabel_7_2);
		
		typeOptions = new JComboBox();
		typeOptions.setModel(new DefaultComboBoxModel(new String[] {"Right arm", "Right hand", "Right leg", "Right foot", "Left arm", "Left hand", "Left leg", "Left foot"}));
		typeOptions.setBounds(160, 112, 130, 22);
		contentPane.add(typeOptions);

	}
}

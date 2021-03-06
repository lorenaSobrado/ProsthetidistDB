package SwingWindows;

import prosthetidist.jdbc.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JSeparator;

public class UploadProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField lengthTxt;
	private JTextField widthTxt;
	private JTextField weightTxt;
	private JComboBox<String> typeOptions;
	private JCheckBox plastic;
	private JCheckBox carbonFiber;
	private JCheckBox aluminium;

	private JDBCProstheticManager pm;
	private JDBCMaterialManager matm;
	private JDBCMeasurementManager mm;

	public UploadProsthetic(JFrame companyMenuDisplay, Company company, JDBCManager manager) {
		pm = new JDBCProstheticManager(manager);
		matm = new JDBCMaterialManager(manager);
		mm = new JDBCMeasurementManager(manager);
		companyMenuDisplay.setEnabled(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(247, 247, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(20, 36, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Functionalities");
		lblNewLabel_2.setBounds(20, 73, 96, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setBounds(20, 108, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Model");
		lblNewLabel_4.setBounds(20, 143, 61, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Materials");
		lblNewLabel_5.setBounds(20, 171, 61, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Measurements\r\n");
		lblNewLabel_6.setBounds(20, 220, 96, 16);
		contentPane.add(lblNewLabel_6);

		textField = new JTextField();
		textField.setBounds(160, 31, 130, 26);
		textField.setBackground(new Color(247, 247, 247));
		textField.setBorder(null);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(160, 68, 130, 26);
		textField_1.setBackground(new Color(247, 247, 247));
		textField_1.setBorder(null);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(160, 138, 130, 26);
		textField_3.setBackground(new Color(247, 247, 247));
		textField_3.setBorder(null);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		plastic = new JCheckBox("Plastic");
		plastic.setBounds(126, 180, 96, 23);
		plastic.setBackground(new Color(247, 247, 247));
		contentPane.add(plastic);

		aluminium = new JCheckBox("Aluminium");
		aluminium.setBounds(364, 180, 99, 23);
		aluminium.setBackground(new Color(247, 247, 247));
		contentPane.add(aluminium);

		carbonFiber = new JCheckBox("Carbon Fiber");
		carbonFiber.setBounds(237, 180, 96, 23);
		carbonFiber.setBackground(new Color(247, 247, 247));
		contentPane.add(carbonFiber);

		lengthTxt = new JTextField();
		lengthTxt.setBounds(136, 218, 96, 20);
		lengthTxt.setBackground(new Color(247, 247, 247));
		lengthTxt.setBorder(null);
		contentPane.add(lengthTxt);
		lengthTxt.setColumns(10);

		widthTxt = new JTextField();
		widthTxt.setBounds(290, 218, 96, 20);
		widthTxt.setBackground(new Color(247, 247, 247));
		widthTxt.setBorder(null);
		contentPane.add(widthTxt);
		widthTxt.setColumns(10);

		weightTxt = new JTextField();
		weightTxt.setBounds(448, 218, 96, 20);
		weightTxt.setBackground(new Color(247, 247, 247));
		weightTxt.setBorder(null);
		contentPane.add(weightTxt);
		weightTxt.setColumns(10);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(company.getEmail() == "trial@gmail.com") {
					JOptionPane.showMessageDialog(UploadProsthetic.this, "Can't upload prosthetics to the data base", "Message",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Measurement m = new Measurement();
					Prosthetic p = new Prosthetic();
					if(plastic.isSelected() || carbonFiber.isSelected() || aluminium.isSelected()) {
						try {
							p.setCompany(company);
							p.setPrice(Float.parseFloat(textField.getText()));
							p.setFunctionalities(textField_1.getText());
							p.setModel(textField_3.getText());
							p.setType(typeOptions.getSelectedItem().toString());
							Float length = Float.parseFloat(lengthTxt.getText());
							Float width = Float.parseFloat(widthTxt.getText());
							Float weight = Float.parseFloat(weightTxt.getText());
							m.setLengthiness(length);
							m.setWidth(width);
							m.setWeight(weight);
							if (mm.getMeasurement(length, width, weight) == null) {
								mm.addMeasurement(m);
							}
							m = mm.getMeasurement(length, width, weight);
							p.setMeasurement(m);
							Integer prosCode = pm.getProstheticCode(p);
							if (prosCode == null) {
								pm.uploadProsthetic(p);
								prosCode = pm.getLastProstheticCode();
								if (plastic.isSelected()) {
									Material plastic = matm.getMaterialByName("Plastic");
									matm.uploadMaterialOfProsthetic(plastic, prosCode);
								}
								if (carbonFiber.isSelected()) {
									Material carbonFiber = matm.getMaterialByName("Carbon Fiber");
									matm.uploadMaterialOfProsthetic(carbonFiber, prosCode);
								}
								if (aluminium.isSelected()) {
									Material aluminium = matm.getMaterialByName("Aluminium");
									matm.uploadMaterialOfProsthetic(aluminium, prosCode);
								}
								JOptionPane.showMessageDialog(UploadProsthetic.this, "New prosthetic added", "Message",
										JOptionPane.PLAIN_MESSAGE);
								companyMenuDisplay.setEnabled(true);
								UploadProsthetic.this.setVisible(false);
							} else {
								if(checkMaterials(prosCode)) {
									pm.uploadProsthetic(p);
									prosCode = pm.getLastProstheticCode();
									if (plastic.isSelected()) {
										Material plastic = matm.getMaterialByName("Plastic");
										matm.uploadMaterialOfProsthetic(plastic, prosCode);
									}
									if (carbonFiber.isSelected()) {
										Material carbonFiber = matm.getMaterialByName("Carbon Fiber");
										matm.uploadMaterialOfProsthetic(carbonFiber, prosCode);
									}
									if (aluminium.isSelected()) {
										Material aluminium = matm.getMaterialByName("Aluminium");
										matm.uploadMaterialOfProsthetic(aluminium, prosCode);
									}
									JOptionPane.showMessageDialog(UploadProsthetic.this, "New prosthetic added", "Message",
											JOptionPane.PLAIN_MESSAGE);
									companyMenuDisplay.setEnabled(true);
									UploadProsthetic.this.setVisible(false);
								} else JOptionPane.showMessageDialog(UploadProsthetic.this, "This prosthetic already exists", "Message",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch(NumberFormatException nfe) {
							JOptionPane.showMessageDialog(UploadProsthetic.this, "Invalid price or measurement", "Message",
									JOptionPane.ERROR_MESSAGE);
						}
					} else JOptionPane.showMessageDialog(UploadProsthetic.this, "Select a material", "Message",
							JOptionPane.WARNING_MESSAGE);
				}
					
			}
		});
		btnNewButton.setBounds(455, 309, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_7 = new JLabel("Length");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(136, 249, 96, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("Width");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setBounds(290, 249, 96, 14);
		contentPane.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_2 = new JLabel("Weight");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setBounds(448, 249, 96, 14);
		contentPane.add(lblNewLabel_7_2);

		typeOptions = new JComboBox<String>();
		typeOptions.setModel(new DefaultComboBoxModel<String>(new String[] { "Right arm", "Right hand", "Right leg",
				"Right foot", "Left arm", "Left hand", "Left leg", "Left foot" }));
		typeOptions.setBounds(160, 105, 130, 22);
		contentPane.add(typeOptions);

		JButton back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyMenuDisplay.setEnabled(true);
				UploadProsthetic.this.setVisible(false);
			}
		});
		back.setForeground(SystemColor.menu);
		back.setBounds(10, 300, 32, 32);
		contentPane.add(back);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(160, 57, 130, 5);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(160, 94, 130, 5);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(160, 164, 130, 5);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(136, 238, 96, 5);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBounds(290, 238, 96, 5);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBounds(448, 238, 96, 5);
		contentPane.add(separator_5);
		
		JLabel lblNewLabel = new JLabel("(cm, kg)");
		lblNewLabel.setBounds(35, 235, 46, 14);
		contentPane.add(lblNewLabel);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if(company.getEmail() == "trial@gmail.com") {
					JDBCCompanyManager cm = new JDBCCompanyManager(manager);
					cm.deleteCompany(company);
				}
				manager.disconnect();
			}
		};
		this.addWindowListener(exitListener);

	}

	private boolean checkMaterials(Integer prosCode) {
		ArrayList<Material> materials = matm.getMaterialsFromProstheticCode(prosCode);

		if ((plastic.isSelected() && carbonFiber.isSelected() && aluminium.isSelected()) && materials.size() != 3) {
			return true;
		}
		if (materials.size() == 2) {
			if ((plastic.isSelected() && carbonFiber.isSelected())
					&& !materials.get(0).getName().equalsIgnoreCase("plastic")
					&& !materials.get(1).getName().equalsIgnoreCase("plastic")) {
				return true;
			}
			if ((plastic.isSelected() && aluminium.isSelected())
					&& !materials.get(0).getName().equalsIgnoreCase("plastic")
					&& !materials.get(1).getName().equalsIgnoreCase("plastic")) {
				return true;
			}
			if ((aluminium.isSelected() && carbonFiber.isSelected())
					&& !materials.get(0).getName().equalsIgnoreCase("aluminium")
					&& !materials.get(1).getName().equalsIgnoreCase("aluminium")) {
				return true;
			}
		}
		if (materials.size() == 1) {
			if (plastic.isSelected() && !materials.get(0).getName().equalsIgnoreCase("plastic")) {
				return true;
			}
			if (aluminium.isSelected() && !materials.get(0).getName().equalsIgnoreCase("aluminium")) {
				return true;
			}
			if (carbonFiber.isSelected() && !materials.get(0).getName().equalsIgnoreCase("carbon fiber")) {
				return true;
			}
		}
		return false;
	}
}

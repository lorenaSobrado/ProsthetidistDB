package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCMaterialManager;
import prosthetidist.jdbc.JDBCPatientManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class DesignProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField functionalities;
	
	private JDBCMaterialManager matm;
	private JDBCPatientManager patm;

	public DesignProsthetic(JFrame patientMenuDisplay, JDBCManager manager) {
		patientMenuDisplay.setEnabled(false);
		matm = new JDBCMaterialManager(manager);
		patm = new JDBCPatientManager(manager);
		patm = new JDBCPatientManager(manager);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(10, 41, 45, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Measurements");
		lblNewLabel_1.setBounds(10, 80, 76, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Functionalities");
		lblNewLabel_2.setBounds(10, 117, 96, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Materials");
		lblNewLabel_3.setBounds(10, 158, 45, 13);
		contentPane.add(lblNewLabel_3);

		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"Right arm", "Right hand", "Right leg", "Right foot", "Left arm", "Left hand", "Left leg", "Left foot"}));
		type.setBounds(128, 37, 96, 21);
		contentPane.add(type);

		textField = new JTextField();
		textField.setBounds(128, 77, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(234, 77, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(353, 77, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		functionalities = new JTextField();
		functionalities.setBounds(128, 114, 96, 19);
		contentPane.add(functionalities);
		functionalities.setColumns(10);

		JCheckBox plastic = new JCheckBox("Plastic");
		plastic.setBounds(131, 154, 93, 21);
		contentPane.add(plastic);

		JCheckBox carbonFiber = new JCheckBox("Carbon Fiber");
		carbonFiber.setBounds(237, 154, 93, 21);
		contentPane.add(carbonFiber);

		JCheckBox aluminium = new JCheckBox("Aluminum");
		aluminium.setBounds(356, 154, 93, 21);
		contentPane.add(aluminium);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				Prosthetic p = new Prosthetic();
				Measurement m = new Measurement();
				Float length = Float.parseFloat(textField.getText());
				Float width = Float.parseFloat(textField_1.getText());
				Float weight = Float.parseFloat(textField_2.getText());
				m.setLengthiness(length);
				m.setWidth(width);
				m.setWeight(weight);
				p.setMeasurements(m);
				p.setType(type.getSelectedItem().toString());
				p.setFunctionalities(functionalities.getText());
				patm.designProsthetic(functionalities.getText(), type.getSelectedItem().toString(), m);
				ArrayList<Material> materials = new ArrayList<Material>();
				if(plastic.isSelected()) {
					Material plastic = matm.getMaterialByName("Plastic");
					materials.add(plastic);
					matm.uploadMaterialsOfProsthetic(plastic, p);
				}
				if(carbonFiber.isSelected()) {
					Material carbonFiber = matm.getMaterialByName("Carbon Fiber");
					materials.add(carbonFiber);
					matm.uploadMaterialsOfProsthetic(carbonFiber, p);
				}
				if(aluminium.isSelected()) {
					Material aluminium = matm.getMaterialByName("Aluminium");
					materials.add(aluminium);
					matm.uploadMaterialsOfProsthetic(aluminium, p);
				}
				JOptionPane.showMessageDialog(DesignProsthetic.this, "Your design has been sent to the companies !", "Message", 
						JOptionPane.INFORMATION_MESSAGE);
				DesignProsthetic.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(306, 275, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				DesignProsthetic.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(417, 275, 85, 21);
		contentPane.add(btnNewButton_1);

	}
}

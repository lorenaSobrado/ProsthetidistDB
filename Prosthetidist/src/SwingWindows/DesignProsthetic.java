package SwingWindows;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCMaterialManager;
import prosthetidist.jdbc.JDBCMeasurementManager;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class DesignProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField lengthTxt;
	private JTextField widthTxt;
	private JTextField weightTxt;
	private JTextField functionalities;
	private JComboBox<String> type;

	private JDBCMaterialManager matm;
	private JDBCPatientManager patm;
	private JDBCMeasurementManager mm;
	private JDBCProstheticManager pm;
	//private DecimalFormat formats = new DecimalFormat ("#.00");
	
	public DesignProsthetic(JFrame patientMenuDisplay, JDBCManager manager) {
		patientMenuDisplay.setEnabled(false);
		matm = new JDBCMaterialManager(manager);
		patm = new JDBCPatientManager(manager);
		mm = new JDBCMeasurementManager(manager);
		pm = new JDBCProstheticManager(manager);
		
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

		type = new JComboBox<String>();
		type.setModel(new DefaultComboBoxModel<String>(new String[] { "Right arm", "Right hand", "Right leg", "Right foot",
				"Left arm", "Left hand", "Left leg", "Left foot" }));
		type.setBounds(128, 37, 96, 21);
		contentPane.add(type);

		lengthTxt = new JTextField();
		lengthTxt.setBounds(128, 77, 96, 19);
		contentPane.add(lengthTxt);
		lengthTxt.setColumns(10);

		widthTxt = new JTextField();
		widthTxt.setBounds(234, 77, 96, 19);
		contentPane.add(widthTxt);
		widthTxt.setColumns(10);

		weightTxt = new JTextField();
		weightTxt.setBounds(353, 77, 96, 19);
		contentPane.add(weightTxt);
		weightTxt.setColumns(10);

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

		JButton btnNewButton = new JButton("DESIGN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Measurement m = new Measurement();
				Float length = Float.parseFloat(lengthTxt.getText());
				Float width = Float.parseFloat(widthTxt.getText());
				Float weight = Float.parseFloat(weightTxt.getText());
				m.setLengthiness(length);
				m.setWidth(width);
				m.setWeight(weight);
				if(mm.getMeasurement(length, width, weight) == null) {
					mm.addMeasurement(m);
				}
				m = mm.getMeasurement(length, width, weight);
				patm.designProsthetic(functionalities.getText(), type.getSelectedItem().toString(), m); //Doesn't matter if anyone else design exactly the same bc the company could offer the 2 (each for each patient)
				Integer prosCode = patm.getDesignCode(functionalities.getText(), type.getSelectedItem().toString(), m);
				Prosthetic p = pm.getProstheticByCode(prosCode);
				ArrayList<Material> materials = new ArrayList<Material>();
				if (plastic.isSelected()) {
					Material plastic = matm.getMaterialByName("Plastic");
					materials.add(plastic);
					matm.uploadMaterialOfProsthetic(plastic, p);
				}
				if (carbonFiber.isSelected()) {
					Material carbonFiber = matm.getMaterialByName("Carbon Fiber");
					materials.add(carbonFiber);
					matm.uploadMaterialOfProsthetic(carbonFiber, p);
				}
				if (aluminium.isSelected()) {
					Material aluminium = matm.getMaterialByName("Aluminium");
					materials.add(aluminium);
					matm.uploadMaterialOfProsthetic(aluminium, p);
				}
				JOptionPane.showMessageDialog(DesignProsthetic.this, "Your design has been sent to the companies !",
						"Message", JOptionPane.INFORMATION_MESSAGE);
				patientMenuDisplay.setEnabled(true);
				DesignProsthetic.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(393, 272, 85, 21);
		contentPane.add(btnNewButton);

		JButton back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				DesignProsthetic.this.setVisible(false);
			}
		});
		back.setBounds(10, 261, 32, 32);
		contentPane.add(back);

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

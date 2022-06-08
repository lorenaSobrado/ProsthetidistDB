package SwingWindows;

import java.awt.Color;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

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
		setBounds(450, 150, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 247, 247));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(10, 41, 45, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Measurements ");
		lblNewLabel_1.setBounds(10, 80, 96, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Functionalities");
		lblNewLabel_2.setBounds(10, 140, 96, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Materials");
		lblNewLabel_3.setBounds(10, 184, 76, 13);
		contentPane.add(lblNewLabel_3);

		type = new JComboBox<String>();
		type.setModel(new DefaultComboBoxModel<String>(new String[] { "Right arm", "Right hand", "Right leg", "Right foot",
				"Left arm", "Left hand", "Left leg", "Left foot" }));
		type.setBounds(128, 37, 96, 21);
		contentPane.add(type);

		lengthTxt = new JTextField();
		lengthTxt.setBounds(128, 76, 96, 19);
		lengthTxt.setBackground(new Color(247, 247, 247));
		lengthTxt.setBorder(null);
		contentPane.add(lengthTxt);
		lengthTxt.setColumns(10);

		widthTxt = new JTextField();
		widthTxt.setBounds(236, 76, 96, 19);
		widthTxt.setBackground(new Color(247, 247, 247));
		widthTxt.setBorder(null);
		contentPane.add(widthTxt);
		widthTxt.setColumns(10);

		weightTxt = new JTextField();
		weightTxt.setBounds(350, 76, 96, 19);
		weightTxt.setBackground(new Color(247, 247, 247));
		weightTxt.setBorder(null);
		contentPane.add(weightTxt);
		weightTxt.setColumns(10);

		functionalities = new JTextField();
		functionalities.setBounds(128, 135, 128, 19);
		functionalities.setBackground(new Color(247, 247, 247));
		functionalities.setBorder(null);
		contentPane.add(functionalities);
		functionalities.setColumns(10);

		JCheckBox plastic = new JCheckBox("Plastic");
		plastic.setBounds(128, 180, 93, 21);
		plastic.setBackground(new Color(247, 247, 247));
		contentPane.add(plastic);

		JCheckBox carbonFiber = new JCheckBox("Carbon Fiber");
		carbonFiber.setBounds(237, 180, 93, 21);
		carbonFiber.setBackground(new Color(247, 247, 247));
		contentPane.add(carbonFiber);

		JCheckBox aluminium = new JCheckBox("Aluminum");
		aluminium.setBounds(353, 180, 93, 21);
		aluminium.setBackground(new Color(247, 247, 247));
		contentPane.add(aluminium);

		JButton btnNewButton = new JButton("DESIGN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Measurement m = new Measurement();
				
				if(plastic.isSelected() || carbonFiber.isSelected() || aluminium.isSelected()) {
					try {
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
						ArrayList<Material> materials = new ArrayList<Material>();
						if (plastic.isSelected()) {
							Material plastic = matm.getMaterialByName("Plastic");
							materials.add(plastic);
							matm.uploadMaterialOfProsthetic(plastic, prosCode);
						}
						if (carbonFiber.isSelected()) {
							Material carbonFiber = matm.getMaterialByName("Carbon Fiber");
							materials.add(carbonFiber);
							matm.uploadMaterialOfProsthetic(carbonFiber, prosCode);
						}
						if (aluminium.isSelected()) {
							Material aluminium = matm.getMaterialByName("Aluminium");
							materials.add(aluminium);
							matm.uploadMaterialOfProsthetic(aluminium, prosCode);
						}
						JOptionPane.showMessageDialog(DesignProsthetic.this, "Your design has been sent to the companies !",
								"Message", JOptionPane.INFORMATION_MESSAGE);
						patientMenuDisplay.setEnabled(true);
						DesignProsthetic.this.setVisible(false);
					} catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(DesignProsthetic.this, "Invalid measurements",
								"Message", JOptionPane.ERROR_MESSAGE);
					}
				} else JOptionPane.showMessageDialog(DesignProsthetic.this, "Select a material", "Message",
						JOptionPane.WARNING_MESSAGE);	
			}
		});
		btnNewButton.setBounds(382, 272, 96, 21);
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
		
		JLabel lblNewLabel_4 = new JLabel("Length");
		lblNewLabel_4.setBounds(151, 100, 57, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Width");
		lblNewLabel_4_1.setBounds(263, 100, 57, 14);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Weight");
		lblNewLabel_4_2.setBounds(373, 100, 57, 14);
		contentPane.add(lblNewLabel_4_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(128, 95, 96, 5);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(236, 95, 96, 5);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(350, 95, 96, 5);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(128, 154, 128, 5);
		contentPane.add(separator_3);
		
		JLabel lblNewLabel_5 = new JLabel("(cm, kg)");
		lblNewLabel_5.setBounds(25, 95, 46, 14);
		contentPane.add(lblNewLabel_5);

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

package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DesignProsthetic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DesignProsthetic frame = new DesignProsthetic();
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
	public DesignProsthetic(JFrame patientMenuDisplay) {
		patientMenuDisplay.setEnabled(false);
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

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(99, 37, 29, 21);
		contentPane.add(comboBox);

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

		textField_3 = new JTextField();
		textField_3.setBounds(128, 114, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JCheckBox chckbxPlastic = new JCheckBox("Plastic");
		chckbxPlastic.setBounds(102, 154, 93, 21);
		contentPane.add(chckbxPlastic);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Carbon Fiber");
		chckbxNewCheckBox_1.setBounds(205, 154, 93, 21);
		contentPane.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Aluminum");
		chckbxNewCheckBox_2.setBounds(311, 154, 93, 21);
		contentPane.add(chckbxNewCheckBox_2);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				Float length = Float.parseFloat(textField.getText());
				Float width = Float.parseFloat(textField_1.getText());
				Float weight = Float.parseFloat(textField_2.getText());
				String functionalities = textField_3.getText();
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
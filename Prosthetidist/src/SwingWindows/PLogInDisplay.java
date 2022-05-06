package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PLogInDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PLogInDisplay frame = new PLogInDisplay();
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
	public PLogInDisplay(JFrame patientDisplay) {
		patientDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setBounds(146, 63, 164, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(146, 100, 164, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame patientMenuDisplay = new PatientMenuDisplay(PLogInDisplay.this);
				patientMenuDisplay.setVisible(true);
			}
		});
		btnNewButton.setBounds(221, 230, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientDisplay.setEnabled(true);
				PLogInDisplay.this.setVisible(false);;
			}
		});
		btnNewButton_1.setBounds(320, 231, 85, 21);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("EMAIL :");
		lblNewLabel.setBounds(28, 67, 65, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PASSWORD :");
		lblNewLabel_1.setBounds(28, 104, 79, 13);
		contentPane.add(lblNewLabel_1);
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

package SwingWindows;

import java.awt.BorderLayout;


import prosthetidist.pojos.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PRegisterDisplay extends JFrame {

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRegisterDisplay frame = new PRegisterDisplay();
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
	public PRegisterDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 50, 61, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 80, 61, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(10, 104, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 139, 45, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Medical Condition");
		lblNewLabel_5.setBounds(10, 169, 85, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setBounds(10, 192, 61, 27);
		contentPane.add(lblNewLabel_6);

		textField_1 = new JTextField(); //PQ TE HACE DE UNA EL 1 Y NO EL QUE ES SIN NUMERO??
		textField_1.setBounds(121, 46, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(121, 76, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(121, 106, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(121, 136, 96, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(121, 167, 96, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(121, 195, 96, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseReleased(MouseEvent e) {
				if (textField_1.getText().length() > 0 && textField_2.getText().length() > 0
						&& textField_3.getText().length() > 0 && textField_4.getText().length() > 0
						&& textField_5.getText().length() > 0 && textField_6.getText().length() > 0) {
					boolean Validar = true; //para que haces esto?
					JFrame patientMenuDisp = new PatientMenuDisplay();
					patientMenuDisp.setVisible(Validar);
					Patient patient = new Patient();
					patient.setName(textField_1.getText());
					patient.setEmail(textField_2.getText());
					patient.setPhone(Integer.parseInt(textField_3.getText()));
					patient.setAddress(textField_4.getText());
					patient.setNotes(textField_5.getText());
					patient.setDob(textField_6.getText()); //debe ser un date 

				} else {
					validarDatos();
				}

				// patient.setDob(new
				// SimpleDateFormat("dd-MM-yyyy").parse(textField_6.getText()));
				// patient.setDob(date.parse(textField_6.getText()));
				/*
				 * LocalDate: patient.setDob(LocalDate.parse("yyyy-MM-dd")); EN Date:
				 * SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy"); Date date =
				 * f.parse(textField_6.getText()); patient.setDob(date);
				 */
			}
		});
		btnNewButton.setBounds(225, 232, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PRegisterDisplay.this.setVisible(false);
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		btnNewButton_1.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_1);
	}

	public void validarDatos() {
		JOptionPane.showMessageDialog(this, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

}

package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDisplay frame = new OrderDisplay();
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
	public OrderDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(419, 469, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				OrderDisplay.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(295, 469, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Your Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblNewLabel.setBounds(10, 10, 494, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Products");
		lblNewLabel_1.setBounds(29, 88, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JList list = new JList();
		list.setBounds(164, 87, 1, 1);
		contentPane.add(list);
		
		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(29, 168, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Model");
		lblNewLabel_3.setBounds(29, 200, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Company");
		lblNewLabel_4.setBounds(29, 234, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Functionalities");
		lblNewLabel_5.setBounds(29, 272, 73, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Price");
		lblNewLabel_6.setBounds(29, 307, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Delivery");
		lblNewLabel_7.setBounds(29, 340, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(107, 304, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(107, 269, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(107, 231, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(107, 197, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(107, 165, 96, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(107, 336, 29, 21);
		contentPane.add(comboBox);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(107, 450, 96, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Total Price");
		lblNewLabel_8.setBounds(29, 453, 73, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Id");
		lblNewLabel_9.setBounds(280, 168, 45, 13);
		contentPane.add(lblNewLabel_9);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(386, 165, 96, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(386, 197, 96, 19);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Name");
		lblNewLabel_10.setBounds(280, 200, 45, 13);
		contentPane.add(lblNewLabel_10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(386, 231, 96, 19);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Phone");
		lblNewLabel_11.setBounds(280, 234, 45, 13);
		contentPane.add(lblNewLabel_11);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(386, 269, 96, 19);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Adress");
		lblNewLabel_12.setBounds(280, 272, 45, 13);
		contentPane.add(lblNewLabel_12);
	}
}

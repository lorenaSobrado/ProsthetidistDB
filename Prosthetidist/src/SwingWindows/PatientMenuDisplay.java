package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JTable patTable;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PatientMenuDisplay frame = new PatientMenuDisplay();
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
	public PatientMenuDisplay(JFrame pLogInDisplay/*, Patient patient*/) {
		pLogInDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pLogInDisplay.setEnabled(true);
				PatientMenuDisplay.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(27, 288, 89, 23);
		contentPane.add(btnNewButton);
		
		patTable = new JTable();
		patTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Code", "Company", "Type", "Model", "Functionality", "Measurments", "Materials", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		patTable.setBounds(10, 88, 416, 141);
		contentPane.add(patTable);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(58, 26, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setToolTipText("User\r\n");
		lblNewLabel.setBounds(27, 29, 45, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Design My Own");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame designProsthetic = new DesignProsthetic(PatientMenuDisplay.this);
				designProsthetic.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(253, 25, 119, 23);
		contentPane.add(btnNewButton_1);
		
		/*JButton btnNewButton_2 = new JButton("ADD TO CART");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame orderFrame= new OrderDisplay();
				orderFrame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(452, 289, 113, 21);
		contentPane.add(btnNewButton_2);*/
		
		JLabel lblNewLabel_1 = new JLabel("Code");
		lblNewLabel_1.setBounds(10, 65, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Company");
		lblNewLabel_2.setBounds(65, 65, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setBounds(126, 66, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Model");
		lblNewLabel_4.setBounds(175, 66, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Function");
		lblNewLabel_5.setBounds(226, 65, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Size");
		lblNewLabel_6.setBounds(281, 66, 28, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Materials");
		lblNewLabel_7.setBounds(326, 66, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Price");
		lblNewLabel_8.setBounds(381, 65, 45, 13);
		contentPane.add(lblNewLabel_8);

		JButton btnNewButton_3 = new JButton("Filters");
		btnNewButton_3.setBounds(382, 24, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("CART");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cartDisplay = new CartDisplay(PatientMenuDisplay.this);
				cartDisplay.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(500, 11, 70, 37);
		contentPane.add(btnNewButton_4);
	}
}

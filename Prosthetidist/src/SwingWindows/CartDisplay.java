package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CartDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartDisplay frame = new CartDisplay();
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
	public CartDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOUR SELECTION :");
		lblNewLabel.setBounds(24, 11, 113, 14);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
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
		table.setBounds(22, 45, 362, 64);
		contentPane.add(table);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("STANDARD");
		rdbtnNewRadioButton.setBounds(41, 177, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("PREMIUM");
		rdbtnNewRadioButton_1.setBounds(195, 177, 111, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("CHOOSE DELIVERY :");
		lblNewLabel_1.setBounds(24, 156, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CREDIT CARD NUMBER :");
		lblNewLabel_2.setBounds(24, 224, 128, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(173, 221, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("BUY");
		btnNewButton.setBounds(377, 282, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setBounds(24, 282, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

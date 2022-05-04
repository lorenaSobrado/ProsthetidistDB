package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class OfferOProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfferOProsthetic frame = new OfferOProsthetic();
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
	public OfferOProsthetic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Prosthetic to design: ");
		lblNewLabel.setBounds(55, 11, 218, 14);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, Boolean.FALSE, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Functionalities", "Type", "Lenght", "Width", "Weight", "Plastic", "Aluminium", "Carbon Fiber"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, Float.class, Float.class, Boolean.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(91, 98, 246, 85);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("DESIGN");
		btnNewButton.setBounds(327, 231, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setBounds(217, 231, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

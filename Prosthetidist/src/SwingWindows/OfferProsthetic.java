package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.eclipse.persistence.internal.oxm.schema.model.List;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JButton;
import javax.swing.JTextField;

public class OfferProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JDBCCompanyManager cm;
	private JTextField textField;
	private JTextField textField_1;

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OfferProsthetic frame = new OfferProsthetic();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public OfferProsthetic(Company company) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Prosthetic to design: ");
		lblNewLabel.setBounds(55, 11, 218, 14);
		contentPane.add(lblNewLabel);
		
		//cm.listProstheticsWithoutCompanyID();
		
		table = new JTable();
		table.setModel(new DefaultTableModel( //en ves de default pasamos directamente el cm.
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
		table.setBounds(85, 48, 246, 85);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("DESIGN");
		btnNewButton.setBounds(327, 231, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setBounds(217, 231, 89, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(55, 173, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(166, 173, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}

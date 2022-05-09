package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCProstheticsManager;
import prosthetidist.pojos.Company;

import javax.swing.JTextField;
import java.awt.Color;

public class DeleteProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JDBCCompanyManager cm;
	private JTextField txtCode;
	private JTextField txtModel;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DeleteProsthetic frame = new DeleteProsthetic();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	


	public DeleteProsthetic(Company company) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		table = new JTable();
		
		cm.listProstheticsOfCompany(company);
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", ""},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"MODEL", "TYPE"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(25, 70, 403, 144);
		contentPane.add(table);
		
		txtCode = new JTextField();
		txtCode.setBackground(Color.LIGHT_GRAY);
		txtCode.setForeground(Color.RED);
		txtCode.setText("          CODE");
		txtCode.setBounds(89, 39, 43, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		txtModel = new JTextField();
		txtModel.setBackground(Color.LIGHT_GRAY);
		txtModel.setForeground(Color.RED);
		txtModel.setText("          MODEL");
		txtModel.setColumns(10);
		txtModel.setBounds(296, 39, 53, 20);
		contentPane.add(txtModel);
	}
}

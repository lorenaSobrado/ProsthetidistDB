package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JDBCCompanyManager cm;
	private JDBCProstheticManager pm;
	private JScrollPane scrollPane;


	public DeleteProsthetic(Company company, JDBCManager manager) {
		cm = new JDBCCompanyManager(manager);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int fil, int col) {
				return false;
			}
		};
		model.addColumn("Code");
		model.addColumn("Price");
		model.addColumn("Functionalities");
		model.addColumn("Type");
		model.addColumn("Model");
		model.addColumn("Length");
		model.addColumn("Width");
		model.addColumn("Weight");
		model.addColumn("Plastic");
		model.addColumn("Carbon Fiber");
		model.addColumn("Aluminium");

		for (Prosthetic p : cm.listProstheticsOfCompany(company)) {

			Object[] datos = new Object[] { p.getCode(), p.getPrice(), p.getFunctionalities(), p.getType(),
					p.getModel(), p.getMeasurements().getLengthiness(), p.getMeasurements().getWidth(),
					p.getMeasurements().getWeight(), p.hasPlastic(), p.hasCarbonFiber(), p.hasAluminium() };
			model.addRow(datos);
		}
		table = new JTable();
		table.setModel(model);
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 23, 403, 144);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton Back = new JButton("BACK");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteProsthetic.this.setVisible(false);
			}
		});
		Back.setBounds(44, 209, 89, 23);
		contentPane.add(Back);
		
		JButton Delete = new JButton("DELETE");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selection = table.getSelectedRows();
				for(int i : selection) {
					Prosthetic p = pm.getProstheticByCode(Integer.valueOf(model.getValueAt(i, 1).toString()));
					pm.deleteProsthetic(p);
				}
				JOptionPane.showMessageDialog(DeleteProsthetic.this, "Prosthetic deleted", "Message", 
						JOptionPane.INFORMATION_MESSAGE);
				DeleteProsthetic.this.setVisible(false);
			}
		});
		Delete.setBounds(312, 209, 89, 23);
		contentPane.add(Delete);
	}
}

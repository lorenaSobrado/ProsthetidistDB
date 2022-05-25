package SwingWindows;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfferProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JDBCProstheticManager pm;

	
	public OfferProsthetic(Company company, JDBCManager manager) {
		pm = new JDBCProstheticManager(manager);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Prosthetic to design: ");
		lblNewLabel.setBounds(55, 11, 218, 14);
		contentPane.add(lblNewLabel);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(26, 48, 370, 119);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int fil, int col) {
				return false;
			}
		};
		model.addColumn("Code");
		model.addColumn("Functionalities");
		model.addColumn("Type");
		model.addColumn("Length");
		model.addColumn("Width");
		model.addColumn("Weight");
		model.addColumn("Plastic");
		model.addColumn("Carbon Fiber");
		model.addColumn("Aluminium");

		for (Prosthetic p : pm.listProstheticsWithoutCompanyId()) {

			Object[] datos = new Object[] { p.getCode(), p.getFunctionalities(), p.getType(),
					p.getMeasurements().getLengthiness(), p.getMeasurements().getWidth(),
					p.getMeasurements().getWeight(), p.hasPlastic(), p.hasCarbonFiber(), p.hasAluminium() };
			model.addRow(datos);
		}
		table = new JTable();
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("DESIGN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				Prosthetic p = pm.getProstheticByCode(Integer.valueOf(model.getValueAt(i, 1).toString()));
				JFrame offerProstheticByCompany = new OfferProstheticByCompany(OfferProsthetic.this, company, p, manager);
				offerProstheticByCompany.setVisible(true);
			}
		});
		btnNewButton.setBounds(327, 231, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfferProsthetic.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(217, 231, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

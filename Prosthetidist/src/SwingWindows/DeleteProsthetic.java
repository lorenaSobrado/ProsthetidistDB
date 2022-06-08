package SwingWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	private JDBCCompanyManager cm;
	private JDBCProstheticManager pm;

	public DeleteProsthetic(JFrame companyMenuDisplay, Company company, JDBCManager manager) {
		cm = new JDBCCompanyManager(manager);
		pm = new JDBCProstheticManager(manager);
		companyMenuDisplay.setEnabled(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 636, 360);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.clearSelection();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			JLabel plasticImg = new JLabel();
			plasticImg.setIcon(new ImageIcon(p.hasPlastic()));
			JLabel carbonFiberImg = new JLabel();
			carbonFiberImg.setIcon(new ImageIcon(p.hasCarbonFiber()));
			JLabel aluminiumImg = new JLabel();
			aluminiumImg.setIcon(new ImageIcon(p.hasAluminium()));

			Object[] datos = new Object[] { p.getCode(), p.getPrice(), p.getFunctionalities(), p.getType(),
					p.getModel(), p.getMeasurement().getLengthiness(), p.getMeasurement().getWidth(),
					p.getMeasurement().getWeight(), plasticImg, carbonFiberImg, aluminiumImg };
			model.addRow(datos);
		}
		table.setModel(model);
		table.getColumn("Plastic").setCellRenderer(new LabelRenderer()); // sets the renderer implemented at the end of
																			// this class
		table.getColumn("Carbon Fiber").setCellRenderer(new LabelRenderer());
		table.getColumn("Aluminium").setCellRenderer(new LabelRenderer());

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(43, 42, 521, 146);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyMenuDisplay.setEnabled(true);
				DeleteProsthetic.this.setVisible(false);
			}
		});
		back.setBounds(10, 278, 32, 32);
		contentPane.add(back);

		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() > 0) {
					int[] selection = table.getSelectedRows();
					for (int i : selection) {
						Prosthetic p = pm.getProstheticByCode(Integer.valueOf(model.getValueAt(i, 0).toString()));
						pm.deleteProsthetic(p);
					}
					JOptionPane.showMessageDialog(DeleteProsthetic.this, "Prosthetic deleted", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					companyMenuDisplay.setEnabled(true);
					DeleteProsthetic.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(DeleteProsthetic.this, "Select a prosthetic to delete first",
							"Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		delete.setBounds(468, 268, 110, 32);
		contentPane.add(delete);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if(company.getEmail() == "trial@gmail.com") {
					cm.deleteCompany(company);
				}
				manager.disconnect();

			}
		};
		this.addWindowListener(exitListener);
	}
}

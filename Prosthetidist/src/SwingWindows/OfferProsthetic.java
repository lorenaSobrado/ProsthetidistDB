package SwingWindows;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OfferProsthetic extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private JDBCProstheticManager pm;

	public OfferProsthetic(JFrame companyMenuDisplay, Company company, JDBCManager manager) {
		pm = new JDBCProstheticManager(manager);
		companyMenuDisplay.setEnabled(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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

		JLabel lblNewLabel = new JLabel("Prosthetic to design: ");
		lblNewLabel.setBounds(55, 11, 218, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(26, 48, 370, 119);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

		for (Prosthetic p : pm.getProstheticsWithoutCompanyId()) {
			JLabel plasticImg = new JLabel();
			plasticImg.setIcon(new ImageIcon(p.hasPlastic()));
			JLabel carbonFiberImg = new JLabel();
			carbonFiberImg.setIcon(new ImageIcon(p.hasCarbonFiber()));
			JLabel aluminiumImg = new JLabel();
			aluminiumImg.setIcon(new ImageIcon(p.hasAluminium()));

			Object[] datos = new Object[] { p.getCode(), p.getFunctionalities(), p.getType(),
					p.getMeasurement().getLengthiness(), p.getMeasurement().getWidth(), p.getMeasurement().getWeight(),
					plasticImg, carbonFiberImg, aluminiumImg };
			model.addRow(datos);
		}
		table.setModel(model);
		table.getColumn("Plastic").setCellRenderer(new LabelRenderer()); // sets the renderer implemented at the end of this class
		table.getColumn("Carbon Fiber").setCellRenderer(new LabelRenderer());
		table.getColumn("Aluminium").setCellRenderer(new LabelRenderer());
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("OFFER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount() > 0) {
					int i = table.getSelectedRow();
					Prosthetic p = pm.getProstheticByCode(Integer.valueOf(model.getValueAt(i, 0).toString()));
					JFrame offerProstheticByCompany = new OfferProstheticByCompany(OfferProsthetic.this, companyMenuDisplay, company, p, manager);
					offerProstheticByCompany.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(OfferProsthetic.this, "Select a prosthetic first", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(327, 231, 89, 23);
		contentPane.add(btnNewButton);

		JButton back = new JButton("");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		back.setIcon(new ImageIcon(img));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyMenuDisplay.setEnabled(true);
				OfferProsthetic.this.setVisible(false);
			}
		});
		back.setBounds(10, 218, 32, 32);
		contentPane.add(back);

		// CLOSING CONNECTION WHEN PRESSING THE X OF THE JFRAME
		WindowListener exitListener = (WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				manager.disconnect();

			}
		};
		this.addWindowListener(exitListener);
	}
}

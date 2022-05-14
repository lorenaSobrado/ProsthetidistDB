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
import javax.swing.table.TableModel;

import prosthetidist.jdbc.JDBCProstheticsManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PatientMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//private JDBCInvoiceManager im;
	
	private JDBCProstheticsManager pm;

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


	public PatientMenuDisplay(JFrame pLogInDisplay, Patient patient) {
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
		
		JLabel lblNewLabel = new JLabel("User :");
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
		
		JLabel username = new JLabel(patient.getName());
		username.setBounds(82, 28, 89, 14);
		contentPane.add(username);
		
		JButton addToCart = new JButton("ADD TO CART");
		addToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//im.addProstheticToCart(la protesis seleccionada en la tabla)
			}
		});
		addToCart.setBounds(424, 288, 111, 23);
		contentPane.add(addToCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(39, 79, 488, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		
		
		
			
		
		}
		
		
		
//	TableModel dataModel= new AbstractTableModel () {
//		
//		public int getColumnCount() {
//			return 7;
//		}
//		public int getRowCount() {
//			return 7; //pm.listAllProstheticsWithCompanyId();
//		}
//		
//		public Object getValueAt (int row, int col) {
//			return new 
//		}
	
		public String[][] getDatos () {
			
			ArrayList<Prosthetic> list = new ArrayList<>();
			list = pm.listAllProstheticsWithCompanyID();

			int fil = pm.listAllProstheticsWithCompanyID().size();
			
			String [][] datos = new String [fil][7];
			
			for (int i=0;i<fil;i++) {
				Prosthetic p = list.get(i);
				datos[i][0] = (String) p.getPrice();
				datos[i][1] = 	
				datos[i][2] = 
				datos[i][3] = 
				datos[i][4] = 
				datos[i][5] = 	
			}
			
			
			
		}
		
}

	
	
	
	
	
	
	
	


package SwingWindows;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.ui.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class PatientMenuDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JDBCInvoiceManager im;
	private JDBCProstheticManager pm;

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


	public PatientMenuDisplay(JFrame pLogInDisplay, Patient patient, JDBCManager manager) {
		pLogInDisplay.setEnabled(false);
		im = new JDBCInvoiceManager(manager);
		pm = new JDBCProstheticManager(manager);
		
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
		Image logOutImg = new ImageIcon(this.getClass().getResource("/logOut.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(logOutImg));
		btnNewButton.setBounds(27, 288, 103, 23);
		contentPane.add(btnNewButton);
		
		JLabel userLabel = new JLabel("");
		Image userImg = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		userLabel.setIcon(new ImageIcon(userImg));
		userLabel.setBounds(27, 15, 45, 39);
		contentPane.add(userLabel);
		
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
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cartDisplay = new CartDisplay(PatientMenuDisplay.this, patient);
				cartDisplay.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(491, 17, 70, 37);
		contentPane.add(btnNewButton_4);
		Image img = new ImageIcon(this.getClass().getResource("/cart.png")).getImage();
		btnNewButton_4.setIcon(new ImageIcon(img));
		
		JLabel username = new JLabel(patient.getName());
		username.setBounds(70, 29, 89, 14);
		contentPane.add(username);
		
		JButton addToCart = new JButton("ADD TO CART");
		addToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount() > 0) {
					int[] selection = table.getSelectedRows();
					for (int i : selection) {
						Integer prosCode = Integer.parseInt(table.getValueAt(i, 0).toString());
						im.addProstheticToCart(patient, prosCode);
					}
					JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Your selection was added to your cart", "Message", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(PatientMenuDisplay.this, "Select a prosthetic first", "Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		addToCart.setBounds(424, 288, 111, 23);
		contentPane.add(addToCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(39, 79, 488, 177);
		contentPane.add(scrollPane);
		
		String[] cabecera = {"Price", "Functionalities", "Type", "Model", "Length", "Weight"," Width", "Materials"};
		
		table = new JTable(/*getDatosBorrar(), cabecera*/);
		scrollPane.setViewportView(table);
		
		
		}
		

	
		public String[][] getData (JDBCProstheticManager pm) {
			
			ArrayList<Prosthetic> list = new ArrayList<>();
			list = pm.listProstheticsWithCompanyId();

			int fil = pm.listProstheticsWithCompanyId().size();
			
			String [][] datos = new String [fil][9];
			
			for (int i=0;i<fil;i++) {
				Prosthetic p = list.get(i);
				datos[i][0] = String.valueOf(p.getCode());
				datos[i][1] = String.valueOf(p.getPrice());
				datos[i][2] = p.getFunctionalities();
				datos[i][3] = p.getType();
				datos[i][4] = p.getModel();
				datos[i][5] = String.valueOf(p.getMeasurements().getLengthiness());
				datos[i][6] = String.valueOf(p.getMeasurements().getWeight());
				datos[i][7] = String.valueOf(p.getMeasurements().getWidth());
				datos[i][8] = "See Materials";
			}
			
			return datos;
			
			
			
		}
public String[][] getDatosBorrar () {
			
		

			//int fil = pm.listAllProstheticsWithCompanyId().size();
			
			String [][] datos = new String [20][8];
			
			for (int i=0;i<20;i++) {
				datos[i][0] = "hola";
				datos[i][1] = "hola";
				datos[i][2] = "hola";
				datos[i][3] = "hola";
				datos[i][4] = "hola4";
				datos[i][5] = "hola";
				datos[i][6] = "hola";
				datos[i][7] = "See Materials";
			}
			
			return datos;
			
			
			
		} //CMABIAR QUE LAS PUEDA MODICIAR Y SELECCIONAR
		
}

	
	
	
	
	
	
	
	


package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CartDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField creditCard;
	private JRadioButton standard;
	private JRadioButton premium;
	private JButton buy;
	private JButton back;
	private JTable table;
	
    private JDBCInvoiceManager im;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CartDisplay frame = new CartDisplay();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public CartDisplay(JFrame patientMenuDisplay, Patient patient) {
		patientMenuDisplay.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOUR SELECTION :");
		lblNewLabel.setBounds(24, 11, 113, 14);
		contentPane.add(lblNewLabel);
		
		standard = new JRadioButton("STANDARD");
		standard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (standard.isSelected()) {
					premium.setSelected(false);
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		standard.setBounds(41, 177, 111, 23);
		contentPane.add(standard);
		
		premium = new JRadioButton("PREMIUM");
		premium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (premium.isSelected()) {
					standard.setSelected(false);
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) {
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		premium.setBounds(195, 177, 111, 23);
		contentPane.add(premium);
		
		JLabel lblNewLabel_2 = new JLabel("CREDIT CARD NUMBER :");
		lblNewLabel_2.setBounds(24, 224, 128, 14);
		contentPane.add(lblNewLabel_2);
		
		creditCard = new JTextField();
		creditCard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (creditCard.getText().length() >= 16) {
					e.consume(); //deja de procesar este evento
					Toolkit.getDefaultToolkit().beep();
				}
				if ((standard.isSelected() || premium.isSelected()) && (creditCard.getText().length() >= 15)) { //pq 15 y no 16?
					buy.setEnabled(true);
				} else {
					buy.setEnabled(false);
				}
			}
		});
		creditCard.setBounds(173, 221, 133, 20);
		contentPane.add(creditCard);
		creditCard.setColumns(10);
		
		buy = new JButton("BUY");
		buy.setEnabled(false);
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				JOptionPane.showMessageDialog(CartDisplay.this, "Thank you for buying with us !", "Message", 
						JOptionPane.INFORMATION_MESSAGE);
				CartDisplay.this.setVisible(false);
			}
		});
		buy.setBounds(377, 282, 89, 23);
		contentPane.add(buy);
		
		back = new JButton("BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientMenuDisplay.setEnabled(true);
				CartDisplay.this.setVisible(false);
			}
		});
		back.setBounds(24, 282, 89, 23);
		contentPane.add(back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 450, 89);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Choose delivery");
		lblNewLabel_1.setBounds(24, 157, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		String[] cabecera = {"Price", "Functionalities", "Type", "Model", "Length", "Weight"," Width", "Materials"};
		
		table = new JTable(/*getDatosBorrar(patient), cabecera*/);
		scrollPane.setViewportView(table);
		
	}
	public String[][] getDatos (Patient patient) {
		
		ArrayList<Prosthetic> list = new ArrayList<>();
		list = im.patientSelection(patient);

		int fil = list.size();
		
		String [][] datos = new String [fil][8];
		
		for (int i=0;i<fil;i++) {
			Prosthetic p = list.get(i);
			datos[i][0] = String.valueOf(p.getPrice());
			datos[i][1] = p.getFunctionalities();
			datos[i][2] = p.getType();
			datos[i][3] = p.getModel();
			datos[i][4] = String.valueOf(p.getMeasurements().getLengthiness());
			datos[i][5] = String.valueOf(p.getMeasurements().getWeight());
			datos[i][6] = String.valueOf(p.getMeasurements().getWidth());
			datos[i][7] = "See Materials";
		}
		
		return datos;
		
		
		
	}
	public String[][] getDatosBorrar (Patient patient) {
		
	
		
		String [][] datos = new String [10][8];
		
		for (int i=0;i<10;i++) {
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
		
		
		
	}
}

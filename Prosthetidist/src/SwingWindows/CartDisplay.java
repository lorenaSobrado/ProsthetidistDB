package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CartDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField creditCard;
	private JTable table;
	private JRadioButton standard;
	private JRadioButton premium;
	private JButton buy;
	private JButton back;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public CartDisplay(JFrame patientMenuDisplay) {
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
		
		table = new JTable();
		table.setBounds(10, 46, 465, 96);
		contentPane.add(table);
	}
}

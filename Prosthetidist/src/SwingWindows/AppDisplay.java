package SwingWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;

public class AppDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField txtProsthetidist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppDisplay frame = new AppDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton_1 = new JButton("PATIENT");
		btnNewButton_1.setBackground(new Color(144, 238, 144));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(228, 203, 145, 36);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFrame patientDisplay = new PatientDisplay();
				patientDisplay.setVisible(true);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("COMPANY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame companyDisplay = new CompanyDisplay();
				companyDisplay.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(36, 203, 145, 36);
		btnNewButton.setBackground(new Color(144, 238, 144));
		contentPane.add(btnNewButton);
		
		txtProsthetidist = new JTextField();
		txtProsthetidist.setBounds(10, 11, 414, 160);
		txtProsthetidist.setBackground(new Color(0, 153, 51));
		txtProsthetidist.setHorizontalAlignment(SwingConstants.CENTER);
		txtProsthetidist.setFont(new Font("Segoe UI Black", Font.ITALIC, 47));
		txtProsthetidist.setText("PROSTHETIDIST\r\n");
		contentPane.add(txtProsthetidist);
		txtProsthetidist.setColumns(30);
	}

}

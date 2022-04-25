package prosthetidist.pojos;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Company");
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Patient");
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
		contentPane.add(btnNewButton_1, BorderLayout.NORTH);
		
		txtProsthetidist = new JTextField();
		txtProsthetidist.setBackground(Color.LIGHT_GRAY);
		txtProsthetidist.setHorizontalAlignment(SwingConstants.CENTER);
		txtProsthetidist.setFont(new Font("Segoe UI Black", Font.ITALIC, 47));
		txtProsthetidist.setText("PROSTHETIDIST\r\n");
		contentPane.add(txtProsthetidist, BorderLayout.CENTER);
		txtProsthetidist.setColumns(30);
	}

}

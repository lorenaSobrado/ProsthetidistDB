package SwingWindows;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterSuccessfull extends JPanel {

	/**
	 * Create the panel.
	 */
	public RegisterSuccessfull(JFrame userDisplay, JFrame registerDisplay) {
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDisplay.setEnabled(true);
				RegisterSuccessfull.this.setVisible(false);
				registerDisplay.setVisible(false);
			}
		});
		setLayout(null);
		btnNewButton.setBounds(100, 188, 203, 43);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("REGISTER COMPLETED");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 83, 244, 26);
		add(lblNewLabel);

	}
}

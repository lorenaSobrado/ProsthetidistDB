package SwingWindows;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuyDisplay extends JPanel {

	
	public BuyDisplay() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THANK YOU FOR BUYING WITH US!!");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 86, 210, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("YOUR ORDER WILL ARRIVE \r\nAS SOON AS POSSIBLE");
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(76, 141, 291, 83);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyDisplay.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(335, 266, 89, 23);
		add(btnNewButton);

	}
}

package ultra_vision;


import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageRents extends JDialog {
	public ManageRents(Ultra_Vision_main mf) {
		setTitle("Manage Rents");
		setBounds(0,0,432,288);
		setLocationByPlatform(true);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton button = new JButton("Register rent");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterRentDialog dRR = new RegisterRentDialog(mf);
				dRR.setVisible(true);
				dRR.fillCombos();
			}
		});
		button.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		button.setBackground(new Color(255, 228, 196));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.weighty = 1.0;
		gbc_button.weightx = 0.5;
		gbc_button.insets = new Insets(5, 5, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		getContentPane().add(button, gbc_button);
		
		JButton button_1 = new JButton("Register return");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterReturnDialog rR = new RegisterReturnDialog(mf);
				rR.setVisible(true);
				rR.fillCombos();
			}
		});
		button_1.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		button_1.setBackground(new Color(255, 228, 196));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(5, 5, 5, 5);
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.weighty = 1.0;
		gbc_button_1.weightx = 0.5;
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		getContentPane().add(button_1, gbc_button_1);
	}

	//getting serial
	
	
	private static final long serialVersionUID = 1L;
	
	


}

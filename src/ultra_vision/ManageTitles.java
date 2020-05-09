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

public class ManageTitles extends JDialog {
	//settings for the class 
	public ManageTitles(Ultra_Vision_main  mf) {
		setTitle("Manage Titles");
		GridBagLayout gridBagLayout = new GridBagLayout();
		setBounds(0, 0, 412, 232);
		setLocationByPlatform(true);

		//getting pane and setting the layout 
		getContentPane().setLayout(gridBagLayout);

		//Creating the add title's button 
		JButton button = new JButton("Add title");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterTitleDialog rT = new RegisterTitleDialog(mf);
				rT.setVisible(true);
			}
		});
		//setting button's position and colour
		button.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		button.setBackground(new Color(255, 228, 196));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(5, 5, 5, 5);
		gbc_button.weighty = 1.0;
		gbc_button.weightx = 0.5;
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		getContentPane().add(button, gbc_button);
		
		 
		JButton button_1 = new JButton("Find T\u00EDtles");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindTitlesDialog ft = new FindTitlesDialog(mf);
				ft.setVisible(true);
				ft.fillTable();
			}
		});
		button_1.setBorder(new LineBorder(new Color(255, 69, 0), 2, true));
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

	
	private static final long serialVersionUID = 1L;

}

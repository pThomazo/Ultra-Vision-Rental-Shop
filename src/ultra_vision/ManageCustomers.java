package ultra_vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;



public class ManageCustomers extends JDialog {
public ManageCustomers(Ultra_Vision_main mf) {
	setTitle("Manage Customers");
	setBounds(0, 0, 455, 303);
	setLocationByPlatform(true);
	
	//setting the results in order column and rows 
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] {0,0,0,0};
	gridBagLayout.rowHeights = new int[] {0,0};
	gridBagLayout.columnWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[] {0.0, Double.MIN_VALUE};
	getContentPane().setLayout(gridBagLayout);
	
	//creating the new customer's button
	JButton button = new JButton("New customer");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			NewCustomerDialog dNC = new NewCustomerDialog(mf);
			dNC.setVisible(true);
		}
	});
	//setting button's position and colour
	button.setBorder(new LineBorder(new Color(255, 228, 196),2 , true));
	button.setBackground(new Color(255, 228, 196));
	GridBagConstraints gbc_button = new GridBagConstraints();
	gbc_button.fill = GridBagConstraints.BOTH;
	gbc_button.weightx = 1.0;
	gbc_button.weighty = 0.3;
	gbc_button.insets = new Insets(5, 5, 5, 5);
	gbc_button.gridx=0;
	gbc_button.gridy=0;
	getContentPane().add(button, gbc_button);
	
	//new button to have the option of update a customers 
	JButton button_1 = new JButton("Update Customer");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UpdateCustomerDialog dNC = new UpdateCustomerDialog(mf);
			dNC.setVisible(true);
			dNC.fillCombo();
		}
	});
	////setting button's position,colour
	button_1.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
	button_1.setBackground(new Color(255, 228, 196));
	GridBagConstraints gbc_button_1 = new GridBagConstraints();
	gbc_button_1.fill = GridBagConstraints.BOTH;
	gbc_button_1.weighty = 1.0;
	gbc_button_1.weightx = 0.3;
	gbc_button_1.insets = new Insets(5, 5, 5, 5);
	gbc_button_1.gridx = 1;
	gbc_button_1.gridy = 0;
	getContentPane().add(button_1, gbc_button_1);
	
	////new button to have the option to Find  a customers 
	JButton button_2 = new JButton("Find customers");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FindCustomersDialog fc = new FindCustomersDialog(mf);
			fc.setVisible(true);
			fc.fillTable();
		}
	});
	
	button_2.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
	button_2.setBackground(new Color(255, 228, 196));
	button_2.setActionCommand("FindCustomers");
	GridBagConstraints gbc_button_2 = new GridBagConstraints();
	gbc_button_2.insets = new Insets(5, 5, 5, 5);
	gbc_button_2.fill = GridBagConstraints.BOTH;
	gbc_button_2.weightx = 2;
	gbc_button_2.gridx = 2;
	gbc_button_2.gridy = 0;
	getContentPane().add(button_2, gbc_button_2);
	
}


private static final long serialVersionUID = 1L;
}

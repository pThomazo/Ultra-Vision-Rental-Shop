package ultra_vision;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class NewCustomerDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	
	private JComboBox<String> comboPlan;
	
	//create the dialog 
	//setting the attributes
	
	public NewCustomerDialog(Ultra_Vision_main main) {
		
		setTitle("Register customer");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5,5,5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[] {0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx= 2;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
			}
		{
			tfName = new JTextField();
			GridBagConstraints gbc_tfName = new GridBagConstraints();
			gbc_tfName.insets = new Insets(0, 0, 5, 0);
			gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfName.gridx =2;
			gbc_tfName.gridy = 0;
			contentPanel.add(tfName, gbc_tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lblPlan = new JLabel("Plan");
			GridBagConstraints gbc_lblPlan = new GridBagConstraints();
			gbc_lblPlan.insets = new Insets(0, 0, 5, 5);
			gbc_lblPlan.gridx = 0;
			gbc_lblPlan.gridy = 1;
			contentPanel.add(lblPlan, gbc_lblPlan);
		}
		{
			comboPlan = new JComboBox<String>();
			comboPlan.setModel(new DefaultComboBoxModel<String>(
					new String[] {"Music Lovers","Video Lovers","TV Lovers","Premiun"}));
			GridBagConstraints gbc_comboPlan = new GridBagConstraints();
			gbc_comboPlan.insets = new Insets(0, 0, 5, 0);
			gbc_comboPlan.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboPlan.gridx = 2;
			gbc_comboPlan.gridy = 1;
			contentPanel.add(comboPlan, gbc_comboPlan);
		}
		{
		JPanel buttonPane = new JPanel ();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
		JButton okButton = new JButton("Register");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//register the customer
				try {
					Connection conn = main.getConnMan().getConnection();
					
					String custName = tfName.getText();
					//String cardNumber = cardNumberField.getText();				
					int idxPlan = comboPlan.getSelectedIndex();
					
					
					String sqlStoreCust = "INSERT INTO customers (name) VALUES(?)";
					
					PreparedStatement ps = conn.prepareStatement(sqlStoreCust);
					ps.setString(1,  custName);
					
					int numRows = ps.executeUpdate();
					
					if(numRows > 0) {
				//after the customer be registered the membership card should also be created 
						
						SimpleDateFormat sdF = new SimpleDateFormat("MMddYYYY");
						
						Random postRan = new Random();
						int postFix = postRan.nextInt(99999);
						String cardNumber = sdF.format(new Date()).concat(String.valueOf(postFix));
						
						//store the card
						String sqlStoreCard = "INSERT INTO cards (number,customerld, access_level_id)VALUES(?,?,?)";
						
						//Getting customer ID
						
						String sqlGetCustID = "INSERT id FROM customers WHERE name=?";
						PreparedStatement psF = conn.prepareStatement(sqlGetCustID);
						psF.setString(1, custName);
						ResultSet rs = psF.executeQuery();
						
						if(rs.next()) {
							int custId = rs.getInt(1);
							
							PreparedStatement pSStoreCard = conn.prepareStatement(sqlStoreCard);
							pSStoreCard.setString(1,  cardNumber);
							pSStoreCard.setInt(2, custId);
							pSStoreCard.setInt(3, idxPlan);
							pSStoreCard.executeUpdate();
							
							//ALL: notify that customer was rig without card or cancel registration
							//GETTING card ID
							
							String sqlGetCardID = "SELECT id FROM cards WHERE number=?";
							
							PreparedStatement psFCardID = conn.prepareStatement(sqlGetCardID);
							psFCardID.setString(1, cardNumber);
							ResultSet rsCID = psFCardID.executeQuery();
							
							if(rsCID.next()) {
								int cardId = rsCID.getInt(1);
								
								String SQLUpdateCustomer = "UPDATE customers SET card id=? WHERE id=?";
								PreparedStatement psUpdateCust = conn.prepareStatement(SQLUpdateCustomer);
								psUpdateCust.setInt(1,cardId);
								psUpdateCust.setInt(2,custId);
								psUpdateCust.executeUpdate();
							}
						}else {
							System.out.println("Cust not found!");
						}
						//Success
						
					JOptionPane.showMessageDialog(NewCustomerDialog.this,"Customer registered","Success", JOptionPane.INFORMATION_MESSAGE);
					tfName.setText("");
						
					}
					
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
				}
			
		});
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new  JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCustomerDialog.this.dispose();
			}
				
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		
		}
		}
	
	}

}

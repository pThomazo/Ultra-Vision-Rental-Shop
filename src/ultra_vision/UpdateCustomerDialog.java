package ultra_vision;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import model.Customer;
import model.MembershipCard;
import model.Plan;

public class UpdateCustomerDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private Ultra_Vision_main mf;
	private JFormattedTextField cardNumberField;
	private JComboBox<String> comboPlan;
	private JComboBox<Customer> cbCustomers;
	
	
	//creating the dialog 
	
	public UpdateCustomerDialog(Ultra_Vision_main mf) {
		this.mf = mf;
		
		setTitle("Update customer");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {0,0,0,0,0};
		gbl_contentPanel.columnWeights = new double[] {0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		//ten numbers 
		MaskFormatter format = null;
		
		try {
			
			format = new MaskFormatter("##########");
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		{
			JLabel lblCustomer = new JLabel("Customer:");
			GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
			gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomer.gridx= 0;
			gbc_lblCustomer.gridy = 0;
			contentPanel.add(lblCustomer, gbc_lblCustomer);
		}
		
		{
			cbCustomers = new JComboBox<Customer>();
			cbCustomers.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					Customer cSel=(Customer)e.getItem();
					tfName.setText(cSel.getName());
					
					if (cSel.getMemberCard() != null) {
						cardNumberField.setText(cSel.getMemberCard().getCardNumber());
						comboPlan.setSelectedcIndex(cSel.getMemberCard().getPlan().cod);
					}else {
						System.err.println("Card not found");
						cardNumberField.setText("");
						comboPlan.setSelectedIndex(0);
					}
				}
				
			});
			GridBagConstraints gbc_cbCustomers = new GridBagConstraints();
			gbc_cbCustomers.insets = new Insets(0, 0, 5, 0);
			gbc_cbCustomers.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbCustomers.gridx = 2;
			gbc_cbCustomers.gridy = 0;
			contentPanel.add(cbCustomers, gbc_cbCustomers);
		}
		
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			tfName = new JTextField();
			GridBagConstraints gbc_tfName = new GridBagConstraints();
			gbc_tfName.insets = new Insets(0, 0, 5,0);
			gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfName.gridx = 2;
			gbc_tfName.gridy = 1;
			contentPanel.add(tfName, gbc_tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lblCardNumb = new JLabel("Card number");
			GridBagConstraints gbc_lblCardNumb = new GridBagConstraints();
			gbc_lblCardNumb.insets = new Insets(0, 0, 5, 5);
			gbc_lblCardNumb.gridx = 0;
			gbc_lblCardNumb.gridy = 2;
			contentPanel.add(lblCardNumb, gbc_lblCardNumb);
			
		}
		
		cardNumberField = new JFormattedTextField(format);
		GridBagConstraints gbc_cardNumberField = new GridBagConstraints();
		gbc_cardNumberField.insets = new Insets(0, 0, 5, 0);
		gbc_cardNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardNumberField.gridx = 2;
		gbc_cardNumberField.gridy = 2;
		contentPanel.add(cardNumberField, gbc_cardNumberField);
		
		{
			JLabel lblPlan = new JLabel("Plan");
			GridBagConstraints gbc_lblPlan = new GridBagConstraints();
			gbc_lblPlan.insets = new Insets(0, 0, 0, 5);
			gbc_lblPlan.gridx = 0;
			gbc_lblPlan.gridy = 3;
			contentPanel.add(lblPlan, gbc_lblPlan);
		}
		
		{
			comboPlan = new JComboBox<String>();
			comboPlan.setModel(new DefaultComboBoxModel<String>(
					new String[] { "Music Lovers", "Video Lovers", "TV Lovers", "Premium" }));
			GridBagConstraints gbc_comboPlan = new GridBagConstraints();
			gbc_comboPlan.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboPlan.gridx = 2;
			gbc_comboPlan.gridy = 3;
			contentPanel.add(comboPlan, gbc_comboPlan);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection conn = mf.getConnMan().getConnection();

							Customer cSel = (Customer) cbCustomers.getSelectedItem();

							String custName = tfName.getText();
							String cardNumber = cardNumberField.getText();
							int idxPlan = comboPlan.getSelectedIndex();

							String sqlStoreCust = "UPDATE customers SET name=? WHERE id=?";
							PreparedStatement ps = conn.prepareStatement(sqlStoreCust);

							ps.setString(1, custName);
//					
							ps.setInt(2, cSel.getId());

							int numRows = ps.executeUpdate();

							if (numRows > 0) {
								// Success

								if (cardNumber.length() < 10) {
									JOptionPane.showMessageDialog(UpdateCustomerDialog.this,
											"The card number must have 10 characters!", "Error",
											JOptionPane.ERROR_MESSAGE);
								}

								String sqlUpdateCard;

								if (cSel.getMemberCard() != null) {
									sqlUpdateCard = "UPDATE cards SET number=?, access_level_id=? WHERE id=?";
									PreparedStatement psUC = conn.prepareStatement(sqlUpdateCard);

									psUC.setString(1, cardNumber);
									psUC.setInt(2, idxPlan);
									psUC.setInt(3, cSel.getMemberCard().getId());

									int numRowsC = psUC.executeUpdate();

									if (numRowsC > 0) {
										JOptionPane.showMessageDialog(UpdateCustomerDialog.this,
												"Customer info were updated!", "Success",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(UpdateCustomerDialog.this,
												"Only customer name was updated!", "Success",
												JOptionPane.WARNING_MESSAGE);

									}

									MembershipCard mC = cSel.getMemberCard();
									switch (idxPlan) {
									case 0:
										mC.setPlan(Plan.MusicLovers);
										break;
									case 1:
										mC.setPlan(Plan.VideoLovers);
										break;
									case 2:
										mC.setPlan(Plan.TVLovers);
										break;
									case 3:
										mC.setPlan(Plan.Premium);
										break;
									}

									mC.setCardNumber(cardNumber);
								} else {
									sqlUpdateCard = "INSERT INTO cards (number, customerId, access_level_id) VALUES(?,?,?)";

									PreparedStatement psUC = conn.prepareStatement(sqlUpdateCard);

									psUC.setString(1, cardNumber);
									psUC.setInt(2, cSel.getId());
									psUC.setInt(3, idxPlan);

									int numRowsC = psUC.executeUpdate();

									if (numRowsC > 0) {
										// Get card ID
										String sqlGetCardID = "SELECT id FROM cards WHERE number=?";

										PreparedStatement psFCardID = conn.prepareStatement(sqlGetCardID);
										psFCardID.setString(1, cardNumber);
										ResultSet rsCID = psFCardID.executeQuery();

										if (rsCID.next()) {
											int cardId = rsCID.getInt(1);

											String SQLUpdateCustomer = "UPDATE customers SET card_id=? WHERE id=?";
											PreparedStatement psUpdateCust = conn.prepareStatement(SQLUpdateCustomer);
											psUpdateCust.setInt(1, cardId);
											psUpdateCust.setInt(2, cSel.getId());
											psUpdateCust.executeUpdate();
										}

										MembershipCard mC = cSel.getMemberCard();
										switch (idxPlan) {
										case 0:
											mC.setPlan(Plan.MusicLovers);
											break;
										case 1:
											mC.setPlan(Plan.VideoLovers);
											break;
										case 2:
											mC.setPlan(Plan.TVLovers);
											break;
										case 3:
											mC.setPlan(Plan.Premium);
											break;
										}

										mC.setCardNumber(cardNumber);

										JOptionPane.showMessageDialog(UpdateCustomerDialog.this,
												"Customer info were updated!", "Success",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(UpdateCustomerDialog.this,
												"Only customer name was updated!", "Success",
												JOptionPane.WARNING_MESSAGE);

									}
								}

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
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCustomerDialog.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
}
  }		
	}
	public void fillCombo() {

		try {
			// Here must be good filter only customers that have rents registered
			 
			Connection cn = mf.getConnMan().getConnection();

			String sqlFindCustomers = "SELECT id,name FROM customers";

			PreparedStatement psFC = cn.prepareStatement(sqlFindCustomers);
			ResultSet rsCust = psFC.executeQuery();

			Customer cust;
			while (rsCust.next()) {
				// System.out.println("Found customer");

				cust = new Customer();

				cust.setId(rsCust.getInt(1));
				cust.setName(rsCust.getString(2));

				String sqlFindCard = "SELECT id,number,access_level_id FROM cards WHERE customerId=?";

				PreparedStatement psFCard = cn.prepareStatement(sqlFindCard);
				psFCard.setInt(1, cust.getId());
				ResultSet rsCard = psFCard.executeQuery();

				if (rsCard.next()) {
					System.out.println("Card found of cust " + cust.getName());

					MembershipCard mC = new MembershipCard();
					mC.setId(rsCard.getInt(1));
					mC.setCardNumber(rsCard.getString(2));
					switch (rsCard.getInt(3)) {
					case 0:
						mC.setPlan(Plan.MusicLovers);
						break;
					case 1:
						mC.setPlan(Plan.VideoLovers);
						break;
					case 2:
						mC.setPlan(Plan.TVLovers);
						break;
					case 3:
						mC.setPlan(Plan.Premium);
						break;
					}

					cust.setMemberCard(mC);
				}

				cbCustomers.addItem(cust);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

				

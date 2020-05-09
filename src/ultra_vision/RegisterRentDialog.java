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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import model.Customer;
import model.Title;

public class RegisterRentDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Customer> comboCustomers;
	private JComboBox<Title> comboTitle;
	private Ultra_Vision_main mf;
	private JFormattedTextField tfDate;

	/**
	 * Create the dialog.
	 */
	public RegisterRentDialog(Ultra_Vision_main mf) {
		this.mf = mf;
		setTitle("Register Rent");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitle = new JLabel("Title:");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		{
			comboTitle = new JComboBox<Title>();
			GridBagConstraints gbc_comboTitle = new GridBagConstraints();
			gbc_comboTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboTitle.insets = new Insets(0, 0, 5, 0);
			gbc_comboTitle.gridx = 1;
			gbc_comboTitle.gridy = 0;
			contentPanel.add(comboTitle, gbc_comboTitle);
		}
		{
			JLabel lblCustomer = new JLabel("Customer:");
			GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
			gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomer.gridx = 0;
			gbc_lblCustomer.gridy = 1;
			contentPanel.add(lblCustomer, gbc_lblCustomer);
		}
		{
			comboCustomers = new JComboBox<Customer>();
			GridBagConstraints gbc_comboCustomer = new GridBagConstraints();
			gbc_comboCustomer.insets = new Insets(0, 0, 5, 0);
			gbc_comboCustomer.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboCustomer.gridx = 1;
			gbc_comboCustomer.gridy = 1;
			contentPanel.add(comboCustomers, gbc_comboCustomer);
		}
		{
			JLabel lblDate = new JLabel("Date:");
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.anchor = GridBagConstraints.EAST;
			gbc_lblDate.insets = new Insets(0, 0, 0, 5);
			gbc_lblDate.gridx = 0;
			gbc_lblDate.gridy = 2;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
		
			tfDate = new JFormattedTextField(new DateFormatter(DateFormat.getDateInstance(DateFormat.SHORT)));
			tfDate.setValue(new Date());
//			tfDate.setFormatterFactory(new AbstractFormatterFactory() {
//				
//				public AbstractFormatter getFormatter(JFormattedTextField tf) {
//					
//				}
//			});
			GridBagConstraints gbc_tfDate = new GridBagConstraints();
			gbc_tfDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfDate.gridx = 1;
			gbc_tfDate.gridy = 2;
			contentPanel.add(tfDate, gbc_tfDate);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Register");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Here the rent should be registered
						try {
							Connection cn = mf.getConnMan().getConnection();

							int titleId = ((Title) comboTitle.getSelectedItem()).getId();
							int customerId = ((Customer) comboCustomers.getSelectedItem()).getId();
							
							//
							 //Here must be checked if the customer plan allow rent of the title type
							 
							
							//query title type
							String sqlQueryType = "SELECT type FROM Titles WHERE id=?";
							PreparedStatement psQT = cn.prepareStatement(sqlQueryType);

							psQT.setInt(1, titleId);
							
							ResultSet resQT = psQT.executeQuery();
							if(resQT.next()) {
								int type = resQT.getInt(1);
																
								//query customer plan
								String sqlQueryCardPlan = "SELECT access_level_id FROM Cards WHERE id IN (SELECT card_id FROM Customers WHERE id=?)";
								PreparedStatement psQPlan = cn.prepareStatement(sqlQueryCardPlan);

								psQPlan.setInt(1, customerId);
								
								ResultSet resQPlan = psQT.executeQuery();
								if(resQPlan.next()) {
									int plan  = resQPlan.getInt(1);
									
									if(plan ==0) {
										//ML
										if(type !=0 && type!=1) {
											//Not allowed
											System.out.println("Not allowed");
											return;
										}
									}else if(plan == 1) {
										if(type !=2) {
											//Not allowed
											System.out.println("Not allowed");
											return;
										}
									}else if(plan == 2) {
										if(type !=3) {
											//Not allowed
											System.out.println("Not allowed");
											return;
										}
									}else if(plan == 3) {
										//Can rent anyone
									}
								}
							}
							
							
							String sqlQueryTotalRents = "SELECT COUNT(id) AS total_rent FROM rents WHERE customerId=? AND returned='1'";
							PreparedStatement psQTotal = cn.prepareStatement(sqlQueryTotalRents);

							psQTotal.setInt(1, customerId);
							
							ResultSet resQTot = psQTotal.executeQuery();
							if(resQTot.next()) {
								int totalRents = resQTot.getInt("total_rent");
								if(totalRents>=4) {									
									System.out.println("Rent limit reached!");
									return;
								}
							}
							
							//Rent allowed
							SimpleDateFormat amerFo = new SimpleDateFormat("YYYY-MM-dd");
							
							String date = amerFo.format((Date)tfDate.getValue());

							
							String sqlStoreRent = "INSERT INTO rents (titleId,customerId,date) VALUES(?,?,?)";
							PreparedStatement ps = cn.prepareStatement(sqlStoreRent);

							ps.setInt(1, titleId);
							ps.setInt(2, customerId);
							ps.setString(3, date);

							int numRows = ps.executeUpdate();

							if (numRows > 0) {
								// Success
								JOptionPane.showMessageDialog(RegisterRentDialog.this, "Rent registered");
							}

						} catch (SQLException e1) {

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
						RegisterRentDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	
	 //This method should load the titles and customers on the system and show them
	 //on the drop down lists
	 
	public void fillCombos() {
		System.out.println("Filling combos...");
		// TODO: Implement
		try {
			Connection cn = mf.getConnMan().getConnection();

			String sqlFindTitles = "SELECT id,name FROM titles";

			PreparedStatement ps = cn.prepareStatement(sqlFindTitles);
			ResultSet rsTitles = ps.executeQuery();

			Title titl;
			while (rsTitles.next()) {
				System.out.println("Found title");
				titl = new Title();
				
				titl.setId(rsTitles.getInt(1));
				System.out.println("Title id: "+titl.getId());
				
				titl.setName(rsTitles.getString(2));

				System.out.println("Title n: "+titl.getName());
				
				comboTitle.addItem(titl);
			}

			
			 // Here must be good filter only customers that have limit to make at least rents
			 
			String sqlFindCustomers = "SELECT id,name FROM customers";

			PreparedStatement psFC = cn.prepareStatement(sqlFindCustomers);
			ResultSet rsCust = psFC.executeQuery();

			Customer cust;
			while (rsCust.next()) {
				System.out.println("Found customer");
				
				cust = new Customer();

				cust.setId(rsCust.getInt(1));
				cust.setName(rsCust.getString(2));

				comboCustomers.addItem(cust);
			}

		} catch (Exception e) {

		}
	}

}

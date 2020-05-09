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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;
import model.Rent;
import model.Title;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class RegisterReturnDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Ultra_Vision_main mf;
	private JComboBox<Customer> comboCustomer;
	private JComboBox<Rent> titleCombo;
	private JTextField tfDate;

	
	 // Create the dialog.
	 
	public RegisterReturnDialog(Ultra_Vision_main mf) {
		setTitle("Register rent return");
		this.mf = mf;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCustomer = new JLabel("Customer:");
			GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
			gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomer.gridx = 0;
			gbc_lblCustomer.gridy = 0;
			contentPanel.add(lblCustomer, gbc_lblCustomer);
		}
		{
			comboCustomer = new JComboBox<Customer>();
			comboCustomer.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					Customer cust = (Customer) e.getItem();

					// Here the titles combo should be updated to show the titles rented by the user
					try {
						Connection cn = mf.getConnMan().getConnection();

						// Maybe delete the rent registry should also work
						String sqlFindRents = "SELECT id,titleId,date FROM rents WHERE customerId=? AND returned='0'";
						PreparedStatement psFR = cn.prepareStatement(sqlFindRents);

						psFR.setInt(1, cust.getId());

						ResultSet resFR = psFR.executeQuery();

						Rent rent;
						while (resFR.next()) {
							rent = new Rent();

							rent.setUserId(resFR.getInt(1));

							int tileId = resFR.getInt(2);
							String sqlFindTi = "SELECT id,name FROM titles WHERE id=?";
							PreparedStatement psFT = cn.prepareStatement(sqlFindTi);

							psFT.setInt(1, tileId);

							ResultSet resTi = psFT.executeQuery();

							Title tl;
							while (resTi.next()) {
								tl = new Title();
								tl.setId(resTi.getInt(1));
								tl.setName(resTi.getString(2));
								rent.getTitles().add(tl);
							}

							SimpleDateFormat sdF = new SimpleDateFormat("YYYY-MM-dd");
							Date rentDate = null;

							try {
								rentDate = sdF.parse(resFR.getString(3));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

							rent.setDateRent(rentDate);
							titleCombo.addItem(rent);

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			});
			GridBagConstraints gbc_comboCustomer = new GridBagConstraints();
			gbc_comboCustomer.insets = new Insets(0, 0, 5, 0);
			gbc_comboCustomer.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboCustomer.gridx = 2;
			gbc_comboCustomer.gridy = 0;
			contentPanel.add(comboCustomer, gbc_comboCustomer);
		}

		{
			JLabel lblTitle = new JLabel("Title:");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 1;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		{
			titleCombo = new JComboBox<Rent>();
			titleCombo.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					Rent rentSel = (Rent) e.getItem();
					SimpleDateFormat sdF = new SimpleDateFormat("MM-dd-YYYY");
					tfDate.setText(sdF.format(rentSel.getDateRent()));

				}
			});
			GridBagConstraints gbc_titleCombo = new GridBagConstraints();
			gbc_titleCombo.insets = new Insets(0, 0, 5, 0);
			gbc_titleCombo.fill = GridBagConstraints.HORIZONTAL;
			gbc_titleCombo.gridx = 2;
			gbc_titleCombo.gridy = 1;
			contentPanel.add(titleCombo, gbc_titleCombo);
		}
		{
			JLabel lblDate = new JLabel("Date:");
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblDate.gridx = 0;
			gbc_lblDate.gridy = 2;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
			tfDate = new JTextField();
			tfDate.setEditable(false);
			GridBagConstraints gbc_tfDate = new GridBagConstraints();
			gbc_tfDate.insets = new Insets(0, 0, 5, 0);
			gbc_tfDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfDate.gridx = 2;
			gbc_tfDate.gridy = 2;
			contentPanel.add(tfDate, gbc_tfDate);
			tfDate.setColumns(10);
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

						Rent rentSel = ((Rent) titleCombo.getSelectedItem());
							int rentId = rentSel.getId();

							// Maybe delete the rent registry should also work
							String sqlStoreReturn = "UPDATE rents SET returned = '1' WHERE id=?";
							PreparedStatement ps = cn.prepareStatement(sqlStoreReturn);

							ps.setInt(1, rentId);

							int numRows = ps.executeUpdate();

							if (numRows > 0) {
								titleCombo.removeItem(rentSel);
								
								// Success
								JOptionPane.showMessageDialog(RegisterReturnDialog.this, "Return registered!");
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
						RegisterReturnDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void fillCombos() {
		// All: Implement
		try {
			
			 // Here must be good filter only customers that have rents registered
			 
			Connection cn = mf.getConnMan().getConnection();

			String sqlFindCustomers = "SELECT id,name FROM customers";

			PreparedStatement psFC = cn.prepareStatement(sqlFindCustomers);
			ResultSet rsCust = psFC.executeQuery();

			Customer cust;
			while (rsCust.next()) {
				System.out.println("Found customer");

				cust = new Customer();

				cust.setId(rsCust.getInt(1));
				cust.setName(rsCust.getString(2));

				comboCustomer.addItem(cust);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

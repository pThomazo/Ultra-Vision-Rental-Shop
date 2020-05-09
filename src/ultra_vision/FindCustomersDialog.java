package ultra_vision;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import model.Customer;

public class FindCustomersDialog extends JDialog {
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fieldNameToSearch;
	private CustomersTableModel customersTableModel;
	private Ultra_Vision_main mF;
	private JTable customersTable;
	
	//creating dialog.
	
	public FindCustomersDialog(Ultra_Vision_main mFrame) {
		this.mF = mFrame;
		//Setting window and parameter (Find customer)
		setTitle("Find customers");
		setBounds(100, 100, 561, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout. CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWeights = new double[] {1.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			//creating label name and adding it to the panel.
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			//creating the button search setting actionlistener to it 
			JButton btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String searchTerm = fieldNameToSearch.getText();
				
				try {
					List<Customer> customers = executeCustomersFind(searchTerm);
					//update the table with the titles found 
					customersTableModel.setCustomers(customers);
				}catch(Exception e1) {
					//All blocks catch automatically generated 
					e1.printStackTrace();
				}
			}
			});
			
			getRootPane().setDefaultButton(btnSearch);
			
			{
				//new JTextField 
				fieldNameToSearch = new JTextField();
				GridBagConstraints gbc_fieldNameToSearch = new GridBagConstraints();
				gbc_fieldNameToSearch.gridwidth = 2;
				gbc_fieldNameToSearch.fill =GridBagConstraints.HORIZONTAL;
				gbc_fieldNameToSearch.insets = new Insets(0, 0, 5, 5);
				gbc_fieldNameToSearch.gridx = 1;
				gbc_fieldNameToSearch.gridy = 0;
				contentPanel.add(fieldNameToSearch, gbc_fieldNameToSearch);
				fieldNameToSearch.setColumns(10);
				
			}
					
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets (0, 0, 5, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		contentPanel.add(btnSearch, gbc_btnSearch);
	
		}
		{
			//creating new customerTableModel
			customersTableModel = new CustomersTableModel();
			
			{
				//add a JScrollPane to have the option to go through options 
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 6;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 2;
				contentPanel.add(scrollPane, gbc_scrollPane);
				customersTable = new JTable(customersTableModel);
				scrollPane.setViewportView(customersTable);
			}
		}
		
	}
	//This method must be called just after this window be turned visible to 
	//the table be filled with titles 
	
	public void fillTable() {
		try {
			List<Customer> allCusto = executeCustomersFind(null);
			customersTableModel.setCustomers(allCusto);
		}catch (Exception e) {
			//all block catch automatically generated 
			e.printStackTrace();
		}
	}
	
	//@param search Term null to the list all titles 
	//@return
	//@throws exception
	
	private List<Customer>executeCustomersFind(String searchTerm) throws Exception{
		try {
			
			Connection cn = mF.getConnMan().getConnection();
			String sqlFindCustomers = "SELECT * FROM customers";
			
			boolean hasTerm = false;
			if(searchTerm != null && !searchTerm.isEmpty()) {
			   sqlFindCustomers += "WHERE name LIKE ?";
			   hasTerm = true;
		}
		PreparedStatement psFT = cn.prepareStatement(sqlFindCustomers);
		if(hasTerm) {
			psFT.setString(1, '%' + searchTerm + '%');
		}
		
		ResultSet rsTitl = psFT.executeQuery();
		
		List<Customer>customers = new ArrayList<>();
		Customer custo;
		
		while(rsTitl.next()) {
			System.out.println("Found customers");
			
			custo = new Customer();
			
			custo.setId(rsTitl.getInt(1));
			custo.setName(rsTitl.getString(2));
			
			customers.add(custo);
		}
	
		
		return customers;
	
	}catch (Exception e) {
		e.printStackTrace();
		
		throw e;
	}

   }
}

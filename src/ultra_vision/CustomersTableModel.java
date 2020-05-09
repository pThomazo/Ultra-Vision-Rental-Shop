package ultra_vision;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class CustomersTableModel extends AbstractTableModel {
	
	
	 
	private static final long serialVersionUID = 1L;
	private List<Customer> customers;
	private static final String[] columnNames = new String[] {"ID","Name"};
	
	  //creating the arrayList customer
	public CustomersTableModel() {
		customers = new ArrayList<Customer>();
	}
	
// the column name is going to be returned 
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
//get the row where name is stored 
	@Override
	public int getRowCount() {
	return customers.size() ;
	}
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
//here  return the element at he specified index
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	Customer titleAtRow = customers.get(rowIndex);
	//getting return name, Id 
	switch(columnIndex) {
	case 0:
		return titleAtRow.getId();
		
	case 1:
		return titleAtRow.getName();
		
		default: 
			return null;
	}
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
		this.fireTableDataChanged();
		
		
	}

}

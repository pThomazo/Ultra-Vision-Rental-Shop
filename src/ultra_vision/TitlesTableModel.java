package ultra_vision;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Title;

public class TitlesTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Title> titles;
	private static final String[] columnNames = new String[] { "ID", "Name", "Type", "Midia", "Year", "Genre" };

	public TitlesTableModel() {
		titles = new ArrayList<Title>();
	}

	@Override
	public String getColumnName(int column) {	
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		return titles.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Title titleAtRow = titles.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return titleAtRow.getId();

		case 1:
			return titleAtRow.getName();

		case 2:
			return titleAtRow.getType().name;
		case 3:
			return titleAtRow.getMidia().name();
		case 4:
			return titleAtRow.getYearReleased();
		case 5:
			return titleAtRow.getGenre();
		default:
			return null;
		}
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
		this.fireTableDataChanged();
	}

}

package org.mtforce.table;

import javax.swing.table.AbstractTableModel;

public class SensorTableModel extends AbstractTableModel {

	private String[] sensors;
	private String[] names;
	public Double[] values;
	private String[] columnNames = TableColumn.getNames();
	private Class<?>[] columnClasses = Constants.COLUMN_CLASSES;

	// This table model works for any one given directory
	public SensorTableModel(String[] sensors, String[] names, Double[] values) {
		this.sensors = sensors;
		this.names = names;
		this.values = values;
	}

	public SensorTableModel()
	{
		this(new String[0], new String[0], new Double[0]);
		
	}
	
	public void updateData(String[] sensors, String[] names, Double[] values)
	{
		this.sensors = sensors;
		this.names = names;
		this.values = values;
	}
	
	// Returns a constant columns number for this model
	public int getColumnCount() {
		return Constants.COLUMN_CLASSES.length;
	}

	// Returns the number of files in directory
	public int getRowCount() {
		return names.length;
	}

	//Returns the name of the given column index
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class<?> getColumnClass(int col) {
		return columnClasses[col];
	}

	// Returns the value of each cell
	public Object getValueAt(int row, int col) {
		TableColumn tableColumn = TableColumn.fromIndex(col);
		switch (tableColumn) {
		case SENSOR:
			return sensors[row];
		case NAME:
			return names[row];
		case VALUE:
			return values[row];
		default:
			return null;
		}
	}
}

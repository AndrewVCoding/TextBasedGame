package TABLES;

import javax.swing.table.AbstractTableModel;

public class GameTableModel extends AbstractTableModel
{
	private String[] columnNames;
	private Object[][] data;

	@Override
	public int getRowCount()
	{
		return data.length;
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data[rowIndex][columnIndex];
	}

	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col)
	{
		return col != 0;
	}

	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}

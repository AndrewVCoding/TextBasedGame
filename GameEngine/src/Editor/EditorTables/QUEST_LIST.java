package Editor.EditorTables;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class QUEST_LIST extends AbstractTableModel
{
	private String[] columnNames = {"Name", "Id", "Steps"};
	private Object[][] data = new Object[1][3];

	public QUEST_LIST(Object[][] dataIn)
	{
		data = dataIn;
	}

	public void updateData(Object[][] dataIn)
	{
		data = dataIn;
		fireTableDataChanged();
	}

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

	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col)
	{
		return col == 0;
	}

	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}

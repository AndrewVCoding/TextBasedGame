package Editor;

import javax.swing.table.AbstractTableModel;

public class AttributeTableModel extends AbstractTableModel
{
	private String[] columnNames = {"Type", "ID", "Instance", "Name"};
	private Object[][] data = new Object[][]{};

	public AttributeTableModel(Object[][] dataIn)
	{
		updateData(dataIn);
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
		try
		{
			return data[rowIndex][columnIndex];
		}catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Could not getValueAt(" + rowIndex + ", " + columnIndex + ")");
		}
		return null;
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

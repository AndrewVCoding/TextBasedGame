package Data;

import TABLES.GameTableModel;

import java.util.List;

public class DataTable extends GameTableModel
{
	public String[] columnNames;
	public List<Object[]> data;

	public void addData(DataTable dataIn)
	{
		for(Object[] row : dataIn.data)
			data.add(row);
	}
}
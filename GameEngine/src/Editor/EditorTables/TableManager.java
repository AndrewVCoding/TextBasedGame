package Editor.EditorTables;

import Data.DataLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableManager
{
	Dimension TABLESIZE = new Dimension(300, 600);

	MODULE_LIST SCENARIOS;
	ROOM_LIST ROOMS;
	ITEM_LIST ITEMS;
	CHARACTER_LIST CHARACTERS;
	FACTION_LIST FACTIONS;

	List<Object[]> SCENARIO_DATA = new ArrayList<>();
	List<Object[]> ROOM_DATA = new ArrayList<>();
	List<Object[]> ITEM_DATA = new ArrayList<>();
	List<Object[]> CHARACTER_DATA = new ArrayList<>();
	List<Object[]> FACTION_DATA = new ArrayList<>();

	public TableManager()
	{

	}

	public Object[][] getTableData(List<Object[]> rowList, int rowLength)
	{
		Object[][] output = new Object[rowList.size()][rowLength];

		int i = 0;
		for(Object[] row : rowList)
		{
			output[i] = row;
			i++;
		}

		return output;
	}

	public Object[][] addModule(String name)
	{
		SCENARIO_DATA.add(new Object[]{name, 0, 0, 0, 0, 0});
		return getTableData(SCENARIO_DATA, 6);
	}

	public Object[][] addRoom(String name)
	{
		String id = getNextID("R");
		String instance = getNextInstance(id);
		SCENARIO_DATA.add(new Object[]{id, "NAME", instance});
		return getTableData(SCENARIO_DATA, 3);
	}

	public void setColumnWidth(JTable table, int width)
	{
		table.setMaximumSize(TABLESIZE);
		for(int i = 0; i < table.getColumnCount(); i++)
		{
			table.getColumnModel().getColumn(i).setMaxWidth(width);
		}
	}

	private String getNextID(String prefix)
	{
		int i = 0;
		if(prefix.equals("R"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals("R" + String.format("%05d", i)))
					return "R" + String.format("%05d", i);
		}
		else if(prefix.equals("I"))
		{
			for(Object[] row : ITEM_DATA)
				if(!row[0].equals("I" + String.format("%05d", i)))
					return "I" + String.format("%05d", i);
		}
		else if(prefix.equals("C"))
		{
			for(Object[] row : CHARACTER_DATA)
				if(!row[0].equals("C" + String.format("%05d", i)))
					return "C" + String.format("%05d", i);
		}
		else if(prefix.equals("F"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals("F" + String.format("%05d", i)))
					return "F" + String.format("%05d", i);

		}
		else if(prefix.equals("Q"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals("Q" + String.format("%05d", i)))
					return "Q" + String.format("%05d", i);
		}
		return prefix + "00000";
	}

	//todo Work on instancing objects
	private String getNextInstance(String id)
	{
		int i = 0;
		if(id.matches("R\\d{5}"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals(id))
					i++;
		}
		else if(id.matches("I\\d{5}"))
		{
			for(Object[] row : ITEM_DATA)
				if(!row[0].equals("I" + String.format("%05d", i)))
					i++;
		}
		else if(id.matches("C\\d{5}"))
		{
			for(Object[] row : CHARACTER_DATA)
				if(!row[0].equals("C" + String.format("%05d", i)))
					i++;
		}
		else if(id.matches("F\\d{5}"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals("F" + String.format("%05d", i)))
					i++;

		}
		else if(id.matches("Q\\d{5}"))
		{
			for(Object[] row : ROOM_DATA)
				if(!row[0].equals("Q" + String.format("%05d", i)))
					i++;
		}
		return String.format("%06d", i);
	}
}

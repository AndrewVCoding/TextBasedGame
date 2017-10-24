package Editor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GlobalGameConstants
{

	public static Object[][] TEST_ENTITY_DATA = new Object[][]{{"ROOM", "R000", "room 0"},{"ROOM", "R001", "room 1"},{"ROOM", "R002", "room 2"},
															   {"ROOM", "R003", "room 3"},{"ROOM", "R004", "room 4"},{"ROOM", "R005", "room 5"},
															   {"ITEM", "I000", "item 0"},{"ITEM", "I001", "item 1"},{"ITEM", "I002", "item 2"},
															   {"ITEM", "I003", "item 3"},{"ITEM", "I004", "item 4"},{"ITEM", "I005", "item 5"},
															   {"SPEC", "S000", "spec 0"},{"SPEC", "S001", "spec 1"},{"SPEC", "S002", "spec 2"},
															   {"SPEC", "S003", "spec 3"},{"SPEC", "S004", "spec 4"},{"SPEC", "S005", "spec 5"},
															   {"PROF", "P000", "prof 0"},{"PROF", "P001", "prof 1"},{"PROF", "P002", "prof 2"},
															   {"PROF", "P003", "prof 3"},{"PROF", "P004", "prof 4"},{"PROF", "P005", "prof 5"},
															   {"CREA", "C000", "crea 0"},{"CREA", "C001", "crea 1"},{"CREA", "C002", "crea 2"},
															   {"CREA", "C003", "crea 3"},{"CREA", "C004", "crea 4"},{"CREA", "C005", "crea 5"},
															   {"CHAR", "CH000", "char 0"},{"CHAR", "CH001", "char 1"},{"CHAR", "CH002", "char 2"},
															   {"CHAR", "CH003", "char 3"},{"CHAR", "CH004", "char 4"},{"CHAR", "CH005", "char 5"}};
	public static String[] ENTITY_TYPE_FILTERS = new String[]{"All", "Rooms", "Items", "Species", "Professions", "Creatures", "Characters"};
	public static Dimension DESCRIPTION_TXT_AREA_SIZE = new Dimension(500,200);

	public static String getFilter(String filter)
	{
		if(filter.equals("Rooms"))
			return "ROOM";
		else if(filter.equals("Items"))
			return "ITEM";
		else if(filter.equals("Species"))
			return "SPEC";
		else if(filter.equals("Professions"))
			return "PROF";
		else if(filter.equals("Creatures"))
			return "CREA";
		else if(filter.equals("Characters"))
			return "CHAR";
		else
			return ".*";
	}

	public static List<Object[]> getTestModuleData()
    {
        List<Object[]> output = new ArrayList<>();
        output.add(new Object[]{"test mod 1", "000", "test"});
        output.add(new Object[]{"test mod 2", "001", "test"});
        output.add(new Object[]{"test mod 3", "002", "test"});

        return output;
    }
}

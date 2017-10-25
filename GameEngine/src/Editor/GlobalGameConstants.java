package Editor;

import java.awt.*;

public class GlobalGameConstants
{
	// GUI constants
	public static String[]  ENTITY_TYPE_FILTERS         = {"Filter by...", "Species", "Professions", "Rooms", "Items", "Containers", "Characters", "Monsters"};
	public static String[]  MODULE_FILTERS              = {"Filter by...", "Core"};
	public static Dimension DESCRIPTION_TXT_AREA_SIZE   = new Dimension(500, 200);
	public static Dimension COMBOBOX_SIZE               = new Dimension(140, 20);
	// Entity COLUMNS
	public static String[]  COLUMNS_SPECIES             = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_PROFESSIONS         = {"Module", "ID", "Name"};
	public static String[]  COLUMNS_ROOMS               = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_ITEMS               = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_CONTAINERS          = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_CHARACTERS          = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_MONSTERS            = {"Module", "ID", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_INSTANCE_ROOMS      = {"Module", "ID", "Instance", "Name", "Aliases", "Description"};
	public static String[]  COLUMNS_INSTANCE_ITEMS      = {"Module", "ID", "Instance", "Name", "Aliases", "Description", "Uses"};
	public static String[]  COLUMNS_INSTANCE_CONTAINERS = {"Module", "ID", "Instance", "Name", "Aliases", "Description", "Key", "Locked"};
	public static String[]  COLUMNS_INSTANCE_CHARACTERS = {"Module", "ID", "Instance", "Name", "Aliases", "Description", "Health", "Energy"};
	public static String[]  COLUMNS_INSTANCE_MONSTERS   = {"Module", "ID", "Instance", "Name", "Aliases", "Description", "Health", "Energy"};
	public static String[]  COLUMNS_DESCRIBED_BY        = {"Module", "Entity", "Instance", "Description", "Inspect", "Size", "Weight", "Color", "Texture", "Species"};
	public static String[]  COLUMNS_CONTAINS            = {"Module", "Container", "Instance", "Content_key", "Content_name", "Quantity"};
	public static String[]  COLUMNS_CONNECTS_TO         = {"Module", "Source", "Destination_id", "Aliases", "Description"};
	public static String[]  COLUMNS_USE                 = {"Module", "Entity", "Target_id", "phrases", "Effects"};
	public static String[]  COLUMNS_ACTIVATES           = {"Module", "Entity", "Target_id", "phrases", "Effects", "Description"};
	public static String[]  COLUMNS_AFFECTS             = {"Module", "Entity", "Target_id", "Target_attribute", "Value"};
	public static String[]  COLUMNS_HAS_ATTRIBUTES      = {"Module", "Entity", "Entity_instance", "Health", "Energy", "Cleverness", "Intelligence", "Diplomacy", "Will", "Strength", "Dexterity",
														   "Acrobatics"};
	public static String[]  COLUMNS_TAGS                = {"Module", "Module", "Tags"};

	public static String getFilter(String filter)
	{
		if(filter.equals("Species"))
			return "SPEC";
		else if(filter.equals("Professions"))
			return "PROF";
		else if(filter.equals("Rooms"))
			return "ROOM";
		else if(filter.equals("Items"))
			return "ITEM";
		else if(filter.equals("Containers"))
			return "CONT";
		else if(filter.equals("Characters"))
			return "CHAR";
		else if(filter.equals("Monsters"))
			return "MONS";
		else
			return ".*";
	}
}

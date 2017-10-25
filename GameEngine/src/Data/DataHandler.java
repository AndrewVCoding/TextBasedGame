package Data;

import java.util.ArrayList;
import java.util.List;

public class DataHandler
{
	// Entities
    public DataTable SPECIES;
    public DataTable ROOMS;
    public DataTable ITEMS;
	public DataTable CONTAINERS;
	public DataTable CHARACTERS;
	public DataTable MONSTERS;

	// Instances
	public DataTable INSTANCE_ROOM;
	public DataTable INSTANCE_ITEM;
	public DataTable INSTANCE_CONTAINER;
	public DataTable INSTANCE_CHARACTER;
	public DataTable INSTANCE_MONSTER;

	// Relations
	public DataTable DESCRIPTIONS;
	public DataTable CONTAINS;
	public DataTable CONNECTS_TO;
	public DataTable USED_WITH;
	public DataTable ACTIVATES;
	public DataTable AFFECTS;
	public DataTable HAS_STATS;
	public DataTable TAGS;

	public String RESOURCES;
	public List<String> WORLDS;
	public List<Object[]> MODULES;
	public String SELECTED_WORLD;
	/**
	 * Loads the list of Worlds
	 * @param resources The folder containing the world files
	 */
	public DataHandler(String resources)
	{
		WORLDS = DataLoader.listFiles(resources);
		RESOURCES = resources + "\\resources\\";
	}
	/**
	 * Loads all modules from the specified world
	 * @param world A world folder
	 */
	public void setWorld(String world)
	{
		SELECTED_WORLD = RESOURCES + world + "\\";
		MODULES = new ArrayList<>();
		List<String> names = DataLoader.listFiles(SELECTED_WORLD);
		for(int i = 0; i < names.size(); i++)
			MODULES.add(new Object[]{names.get(i)});
	}

	public void loadModule(String module)
    {
    	// Load all prototype entities in the module
		SPECIES = DataLoader.loadData(module + "\\species");
		ROOMS = DataLoader.loadData(module + "\\rooms");
		ITEMS = DataLoader.loadData(module + "\\items");
		CONTAINERS = DataLoader.loadData(module + "\\containers");
		CHARACTERS = DataLoader.loadData(module + "\\characters");
		MONSTERS = DataLoader.loadData(module + "\\monsters");

		//Load all instances of entities in the module
		INSTANCE_ROOM = DataLoader.loadData(module + "\\instances\\rooms");
		INSTANCE_ITEM = DataLoader.loadData(module + "\\instances\\items");
		INSTANCE_CONTAINER = DataLoader.loadData(module + "\\instances\\containers");
		INSTANCE_CHARACTER = DataLoader.loadData(module + "\\instances\\characters");
		INSTANCE_MONSTER = DataLoader.loadData(module + "\\instances\\monsters");

		//Load all relationship tables in the module
		DESCRIPTIONS = DataLoader.loadData(module + "\\relations\\descriptions");
		CONTAINS = DataLoader.loadData(module + "\\relations\\contains");
		CONNECTS_TO = DataLoader.loadData(module + "\\relations\\connects_to");
		USED_WITH = DataLoader.loadData(module + "\\relations\\used_with");
		ACTIVATES = DataLoader.loadData(module + "\\relations\\activates");
		AFFECTS = DataLoader.loadData(module + "\\relations\\affects");
		HAS_STATS = DataLoader.loadData(module + "\\relations\\has_stats");
		TAGS = DataLoader.loadData(module + "\\resources\\tags");
    }

    public void saveModule(String module)
	{
		// Save all prototype entities in the module
		DataLoader.saveData(SPECIES, module + "\\species");
		DataLoader.saveData(ROOMS, module + "\\rooms");
		DataLoader.saveData(ITEMS, module + "\\items");
		DataLoader.saveData(CONTAINERS, module + "\\containers");
		DataLoader.saveData(CHARACTERS, module + "\\characters");
		DataLoader.saveData(MONSTERS, module + "\\monsters");

		// Save all instances of entities in the module
		DataLoader.saveData(INSTANCE_ROOM, module + "\\instances\\rooms");
		DataLoader.saveData(INSTANCE_ITEM, module + "\\instances\\items");
		DataLoader.saveData(INSTANCE_CONTAINER, module + "\\instances\\containers");
		DataLoader.saveData(INSTANCE_CHARACTER, module + "\\instances\\characters");
		DataLoader.saveData(INSTANCE_MONSTER, module + "\\instances\\monsters");

		// Save all relationship tables in the module
		DataLoader.saveData(DESCRIPTIONS, module + "\\relations\\descriptions");
		DataLoader.saveData(CONTAINS,module + "\\relations\\contains");
		DataLoader.saveData(CONNECTS_TO,module + "\\relations\\connects_to");
		DataLoader.saveData(USED_WITH,module + "\\relations\\used_with");
		DataLoader.saveData(ACTIVATES, module + "\\relations\\activates");
		DataLoader.saveData(AFFECTS, module + "\\relations\\affects");
		DataLoader.saveData(HAS_STATS, module + "\\relations\\has_stats");
		DataLoader.saveData(TAGS, module + "\\relations\\tags");
	}

	public void createWorld(String name)
	{

	}

    /**
     * Return a tuple from a table
     * @param key The key of the tuple
     * @param table The table to look for the tuple in
     * @return
     */
	public Object[] getEntity(String key, List<Object[]> table)
    {
        for (Object[] module:table)
        {
            if (module[0].toString().equals(key))
                return module;
        }
        return null;
    }

	/**
	 * Returns the data from List<Object[]> as Object[][]
	 * @param data
	 * @return
	 */
	public Object[][] getData(List<Object[]> data)
    {
    	try
		{
			Object[][] output = new Object[data.size()][data.toArray().length];

			for(int row = 0; row < data.size(); row++)
				for(int col = 0; col < data.toArray().length; col++)
					output[row][col] = data.get(row)[col];

			return output;
		}catch(NullPointerException n)
		{
			System.out.println("No existing data to load");
		}
		return new Object[][]{};
    }

	/**
	 * Returns the data from a DataTable as a 2D Object array
	 * @param dataTable
	 * @return
	 */
	public Object[][] getData(DataTable dataTable)
	{
		return getData(dataTable.data);
	}
}

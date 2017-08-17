import java.util.List;

public class World
{
	public static List<Item> ITEMS;
	public static List<Room> ROOMS;
	public static List<Container> CONTAINERS;
	public static List<String> EXITS;
	public static Player PLAYER;
	public static Room STARTING_ROOM;
	public static Map MAP;

	public static void buildWorld(String startingRoom)
	{
		//Load all of the base information
		DataHandler.loadAllBaseFiles();

		//Set the starting room
		STARTING_ROOM = getRoom(startingRoom);

		//Build out the map of the rooms
		MAP.build(EXITS, ROOMS);
	}

	/**
	 * @param ID the ID of the room you want
	 * @return
	 */
	public static Room getRoom(String ID)
	{
		if(ID.equals("start"))
			return STARTING_ROOM;
		for(Room room : ROOMS)
			if(room.ID.equalsIgnoreCase(ID))
				return room;
		Interface.display(Interface.DISPLAY + "I don't know how to get there.");
		return null;
	}

	/**
	 * @param ID the ID of the item you want
	 * @return
	 */
	public static Item getItem(String ID)
	{
		for(Item item : ITEMS)
			if(item.NAME.equalsIgnoreCase(ID))
				return item;
		Interface.display(Interface.DISPLAY + "I don't see that here");
		return null;
	}

	/**
	 * @param ID the ID of the container you want
	 * @return
	 */
	public static Container getContainer(String ID)
	{
		for(Container container : CONTAINERS)
			if(container.NAME.equalsIgnoreCase(ID))
				return container;
		Interface.display(Interface.DISPLAY + "I don't see that here");
		return null;
	}

	public static String getIDName(String id)
	{
		if(id.matches("R-[\\d]*"))
			return getRoom(id).NAME;
		else if(id.matches("I-[\\d]*"))
			return getItem(id).NAME;
		else if(id.matches("C-[\\d]*"))
			return getContainer(id).NAME;
		else
			return "NILL";
	}
}

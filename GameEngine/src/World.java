import java.util.ArrayList;
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
	 * @param instance the ID of the room you want
	 * @return
	 */
	public static Room getRoom(String instance)
	{
		if(instance.equals("start"))
			return STARTING_ROOM;
		for(Room room : ROOMS)
			if(room.ID.equalsIgnoreCase(instance))
				return room;
		Interface.display(Interface.DISPLAY + "I don't know how to get there.");
		return null;
	}

	/**
	 * @param instance the ID of the item you want
	 * @return
	 */
	public static Item getItem(String instance)
	{
		for(Item item : ITEMS)
			if(item.NAME.equalsIgnoreCase(instance))
				return item;
		Interface.display(Interface.DISPLAY + "I don't see that here");
		return null;
	}

	/**
	 * @param instance the ID of the container you want
	 * @return
	 */
	public static Container getContainer(String instance)
	{
		for(Container container : CONTAINERS)
			if(container.NAME.equalsIgnoreCase(instance))
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

	public static boolean isVisible(Item item)
	{
		for(ContentSlot slot : getVisibleSlots())
			for(String id : slot.CONTENT_INSTANCES)
				if(item.ID.equals(id))
					return true;
		return false;
	}

	public static boolean isVisible(Container container)
	{
		for(ContentSlot slot : getVisibleSlots())
			for(String id : slot.CONTENT_INSTANCES)
				if(container.ID.equals(id))
					return true;
		return false;
	}

	public static boolean isAccessible(Item item)
	{
		for(ContentSlot slot : getAccessibleSlots())
			for(String id : slot.CONTENT_INSTANCES)
				if(item.ID.equals(id))
					return true;
		return false;
	}

	public static boolean isAccessible(Container container)
	{
		for(ContentSlot slot : getAccessibleSlots())
			for(String id : slot.CONTENT_INSTANCES)
				if(container.ID.equals(id))
					return true;
		return false;
	}

	/**
	 * @return a list of all items currently visible to the player, in their inventory and in the room with them(outside of containers)
	 */
	public static List<ContentSlot> getVisibleSlots()
	{
		List<ContentSlot> visible = new ArrayList<>();

		try
		{
			for(ContentSlot slot : Player.INVENTORY)
				visible.add(slot);
			for(ContentSlot slot : Player.LOCATION.CONTENTS)
				visible.add(slot);

		} catch(NullPointerException e)
		{

		}

		return visible;
	}

	/**
	 * @return a list of all items currently accessible to the player, in their inventory, the room, and in containers
	 */
	public static List<ContentSlot> getAccessibleSlots()
	{
		List<ContentSlot> accessible = new ArrayList<>();

		try
		{
			//Get all visible IDs
			accessible = getVisibleSlots();
			//If any containers are visible and open, add the IDs of their contents
			for(ContentSlot slot : accessible)
				for(String id : slot.CONTENT_INSTANCES)
					if(id.matches("C-\\d*"))
					{
						Container container = World.getContainer(id);
						if(container.OPEN)
							for(ContentSlot cs : container.CONTENTS)
								accessible.add(cs);
					}

		} catch(NullPointerException e)
		{

		}

		return accessible;
	}

	/**
	 * @return a list of all rooms currently connected to the room the player is in
	 */
	public static List<Room> getVisibleExits()
	{
		List<Room> visible = new ArrayList<>();

		try
		{
			for(String s : Player.LOCATION.EXITS)
				visible.add(StaticWorld.getRoom(s));
		} catch(NullPointerException e)
		{
		}

		return visible;
	}

	public static List<Container> getVisibleContainers()
	{
		List<Container> visible = new ArrayList<>();

		try
		{
			for(ContentSlot slot : getVisibleSlots())
				for(String id : slot.CONTENT_INSTANCES)
					if(id.matches("C-\\d*"))
						visible.add(World.getContainer(id));
		} catch(NullPointerException e)
		{
		}

		return visible;
	}

	public static List<ContentSlot> getAccesibleSlotsNamed(String name)
	{
		List<ContentSlot> output = new ArrayList<>();
		for(ContentSlot slot : World.getAccessibleSlots())
			if(slot.referenced(name))
				output.add(slot);
		return output;
	}
}

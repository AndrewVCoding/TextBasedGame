import java.util.ArrayList;
import java.util.List;

public class World
{
	public static EntityManager ENTITY_MANAGER;
	public static List<Entity> ENTITIES;
	public static List<Room> ROOMS = new ArrayList<>();
	public static List<String> EXITS = new ArrayList<>();
	public static Room STARTING_ROOM;
	public static Map MAP;

	public static void buildWorld(String startingRoom)
	{
		//Load all of the base information
		ENTITIES = DataHandler.loadAllBaseFiles("test");

		//populate the entity manager
		ENTITY_MANAGER = new EntityManager(ENTITIES);

		//Set the starting room
		STARTING_ROOM = getRoom(startingRoom);

		//Build out the map of the rooms
		MAP = new Map();
		MAP.build(EXITS, ROOMS);
		MAP.print();
	}

	/**
	 * @param id the ID of the room you want
	 * @return Room with the specified ID
	 */
	public static Room getRoom(String id)
	{
		if(id.equals("start"))
			return STARTING_ROOM;
		for(Room room : ROOMS)
			if(room.ID.equalsIgnoreCase(id))
				return room;
		Interface.display(Interface.DISPLAY + "I don't know how to get there.");
		return null;
	}

	/**
	 * @param key the "ID:Instance" of the item you want
	 * @return Item with specified key
	 */
	public static Item getItem(String key)
	{
		return ENTITY_MANAGER.getItem(key);
	}

	/**
	 * @param key the "ID:Instance" of the container you want
	 * @return Container with specified key
	 */
	public static Container getContainer(String key)
	{
		return ENTITY_MANAGER.getContainer(key);
	}

	public static String getIDName(String id)
	{
		try
		{
			if(id.matches("R-[\\d]*"))
				return getRoom(id).NAME;
			else if(id.matches("I-[\\d]*"))
				return getItem(id).NAME;
			else if(id.matches("C-[\\d]*"))
				return getContainer(id).NAME;
		} catch(NullPointerException e)
		{
			return "NILL";
		}
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
			visible.addAll(Player.INVENTORY);
			visible.addAll(Player.LOCATION.CONTENTS);

		} catch(NullPointerException e)
		{
			System.out.println("Could not get visible slots");
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
							accessible.addAll(container.CONTENTS);
					}

		} catch(NullPointerException e)
		{
			System.out.println("Could not get accessible slots");
		}

		return accessible;
	}

	/**
	 * @return a list of all rooms currently connected to the room the player is in
	 */
	public static List<Room> getVisibleExits()
	{
		return MAP.getExits();
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
			System.out.println("Could not get visible containers");
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

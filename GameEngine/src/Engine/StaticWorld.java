package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * A prebuilt set of Engine.World data for effect in testing the game while developing.
 * Creates all items, containers, and rooms.
 */
class StaticWorld
{
	final private static List<Room> ROOMS = new ArrayList<>();
	final private static List<String> PATHS = new ArrayList<>();
	final private static List<Entity> ENTITIES = new ArrayList<>();
	final private static List<Item> ITEMS = new ArrayList<>();
	final private static List<Container> CONTAINERS = new ArrayList<>();

	public static void buildWorld()
	{
//		Engine.Item pocketKnife = new Engine.Item("pocket knife", "I-000000", "000000", "A small folding knife", "none",
//									"You carefully slip the pocket knife into your pocket", true, false,
//									new String[]{"attack", "2"});
//
//		Engine.Item coldPizza = new Engine.Item("cold pizza", "I-000001", "000000", "A slice of cold pizza. Who knows how old it is",
//								  "none", "You stuff the greasy slice of pizza into your pocket", true, false,
//								  new String[]{"consume", "self", "health", "5",
//											   "You eat the slice, savoring each bite of chilly, cheesy goodness."});
//
//		Engine.Item remote = new Engine.Item("remote", "I-000002", "000000", "The tv remote", "none",
//							   "You put the remote in your pocket", true, false, new String[]{"effect", "remote", "tv",
//																							  "The tv powers on, casting a soft glow across the room",
//																							  "The screen on the tv grows dark, leaving the room darker than before"});
//
//		Engine.Item television = new Engine.Item("tv", "I-000003", "000000",
//								   "The tv is currently turned off, but you don't want to walk all the way over to it to turn it on.",
//								   "A soft glow emanates from the tv, as it displays current events and news stories about current events.",
//								   "The tv is too heavy to lift and wouldn't fit in your pockets anyways", false, false,
//								   new String[]{"act", "self", "You don't feel like walking up to the tv",
//												"You don't feel like walking up to the tv"});
//
//		Engine.Item socks = new Engine.Item("socks", "I-000004", "000000", "A pair of clean socks, neatly folded.", "none",
//							  "You pick up the socks and put them in your pocket", true, false, new String[]{"none"});
//
//		ENTITIES.add(pocketKnife);
//		ENTITIES.add(coldPizza);
//		ENTITIES.add(remote);
//		ENTITIES.add(television);
//		ENTITIES.add(socks);
//
//		Engine.Container drawers = new Engine.Container("drawers", "C-000000", "000000", "The drawers next to your bed are closed",
//										  "The drawer hangs open, allowing you to see inside", "none", false, false,
//										  new List<String[]>{new String[]{"none"}}, "none", "none", "You slide the drawer open to reveal:",
//										  "You push the drawer closed", "k-000000", false, false);
//		drawers.add(socks);
//
//		ENTITIES.add(drawers);
//
//		Engine.Room livingRoom = new Engine.Room();
//		livingRoom.NAME = "living room";
//		livingRoom.ID = "R-000000";
//		livingRoom.ENTRANCE = "You walk into the living room.";
//		livingRoom.DESCRIPTION = "This is your living room. It's very comfy.";
//		livingRoom.LOOK = "You look around your living room, taking note of any objects that might be useful.";
//		livingRoom.EXITS.add("kitchen");
//		livingRoom.EXITS.add("bedroom");
//		livingRoom.EXITS.add("bathroom");
//		livingRoom.add(television);
//		livingRoom.add(remote);
//
//		Engine.Room kitchen = new Engine.Room();
//		kitchen.NAME = "kitchen";
//		kitchen.ID = "R-000001";
//		kitchen.ENTRANCE = "You enter your kitchen.";
//		kitchen.DESCRIPTION = "This is your kitchen. You briefly wonder if you should eat something.";
//		kitchen.LOOK = "You look at the messy kitchen, briefly wondering how you can stand to prepare your meals in such a disgusting environment";
//		kitchen.EXITS.add("living room");
//		kitchen.add(coldPizza);
//
//		Engine.Room bedroom = new Engine.Room();
//		bedroom.NAME = "bedroom";
//		bedroom.ID = "R-000002";
//		bedroom.ENTRANCE = "You enter your bedroom.";
//		bedroom.DESCRIPTION = "This is your bedroom. You wish you were still in bed.";
//		bedroom.LOOK = "Your bedroom is immaculately clean, even all of your book shelves are dusted and organized";
//		bedroom.EXITS.add("living room");
//		bedroom.EXITS.add("bathroom");
//		bedroom.add(pocketKnife);
//		bedroom.add(drawers);
//
//		Engine.Room bathroom = new Engine.Room();
//		bathroom.NAME = "bathroom";
//		bathroom.ID = "R-000003";
//		bathroom.ENTRANCE = "You enter your bathroom.";
//		bathroom.DESCRIPTION = "This is your bathroom. You briefly wonder if you should eat something.";
//		bathroom.LOOK = "What is there to say, it's just a smelly bathroom. The shower is clean, but the toilet seat...";
//		bathroom.EXITS.add("bedroom");
//
//		Engine.World.ROOMS.add(livingRoom);
//		Engine.World.ROOMS.add(kitchen);
//		Engine.World.ROOMS.add(bathroom);
//		Engine.World.ROOMS.add(bedroom);
//
//		Engine.World.PATHS.add("R-000000|R-000001");
//		Engine.World.PATHS.add("R-000001|R-000000");
//		Engine.World.PATHS.add("R-000000|R-000002");
//		Engine.World.PATHS.add("R-000002|R-000000");
//		Engine.World.PATHS.add("R-000003|R-000000");
//		Engine.World.PATHS.add("R-000000|R-000003");
//		Engine.World.PATHS.add("R-000003|R-000002");
//		Engine.World.PATHS.add("R-000002|R-000003");
	}

	public static void buildPaths()
	{
//		Engine.World.PATHS.add("R-000000|R-000001");
//		Engine.World.PATHS.add("R-000001|R-000000");
//		Engine.World.PATHS.add("R-000000|R-000002");
//		Engine.World.PATHS.add("R-000002|R-000000");
//		Engine.World.PATHS.add("R-000003|R-000000");
//		Engine.World.PATHS.add("R-000000|R-000003");
//		Engine.World.PATHS.add("R-000003|R-000002");
//		Engine.World.PATHS.add("R-000002|R-000003");
	}

	public static List<Entity> getEntities()
	{
		return ENTITIES;
	}

	public static Room getStartingRoom()
	{
		return ROOMS.get(0);
	}

	public static Room getRoom(String name)
	{
		for(Room room : ROOMS)
			if(room.NAME.equalsIgnoreCase(name))
				return room;
		Interface.display(Interface.DISPLAY + "I don't see a way to get there.");
		return null;
	}

	public static Item getItem(String name)
	{
		for(Item item : ITEMS)
			if(item.NAME.equalsIgnoreCase(name))
				return item;
		Interface.display(Interface.DISPLAY + "I don't see that here");
		return null;
	}

	public static Container getContainer(String name)
	{
		for(Container container : CONTAINERS)
			if(container.NAME.equalsIgnoreCase(name))
				return container;
		Interface.display(Interface.DISPLAY + "I don't see that here");
		return null;
	}
}

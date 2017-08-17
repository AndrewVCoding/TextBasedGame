import java.util.ArrayList;
import java.util.List;

class StaticWorld
{
	final private static List<Room> ROOMS = new ArrayList<>();
	final private static List<Item> ITEMS = new ArrayList<>();
	final private static List<Container> CONTAINERS = new ArrayList<>();

	public static void buildWorld()
	{
		Item pocketKnife = new Item();
		pocketKnife.NAME = "pocket knife";
		pocketKnife.DESCRIPTION = "A small folding knife";
		pocketKnife.PICKUP = "You carefully slip the pocket knife into your pocket";
		pocketKnife.TAKEABLE = true;
		pocketKnife.EFFECTS = new String[]{"attack", "2"};

		Item coldPizza = new Item();
		coldPizza.NAME = "cold pizza";
		coldPizza.DESCRIPTION = "A slice of cold pizza. Who knows how old it is";
		coldPizza.PICKUP = "You stuff the greasy slice of pizza into your pocket";
		coldPizza.TAKEABLE = true;
		coldPizza.EFFECTS = new String[]{"consume", "self","health", "5", "You eat the slice, savoring each bite of chilly, cheesy goodness."};

		Item remote = new Item();
		remote.NAME = "remote";
		remote.DESCRIPTION = "The tv remote";
		remote.PICKUP = "You put the remote in your pocket";
		remote.TAKEABLE = true;
		remote.EFFECTS = new String[]{"use", "remote","tv", "The tv powers on, casting a soft glow across the room", "The screen on the tv grows dark, leaving the room darker than before"};

		Item television = new Item();
		television.NAME = "tv";
		television.DESCRIPTION = "The tv is currently turned off, but you don't want to walk all the way over to it to turn it on.";
		television.PICKUP = "The tv is too heavy to lift and wouldn't fit in your pockets anyways";
		television.ACT_DESC = "A soft glow emanates from the tv, as it displays current events and news stories about current events.";
		television.EFFECTS = new String[]{"act", "self", "You don't feel like walking up to the tv", "You don't feel like walking up to the tv"};

		Item socks = new Item();
		socks.NAME = "socks";
		socks.DESCRIPTION = "A pair of clean socks, neatly folded.";
		socks.TAKEABLE = true;
		socks.PICKUP = "You pick up the socks and put them in your pocket";

		ITEMS.add(pocketKnife);
		ITEMS.add(coldPizza);
		ITEMS.add(remote);
		ITEMS.add(television);
		ITEMS.add(socks);

		Container drawers = new Container();
		drawers.NAME = "drawers";
		drawers.DESCRIPTION = "The drawers next to your bed are closed";
		drawers.ACT_DESC = "The drawer hangs open, allowing you to see inside";
		drawers.OPEN_DESC = "You slide the drawer open to reveal:";
		drawers.CLOSE_DESC = "You push the drawer closed";
		drawers.LOCKED = false;
		drawers.CONTENTS.add(socks);

		CONTAINERS.add(drawers);

		Room livingRoom = new Room();
		livingRoom.NAME = "living room";
		livingRoom.ENTRANCE = "You walk into the living room.";
		livingRoom.DESCRIPTION = "This is your living room. It's very comfy.";
		livingRoom.LOOK = "You look around your living room, taking note of any objects that might be useful.";
		livingRoom.EXITS.add("kitchen");
		livingRoom.EXITS.add("bedroom");
		livingRoom.ITEMS.add(television);
		livingRoom.ITEMS.add(remote);

		Room kitchen = new Room();
		kitchen.NAME = "kitchen";
		kitchen.ENTRANCE = "You enter your kitchen.";
		kitchen.DESCRIPTION = "This is your kitchen. You briefly wonder if you should eat something.";
		kitchen.LOOK = "You look at the messy kitchen, briefly wondering how you can stand to prepare your meals in such a disgusting environment";
		kitchen.EXITS.add("living room");
		kitchen.ITEMS.add(coldPizza);

		Room bedroom = new Room();
		bedroom.NAME = "bedroom";
		bedroom.ENTRANCE = "You enter your bedroom.";
		bedroom.DESCRIPTION = "This is your bedroom. You wish you were still in bed.";
		bedroom.LOOK = "Your bedroom is immaculately clean, even all of your book shelves are dusted and organized";
		bedroom.EXITS.add("living room");
		bedroom.EXITS.add("bathroom");
		bedroom.ITEMS.add(pocketKnife);
		bedroom.CONTAINERS.add(drawers);

		Room bathroom = new Room();
		bathroom.NAME = "bathroom";
		bathroom.ENTRANCE = "You enter your bathroom.";
		bathroom.DESCRIPTION = "This is your bathroom. You briefly wonder if you should eat something.";
		bathroom.LOOK = "What is there to say, it's just a smelly bathroom. The shower is clean, but the toilet seat...";
		bathroom.EXITS.add("bedroom");

		ROOMS.add(livingRoom);
		ROOMS.add(kitchen);
		ROOMS.add(bathroom);
		ROOMS.add(bedroom);
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

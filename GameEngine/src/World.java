import java.util.ArrayList;
import java.util.List;

public class World
{
	final private static List<Room> ROOMS = new ArrayList<>();
	final private static List<Item> ITEMS = new ArrayList<>();

	public static void populate()
	{
		Item pocketKnife = new Item();
		pocketKnife.NAME = "pocket knife";
		pocketKnife.DESCRIPTION = "A small folding knife";
		pocketKnife.PICKUP = "You carefully slip the knife into your pocket";
		pocketKnife.EQUIPABLE = true;
		pocketKnife.TAKEABLE = true;
		pocketKnife.EFFECT = new String[]{"attack", "2"};

		Item coldPizza = new Item();
		coldPizza.NAME = "cold pizza";
		coldPizza.DESCRIPTION = "A cold slice of pizza. Who knows how old it is";
		coldPizza.PICKUP = "You stuff the greasy slice of cold pizza into your pocket";
		coldPizza.CONSUMABLE = true;
		coldPizza.TAKEABLE = true;
		coldPizza.EFFECT = new String[]{"heal", "5", "You eat the cold slice, savoring each bite of chilly, cheesy goodness."};

		Item remote = new Item();
		remote.NAME = "remote";
		remote.DESCRIPTION = "The tv remote";
		remote.PICKUP = "You put the remote in your pocket";
		remote.TAKEABLE = true;
		remote.EFFECT = new String[]{"use", "tv", "The tv powers on, casting a soft glow across the room", "The screen on the tv grows dark, leaving the room darker than before"};

		Item television = new Item();
		television.NAME = "tv";
		television.DESCRIPTION = "The tv is currently turned off, but you don't want to walk up to it to turn it on.";
		television.PICKUP = "The tv is too heavy to lift and wouldn't fit in your pockets anyways";
		television.ACT_DESC = "A soft glow eminates from the tv, as it displays current events and news stories about ";

		television.EFFECT = new String[]{"use", "self", "You don't feel like walking up to the tv", "You don't feel like walking up to the tv"};

		ITEMS.add(pocketKnife);
		ITEMS.add(coldPizza);
		ITEMS.add(remote);
		ITEMS.add(television);

		Room livingRoom = new Room();
		livingRoom.NAME = "livingroom";
		livingRoom.ENTRANCE = "You walk into the living room.";
		livingRoom.DESCRIPTION = "This is your living room. It's very comfy.";
		livingRoom.EXITS.add("kitchen");
		livingRoom.EXITS.add("bedroom");
		livingRoom.CONTENTS.add("tv");
		livingRoom.CONTENTS.add("remote");

		Room kitchen = new Room();
		kitchen.NAME = "kitchen";
		kitchen.ENTRANCE = "You enter your kitchen.";
		kitchen.DESCRIPTION = "This is your kitchen. You briefly wonder if you should eat something.";
		kitchen.EXITS.add("livingroom");
		kitchen.CONTENTS.add("cold pizza");

		Room bedroom = new Room();
		bedroom.NAME = "bedroom";
		bedroom.ENTRANCE = "You enter your bedroom.";
		bedroom.DESCRIPTION = "This is your bedroom. You wish you were still in bed.";
		bedroom.EXITS.add("living room");
		bedroom.EXITS.add("bathroom");
		bedroom.CONTENTS.add("pocket knife");

		Room bathroom = new Room();
		bathroom.NAME = "bathroom";
		bathroom.ENTRANCE = "You enter your bathroom.";
		bathroom.DESCRIPTION = "This is your bathroom. You briefly wonder if you should eat something.";
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
		Interface.setDISPLAY("That room does not exist");
		return null;
	}

	public static Item getItem(String name)
	{
		for(Item item : ITEMS)
			if(item.NAME.equalsIgnoreCase(name))
				return item;
		Interface.setDISPLAY("I don't see that here");
		return null;
	}
}

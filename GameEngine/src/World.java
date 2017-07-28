import java.util.ArrayList;
import java.util.List;

public class World
{
	final private static List<Room> ROOMS = new ArrayList<>();

	public static void populate()
	{
		Room livingRoom = new Room();
		livingRoom.NAME = "livingroom";
		livingRoom.ENTRANCE = "You walk into the living room.";
		livingRoom.DESCRIPTION = "This is your living room. It's very comfy.";
		livingRoom.EXITS.add("kitchen");
		livingRoom.EXITS.add("bedroom");

		Room kitchen = new Room();
		kitchen.NAME = "kitchen";
		kitchen.ENTRANCE = "You enter your kitchen.";
		kitchen.DESCRIPTION = "This is your kitchen. You briefly wonder if you should eat something.";
		kitchen.EXITS.add("livingroom");

		Room bedroom = new Room();
		bedroom.NAME = "bedroom";
		bedroom.ENTRANCE = "You enter your bedroom.";
		bedroom.DESCRIPTION = "This is your bedroom. You wish you were still in bed.";
		bedroom.EXITS.add("living room");
		bedroom.EXITS.add("bathroom");

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
		for(Room room: ROOMS)
			if(room.NAME.equalsIgnoreCase(name))
				return room;
		return null;
	}
}

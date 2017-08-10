import java.util.ArrayList;
import java.util.List;

public class GameSystems
{
	/**
	 * Auto starts the game as a prenamed character and class
	 * @param name
	 * @param clas
	 */
	public static void autoStartGame(String name, int clas)
	{
		CommandHandler.command("start");
		CommandHandler.command(name);
		CommandHandler.command("" + clas);
	}

	/**
	 *
	 * @return a list of all items currently visible to the player
	 */
	public static List<Item> getVisibleItems()
	{
		List<Item> visible = new ArrayList<>();

		try
		{
			for(Item i : Player.INVENTORY)
				visible.add(i);
			for(String s : Player.LOCATION.CONTENTS)
				visible.add(World.getItem(s));
		}catch(NullPointerException e)
		{

		}

		return visible;
	}

	public static List<Room> getVisibleExits()
	{
		List<Room> visible = new ArrayList<>();

		try
		{
		for(String s: Player.LOCATION.EXITS)
			visible.add(World.getRoom(s));
		}catch(NullPointerException e)
		{

		}

		return visible;
	}
}

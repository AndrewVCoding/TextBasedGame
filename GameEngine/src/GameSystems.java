import java.util.ArrayList;
import java.util.List;

class GameSystems
{
	public static String GAME_STATE = "start";
	public static String[] COMMAND;

	/**
	 * Auto starts the game as a prenamed character and class
	 *
	 * @param name preset name for an automatic character
	 * @param clas preset class for an automatic character
	 */
	public static void autoStartGame(String name, int clas)
	{
		CommandHandler.command("start");
		CommandHandler.command(name);
		CommandHandler.command("" + clas);
	}

	/**
	 * @return a list of all items currently visible to the player, in their inventory and in the room with them(outside of containers)
	 */
	public static List<Item> getVisibleItems()
	{
		List<Item> visible = new ArrayList<>();

		try
		{
			visible.addAll(Player.INVENTORY);
			visible.addAll(Player.LOCATION.ITEMS);

		} catch(NullPointerException e)
		{

		}

		return visible;
	}

	/**
	 * @return a list of all items currently accessible to the player, in their inventory, the room, and in containers
	 */
	public static List<Item> getAccessibleItems()
	{
		List<Item> accessible = new ArrayList<>();

		try
		{
			accessible.addAll(Player.INVENTORY);
			accessible.addAll(Player.LOCATION.ITEMS);
			for(Container c : Player.LOCATION.CONTAINERS)
				if(c.OPEN)
					accessible.addAll(c.ITEMS);

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
			visible.addAll(Player.LOCATION.CONTAINERS);
		} catch(NullPointerException e)
		{
		}

		return visible;
	}

	/**
	 * When the game is first loaded, the only option is to start the game, which currently goes to create a new character
	 * todo add in load/save world/player options
	 */
	public static void start()
	{
		if(GAME_STATE.equals("start"))
			StartState.start();
		else if(GAME_STATE.equals("idle"))
			Interface.display("Sorry, that command is not available right now.");
		else
			invalid();
	}

	/**
	 * When the command is go XXXXX then move the player to the specified room
	 * todo allow using just N/S/E/W as quick commands, as well as just putting the name of the exit/destination room
	 */
	public static void go()
	{
		if(GAME_STATE.equals("idle"))
			IdleState.go(COMMAND);
		else
			Interface.display("There is nowhere to go");
	}

	/**
	 * displays an overview of the player's current location
	 * todo allow the player to target a specific item to look at
	 */
	public static void look()
	{
		if(GAME_STATE.equals("idle"))
			IdleState.look(COMMAND);
		else
			Interface.display("There is nothing to look at");
	}

	/**
	 * Moves an object to the players inventory
	 */
	public static void take()
	{
		if(GAME_STATE.equals("idle"))
			IdleState.take(COMMAND);
		else
			Interface.display("There is nothing to take");
	}

	/**
	 * If the command is to use/eat/drink an item, find the relevant effects and act on them
	 */
	public static void act()
	{
		if(GAME_STATE.equals("idle"))
			IdleState.act(COMMAND);
		else
			Interface.display("There is nothing to do that to");
	}

	/**
	 * When using an object, array contents are as follows
	 * [   act | source | target | activation description | deactivation description ]
	 * [    0  |    1   |    2   |           3            |              4           ]
	 */
	public static void use(Item source, int index)
	{
		if(GAME_STATE.equals("idle"))
			IdleState.use(source, index, COMMAND);
		else
			Interface.display("There is nothing to use");
	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	public static void unknown(String input)
	{
		String[] command = input.split(" ");

		if(GAME_STATE.equals("character name"))
		{
			Player.NAME = command[0];
			GAME_STATE = "character class";
			Interface.display("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");

		}
		else if(GAME_STATE.equals("character class"))
		{
			if(command[0].equals("1"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Warrior";
				Player.HP = 100;
				Player.MAX_HP = 100;
				Player.moveRoom(StaticWorld.getStartingRoom());

			}
			else if(command[0].equals("2"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Wizard";
				Player.HP = 70;
				Player.MAX_HP = 70;
				Player.moveRoom(StaticWorld.getStartingRoom());

			}
			else if(command[0].equals("3"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Rogue";
				Player.HP = 80;
				Player.MAX_HP = 80;
				Player.moveRoom(StaticWorld.getStartingRoom());
			}
			else
			{
				Interface.display("That is not a valid class choice");
			}

			Interface.INTERACTIONS = "";
		}
		else
			invalid();
	}

	public static void open()
	{
		for(Container container : GameSystems.getVisibleContainers())
			if(container.NAME.equals(COMMAND[1]))
				container.open();
	}

	public static void close()
	{
		for(Container container : GameSystems.getVisibleContainers())
			if(container.NAME.equals(COMMAND[1]))
				container.close();
	}

	public static void invalid()
	{
		Interface.display(COMMAND[1]);
	}
}

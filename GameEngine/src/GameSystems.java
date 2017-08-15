import java.util.ArrayList;
import java.util.List;

public class GameSystems
{
	public static String GAME_STATE = "start";
	public static String[] COMMAND;

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
			for(Item s : Player.LOCATION.ITEMS)
				visible.add(World.getItem(s.NAME));
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

	public static List<Container> getVisibleContainers()
	{
		List<Container> visible = new ArrayList<>();

		for(Container s: Player.LOCATION.CONTAINERS)
			try
			{
				visible.add(World.getContainer(s.NAME));
			}
			catch(NullPointerException e){}

		return visible;
	}

	/**
	 * When the game is first loaded, the only option is to start the game, which currently goes to create a new character
	 * todo add in load/save world/player options
	 */
	public static void start()
	{
		if(GAME_STATE.equals("start"))
		{
			GAME_STATE = "character name";
			Interface.display("Please enter a name for your character");
		}
		else if(GAME_STATE.equals("idle"))
		{
			Interface.display("Sorry, that command is not available right now.");
		}
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
		{
			Player.moveRoom(World.getRoom(COMMAND[1]));
		}
	}

	/**
	 * displays an overview of the player's current location
	 * todo allow the player to target a specific item to look at
	 */
	public static void look()
	{
		if(GAME_STATE.equals("idle"))
		{
			//"look", "inventory"
			if(COMMAND[1].equalsIgnoreCase("inventory"))
				Player.viewInventory();
				//"look", "room"
			else if(COMMAND[1].equals("room"))
				Interface.setDisplay(Player.LOCATION.look());
				//"look", item.NAME
			else
				for(Item item : GameSystems.getVisibleItems())
					if(COMMAND[1].equals(item.NAME))
						Interface.display(item.look());
		}
	}

	/**
	 * Moves an object from the room to the players inventory
	 */
	public static void take()
	{
		if(GAME_STATE.equals("idle"))
		{
			Item target = World.getItem(COMMAND[1]);
			if(target.TAKEABLE)
			{
				Player.LOCATION.ITEMS.remove(target.NAME);
				Player.INVENTORY.add(target);
			}

			Interface.display(target.PICKUP);
		}
	}

	/**
	 * If the command is to use/eat/drink an item, find the relevant effects and act on them
	 */
	public static void act()
	{
		if(COMMAND[1] != null)
		{
			Item source = World.getItem(COMMAND[1]);

			//todo add a System class that deals with item visibility and accessibility in regards to the player

			//The item is usable
			if(source != null)
			{
				String action = COMMAND[0];
				int index = source.effect(action);

				//If the specified action is valid for the item
				if(index > -1)
				{
					if(action.equalsIgnoreCase("use"))
						use(source, index);
					else if(action.equalsIgnoreCase("consume"))
						consume(source, index);
					else
						invalid();
				}
			}
		}
	}

	/**
	 * When using an object, array contents are as follows
	 * [   act | source | target | activation description | deactivation description ]
	 * [    0  |    1   |    2   |           3            |              4           ]
	 */
	public static void use(Item source, int index)
	{
		Item target = World.getItem(COMMAND[2]);

		if(source.EFFECTS[index + 2].equals("player"))
		{
			//Place holder for future items
		}

		else if(source.EFFECTS[index + 2].equals(target.NAME))
		{
			if(!target.ACTIVATED)
				Interface.display(source.EFFECTS[index + 3]);
			if(target.ACTIVATED)
				Interface.display(source.EFFECTS[index + 4]);

			target.ACTIVATED = !target.ACTIVATED;
			System.out.print(target.ACTIVATED);
		}
	}

	/**
	 * When consuming an object, array contents are as follows
	 * [ consume | target | attribute | amount ]
	 * [ index   |    +1  |    +2     |   +3   ]
	 *
	 * @param source
	 */
	public static void consume(Item source, int index)
	{
		System.out.println("consuming " + source.NAME);
		if(source.EFFECTS[index + 2].equalsIgnoreCase("health"))
		{
			Player.HP += Integer.parseInt(source.EFFECTS[index + 3]);
			Interface.display("You were healed for " + source.EFFECTS[index + 3]);
		}
		else
			Interface.display("The item had no effect");
	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	public static void unknown(String input)
	{
		COMMAND = input.split(" ");

		if(GAME_STATE.equals("character name"))
		{
			Player.NAME = COMMAND[0];
			GAME_STATE = "character class";
			Interface.display("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");

		}
		else if(GAME_STATE.equals("character class"))
		{
			if(COMMAND[0].equals("1"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Warrior";
				Player.HP = 100;
				Player.moveRoom(World.getStartingRoom());

			}
			else if(COMMAND[0].equals("2"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Wizard";
				Player.HP = 70;
				Player.moveRoom(World.getStartingRoom());

			}
			else if(COMMAND[0].equals("3"))
			{
				GAME_STATE = "idle";
				Player.CLASS = "Rogue";
				Player.HP = 80;
				Player.moveRoom(World.getStartingRoom());
			}
			else
			{
				Interface.display("That is not a valid class choice");
			}

		}
		else if(GAME_STATE.equals("go"))
		{
			COMMAND = new String[]{"go", COMMAND[0]};
			GAME_STATE = "idle";
			go();
		}
		else
			invalid();
	}

	public static void open()
	{
		//todo this
		for(Container container : GameSystems.getVisibleContainers())
		{
			if(container.NAME.equals(COMMAND[1]))
			{
				//todo
			}
		}
	}

	public static void invalid()
	{
		Interface.display("Sorry, that command is not available now.");
	}
}

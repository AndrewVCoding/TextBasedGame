public class CommandHandler
{
	private static String GAME_STATE = "start";
	private static String[] COMMAND;

	/**
	 * All commands have a unique first word, but can be context sensitive after that.
	 *
	 * @param com The user input command
	 */
	public static void command(String com)
	{
		//First split the command into an array of strings
		COMMAND = CommandParser.parse(com);

		//Check the first word and run the appropriate method
		if(COMMAND[0].equalsIgnoreCase("start"))
			start();
		else if(COMMAND[0].equalsIgnoreCase("go"))
			go();
		else if(COMMAND[0].equalsIgnoreCase("look"))
			look();
		else if(COMMAND[0].equalsIgnoreCase("take"))
			take();
		else if(COMMAND[0].equalsIgnoreCase("inventory"))
			look();
		else if(COMMAND[0].equalsIgnoreCase("use"))
			act();
		else
			unknown(com);

	}

	/**
	 * When the game is first loaded, the only option is to start the game, which currently goes to create a new character
	 * todo add in load/save world/player options
	 */
	private static void start()
	{
		if(GAME_STATE.equals("start"))
		{
			GAME_STATE = "character name";
			Interface.setDISPLAY("Please enter a name for your character");
		}
		else if(GAME_STATE.equals("idle"))
		{
			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Sorry, that command is not available at start.");
		}
		else
			invalid();
	}

	/**
	 * When the command is go XXXXX then move the player to the specified room
	 * todo allow using just N/S/E/W as quick commands, as well as just putting the name of the exit/destination room
	 */
	private static void go()
	{
		if(GAME_STATE.equals("idle"))
		{
			Interface.moveRoom(World.getRoom(COMMAND[1]));
		}
	}

	/**
	 * displays an overview of the player's current location
	 * todo allow the player to target a specific item to look at
	 */
	private static void look()
	{
		if(GAME_STATE.equals("idle"))
		{
			//"look", "inventory"
			if(COMMAND[1].equalsIgnoreCase("inventory"))
				Player.viewInventory();
			//"look", "room"
			else if(COMMAND[1].equals("room"))
				Interface.setDISPLAY(Player.LOCATION.look());
			//"look", item.NAME
			else
				for(Item item : GameSystems.getVisibleItems())
					if(COMMAND[1].equals(item.NAME))
						Interface.setDISPLAY(Interface.DISPLAY + "\n>>" + item.look());
		}
	}

	/**
	 * Moves an object from the room to the players inventory
	 */
	private static void take()
	{
		if(GAME_STATE.equals("idle"))
		{
			Item target = World.getItem(COMMAND[1]);
			if(target.TAKEABLE)
			{
				Player.LOCATION.CONTENTS.remove(target.NAME);
				Player.addINVENTORY(target);
			}

			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + target.PICKUP);
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
					else if(action.equalsIgnoreCase("eat") || action.equalsIgnoreCase("drink"))
						consume(source, index);
					else
						invalid();
				}
			}
		}
	}

	/**
	 * When using an object, array contents are as follows
	 * [   use | source | target | activation description | deactivation description ]
	 * [ index |   +1   |   +2   |          +3            |             +4           ]
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
				Interface.setDISPLAY(Interface.DISPLAY + "\n" + source.EFFECTS[index + 3]);
			if(target.ACTIVATED)
				Interface.setDISPLAY(Interface.DISPLAY + source.EFFECTS[index + 4]);

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
			Interface.setDISPLAY(Interface.DISPLAY + "\nYou were healed for " + source.EFFECTS[index + 3]);
		}
		else
			Interface.setDISPLAY(Interface.DISPLAY + "\nThe item had no effect");
	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	private static void unknown(String input)
	{
		COMMAND = input.split(" ");

		if(GAME_STATE.equals("character name"))
		{
			Player.setNAME(COMMAND[0]);
			GAME_STATE = "character class";
			Interface.setDISPLAY("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");

		}
		else if(GAME_STATE.equals("character class"))
		{
			if(COMMAND[0].equals("1"))
			{
				GAME_STATE = "idle";
				Player.setCLASS("Warrior");
				Player.setHP(100);
				Player.setLOCATION(World.getStartingRoom());
				Interface.setDISPLAY(Player.LOCATION.enter());

			}
			else if(COMMAND[0].equals("2"))
			{
				GAME_STATE = "idle";
				Player.setCLASS("Wizard");
				Player.setHP(70);
				Player.setLOCATION(World.getStartingRoom());
				Player.LOCATION.enter();
				Interface.setDISPLAY(Player.LOCATION.enter());

			}
			else if(COMMAND[0].equals("3"))
			{
				GAME_STATE = "idle";
				Player.setCLASS("Rogue");
				Player.setHP(80);
				Player.setLOCATION(World.getStartingRoom());
				Player.LOCATION.enter();
				Interface.setDISPLAY(Player.LOCATION.enter());
			}
			else
			{
				Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "That is not a valid class choice");
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

	private static void invalid()
	{
		Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Sorry, that command is not available now.");
	}
}

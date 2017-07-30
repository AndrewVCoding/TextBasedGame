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
		COMMAND = com.split(" ");

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
		else
			unknown();

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
			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Sorry, that command is not available now.");
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
			if(COMMAND.length > 1)
			{
				Interface.moveRoom(World.getRoom(COMMAND[1]));
			}
			else
			{
				Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Where do you want to go?");
				GAME_STATE = "go";
			}
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
			if(COMMAND.length > 1)
			{
				if(COMMAND[1].equalsIgnoreCase("inventory"))
					Player.viewInventory();
			}
			else if(COMMAND[0].equalsIgnoreCase("inventory"))
				Player.viewInventory();
			else
				Interface.setDISPLAY(Player.LOCATION.look());
		}
	}

	/**
	 * todo allow the player to target an item and move it from room contents to player's inventory
	 */
	private static void take()
	{
		if(GAME_STATE.equals("idle"))
		{
			String itemName = "";
			for(int i = 1; i < COMMAND.length - 1; i++)
				itemName += COMMAND[i] + " ";

			itemName += COMMAND[COMMAND.length - 1];

			Item target = World.getItem(itemName);
			if(target.TAKEABLE)
			{
				Player.LOCATION.CONTENTS.remove(target.NAME);
				Player.addINVENTORY(target);
			}

			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + target.PICKUP);
		}
	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	private static void unknown()
	{
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

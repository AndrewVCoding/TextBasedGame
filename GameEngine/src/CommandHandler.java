public class CommandHandler
{
	private static String GAME_STATE = "start";
	private static String[] COMMAND;

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
			Interface.setDISPLAY("Sorry, that command is not available now.");
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
				Interface.setDISPLAY("Where do you want to go?");
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
			Interface.setDISPLAY(Player.LOCATION.look());
		}
	}

	/**
	 * todo allow the player to target an item and move it from room contents to player's inventory
	 */
	private static void take()
	{

	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	private static void unknown()
	{
		if(GAME_STATE.equals("character name"))
		{
			Player.NAME = COMMAND[0];
			GAME_STATE = "character class";
			Interface.setDISPLAY("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");

		}
		else if(GAME_STATE.equals("character class"))
		{
			switch(COMMAND[0])
			{
				case "1":
					GAME_STATE = "idle";
					Player.setCLASS("Warrior");
					Interface.moveRoom(World.getStartingRoom());
					Player.setLOCATION(World.getStartingRoom());
					break;
				case "2":
					GAME_STATE = "idle";
					Player.setCLASS("Wizard");
					Interface.moveRoom(World.getStartingRoom());
					Player.setLOCATION(World.getStartingRoom());
					break;
				case "3":
					GAME_STATE = "idle";
					Player.setCLASS("Rogue");
					Interface.moveRoom(World.getStartingRoom());
					Player.setLOCATION(World.getStartingRoom());
					break;
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
		Interface.setDISPLAY("Sorry, that command is not available now.");
	}
}

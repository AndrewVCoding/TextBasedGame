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

	private static void go()
	{

	}

	private static void look()
	{

	}

	private static void take()
	{

	}

	private static void unknown()
	{
		switch(GAME_STATE)
		{
			case "character name":
				Player.NAME = COMMAND[0];
				GAME_STATE = "character class";
				Interface.setDISPLAY("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");
				break;
			case "character class":
				switch(COMMAND[0])
				{
					case "1":
						Player.CLASS = "Warrior";
						GAME_STATE = "idle";
						Interface.moveRoom(World.getRoom("livingroom"));
						break;
					case "2":
						Player.CLASS = "Wizard";
						GAME_STATE = "idle";
						Interface.moveRoom(World.getRoom("livingroom"));
						break;
					case "3":
						Player.CLASS = "Rogue";
						GAME_STATE = "idle";
						Interface.moveRoom(World.getRoom("livingroom"));
						break;
				}
				break;
		}
	}

	private static void invalid()
	{
		Interface.setDISPLAY("Sorry, that command is not available now.");
	}
}

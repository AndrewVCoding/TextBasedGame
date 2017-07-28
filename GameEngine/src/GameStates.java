public class GameStates
{
	private Interface INTERFACE;
	private World WORLD;
	private head HEAD;
	private start START;
	public boolean POP = false;

	public GameStates(Interface INTERFACE, World world)
	{
		this.INTERFACE = INTERFACE;
		WORLD = world;
		HEAD = new head();
		START = new start();
	}

	public void parseCommand(CommandHandler.command input)
	{
		POP = false;
		if(input.source.equals("Head"))
			HEAD.parseCommand(input);
		else if(input.source.equals("Start"))
			START.parseCommand(input);
	}

	public class head
	{
		public void parseCommand(CommandHandler.command input)
		{
			if(input.input.equalsIgnoreCase("start"))
			{
				input.target = "Start";
				INTERFACE.moveRoom(WORLD.getStartingRoom());
			}
			else
				input.target = input.source;
		}
	}

	public class start
	{
		public void parseCommand(CommandHandler.command input)
		{
			String[] com = input.input.split(" ");

			if(com[0].equalsIgnoreCase("look"))
			{
				input.target = "Start";
				POP = true;
				INTERFACE.displayCurrentRoom();
			}
			else if(com[0].equalsIgnoreCase("go"))
			{
				input.target = "Start";
				POP = true;
				//Check if the specified room is in the list of exits
				if(INTERFACE.PLAYER_ROOM.exitExists(com[1]))
					INTERFACE.moveRoom(WORLD.getRoom(com[1]));
				else
					INTERFACE.setDISPLAY("Sorry, you can't go that way.");
			}
			else
				input.target = input.source;
		}
	}
}

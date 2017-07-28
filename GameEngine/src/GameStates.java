public class GameStates
{
	private Interface INTERFACE;
	private head HEAD;
	private start START;
	private character CHARACTER;
	public boolean POP = false;

	public GameStates(Interface INTERFACE, World world)
	{
		this.INTERFACE = INTERFACE;
		HEAD = new head();
		START = new start();
		CHARACTER = new character();
	}

	public void parseCommand(CommandHandler.command input)
	{
		POP = false;
		if(input.source.equals("Head"))
			HEAD.parseCommand(input);
		else if(input.source.equals("Start"))
			START.parseCommand(input);
		else if(input.source.equals("Character Creation"))
			CHARACTER.parseCommand(input);
	}

	public class head
	{
		public void parseCommand(CommandHandler.command input)
		{
			if(input.input.equalsIgnoreCase("start"))
			{
				input.target = "Character Creation";
				INTERFACE.setDISPLAY("Enter a name for your character");
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
				if(Player.LOCATION.exitExists(com[1]))
					INTERFACE.moveRoom(World.getRoom(com[1]));
				else
					INTERFACE.setDISPLAY("Sorry, you can't go that way.");
			}
			else
				input.target = input.source;
		}
	}

	public class character
	{
		public void parseCommand(CommandHandler.command input)
		{
			Player.setNAME(input.input);
			INTERFACE.moveRoom(World.getStartingRoom());
			input.target = "Start";
		}
	}
}

package Engine;

/**
 * Parses the input into a usable command for the engine based on how many objects were found in the input
 */
class CommandParser
{
	public static void simpleCommand(Command command)
	{
		String input = command.INPUT;
		String output = "unknown";

		if(GameSystems.GAME_STATE.equals("start"))
		{
			if(input.matches("create character|new character|new|start"))
				output = "create character";
		}
		else if(GameSystems.GAME_STATE.equals("character creation"))
		{
			output = CharacterCreation.getStage() + "|" + input;
		}
		else if(GameSystems.GAME_STATE.equals("idle"))
		{
			if(input.matches("go [ \\w\\d]*") && command.hasRoom())
				output = "go";
			if(input.matches("(look|look around)"))
				output = "look";
			else if(input.matches("(look (at |)|)inventory"))
				output ="inventory";
		}
		command.COMMAND = output;
	}

	/**
	 * Commands available for one object
	 * @param command
	 */
	public static void oneObjCommand(Command command)
	{
		String input = command.INPUT;
		String objName = command.SLOT_ONE.NAME;
		String output = "unknown";
		if(GameSystems.GAME_STATE.equals("start"))
			output = "unavailable";
		else if(GameSystems.GAME_STATE.equals("character creation"))
			output = "unavailable";
		else if(GameSystems.GAME_STATE.equals("idle"))
		{
			//Check if an action is being performed on the object
			boolean act = false;
			int i = 0;
			for(String[] action : command.EFFECTS_ONE)
			{
				if(input.matches(action[0] + " (the |)" + objName))
				{
					output = "act|" + i;
					act = true;
				}
				else
					i++;
			}
			if(!act)
			{
				if(input.matches("(look|look at|look at the) " + objName))
					output = "look at";
				else if(input.matches("(take|pickup|pick up)( the|) " + objName))
					output = "take";
				else if(input.matches("(eat|drink) (the |)" + objName))
					output = "consume";
				else if(input.matches("open (the |)" + objName))
					output = "open";
				else if(input.matches("close (the |)" + objName))
					output = "close";
			}
		}
		command.COMMAND = output;
	}

	public static void twoObjCommand(Command command)
	{
		String output = "unknown";
		String objOne = command.SLOT_ONE.NAME;
		String objTwo = command.SLOT_ONE.NAME;

		if(GameSystems.GAME_STATE.equals("idle"))
		{
			if(command.INPUT.matches("use (the |)" + objOne + " (with|on) (the |)" + objTwo))
				output = "effect with";
		}

		command.COMMAND = output;
	}
}

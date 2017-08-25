/**
 * Parses the input into a usable command for the engine based on how many objects were found in the input
 */
class CommandParser
{
	public static String simpleCommand(Command command)
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
		return output;
	}

	public static String oneObjCommand(String input, String objName)
	{
		String output = "unknown";
		if(GameSystems.GAME_STATE.equals("start"))
			output = "unavailable";
		else if(GameSystems.GAME_STATE.equals("character creation"))
			output = "unavailable";
		else if(GameSystems.GAME_STATE.equals("idle"))
		{
			if(input.matches("(look|look at|look at the) " + objName))
				output = "look at";
			else if(input.matches("(take|pickup|pick up)( the|) " + objName))
				output = "take";
			else if(input.matches("use (the|)" + objName))
				output = "use";
			else if(input.matches("(eat|drink) (the |)" + objName))
				output = "consume";
			else if(input.matches("open (the |)" + objName))
				output = "open";
			else if(input.matches("close (the |)" + objName))
				output = "close";
		}
		return output;
	}

	public static String twoObjCommand(String input, String objOne, String objTwo)
	{
		String output = "unknown";


		return output;
	}
}

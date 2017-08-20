/**
 * Determines which function to call based on the parsed
 */
class CommandHandler
{
	/**
	 * All commands have a unique first word, but can be context sensitive after that.
	 *
	 * @param input The user input command
	 */
	public static void command(String input)
	{
		Interface.display("\n>>" + input);
		//First split the command into an array of strings
		Command command = new Command(input);
		System.out.println("command created with " + command.NUMBER_OF_OBJECTS + " objects");

		//There are no objects referenced in the command
		if(command.NUMBER_OF_OBJECTS == 0)
		{
			if(command.COMMAND.get(0).equals("start"))
				GameSystems.start(command);
			else if(command.COMMAND.get(0).equals("go"))
				GameSystems.go(command);
			else if(command.COMMAND.get(0).equals("look"))
				GameSystems.look(command);
			else if(command.COMMAND.get(0).equals("take"))
				GameSystems.take(command);
			else if(command.COMMAND.get(0).equals("inventory"))
				GameSystems.look(command);
			else if(command.COMMAND.get(0).equals("use"))
				GameSystems.act(command);
			else if(command.COMMAND.get(0).equals("consume"))
				GameSystems.act(command);
			else if(command.COMMAND.get(0).equals("open"))
				GameSystems.open(command);
			else if(command.COMMAND.get(0).equals("close"))
				GameSystems.close(command);
			else if(command.COMMAND.get(0).equals("unknown"))
				GameSystems.unknown(command);
		}
	}
}

public class CommandHandler
{

	/**
	 * All commands have a unique first word, but can be context sensitive after that.
	 *
	 * @param com The user input command
	 */
	public static void command(String com)
	{
		Interface.display(">>" + com);
		//First split the command into an array of strings
		GameSystems.COMMAND = CommandParser.parse(com);

		//Check the first word and run the appropriate method
		if(GameSystems.COMMAND[0].equalsIgnoreCase("start"))
			GameSystems.start();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("go"))
			GameSystems.go();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("look"))
			GameSystems.look();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("take"))
			GameSystems.take();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("inventory"))
			GameSystems.look();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("use"))
			GameSystems.act();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("consume"))
			GameSystems.act();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("open"))
			GameSystems.open();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("close"))
			GameSystems.close();
		else if(GameSystems.COMMAND[0].equalsIgnoreCase("unknown"))
			GameSystems.unknown(com);

	}
}

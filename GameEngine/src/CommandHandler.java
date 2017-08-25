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
		Interface.INTERACTIONS += "\n>>" + input;
		Command command = new Command(input);

		//There are no objects referenced in the command
		if(command.NUMBER_OF_OBJECTS == 0)
		{
			if(command.equals("start"))
				GameSystems.start(command);
			else if(command.equals("save"))
				DataHandler.saveGame(command.TARGET);
			else if(command.equals("load"))
				DataHandler.loadGame(command.TARGET);
			else if(command.equals("list saves"))
				DataHandler.listSaves();
			else if(command.equals("create character"))
				CharacterCreation.createCharacter(command);
			else if(command.equals("go"))
				GameSystems.go(command);
			else if(command.equals("look"))
				GameSystems.look(command);
			else if(command.equals("inventory"))
				Player.viewInventory();
			else
				GameSystems.unknown(command);
		}

		else if(command.NUMBER_OF_OBJECTS == 1)
		{
			if(command.equals("look at"))
				GameSystems.look(command);
			else if(command.equals("take"))
				GameSystems.take(command);
			else if(command.COMMAND.matches("act\\|\\d\\d*"))
				GameSystems.use(command);
			else if(command.equals("consume"))
				GameSystems.act(command);
			else if(command.equals("open"))
				GameSystems.open(command);
			else if(command.equals("close"))
				GameSystems.close(command);
			else
				GameSystems.unknown(command);
		}
	}
}

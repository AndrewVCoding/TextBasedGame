public class CharacterCreation
{
	private static String[] STAGE = {"start", "name", "class", "idle"};
	private static int STAGE_NUM = 0;
	/**
	 * Commands should come in as:
	 * ["character creation", "name", input]
	 * ["character creation", "class", input]
	 * @param command The input from the user
	 */
	public static void createCharacter(Command command)
	{

		String[] input = command.COMMAND.split("\\|");

		if(getStage().equals("start"))
		{
			STAGE_NUM++;
			GameSystems.GAME_STATE = "character creation";
		}
		else if(getStage().equals("name"))
			pickName(input[1]);
		else if(getStage().equals("class"))
			pickClass(input[1]);
		if(getStage().equals("idle"))
			GameSystems.enterGame();
		displayPrompt();
	}

	private static void pickName(String input)
	{
		Player.NAME = input;
		STAGE_NUM++;
	}

	private static void pickClass(String input)
	{
		if(input.equals("1"))
		{
			Player.CLASS = "Warrior";
			Player.HP = 100;
			Player.MAX_HP = 100;
			Player.moveRoom(World.STARTING_ROOM);
			STAGE_NUM++;
		}
		else if(input.equals("2"))
		{
			Player.CLASS = "Wizard";
			Player.HP = 70;
			Player.MAX_HP = 70;
			Player.moveRoom(World.STARTING_ROOM);
			STAGE_NUM++;
		}
		else if(input.equals("3"))
		{
			Player.CLASS = "Rogue";
			Player.HP = 80;
			Player.MAX_HP = 80;
			Player.moveRoom(World.STARTING_ROOM);
			STAGE_NUM++;
		}
		else
		{
			Interface.display("That is not a valid class choice");
		}
	}

	public static String getStage()
	{
		return STAGE[STAGE_NUM];
	}

	private static void displayPrompt()
	{
		if(!STAGE[STAGE_NUM].equals("idle"))
		{
			if(STAGE[STAGE_NUM].equals("name"))
				Interface.display("Please enter a name for your character");
			if(STAGE[STAGE_NUM].equals("class"))
				Interface.display("Now pick a class:\n1)warrior\n2)Wizard\n3)Rogue");
		}
	}
}

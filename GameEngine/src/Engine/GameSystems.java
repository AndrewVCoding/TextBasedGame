package Engine;

public class GameSystems
{
	public static String GAME_STATE = "start";

	/**
	 * Auto starts the game as a prenamed character and class
	 *
	 * @param name preset name for an automatic character
	 * @param clas preset class for an automatic character
	 */
	public static void autoStartGame(String name, int clas)
	{

	}

	/**
	 * When the game is first loaded, the only option is to start the game, which currently goes to create a new character
	 * todo add in load/save world/player options
	 */
	public static void start(Command command)
	{
		if(GAME_STATE.equals("start"))
			StartState.start();
		else if(GAME_STATE.equals("idle"))
			Interface.display("Sorry, that command is not available right now.");
		else
			invalid(command);
	}

	/**
	 * When the command is go XXXXX then move the player to the specified room
	 * todo allow using just N/S/E/W as quick commands, as well as just putting the name of the exit/destination room
	 */
	public static void go(Command command)
	{
		if(GAME_STATE.equals("idle"))
			IdleState.go(command);
		else
			Interface.display("You can't leave right now");
	}

	/**
	 * displays an overview of the player's current location
	 * todo allow the player to target a specific item to look at
	 */
	public static void look(Command command)
	{
		if(GAME_STATE.equals("idle"))
			IdleState.look(command);
		else
			Interface.display("There is nothing to look at");
	}

	/**
	 * Moves an object to the players inventory
	 */
	public static void take(Command command)
	{
		if(GAME_STATE.equals("idle"))
		{
			IdleState.take(command);
		}
		else
			Interface.display("You can't do that right now");
	}

	/**
	 * If the command is to effect/eat/drink an item, find the relevant effects and act on them
	 */
	public static void act(Command command)
	{
		System.out.println(":act:");
		if(GAME_STATE.equals("idle"))
			IdleState.act(command);
		else
			Interface.display("There is nothing to do that to");
	}

	/**
	 * When using an object, array contents are as follows
	 * [   act | source | target | activation description | deactivation description ]
	 * [    0  |    1   |    2   |           3            |              4           ]
	 */
	public static void use(Command command)
	{
		System.out.println(":effect:");
		if(GAME_STATE.equals("idle"))
			IdleState.effect(command);
		else
			Interface.display("There is nothing to effect");
	}

	/**
	 * The command is unknown, either being a player response to a query or not a valid command
	 */
	public static void unknown(Command command)
	{
		System.out.println(":unknown:");
		if(GAME_STATE.equals("start"))
		{
			if(command.COMMAND.equals("create character"))
				CharacterCreation.createCharacter(command);
		}
		else if(GAME_STATE.equals("character creation"))
		{
			CharacterCreation.createCharacter(command);
		}
		else
			invalid(command);
	}

	public static void open(Command command)
	{
		System.out.println("opening");

		command.SLOT_ONE.getTop().open();
	}

	public static void close(Command command)
	{
		System.out.println("closing");
	}

	public static void invalid(Command command)
	{
		System.out.println("invalid");
	}

	public static void enterGame()
	{
		GAME_STATE = "idle";
		World.getRoom(World.STARTING_ROOM).spawn();
		Interface.resetDisplay(false);
	}
}

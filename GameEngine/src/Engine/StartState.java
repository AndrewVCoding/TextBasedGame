package Engine;

class StartState
{
	/**
	 * When the game is first loaded, the only option is to start the game, which currently goes to create a new character
	 * todo add in load/save world/player options
	 */
	public static void start()
	{
		GameSystems.GAME_STATE = "character name";
		Interface.display("Please enter a name for your character");
	}
}

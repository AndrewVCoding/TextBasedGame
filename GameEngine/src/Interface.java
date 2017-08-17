class Interface
{
	public static String DISPLAY;
	public static String INTERACTIONS;
	private static String HEADER;

	public static void display(String display)
	{
		if(GameSystems.GAME_STATE.equals("idle"))
			HEADER = Player.LOCATION.enter(false);
		INTERACTIONS += "\n" + display;
		DISPLAY = HEADER + INTERACTIONS;
	}

	public static void resetDisplay()
	{
		if(GameSystems.GAME_STATE.equals("idle"))
			HEADER = Player.LOCATION.enter(true);
		INTERACTIONS = "";
		DISPLAY = HEADER + INTERACTIONS;
	}
}

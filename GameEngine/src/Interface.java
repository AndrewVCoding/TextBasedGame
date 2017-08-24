class Interface
{
	public static String DISPLAY;
	public static String INTERACTIONS;
	public static boolean MOVED = false;
	private static String HEADER;

	public static void display(String display)
	{
		if(GameSystems.GAME_STATE.equals("idle"))
		{
			HEADER = Player.LOCATION.enter(MOVED);
			INTERACTIONS += "\n" + display;
			DISPLAY = HEADER + INTERACTIONS;
		}
		else
		{
			INTERACTIONS += "\n" + display;
			DISPLAY = INTERACTIONS;
		}
	}

	public static void resetDisplay()
	{
		if(GameSystems.GAME_STATE.equals("idle"))
		{
			HEADER = Player.LOCATION.enter(MOVED);
			INTERACTIONS = "";
			DISPLAY = HEADER + INTERACTIONS;
		}
		else
		{
			INTERACTIONS += "";
			DISPLAY = INTERACTIONS;
		}
	}
}

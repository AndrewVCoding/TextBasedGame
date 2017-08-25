class Interface
{
	public static String DISPLAY = "";
	public static String INTERACTIONS = "";
	public static String HEADER = "";

	public static void welcome()
	{
		DISPLAY = "WELCOME!\nYou can either start a 'new' character or 'load' a previous game:";
	}

	public static void display(String display)
	{
		if(GameSystems.GAME_STATE.equals("idle"))
		{
			System.out.println("idle display");
			HEADER = Player.LOCATION.enter(false);
			INTERACTIONS += "\n" + display;
			DISPLAY = HEADER + INTERACTIONS;
		}
		else
		{
			System.out.println("display");
			INTERACTIONS += "\n" + display;
			DISPLAY = INTERACTIONS;
		}
	}

	public static void resetDisplay(boolean moved)
	{
		if(GameSystems.GAME_STATE.equals("idle"))
		{
			System.out.println("idle resetDisplay");
			HEADER = Player.LOCATION.enter(moved);
			INTERACTIONS = "";
			DISPLAY = HEADER + INTERACTIONS;
		}
		else
		{
			System.out.println("resetDisplay");
			INTERACTIONS += "";
			DISPLAY = INTERACTIONS;
		}
		DISPLAY.replace("\n", "\n     ");
	}
}

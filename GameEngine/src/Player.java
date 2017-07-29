public class Player
{
	public static String NAME;
	public static String CLASS;
	public static Room LOCATION = World.getStartingRoom();

	public static void setNAME(String NAME)
	{
		Player.NAME = NAME;
	}

	public static void setCLASS(String CLASS)
	{
		Player.CLASS = CLASS;
	}

	public static void setLOCATION(Room room)
	{
		LOCATION = room;
	}
}

public class Interface
{
	public static String DISPLAY;

	public static void setDISPLAY(String display)
	{
		DISPLAY = display;
	}

	public static void moveRoom(Room room)
	{
		DISPLAY = room.enter();
		Player.setLOCATION(room);
	}
}

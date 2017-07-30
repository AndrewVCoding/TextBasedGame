public class Interface
{
	public static String DISPLAY;

	public static void setDISPLAY(String display)
	{
		DISPLAY = display;
	}

	public static void displayCurrentRoom()
	{
		DISPLAY = Player.LOCATION.look();
	}

	public static void moveRoom(Room room)
	{
		if(Player.LOCATION.isExit(room))
		{
			DISPLAY = room.enter();
			Player.setLOCATION(room);
		}
		else
			setDISPLAY("You can't go that way");
	}
}

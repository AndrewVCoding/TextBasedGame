public class Interface
{
	public static String DISPLAY;

	public void setDISPLAY(String display)
	{
		DISPLAY = display;
	}

	public void displayCurrentRoom()
	{
		DISPLAY = Player.LOCATION.look();
	}

	public void moveRoom(Room room)
	{
		DISPLAY = room.enter();
		Player.setLOCATION(room);
	}
}

public class Interface
{
	public String DISPLAY;
	public Room PLAYER_ROOM;

	public void setDISPLAY(String display)
	{
		DISPLAY = display;
	}

	public void displayCurrentRoom()
	{
		DISPLAY = PLAYER_ROOM.look();
	}

	public void moveRoom(Room room)
	{
		DISPLAY = room.enter();
		PLAYER_ROOM = room;
	}
}

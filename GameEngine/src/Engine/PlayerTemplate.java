package Engine;

import java.util.ArrayList;
import java.util.List;

public class PlayerTemplate
{
	public String NAME = "default";
	public String CLASS = "default";
	public int HP = 0;
	public int MAX_HP = 0;
	public Room LOCATION = new Room();
	public List<ContentSlot> INVENTORY = new ArrayList<>();
	public List<ContentSlot> TO_PURGE = new ArrayList<>();

	public PlayerTemplate()
	{
		NAME = Player.NAME;
		CLASS = Player.PROFESSION;
		HP = Player.HP;
		MAX_HP = Player.MAX_HP;
		LOCATION = Player.LOCATION;
		INVENTORY = Player.INVENTORY;
		TO_PURGE = Player.TO_PURGE;
	}
}

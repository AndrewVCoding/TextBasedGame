import java.util.ArrayList;
import java.util.List;

class Player
{
	public static String NAME;
	public static String CLASS;
	public static int HP;
	public static int MAX_HP;
	public static Room LOCATION;
	public static final List<ContentSlot> INVENTORY = new ArrayList<>();

	public static void addToInventory(String contentID)
	{
		for(ContentSlot slot : INVENTORY)
			addToInventory(contentID);
	}

	public static void removeFromInventory(String contentID)
	{

	}

	public static void viewInventory()
	{
		if(!INVENTORY.isEmpty())
		{
			StringBuilder display = new StringBuilder();
			int i = 1;
			for(ContentSlot contentSlot : INVENTORY)
			{
				display.append(i).append(")").append(World.getIDName(contentSlot.NAME)).append(" X").append(contentSlot.QUANTITY).append("\n");
				i++;
			}
			Interface.display("Inventory:\n" + display);
		}
		else
			Interface.display("Inventory is empty");
	}

	public static void moveRoom(Room room)
	{
		LOCATION = room;
		Interface.resetDisplay();
	}

	public static void heal(int amount)
	{
		HP += amount;
		if(HP > MAX_HP)
			HP = MAX_HP;
	}
}

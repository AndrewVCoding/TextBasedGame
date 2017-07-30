import java.util.ArrayList;
import java.util.List;

public class Player
{
	public static String NAME;
	public static String CLASS;
	public static int HP;
	public static Room LOCATION;
	public static List<Item> INVENTORY = new ArrayList<>();

	public static void setNAME(String NAME)
	{
		Player.NAME = NAME;
	}

	public static void setCLASS(String CLASS)
	{
		Player.CLASS = CLASS;
	}

	public static void setHP(int HP)
	{
		Player.HP = HP;
	}

	public static void setLOCATION(Room room)
	{
		LOCATION = room;
	}

	public static void addINVENTORY(Item item)
	{
		INVENTORY.add(item);
	}

	public static void viewInventory()
	{
		if(!INVENTORY.isEmpty())
		{
			String display = "";
			int i = 1;
			for(Item item : INVENTORY)
			{
				display += i + ")" + item.NAME + "\n";
				i++;
			}
			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Inventory:\n" + display);
		}
		else
			Interface.setDISPLAY(Interface.DISPLAY + "\n\n>>" + "Inventory is empty");
	}
}

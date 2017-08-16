import java.util.ArrayList;
import java.util.List;

public class Player
{
	public static String NAME;
	public static String CLASS;
	public static int HP;
	public static Room LOCATION;
	public static List<Item> INVENTORY = new ArrayList<>();

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
}

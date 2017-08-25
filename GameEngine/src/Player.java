import java.util.ArrayList;
import java.util.List;

class Player
{
	public static String NAME;
	public static String CLASS;
	public static int HP;
	public static int MAX_HP;
	public static Room LOCATION;
	public static List<ContentSlot> INVENTORY = new ArrayList<>();
	public static List<ContentSlot> TO_PURGE = new ArrayList<>();

	public static void createFromTemplate(PlayerTemplate template)
	{
		NAME = template.NAME;
		CLASS = template.CLASS;
		HP = template.HP;
		MAX_HP = template.MAX_HP;
		LOCATION = template.LOCATION;
		INVENTORY = template.INVENTORY;
		TO_PURGE = template.TO_PURGE;
	}

	public static void addToInventory(ContentSlot contentSlot)
	{
		purge();
		boolean added = false;
		for(ContentSlot slot : INVENTORY)
			if(slot.NAME.equals(contentSlot.NAME))
			{
				slot.add(contentSlot);
				added = true;
				break;
			}
		if(!added)
			INVENTORY.add(contentSlot);
	}

	//todo set up a system to put items from inventory into a Room's contents or a Container. It will work basically the same as adding to inventory but in reverse.

	public static void viewInventory()
	{
		purge();
		if(!INVENTORY.isEmpty())
		{
			StringBuilder display = new StringBuilder();
			int i = 1;
			for(ContentSlot contentSlot : INVENTORY)
			{
				if(contentSlot.QUANTITY > 1)
					display.append(i).append(")").append(contentSlot.NAME).append(" X").append(contentSlot.QUANTITY).append("\n");
				else
					display.append(i).append(")").append(contentSlot.NAME).append("\n");
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
		Interface.resetDisplay(true);
	}

	public static void heal(int amount)
	{
		HP += amount;
		if(HP > MAX_HP)
			HP = MAX_HP;
	}

	private static void purge()
	{
		for(ContentSlot slot : INVENTORY)
			if(slot.PURGE)
				TO_PURGE.add(slot);
		for(ContentSlot slot : TO_PURGE)
			INVENTORY.remove(slot);
		TO_PURGE = new ArrayList<>();
	}
}

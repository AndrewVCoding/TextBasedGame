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

	//todo FIX THIS WHOLE MESS
	public static void addToInventory(Item item)
	{
		boolean added = false;
		for(ContentSlot slot : INVENTORY)
			if(slot.NAME.equals(item.NAME))
			{
				slot.add(item);
				added = true;
				break;
			}
		if(!added)
			INVENTORY.add(new ContentSlot(item));
	}

	public static void addToInventory(Container container)
	{
		boolean added = false;
		for(ContentSlot slot : INVENTORY)
			if(slot.NAME.equals(container.NAME))
			{
				slot.add(container);
				added = true;
				break;
			}
		if(!added)
			INVENTORY.add(new ContentSlot(container));
	}

	public static void addToInventory(ContentSlot contentSlot)
	{
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

	public static void removeFromInventory(Item item)
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

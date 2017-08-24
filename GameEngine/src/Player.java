import java.util.ArrayList;
import java.util.List;

class Player
{
	public static String NAME;
	public static String CLASS;
	public static int HP;
	public static int MAX_HP;
	public static Room LOCATION = World.STARTING_ROOM;
	public static final List<ContentSlot> INVENTORY = new ArrayList<>();
	private static List<ContentSlot> TO_PURGE = new ArrayList<>();

	//todo FIX THIS WHOLE MESS
	public static void addToInventory(Item item)
	{
		purge();
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

	public static void addToInventory(Entity entity)
	{
		purge();
		boolean added = false;
		for(ContentSlot slot : INVENTORY)
			if(slot.NAME.equals(entity.NAME))
			{
				slot.add(entity);
				added = true;
				break;
			}
		if(!added)
			INVENTORY.add(new ContentSlot(entity));
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

	public static void removeFromInventory(Item item)
	{

	}

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
		Interface.resetDisplay();
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

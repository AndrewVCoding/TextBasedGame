import java.util.List;

public class Container extends Item
{
	public String LOCKED_DESCRIPTION;
	public String UNLOCK;
	public String LOCK;
	public String KEY_ID;
	public boolean LOCKED = false;
	public List<Item> CONTENTS;

	public String look()
	{
		if(ACTIVATED)
			return ACT_DESC;
		else if(LOCKED)
			return LOCKED_DESCRIPTION;
		else
		{
			DESCRIPTION += "\nContains: ";
			for(Item item:CONTENTS)
				DESCRIPTION += "\n" + item.NAME;
			return DESCRIPTION;
		}
	}

	public void unlock()
	{
		for(Item item : Player.INVENTORY)
			if(item.ID.equals(KEY_ID))
			{
				if(LOCKED)
					Interface.display(UNLOCK);
				else
					Interface.display("It is already unlocked");
			}
	}

	public void lock()
	{
		for(Item item : Player.INVENTORY)
			if(item.ID.equals(KEY_ID))
			{
				if(LOCKED)
					Interface.display("It is already locked");
				else
				Interface.display(LOCK);
			}
	}
}

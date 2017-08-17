import java.util.ArrayList;
import java.util.List;

public class Container extends Item
{
	private String UNLOCK;
	private String LOCK;
	public String OPEN_DESC;
	public String CLOSE_DESC;
	public String ID;
	private String KEY_ID;
	public boolean OPEN = false;
	public boolean LOCKED = false;
	public final List<Item> ITEMS = new ArrayList<>();
	public final List<Container> CONTAINERS = new ArrayList<>();

	public String look()
	{
		if(OPEN)
			return ACT_DESC;
		else
			return DESCRIPTION;
	}

	public void unlock()
	{
		for(ContentSlot contentSlot : Player.INVENTORY)
		{
			//check if the key's ID is in the contentSlot
			for(String ID : contentSlot.CONTENT_IDS)
				if(ID.equals(KEY_ID))
				{
					if(LOCKED)
						Interface.display(UNLOCK);
					else
						Interface.display("It is already unlocked");
				}
		}
	}

	public void lock()
	{
		for(ContentSlot contentSlot : Player.INVENTORY)
		{
			//check if the key's ID is in the contentSlot
			for(String ID : contentSlot.CONTENT_IDS)
				if(ID.equals(KEY_ID))
				{
					if(!LOCKED)
						Interface.display(LOCK);
					else
						Interface.display("It is already unlocked");
				}
		}
	}

	public void open()
	{
		if(!LOCKED)
		{
			if(!OPEN)
			{
				StringBuilder contents = new StringBuilder();
				for(Item item : ITEMS)
					contents.append(item.NAME).append("\n");
				OPEN = true;
				Interface.display(OPEN_DESC + "\n" + contents);
			}
			else
				Interface.display("The " + NAME + " is already open");
		}
	}

	public void close()
	{
		if(OPEN)
		{
			OPEN = false;
			Interface.display(CLOSE_DESC);
		}
		else
			Interface.display("The " + NAME + " is already closed");
	}
}

import java.util.ArrayList;
import java.util.List;

public class Container extends Item
{
	public String UNLOCK;
	public String LOCK;
	public String OPEN_DESC;
	public String CLOSE_DESC;
	public String KEY_ID;
	public boolean OPEN = false;
	public boolean LOCKED = false;
	public List<Item> CONTENTS = new ArrayList<>();

	public String look()
	{
		if(OPEN)
			return ACT_DESC;
		else
			return DESCRIPTION;
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

	public void open()
	{
		if(!LOCKED)
		{
			if(!OPEN)
			{
				String contents = "";
				for(Item item : CONTENTS)
					contents += item.NAME + "\n";
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

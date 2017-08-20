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
	public final List<ContentSlot> CONTENTS = new ArrayList<>();

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
			for(String ID : contentSlot.CONTENT_INSTANCES)
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
			for(String ID : contentSlot.CONTENT_INSTANCES)
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
				OPEN = true;
				StringBuilder contents = new StringBuilder();
				for(ContentSlot slot : CONTENTS)
					for(String id : slot.CONTENT_INSTANCES)
						contents.append(World.getIDName(id)).append("\n");
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

	public boolean contains(Item item)
	{
		for(ContentSlot slot : CONTENTS)
			if(slot.contains(item))
				return true;
		return false;
	}

	public boolean contains(Container container)
	{
		for(ContentSlot slot : CONTENTS)
			if(slot.contains(container))
				return true;
		return false;
	}

	public boolean contains(String id)
	{
		for(ContentSlot slot : CONTENTS)
			if(slot.contains(id))
				return true;
		return false;
	}

	public void add(Item item)
	{
		boolean added = false;
		for(ContentSlot slot : CONTENTS)
			if(slot.NAME.equals(item.NAME))
			{
				slot.add(item);
				added = true;
				break;
			}
		if(!added)
			CONTENTS.add(new ContentSlot(item));
	}

	public void add(Container container)
	{
		boolean added = false;
		for(ContentSlot slot : CONTENTS)
			if(slot.NAME.equals(container.NAME))
			{
				slot.add(container);
				added = true;
				break;
			}
		if(!added)
			CONTENTS.add(new ContentSlot(container));
	}

	public void remove(Item item)
	{
		if(contains(item))
			for(ContentSlot slot : CONTENTS)
				if(slot.NAME.equals(item.NAME))
					slot.remove(item);
	}

	public void remove(Container container)
	{
		if(contains(container))
			for(ContentSlot slot : CONTENTS)
				if(slot.NAME.equals(container.NAME))
					slot.remove(container);
	}

	public void remove(String id)
	{
		if(contains(id))
			for(ContentSlot slot : CONTENTS)
				if(slot.contains(id))
					slot.remove(id);
	}
}

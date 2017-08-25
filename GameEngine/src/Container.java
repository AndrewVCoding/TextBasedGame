import java.util.ArrayList;
import java.util.List;

public class Container extends Item
{
	private final String UNLOCK;
	private final String LOCK;
	public final String OPEN_DESC;
	public final String CLOSE_DESC;
	private final String KEY_ID;
	public boolean OPEN;
	public boolean LOCKED;
	public final List<ContentSlot> CONTENTS;

	public Container()
	{
		super();
		UNLOCK = "default";
		LOCK = "default";
		OPEN_DESC = "default";
		CLOSE_DESC = "default";
		KEY_ID = "K-000000";
		OPEN = false;
		LOCKED = false;
		CONTENTS = new ArrayList<>();
	}

	public Container(String name, String id, String instance, String description, String actDescription, String pickup, boolean takeable, boolean activated, String[] effects,
					 String unlock, String lock, String open_desc, String close_desc, String key_id, boolean open, boolean locked)
	{
		super(name, id, instance, description, actDescription, pickup, takeable, activated, effects);
		UNLOCK = unlock;
		LOCK = lock;
		OPEN_DESC = open_desc;
		CLOSE_DESC = close_desc;
		KEY_ID = key_id;
		OPEN = open;
		LOCKED = locked;
		CONTENTS = new ArrayList<>();
	}

	public void look()
	{
		if(OPEN)
			Interface.display(ACT_DESC);
		else
			Interface.display(DESCRIPTION);
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

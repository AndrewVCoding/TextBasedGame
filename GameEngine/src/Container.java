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

	public Container(String name, String id, String instance, String description, String actDescription, String pickup,
					 boolean takeable, boolean activated, List<String[]> effects,
					 String unlock, String lock, String open_desc, String close_desc, String key_id, boolean open,
					 boolean locked)
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

	/**
	 * Displays a visual description of the Container and lists it's contents
	 */
	public void look()
	{
		if(OPEN)
		{
			Interface.display(ACT_DESC);
			displayContents();
		}
		else
			Interface.display(DESCRIPTION);
	}

	/**
	 * unlocks the container if the correct key is given
	 *
	 * @param key The key of the item being used with the container
	 */
	public void unlock(String key)
	{
		//check if the key is the correct entity to unlock the container
		if(key.equals(KEY_ID))
		{
			if(LOCKED)
			{
				Interface.display(UNLOCK);
				LOCKED = false;
			}
			else
				Interface.display("It is already unlocked");
		}
	}

	/**
	 * locks the container if the correct key is given
	 *
	 * @param key The key of the item being used with the container
	 */
	public void lock(String key)
	{
		if(key.equals(KEY_ID))
		{
			if(OPEN)
				close();
			if(!LOCKED)
			{
				Interface.display(LOCK);
				LOCKED = true;
			}
			else
				Interface.display("It is already locked");
		}
	}

	/**
	 * displays the open description for the Container and sets it to open if unlocked
	 */
	public void open()
	{
		if(!LOCKED)
		{
			if(!OPEN)
			{
				OPEN = true;
				displayContents();
			}
			else
				Interface.display("The " + NAME + " is already open");
		}
	}

	/**
	 * Displays the description for closing the Container and sets it to closed
	 */
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

	/**
	 * Displays the contents of the chest
	 */
	public void displayContents()
	{
		StringBuilder contents = new StringBuilder();
		for(ContentSlot slot : CONTENTS)
			for(String id : slot.CONTENT_INSTANCES)
				contents.append(World.getIDName(id)).append("\n");
		Interface.display(OPEN_DESC + "\n" + contents);

	}

	/**
	 * @param entity
	 * @return true if the Entity is contained in the container
	 */
	public boolean contains(Entity entity)
	{
		for(ContentSlot slot : CONTENTS)
			if(slot.contains(entity))
				return true;
		return false;
	}

	/**
	 *
	 * @param key
	 * @return true if the key corresponds to an entity in the container
	 */
	public boolean contains(String key)
	{
		String[] com = key.split(":");
		String instance = com[1];

		for(ContentSlot slot : CONTENTS)
			if(slot.ID.equals(com[0]))
				if(slot.contains(key))
					return true;
		return false;
	}

	/**
	 * adds the entity to the proper ContainerSlot in the container
	 * @param entity
	 */
	public void add(Entity entity)
	{
		boolean added = false;
		for(ContentSlot slot : CONTENTS)
			if(slot.NAME.equals(entity.NAME))
			{
				slot.add(entity);
				added = true;
				break;
			}
		if(!added)
			CONTENTS.add(new ContentSlot(entity));
	}

	/**
	 * Removes the specified entity from the container
	 * @param entity
	 */
	public void remove(Entity entity)
	{
		if(contains(entity))
			for(ContentSlot slot : CONTENTS)
				if(slot.NAME.equals(entity.NAME))
					slot.remove(entity);
	}

	/**
	 * Removes the entity specified by the key from the container
	 * @param key
	 */
	public void remove(String key)
	{
		if(contains(key))
			for(ContentSlot slot : CONTENTS)
				if(slot.contains(key))
					slot.remove(key);
	}

	/**
	 * @return the list of effects for the object
	 */
	public List<String[]> getEffects()
	{
		return EFFECTS;
	}
}

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of IDs of items of the same name, and the number of items in the list
 */
public class ContentSlot
{
	public String NAME;
	public String ID;
	public List<String> CONTENT_INSTANCES = new ArrayList<>();
	public List<String> TO_PURGE = new ArrayList<>();
	public int QUANTITY;
	public boolean PURGE = false;

	/**
	 * Creates a new ContentSlot to hold instances of an Entity
	 * @param entity the Entity you want to create a ContentSlot for
	 */
	public ContentSlot(Entity entity)
	{
		NAME = entity.NAME;
		ID = entity.ID;
		CONTENT_INSTANCES.add(entity.INSTANCE);
		QUANTITY = 1;
	}

	/**
	 * Creates a new ContentSlot to hold instances of an Entity
	 * @param key the  corresponding key of the Entity you want to create a ContentSlot for
	 */
	public ContentSlot(String key)
	{
		Entity entity = World.ENTITY_MANAGER.get(key);
		NAME = entity.NAME;
		ID = entity.ID;
		CONTENT_INSTANCES.add(entity.INSTANCE);
		QUANTITY = 1;
	}

	/**
	 * Creates a new ContentSlot as an exact copy of another ContentSlot, containing the same instances
	 * @param contentSlot the ContentSlot you want to copy
	 */
	public ContentSlot(ContentSlot contentSlot)
	{
		NAME = contentSlot.NAME;
		ID = contentSlot.ID;
		CONTENT_INSTANCES = contentSlot.CONTENT_INSTANCES;
		QUANTITY = contentSlot.QUANTITY;
	}

	public void look()
	{
		World.ENTITY_MANAGER.get(getKey()).look();
	}

	public boolean referenced(String input)
	{
		return input.matches("[ \\d\\w]*" + NAME + "[ \\w\\d]*");
	}

	public boolean contains(Entity entity)
	{
		for(String id : CONTENT_INSTANCES)
			if(entity.ID.equals(id))
				return true;
		return false;
	}

	public boolean contains(String key)
	{
		for(String instance : CONTENT_INSTANCES)
			if(key.equals(ID + ":" + instance))
				return true;
		return false;
	}

	public void add(Entity entity)
	{
		if(entity.NAME.equals(NAME))
			if(!CONTENT_INSTANCES.contains(entity))
			{
				CONTENT_INSTANCES.add(entity.ID);
				QUANTITY++;
			}
	}

	public void add(String id)
	{
		if(World.getIDName(id).equals(NAME))
			if(!CONTENT_INSTANCES.contains(id))
			{
				CONTENT_INSTANCES.add(id);
				QUANTITY++;
			}
	}

	public void add(ContentSlot slot)
	{
		if(slot.NAME.equals(NAME))
			for(String newID : slot.CONTENT_INSTANCES)
				add(newID);
	}

	public void remove(Entity entity)
	{
		for(String instance : CONTENT_INSTANCES)
			if(entity.INSTANCE.equals(instance))
			{
				CONTENT_INSTANCES.remove(instance);
				QUANTITY--;
			}
	}

	public void remove(String inputInstance)
	{
		for(String instance : CONTENT_INSTANCES)
			if(inputInstance.equals(instance))
			{
				CONTENT_INSTANCES.remove(instance);
				QUANTITY--;
			}
	}

	/**
	 * Returns a new content slot with the specified number of objects or, if the specified number is greater than or equal to the existing number of objects, this content slot itself
	 *
	 * @param num number of objects requested
	 * @return content slot with the requested number of objects
	 */
	public void take(int num)
	{
		System.out.println("take: " + num + " " + NAME);

		//If the specified number is greater than or equal to num
		if(num >= QUANTITY)
		{
			Player.addToInventory(new ContentSlot(this));
			PURGE = true;
			World.ENTITY_MANAGER.get(getKey()).take();
		}

		//Otherwise, create a new Content container and move num items to it
		else if(num != 0)
		{
			ContentSlot output = new ContentSlot(getKey());

			for(int i = num; i > 0; i--)
			{
				System.out.println("1 taken");
				output.add(CONTENT_INSTANCES.get(i));
				remove(CONTENT_INSTANCES.get(i));
			}

			Player.addToInventory(output);
		}

		else if(QUANTITY == 0)
		{
			PURGE = true;
			Interface.display("There is nothing to take");
		}

		System.out.println(QUANTITY + " left");
	}

	public Entity getTop()
	{
		return World.ENTITY_MANAGER.get(getKey());
	}

	/**
	 *
	 * @return the key of the top instance in the ContentSlot
	 */
	public String getKey()
	{
		return ID + ":" + CONTENT_INSTANCES.get(0);
	}

	public void purge()
	{
		for(String instance : CONTENT_INSTANCES)
			if(World.ENTITY_MANAGER.get(ID + ":" + instance).PURGE)
				TO_PURGE.add(instance);
		for(String instance : TO_PURGE)
			CONTENT_INSTANCES.remove(instance);
		TO_PURGE = new ArrayList<>();
	}
}

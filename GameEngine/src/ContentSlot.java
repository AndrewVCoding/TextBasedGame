import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of IDs of items of the same name, and the number of items in the list
 */
public class ContentSlot
{
	public String NAME;
	//public String TYPE;
	public String ID;
	public List<String> CONTENT_INSTANCES = new ArrayList<>();
	public int QUANTITY;

	public ContentSlot(Item item)
	{
		NAME = item.NAME;
		ID = item.ID;
		//TYPE = "item";
		CONTENT_INSTANCES.add(item.INSTANCE);
		QUANTITY = 1;
	}

	public ContentSlot(Container container)
	{
		NAME = container.NAME;
		ID = container.ID;
		//TYPE = "container";
		CONTENT_INSTANCES.add(container.INSTANCE);
		QUANTITY = 1;
	}

	public ContentSlot(Entity entity)
	{
		NAME = entity.NAME;
		ID = entity.ID;
		//TYPE = "container";
		CONTENT_INSTANCES.add(entity.INSTANCE);
		QUANTITY = 1;
	}

	public ContentSlot(String key)
	{
		Entity entity = World.ENTITY_MANAGER.get(key);
		NAME = entity.NAME;
		ID = entity.ID;
		//TYPE = "container";
		CONTENT_INSTANCES.add(entity.INSTANCE);
		QUANTITY = 1;
	}

	public void look()
	{
		World.ENTITY_MANAGER.get(ID + ":" + CONTENT_INSTANCES.get(0)).look();
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

	public boolean contains(String idInstance)
	{
		for(String instance : CONTENT_INSTANCES)
			if(idInstance.equals(ID + ":" + instance))
				return true;
		return false;
	}

	public void add(Item item)
	{
		if(item.NAME.equals(NAME))
			if(!CONTENT_INSTANCES.contains(item))
			{
				CONTENT_INSTANCES.add(item.ID);
				QUANTITY++;
			}
	}

	public void add(Container container)
	{
		if(container.NAME.equals(NAME))
			if(!CONTENT_INSTANCES.contains(container))
			{
				CONTENT_INSTANCES.add(container.ID);
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

	public void remove(Item item)
	{
		for(String instance : CONTENT_INSTANCES)
			if(item.INSTANCE.equals(instance))
			{
				CONTENT_INSTANCES.remove(instance);
				QUANTITY--;
			}
	}

	public void remove(Container container)
	{
		for(String id : CONTENT_INSTANCES)
			if(container.ID.equals(id))
			{
				CONTENT_INSTANCES.remove(id);
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
	 * Returns a new content slot with the specified number of objects or, if the specified number is greater than the existing number of objects, this content slot itself
	 * @param num number of objects requested
	 * @return content slot with the requested number of objects
	 */
	public ContentSlot take(int num)
	{
		if(num >= QUANTITY)
			return this;
		int i = 1;
		ContentSlot output = new ContentSlot(CONTENT_INSTANCES.get(CONTENT_INSTANCES.size() - i));
		CONTENT_INSTANCES.remove(CONTENT_INSTANCES.size() - i);
		i++;
		num--;
		while(num <= QUANTITY && num > 0)
		{
			output.add(CONTENT_INSTANCES.get(CONTENT_INSTANCES.size() - i));
			CONTENT_INSTANCES.remove(CONTENT_INSTANCES.size() - i);
			i++;
			num--;
		}
		return output;
	}
}

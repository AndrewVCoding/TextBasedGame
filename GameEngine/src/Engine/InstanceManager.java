package Engine;

import java.util.ArrayList;
import java.util.List;

public class InstanceManager
{
	public String ID;
	public int instances = 0;
	public final List<Entity> ENTITIES = new ArrayList<>();

	public InstanceManager(Entity entity)
	{
		ID = entity.ID;
		ENTITIES.add(entity);
	}

	public void add(Entity entity)
	{
		if(Integer.parseInt(entity.INSTANCE) > instances)
			instances = Integer.parseInt(entity.INSTANCE);
		ENTITIES.add(entity);
	}

	public void remove(Entity entity)
	{
		ENTITIES.remove(entity);
	}

	public Item retrieveItem(String instance)
	{
		for(Entity entity : ENTITIES)
			if(entity.INSTANCE.equals(instance))
				return (Item) entity;
		return null;
	}

	public Container retrieveContainer(String instance)
	{
		for(Entity entity : ENTITIES)
			if(entity.INSTANCE.equals(instance))
				return (Container) entity;
		return null;
	}

	public String toString()
	{
		StringBuilder output = new StringBuilder("     ID: " + ID);
		for(Entity entity : ENTITIES)
			output.append("\n          ").append(entity.INSTANCE).append(":").append(entity.NAME);
		return output.toString();
	}
}

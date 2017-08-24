import java.util.ArrayList;
import java.util.List;

public class EntityManager
{
	public class InstanceManager
	{
		public String ID;
		private int instances = 0;
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

	public List<InstanceManager> INSTANCE_MANAGERS = new ArrayList<>();

	public EntityManager(List<Entity> entities)
	{
		List<String> existingIDs = new ArrayList<>();

		//Get a list of all IDs needed

		if(INSTANCE_MANAGERS.size() == 0)
			INSTANCE_MANAGERS.add(new InstanceManager(new Entity()));

		for(Entity entity : entities)
			add(entity);
	}

	/**
	 * Add a new Entity to the game
	 *
	 * @param entity
	 */
	public void add(Entity entity)
	{
		boolean found = false;

		for(InstanceManager im : INSTANCE_MANAGERS)
		{
			if(entity.ID.equals(im.ID))
			{
				found = true;
				im.add(entity);
			}
		}

		if(!found)
			INSTANCE_MANAGERS.add(new InstanceManager(entity));
	}

	/**
	 * Returns the Entity specified by ID:Instance
	 * @param idInstance
	 * @return
	 */
	public Entity get(String idInstance)
	{
		Entity output = null;
		String[] params = idInstance.split(":");

		for(InstanceManager im : INSTANCE_MANAGERS)
			if(im.ID.equals(params[0]))
				output = im.retrieveItem(params[1]);
		return output;
	}

	/**
	 * Get the highest instance number of a specific entity by ID
	 *
	 * @param id
	 * @return
	 */
	public int getInstance(String id)
	{
		for(InstanceManager im : INSTANCE_MANAGERS)
			if(im.ID.equals(id))
				return im.instances;
		return 0;
	}

	/**
	 * returns the specified instance of an Item
	 * @param idInstance
	 * @return
	 */
	public Item getItem(String idInstance)
	{
		Item output = null;
		String[] params = idInstance.split(":");

		for(InstanceManager im : INSTANCE_MANAGERS)
			if(im.ID.equals(params[0]))
				output = im.retrieveItem(params[1]);
		return output;
	}

	/**
	 * returns the specified instance of a Container
	 * @param idInstance
	 * @return
	 */
	public Container getContainer(String idInstance)
	{
		Container output = null;
		String[] params = idInstance.split(":");

		for(InstanceManager im : INSTANCE_MANAGERS)
			if(im.ID.equals(params[0]))
				output = im.retrieveContainer(params[1]);
		return output;
	}

	public String toString()
	{
		StringBuilder output = new StringBuilder("EntityManager: ");
		for(InstanceManager im: INSTANCE_MANAGERS)
			output.append("\n").append(im.toString());
		return output.toString();
	}
}

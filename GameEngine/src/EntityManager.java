import java.util.ArrayList;
import java.util.List;

public class EntityManager
{

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

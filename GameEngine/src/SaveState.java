import java.util.List;

/**
 * Keeps track of where objects are in the world and their current state, as well as player information and exit information
 */
public class SaveState
{
	/**
	 * Every instance of an item has an itemState that tracks where it is located in the world and its ID
	 */
	public class itemState
	{
		Item item;
		String ID;
		String parentID;

		public itemState(Item i, String p)
		{
			item = i;
			ID = item.ID;
			parentID = p;
		}
	}

	/**
	 * Every instance of a container has an containerState that tracks where it is located in the world and its ID
	 */
	public class containerState
	{
		Container container;
		String ID;
		String parentID;

		public containerState(Container c, String p)
		{
			container = c;
			ID = container.ID;
			parentID = p;
		}
	}

	/**
	 * Stores all information about the player
	 */
	public class playerState
	{
		public String NAME;
		public String CLASS;
		public int HP;
		public int MAX_HP;
		public String LOCATION_ID;
	}

	public List<itemState> ITEM_STATES;
	public List<containerState> CONTAINER_STATES;
	public playerState PLAYER_STATE;
	public Map MAP;

	//todo should accept any ID and find either a container or room that contains the object associated with the ID
	public String findParentID(String ID)
	{
		return null;
	}

	public itemState createItemState(String ID, String parentID)
	{
		return new itemState(World.getItem(ID), parentID);
	}

	public containerState createContainerState(String ID, String parentID)
	{
		return new containerState(World.getContainer(ID), parentID);
	}
}

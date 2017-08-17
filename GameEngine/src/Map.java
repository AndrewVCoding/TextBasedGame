import java.util.List;

/**
 * Graph representation of all rooms and their connectivity
 */
public class Map
{
	/**
	 * Used to represent each room and the exits from each room as a map of the world
	 * todo add ability to lock exits and describe them
	 */
	private class node
	{
		public Room room;
		public List<node> exits;

		/**
		 * creates the node pointing to the room it represents
		 * @param r the room represented by the node
		 */
		public node(Room r)
		{
			room = r;
		}

		/**
		 * adds a node to list of exits
		 * @param n the node to be added
		 */
		public void addExit(node n)
		{
			exits.add(n);
		}

		public String toString()
		{
			return room.ID;
		}
	}

	public List<node> MAP;
	public List<String> PATHS;
	public List<Room> ROOMS;

	public void build(List<String> paths, List<Room> rooms)
	{
		PATHS = paths;
		ROOMS = rooms;

		//Go through each room, and create a node from it
		for(String path: PATHS)
		{
			String[] pathData = path.split("\\|");

			node source = createNode(pathData[0]);
			node destination = createNode(pathData[1]);

			source.addExit(destination);
		}
	}

	private node createNode(String roomID)
	{
		for(node n: MAP)
			if(n.room.ID.equals(roomID))
				return n;

		node n = new node(World.getRoom(roomID));
		MAP.add(n);

		return n;
	}
}

import java.util.ArrayList;
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
		public String id;
		public List<node> exits = new ArrayList<>();

		/**
		 * creates the node pointing to the room it represents
		 *
		 * @param r the room represented by the node
		 */
		public node(Room r)
		{
			room = r;
			id = r.ID;
		}

		/**
		 * adds a node to list of exits
		 *
		 * @param n the node to be added
		 */
		public void addExit(node n)
		{
			exits.add(n);
		}

		public String toString()
		{
			return id;
		}

		public boolean equals(String input)
		{
			return id.equals(input);
		}
	}

	public List<node> MAP = new ArrayList<>();
	public List<String> PATHS = new ArrayList<>();
	public List<Room> ROOMS = new ArrayList<>();

	public void build(List<String> paths, List<Room> rooms)
	{
		PATHS = paths;
		ROOMS = rooms;

		//Go through each room, and create a node from it
		for(Room room : ROOMS)
		{
			node n = new node(room);
			MAP.add(n);
		}

		//For each path, add the destination node as an exit from the source node
		for(String path : PATHS)
		{
			String[] pathData = path.split("\\|");

			getNode(pathData[0]).addExit(getNode(pathData[1]));
		}
	}

	private node createNode(String roomID)
	{
		if(MAP.size() > 0)
		{
			for(node n : MAP)
				if(n.equals(roomID))
					return n;
		}
		node n = new node(World.getRoom(roomID));
		MAP.add(n);
		return n;
	}

	private node getNode(Room room)
	{
		for(node n : MAP)
			if(n.equals(room.ID))
				return n;
		return null;
	}

	private node getNode(String roomID)
	{
		for(node n : MAP)
			if(n.equals(roomID))
				return n;

		return null;
	}

	public List<Room> getExits()
	{
		List<Room> output = new ArrayList<>();

		for(node n: getNode(Player.LOCATION).exits)
			output.add(n.room);

		return output;
	}

	public void print()
	{
		String output = "";
		for(node n : MAP)
		{
			output += n.room.NAME + ":" + n.id + "\n";
			for(node e : n.exits)
				output += "     " + e.room.NAME + ":" + e.id + "\n";
		}

		System.out.println(output);
	}
}

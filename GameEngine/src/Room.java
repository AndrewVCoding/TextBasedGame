import java.util.ArrayList;
import java.util.List;

public class Room
{
	public String NAME;
	public String ENTRANCE;
	public String DESCRIPTION;
	public String LOOK;
	public List<String> EXITS = new ArrayList<>();
	public List<Item> ITEMS = new ArrayList<>();
	public List<Container> CONTAINERS = new ArrayList<>();


	public String enter(boolean move)
	{
		String exits = "";
		String contents = "";
		for(String exit : EXITS)
			exits += "\n   " + exit;
		for(Item item : ITEMS)
			contents += item.NAME + ", ";
		for(Container container : CONTAINERS)
			contents += container.NAME + ", ";
		if(move)
			return ENTRANCE + "\n\n" + DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
		else
			return DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
	}

	public String look()
	{
		String exits = "";
		String contents = "";
		for(String exit : EXITS)
			exits += "\n   " + exit;
		for(Item item : ITEMS)
			contents += item.NAME + ", ";
		for(Container container : CONTAINERS)
			contents += container.NAME + ", ";
		return LOOK;
	}

	public boolean isExit(Room room)
	{
		for(String name : EXITS)
			if(name.equals(room.NAME))
				return true;
		return false;
	}
}

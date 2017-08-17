import java.util.ArrayList;
import java.util.List;

public class Room
{
	public String NAME;
	public String ID;
	public String ENTRANCE;
	public String DESCRIPTION;
	public String LOOK;
	//All contents should be stored by ID rather than name to allow multiple distinct objects with the same name
	public final List<String> EXITS = new ArrayList<>();
	public final List<Item> ITEMS = new ArrayList<>();
	public final List<Container> CONTAINERS = new ArrayList<>();


	public String enter(boolean move)
	{
		StringBuilder exits = new StringBuilder();
		StringBuilder contents = new StringBuilder();
		for(String exit : EXITS)
			exits.append("\n   ").append(exit);
		for(Item item : ITEMS)
			contents.append(item.NAME).append(", ");
		for(Container container : CONTAINERS)
			contents.append(container.NAME).append(", ");
		if(move)
			return ENTRANCE + "\n\n" + DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
		else
			return DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
	}

	public String look()
	{
		StringBuilder exits = new StringBuilder();
		StringBuilder contents = new StringBuilder();
		for(String exit : EXITS)
			exits.append("\n   ").append(exit);
		for(Item item : ITEMS)
			contents.append(item.NAME).append(", ");
		for(Container container : CONTAINERS)
			contents.append(container.NAME).append(", ");
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

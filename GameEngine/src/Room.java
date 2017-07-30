import java.util.ArrayList;
import java.util.List;

public class Room
{
	public String NAME;
	public String ENTRANCE;
	public String DESCRIPTION;
	public List<String> EXITS = new ArrayList<>();
	public List<String> CONTENTS = new ArrayList<>();


	public String enter()
	{
		String exits = "";
		String contents = "";
		for(String exit : EXITS)
			exits += "\n   " + exit;
		for(String item : CONTENTS)
			contents += item + ", ";
		return ENTRANCE + "\n" + DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits;
	}

	public String look()
	{
		String exits = "";
		String contents = "";
		for(String exit : EXITS)
			exits += "\n   " + exit;
		for(String item : CONTENTS)
			contents += item + ", ";
		return DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits;
	}

	public boolean isExit(Room room)
	{
		for(String name : EXITS)
			if(name.equals(room.NAME))
				return true;
		return false;
	}
}

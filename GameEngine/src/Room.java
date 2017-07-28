import java.util.ArrayList;
import java.util.List;

public class Room
{
	public String NAME;
	public String ENTRANCE;
	public String DESCRIPTION;
	public List<String> EXITS = new ArrayList<>();


	public String enter()
	{
		String exits = "";
		for(String exit: EXITS)
			exits += "\n   " + exit;
		return ENTRANCE + "\n" + DESCRIPTION + "\nExits: " + exits;
	}

	public String look()
	{
		String exits = "";
		for(String exit: EXITS)
			exits += "\n   " + exit;
		return DESCRIPTION + "\nExits: " + exits;
	}

	public boolean exitExists(String exit)
	{
		for(String e: EXITS)
			if(e.equalsIgnoreCase(exit))
				return true;
		return false;
	}
}

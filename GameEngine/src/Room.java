import java.util.ArrayList;
import java.util.List;

public class Room
{
	public String NAME = "Home";
	public String ENTRANCE = "You arrive home.";
	public String DESCRIPTION = "This is your home. It's very comfy.";
	public List<String> EXITS = new ArrayList<>();


	public String enter()
	{
		String exits = "";
		for(String exit: EXITS)
			exits += "\n   " + exit;
		return ENTRANCE + "\n" + DESCRIPTION + "\nWays out: " + exits;
	}

	public String look()
	{
		String exits = "";
		for(String exit: EXITS)
			exits += "\n   " + exit;
		return DESCRIPTION + "\nWays out: " + exits;
	}

	public boolean exitExists(String exit)
	{
		for(String e: EXITS)
			if(e.equalsIgnoreCase(exit))
				return true;
		return false;
	}
}

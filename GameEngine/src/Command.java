import java.util.ArrayList;
import java.util.List;

/**
 * Represents the command to be used and contains the references to relevant objects
 */
public class Command
{
	public String INPUT = "";
	public List<String> COMMAND = new ArrayList<>();
	public int NUMBER_OF_OBJECTS = 0;
	public Room DESTINATION;
	public ContentSlot OBJECT_ONE;
	public ContentSlot OBJECT_TWO;
	private List<ContentSlot> MATCHING_SLOTS = new ArrayList<>();
	private String[] SINGLE_OBJECT_COMMANDS = {"look (at|) ", "go (to|to the|)", "use (the|)", "(take|pick up) (the|)", "(activate|turn on) (the|)", "(deactivate|turn off) (the|)"};

	public Command(String input)
	{
		INPUT = input;

		//Get a list of slots whose names can be found in the command
		MATCHING_SLOTS = World.getAccesibleSlotsNamed(INPUT);
		COMMAND.add("unknown");
		COMMAND.add(INPUT);

		//If the command is to go to a room
		if(hasRoom())
			COMMAND.set(0, "go");

		//if there are multiple objects being referenced in the command, determine their order and look for a command that works between them
		else if(multiple())
		{
			System.out.print("mulitple objects in command");
			NUMBER_OF_OBJECTS = 2;
		}

		//Otherwise just look for commands with OBJECT_ONE
		else if(singleObj())
			singleObjCommand();

		//Or it's a command with no objects
		else
		{
			simpleCommand();
			System.out.print("no objects in command");
		}

		System.out.print("\n" + INPUT + ": ");
		for(String s:COMMAND)
			System.out.print(s + " | ");
		System.out.println("\n");
	}

	public void singleObjCommand()
	{
		System.out.print("one object in command");
		NUMBER_OF_OBJECTS = 1;
		for(int i = 0; i < SINGLE_OBJECT_COMMANDS.length; i++)
			if(INPUT.matches(SINGLE_OBJECT_COMMANDS[i] + OBJECT_ONE.NAME))
			{
				oneObjectCommand(i);
				break;
			}
	}

	private void simpleCommand()
	{
		if(GameSystems.GAME_STATE.equals("start"))
		{
			if(INPUT.equals("create character"))
				COMMAND.add("create character");
		}
		else if(GameSystems.GAME_STATE.equals("character creation"))
		{
			COMMAND.add(1, INPUT);
		}
		else if(GameSystems.GAME_STATE.equals("idle"))
		{
			//Here can be all of the commands that don't require objects as targets
		}
	}

	public String get(int index)
	{
		if(index < COMMAND.size())
			return COMMAND.get(index);
		else
			return "index: " + index + " DNE";
	}

	/**
	 * Determine if there are two objects in the command, and determine their order
	 * @return
	 */
	private boolean multiple()
	{
		if(MATCHING_SLOTS.size() > 1)
		for(ContentSlot slot_one : MATCHING_SLOTS)
		{
			OBJECT_ONE = slot_one;
			for(ContentSlot slot_two : MATCHING_SLOTS)
			{
				OBJECT_TWO = slot_two;
				if(INPUT.matches("[ \\w\\d]*" + OBJECT_ONE.NAME + "[ \\w\\d]*" + OBJECT_TWO.NAME))
					return true;
			}
		}
		OBJECT_TWO = null;
		return false;
	}

	/**
	 * @return true if there is only one object in the command
	 */
	private boolean singleObj()
	{
		if(OBJECT_ONE == null)
			return false;
		else
			return true;
	}

	/**
	 * Sets the action keyword for the command
	 * @param i the index of the action matching the command, for when there is one object involved
	 */
	private void oneObjectCommand(int i)
	{
		if(i == 1)
			COMMAND.set(0, "look");
		if(i == 2)
			COMMAND.set(0, "go");
		if(i == 3)
			COMMAND.set(0, "use");
		if(i == 4)
			COMMAND.set(0, "take");
		if(i == 5)
			COMMAND.set(0, "activate");
		if(i == 6)
			COMMAND.set(0, "deactivate");
	}

	private void multiObjectCommand()
	{

	}

	private boolean hasRoom()
	{
		for(Room room : World.getVisibleExits())
			if(INPUT.matches("(go|go to|go to the|enter|enter the) " + room.NAME))
			{
				DESTINATION = room;
				return true;
			}
		return false;
	}

	private void command()
	{

	}
}

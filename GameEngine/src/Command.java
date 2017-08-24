/**
 * Represents the command to be used and contains the references to relevant objects
 */
public class Command
{
	public String INPUT = "";
	public String COMMAND = "unknown";
	public int NUMBER_OF_OBJECTS = 0;
	public Room DESTINATION;
	public ContentSlot SLOT_ONE;
	public ContentSlot SLOT_TWO;

	public Command(String input)
	{
		INPUT = input;
		NUMBER_OF_OBJECTS = getNumberOfObj();

		if(NUMBER_OF_OBJECTS == 0)
		{
			if(INPUT.matches("go [ \\w\\d]*"))
			{
				if(getRoom())
					COMMAND = "go";
			}
			else
				COMMAND = CommandParser.simpleCommand(INPUT);
		}
		else if(NUMBER_OF_OBJECTS == 1)
			COMMAND = CommandParser.oneObjCommand(INPUT, SLOT_ONE.NAME);
		else if(NUMBER_OF_OBJECTS == 2)
			COMMAND = CommandParser.twoObjCommand(INPUT, SLOT_ONE.NAME, SLOT_TWO.NAME);

	}

	public boolean getRoom()
	{
		for(Room room : World.getVisibleExits())
			if(INPUT.matches("go (to |to the |)" + room.NAME))
			{
				DESTINATION = room;
				return true;
			}
		return false;
	}

	public int getNumberOfObj()
	{
		int output = 0;
		for(ContentSlot slotOne : World.getAccessibleSlots())
		{
			if(INPUT.matches("[ \\w\\d]*" + slotOne.NAME + "[\\w\\d]*"))
			{
				output = 1;
				SLOT_ONE = slotOne;
				for(ContentSlot slotTwo : World.getAccessibleSlots())
					if(INPUT.matches("[ \\w\\d]*" + slotOne.NAME + "[ \\w\\d]*" + slotTwo.NAME + "[ \\w\\d]*"))
					{
						output = 2;
						SLOT_TWO = slotTwo;
					}
			}
		}
		return output;
	}

	public boolean equals(String input)
	{
		return COMMAND.equals(input);
	}
}

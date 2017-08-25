/**
 * Represents the command to be used and contains the references to relevant objects
 */
public class Command
{
	public String INPUT = "";
	public String COMMAND = "unknown";
	public String TARGET = "";
	public int NUMBER_OF_OBJECTS = 0;
	public Room DESTINATION;
	public ContentSlot SLOT_ONE;
	public ContentSlot SLOT_TWO;

	public Command(String input)
	{
		INPUT = input;

		if(INPUT.matches("save \\w*"))
		{
			COMMAND = "save";
			String[] source = INPUT.split(" ");
			TARGET = source[1];
		}
		else if(INPUT.matches("load \\w*"))
		{
			COMMAND = "load";
			String[] source = INPUT.split(" ");
			TARGET = source[1];
		}
		else if(INPUT.matches("list saves"))
			COMMAND = "list saves";
		else
		{
			NUMBER_OF_OBJECTS = getNumberOfObj();
			if(NUMBER_OF_OBJECTS == 0)
			{
				COMMAND = CommandParser.simpleCommand(this);
			}
			else if(NUMBER_OF_OBJECTS == 1)
				COMMAND = CommandParser.oneObjCommand(INPUT, SLOT_ONE.NAME);
			else if(NUMBER_OF_OBJECTS == 2)
				COMMAND = CommandParser.twoObjCommand(INPUT, SLOT_ONE.NAME, SLOT_TWO.NAME);
		}

	}

	public boolean hasRoom()
	{
		try
		{
			for(Room room : World.getVisibleExits())
				if(INPUT.matches("go (to |to the |)" + room.NAME))
				{
					DESTINATION = room;
					return true;
				}
		} catch(NullPointerException e)
		{
			System.out.println("No exits found");
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

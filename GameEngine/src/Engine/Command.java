package Engine;

import java.util.ArrayList;
import java.util.List;

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
	public List<String[]> EFFECTS_ONE = new ArrayList<>();
	public List<String[]> EFFECTS_TWO = new ArrayList<>();
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
				CommandParser.simpleCommand(this);
			}
			else if(NUMBER_OF_OBJECTS == 1)
				CommandParser.oneObjCommand(this);
			else if(NUMBER_OF_OBJECTS == 2)
				CommandParser.twoObjCommand(this);
		}

	}

	/**
	 * Determines whether there is a Engine.Room mentioned in the input
	 * @return true if a Engine.Room is mentioned
	 */
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

	/**
	 * Gets the number of entities contained in the user command and the order in which they are mentioned
	 * @return the number of entities
	 */
	public int getNumberOfObj()
	{
		int output = 0;
		for(ContentSlot slotOne : World.getAccessibleSlots())
		{
			if(INPUT.matches("[ \\w\\d]*" + slotOne.NAME + "[\\w\\d]*"))
			{
				output = 1;
				SLOT_ONE = slotOne;
				getFirstEffects();
				for(ContentSlot slotTwo : World.getAccessibleSlots())
					if(INPUT.matches("[ \\w\\d]*" + slotOne.NAME + "[ \\w\\d]*" + slotTwo.NAME + "[ \\w\\d]*"))
					{
						output = 2;
						SLOT_TWO = slotTwo;
						getSecondEffects();
					}
			}
		}
		return output;
	}

	/**
	 * Gets the effects list from the first entity
	 */
	public void getFirstEffects()
	{
		EFFECTS_ONE = SLOT_ONE.EFFECTS;
	}

	/**
	 * Gets the effects list from the second entity
	 */
	public void getSecondEffects()
	{
		EFFECTS_TWO = SLOT_TWO.EFFECTS;
	}

	public boolean equals(String input)
	{
		return COMMAND.equals(input);
	}
}

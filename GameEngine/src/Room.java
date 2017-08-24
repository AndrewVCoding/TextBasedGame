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
	//todo move everything over to the new ContentSlot system
	public final List<ContentSlot> CONTENTS = new ArrayList<>();
	public final List<String> EXITS = new ArrayList<>();
	private List<ContentSlot> TO_PURGE = new ArrayList<>();

	public String spawn()
	{
		Interface.MOVED = false;
		System.out.println(":enter:");
		purge();
		StringBuilder exits = new StringBuilder();
		StringBuilder contents = new StringBuilder();
		for(String exit : EXITS)
			exits.append("\n   ").append(exit);
		buildDisplay(exits, contents);
			return DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
	}

	public String enter(boolean moved)
	{
		Interface.MOVED = moved;
		System.out.println(":enter:");
		purge();
		StringBuilder exits = new StringBuilder();
		StringBuilder contents = new StringBuilder();
		for(String exit : EXITS)
			exits.append("\n   ").append(exit);
		buildDisplay(exits, contents);
		if(moved)
			return ENTRANCE + "\n\n" + DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
		else
			return DESCRIPTION + "\n\nContains: " + contents + "\nExits: " + exits + "\n" + "_______________________________________________________________";
	}

	public String look()
	{
		purge();
		StringBuilder exits = new StringBuilder();
		StringBuilder contents = new StringBuilder();
		for(String exit : EXITS)
			exits.append("\n   ").append(exit);
		for(ContentSlot slot : CONTENTS)
		{
			if(slot.QUANTITY > 0)
				contents.append(slot.NAME).append(", ");
			else
				CONTENTS.remove(slot);
		}
		return LOOK;
	}

	public void buildDisplay(StringBuilder exits, StringBuilder contents)
	{
		for(ContentSlot slot : CONTENTS)
		{
			if(slot.QUANTITY == 1)
				contents.append(slot.NAME).append(", ");
			else if(slot.QUANTITY > 1)
				contents.append(slot.NAME + " x" + slot.QUANTITY).append(", ");
		}
	}

	public boolean contains(Item item)
	{
		for(ContentSlot slot : CONTENTS)
			for(String id : slot.CONTENT_INSTANCES)
				if(item.ID.equals(id))
					return true;
		return false;
	}

	public boolean contains(Container container)
	{
		for(ContentSlot slot : CONTENTS)
			for(String id : slot.CONTENT_INSTANCES)
				if(container.ID.equals(id))
					return true;
		return false;
	}

	public boolean contains(String id)
	{
		for(ContentSlot slot : CONTENTS)
			for(String contentID : slot.CONTENT_INSTANCES)
				if(contentID.equals(id))
					return true;
		return false;
	}

	public void add(Item item)
	{
		boolean added = false;
		for(ContentSlot slot : CONTENTS)
			if(slot.NAME.equals(item.NAME))
			{
				slot.add(item);
				added = true;
				break;
			}
		if(!added)
			CONTENTS.add(new ContentSlot(item));
	}

	public void add(Container container)
	{
		boolean added = false;
		for(ContentSlot slot : CONTENTS)
			if(slot.NAME.equals(container.NAME))
			{
				slot.add(container);
				added = true;
				break;
			}
		if(!added)
			CONTENTS.add(new ContentSlot(container));
	}

	public void remove(Item item)
	{
		if(contains(item))
			for(ContentSlot slot : CONTENTS)
				if(slot.NAME.equals(item.NAME))
				slot.remove(item);
	}

	public void remove(Container container)
	{
		if(contains(container))
			for(ContentSlot slot : CONTENTS)
				if(slot.NAME.equals(container.NAME))
					slot.remove(container);
	}

	public void remove(String id)
	{
		if(contains(id))
			for(ContentSlot slot : CONTENTS)
				if(slot.contains(id))
				slot.remove(id);
	}

	/**
	 * removes any ContentSlots which have been marked to be purged
	 */
	public void purge()
	{
		for(ContentSlot slot : CONTENTS)
		{
			if(slot.PURGE)
			{
				slot.purge();
				TO_PURGE.add(slot);
			}
		}

		for(ContentSlot slot : TO_PURGE)
			CONTENTS.remove(slot);
		TO_PURGE = new ArrayList<>();
	}
}

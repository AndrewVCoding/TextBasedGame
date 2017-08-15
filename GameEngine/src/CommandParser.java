import java.util.ArrayList;
import java.util.List;

public class CommandParser
{
	public static String[] parse(String input)
	{
		//Make sure commands are not case sensitive
		input = input.toLowerCase();
		//get a list of all exits
		List<Room> exits = GameSystems.getVisibleExits();
		//get a list of all visible items
		List<Item> visibleItems = GameSystems.getVisibleItems();

		/*
		input: start
		 */
		if(input.matches("start"))
		{
			return new String[]{"start"};
		}

		/*
		input: look
		should look at room if no target is specified, otherwise isolate the words after "look at" and search for any visible items with the same name. If none exist, try to match an item name in the input.
		 */
		if(input.matches("look"))
			return new String[]{"look", "room", Player.LOCATION.NAME};
		if(input.matches("look (at |)inventory"))
			return new String[]{"look", "inventory"};
		if(input.matches("look at [ \\w\\d]*"))
		{
			//Check if the target is an exact match for an item or a room
			for(Item item : visibleItems)
				if(input.matches("look (at |)" + item.NAME))
					return new String[]{"look", item.NAME};
		}

		/*
		input: go
		must have the complete or partial match of a visible room name following "go "
		 */
		if(input.matches("go[ \\w\\d]*"))
		{
			for(Room room : exits)
			{
				if(input.matches("go (to |to the |)" + room.NAME))
					return new String[]{"go", room.NAME};
			}
		}
		if(input.matches("go"))
			return new String[]{"go"};

		/*
		input: use
		can be basic: "use item"
		or can be: "use item1 on/with item2"
		these commands are much easier if looking for perfect matches without worrying about partial matches
		 */
		if(input.matches("use [ \\w\\d]*"))
		{
			Item source = null;
			//Check if "use" is followed by any item names that are visible
			for(Item item : visibleItems)
			{
				if(input.matches("use " + item.NAME))
				{
					return new String[]{"use", item.NAME};
				}
				else if(input.matches("use " + item.NAME + " [ \\w\\d]*"))
				{
					source = item;
				}
			}
			//Now have the source item if there was more to the input after the item. If a second item name is not found, then just "use item"
			if(source != null)
			{
				for(Item item: visibleItems)
				{
					if(input.matches("use " + source.NAME + " (on|with) " + item.NAME))
					{
						return new String[]{"use", source.NAME, item.NAME};
					}
					else
						return new String[]{"use", item.NAME};
				}
			}
		}

		/*
		input: eat
		can be basic: "eat food"
		these commands are much easier if looking for perfect matches without worrying about partial matches
		 */
		if(input.matches("eat [ \\w\\d]*"))
		{
			//Check if "eat" is followed by any item names that are visible
			for(Item item : visibleItems)
			{
				if(input.matches("eat " + item.NAME))
					return new String[]{"consume", item.NAME};
			}
		}

		/*
		input: inventory
		 */
		if(input.matches("inventory"))
			return new String[]{"look", "inventory"};

		/*
		input: take
		 */
		if(input.matches("take [ \\w\\d]*"))
		{
			for(Item item: visibleItems)
				if(input.matches("take " + item.NAME))
					return new String[]{"take", item.NAME};
		}

		/*
		input: open
		 */
		if(input.matches("open [ \\w\\d]"))
			for(Container container: GameSystems.getVisibleContainers())
				if(input.matches("open [ \\d\\w]" + container.NAME + "[ \\d\\w]"))
					return new String[]{"open", container.NAME};

		return new String[]{"unknown"};
	}
}

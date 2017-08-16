import java.lang.management.GarbageCollectorMXBean;

public class IdleState
{
	public static String[] COMMAND;

	public static void look(String[] command)
	{
		//"look", "inventory"
		if(command[1].equalsIgnoreCase("inventory"))
			Player.viewInventory();
			//"look", "room"
		else if(command[1].equals("room"))
			Interface.display(Player.LOCATION.look());
			//"look", item.NAME
		else
			for(Item item : GameSystems.getAccessibleItems())
				if(command[1].equals(item.NAME))
					Interface.display(item.look());
	}

	public static void take(String[] command)
	{
		Item target = new Item();

		for(Item item : GameSystems.getAccessibleItems())
			if(item.NAME.equals(command[1]))
				target = item;

		if(target.TAKEABLE)
		{
			//If the item is in the room
			if(Player.LOCATION.ITEMS.contains(target))
			{
				Player.LOCATION.ITEMS.remove(target);
				Player.INVENTORY.add(target);
			}

			//Otherwise, check if it is in a container
			else
				for(Container container : GameSystems.getVisibleContainers())
					if(container.CONTENTS.contains(target) && container.OPEN)
					{
						container.CONTENTS.remove(target);
						Player.INVENTORY.add(target);
					}
		}
		Interface.display(target.PICKUP);
	}

	public static void act(String[] command)
	{
		Item source = World.getItem(command[1]);

		//The item is usable
		if(source != null)
		{
			String action = command[0];
			int index = source.effect(action);

			//If the specified action is valid for the item
			if(index > -1)
			{
				if(action.equalsIgnoreCase("use"))
					GameSystems.use(source, index);
				else if(action.equalsIgnoreCase("consume"))
					GameSystems.consume(source, index);
				else
					GameSystems.invalid();
			}
		}
	}
}

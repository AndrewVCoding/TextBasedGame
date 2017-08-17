/**
 * Available commands while idle are:
 * look, take, use, consume, go
 */
class IdleState
{

	/**
	 * When the command is go XXXXX then move the player to the specified room
	 * todo allow using just N/S/E/W as quick commands, as well as just putting the name of the exit/destination room
	 */
	public static void go(String[] command)
	{
		Player.moveRoom(StaticWorld.getRoom(command[1]));
	}

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

	/**
	 * When using an object, array contents are as follows
	 * [   act | source | target | activation description | deactivation description ]
	 * [    0  |    1   |    2   |           3            |              4           ]
	 */
	public static void use(Item source, int index, String[] command)
	{
		Item target = StaticWorld.getItem(command[2]);

		if(source.EFFECTS[index + 2].equals("player"))
		{
			//Place holder for future items
		}

		else if(source.EFFECTS[index + 2].equals(target != null ? target.NAME : null))
		{
			if(!target.ACTIVATED)
				Interface.display(source.EFFECTS[index + 3]);
			if(target.ACTIVATED)
				Interface.display(source.EFFECTS[index + 4]);

			target.ACTIVATED = !target.ACTIVATED;
		}
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
		Item source = StaticWorld.getItem(command[1]);

		//The item is usable
		if(source != null)
		{
			String action = command[0];
			int index = source.effect(action);

			//If the specified action is valid for the item
			if(index > -1)
			{
				if(action.equalsIgnoreCase("use"))
					use(source, index, command);
				else if(action.equalsIgnoreCase("consume"))
					consume(source, index);
				else
					GameSystems.invalid();
			}
		}
	}

	/**
	 * When consuming an object, array contents are as follows
	 * [ consume | target | attribute | amount | description]
	 * [ index   |    +1  |    +2     |   +3   |     +4     ]
	 *
	 * @param source The item being consumed
	 * @param index The index of the Effects array of the item where the consume effect starts
	 */
	private static void consume(Item source, int index)
	{
		System.out.println("consuming " + source.NAME);
		if(source.EFFECTS[index + 2].equalsIgnoreCase("health"))
		{
			Player.heal(Integer.parseInt(source.EFFECTS[index + 3]));
			Interface.display(source.EFFECTS[index + 4]);
		}
		else
			Interface.display("The item had no effect");
	}
}

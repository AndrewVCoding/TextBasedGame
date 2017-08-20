/**
 * Available commands while idle are:
 * look, take, use, consume, go
 */
class IdleState
{

	/**
	 * When the command is go XXXXX then move the player to the specified room
	 * todo allow using quicker commands to move to other rooms, giving each exit multiple reference names to look for in a command
	 */
	public static void go(Command command)
	{
		Player.moveRoom(command.DESTINATION);
	}

	public static void look(Command command)
	{
		//"look", "inventory"
		if(command.get(1).equalsIgnoreCase("inventory"))
			Player.viewInventory();
		else if(command.get(1).equals("room"))
			Interface.display(Player.LOCATION.look());
		else if(command.OBJECT_ONE != null)
			command.OBJECT_ONE.look();
	}

	/**
	 * When using an object, array contents are as follows
	 * [   act | source | target | activation description | deactivation description ]
	 * [    0  |    1   |    2   |           3            |              4           ]
	 */
	public static void use(Command command, int index)
	{

	}

	public static void take(Command command)
	{

	}

	public static void act(Command command)
	{
		Item source = World.getItem(command.get(1));

		//The item is usable
		if(source != null)
		{
			String action = command.get(1);
			int index = source.effect(action);

			//If the specified action is valid for the item
			if(index > -1)
			{
				if(action.equalsIgnoreCase("use"))
					use(command, index);
				else if(action.equalsIgnoreCase("consume"))
					consume(source, index);
				else
					GameSystems.invalid(command);
			}
		}
	}

	/**
	 * When consuming an object, array contents are as follows
	 * [ consume | target | attribute | amount | description]
	 * [ index   |    +1  |    +2     |   +3   |     +4     ]
	 *
	 * @param source The item being consumed
	 * @param index  The index of the Effects array of the item where the consume effect starts
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

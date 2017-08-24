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
		if(command.equals("look inventory"))
			Player.viewInventory();
		else if(command.equals("look"))
			Interface.display(Player.LOCATION.look());
		else if(command.equals("look at"))
			command.SLOT_ONE.look();
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
		if(Player.INVENTORY.contains(command.SLOT_ONE))
			Interface.display("You already have that");
		else
			command.SLOT_ONE.take(1);
	}

	public static void act(Command command)
	{

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
		if(source.EFFECTS[index + 2].equalsIgnoreCase("health"))
		{
			Player.heal(Integer.parseInt(source.EFFECTS[index + 3]));
			Interface.display(source.EFFECTS[index + 4]);
		}
		else
			Interface.display("The item had no effect");
	}
}

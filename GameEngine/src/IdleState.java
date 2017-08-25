/**
 * Available commands while idle are:
 * look, take, effect, consume, go
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
	 * [ effect | target | activation description | deactivation description ]
	 * [   0    |    1   |            2           |             3            ]
	 */
	public static void effect(Command command)
	{
		//get the index of the effect
		String[] com = command.COMMAND.split("|");
		int index = Integer.parseInt(com[1]);

		command.SLOT_ONE.effect(index);
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
}

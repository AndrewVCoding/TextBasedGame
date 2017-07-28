public class CommandHandler
{
	public class command
	{
		command next;
		public String source;
		public String input;
		public String target;

		public command(String source, String input, String target)
		{
			this.source = source;
			this.input = input;
			this.target = target;
		}

		public void display()
		{
			System.out.println("Source: " + source + " || Input: " + input + " || Target: " + target);
		}
	}

	private class commandStack
	{
		final command head = new command("Head", "Head", "Head");

		public void push(command input)
		{
			input.next = head.next;
			head.next = input;
		}

		public command pop()
		{
			command output = head.next;
			head.next = output.next;
			return output;
		}

		public command look()
		{
			if(head.next != null)
				return head.next;
			else
				return head;
		}

		public void display()
		{
			System.out.println();
			command temp = head;
			while(temp.next != null)
			{
				temp = temp.next;
				System.out.println("Source: " + temp.source + " || Command: " + temp.input + " || Target: " + temp
						.target);
			}
		}
	}

	private class commandQueue
	{
		final command head = new command("Head", "Head", "Head");
		command tail = head;

		public void enqueue(command input)
		{
			tail.next = input;
			tail = input;
		}

		public command dequeue()
		{
			command output = head.next;
			if(output.next != null)
			head.next = output.next;
			return output;
		}

		public command look()
		{
			if(head.next != null)
				return head.next;
			else
				return head;
		}

		public void display()
		{
			System.out.println();
			command temp = head;
			System.out.println("Source: " + temp.source + " || Command: " + temp.input + " || Target: " + temp.target);
			while(temp.next != null)
			{
				temp = temp.next;
				System.out.println("Source: " + temp.source + " || Command: " + temp.input + " || Target: " + temp
						.target);
			}

			System.out.println("Queue Head: " + head.input + "\nQueue Tail: " + tail.input);
		}
	}

	private Interface INTERFACE;
	private commandStack COMMAND_STACK;
	private commandQueue COMMAND_QUEUE;
	private GameStates GAME_STATES;
	private World WORLD;

	public CommandHandler(Interface inter)
	{
		INTERFACE = inter;
		COMMAND_STACK = new commandStack();
		WORLD = new World();
		GAME_STATES = new GameStates(INTERFACE, WORLD);
	}

	public void newCommand(String input)
	{
		COMMAND_STACK.push(new command(COMMAND_STACK.look().target, input, "parse"));
	}

	public void executeNextCommand()
	{
		GAME_STATES.parseCommand(COMMAND_STACK.look());
		if(GAME_STATES.POP)
			COMMAND_STACK.pop();
	}

	public void displayCommandQueue()
	{
		COMMAND_STACK.display();
	}
}

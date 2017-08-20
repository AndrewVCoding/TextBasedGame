import java.util.List;

class CommandParser
{
	public static Command parse(String input)
	{
		return new Command(input.toLowerCase());
	}
}

import javax.swing.*;

public class driver
{
	public static void main(String[] args)
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(mainWindow.getPreferredSize());
		mainWindow.setResizable(false);

		World.populate();
		mainWindow.setDisplay("Welcome! To begin the game and create your character, input \"start\"");

		//For testing purposes, to get into the game faster, automate the character creation
		GameSystems.autoStartGame("Andrew", 1);
		mainWindow.updateDisplay();
	}
}

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
		mainWindow.setDisplay("Welcome!");
	}
}

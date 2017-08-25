import javax.swing.*;

class driver
{
	public static void main(String[] args)
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.setSize(mainWindow.getPreferredSize());
		mainWindow.setResizable(false);

		DataHandler.loadAllBaseFiles();
		Interface.welcome();

		mainWindow.updateDisplay();
	}
}

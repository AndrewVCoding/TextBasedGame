package Editor;

import Data.DataHandler;

import javax.swing.*;

public class editor
{
	public static void main(String[] args)
	{
        DataHandler dataHandler = new DataHandler(System.getProperty("user.dir"));

		//Create the WorldEditorWindow
		WorldEditorWindow worldEditorWindow = new WorldEditorWindow(dataHandler);
		worldEditorWindow.setVisible(true);
		worldEditorWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		worldEditorWindow.setSize(worldEditorWindow.getPreferredSize());
		worldEditorWindow.setResizable(false);
		worldEditorWindow.setTitle("World Editor");

		//Create the WorldSelectWindow
		WorldSelectWindow worldSelectWindow = new WorldSelectWindow(worldEditorWindow, dataHandler);
		worldSelectWindow.setVisible(true);
		worldSelectWindow.setSize(worldSelectWindow.getPreferredSize());
		worldSelectWindow.setResizable(false);
		worldSelectWindow.setTitle("World Select");

	}
}

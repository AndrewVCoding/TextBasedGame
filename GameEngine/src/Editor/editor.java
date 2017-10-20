package Editor;

import javax.swing.*;

public class editor
{
	public static void main(String[] args)
	{
		//Create the WorldEditorWindow
		WorldEditorWindow worldEditorWindow = new WorldEditorWindow();
		worldEditorWindow.setVisible(true);
		worldEditorWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		worldEditorWindow.setSize(worldEditorWindow.getPreferredSize());
		worldEditorWindow.setResizable(false);
		worldEditorWindow.setTitle("World Editor");

		//Create the WorldSelectWindow
		WorldSelectWindow worldSelectWindow = new WorldSelectWindow(worldEditorWindow);
		worldSelectWindow.setVisible(true);
		worldSelectWindow.setSize(worldSelectWindow.getPreferredSize());
		worldSelectWindow.setResizable(false);
		worldSelectWindow.setTitle("World Select");
	}
}

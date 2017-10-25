package Editor;

import Data.DataHandler;
import Data.DataLoader;
import Data.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WorldSelectWindow extends JFrame implements ActionListener
{
	private JComboBox<String> WORLD_SELECTION;
	private JLabel LBL_MSG;
	private JButton EDIT, NEW;
	private List<String> WORLDS;

	public String SELECTED_WORLD = "";
	private WorldEditorWindow EDITOR_WINDOW;
	private DataHandler DATA_HANDLER;

	public WorldSelectWindow(WorldEditorWindow editorWindow, DataHandler dataHandler)
	{
		setAlwaysOnTop(true);
		EDITOR_WINDOW = editorWindow;
		DATA_HANDLER = dataHandler;
		WORLD_SELECTION = new JComboBox<>();
		WORLD_SELECTION.setMinimumSize(GlobalGameConstants.COMBOBOX_SIZE);
		EDIT = new JButton("edit");
		EDIT.addActionListener(this);
		NEW = new JButton("new");
		NEW.addActionListener(this);
		LBL_MSG = new JLabel("Please select a world or create a new one");

		JPanel MAIN = new JPanel();
		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
											   .addComponent(LBL_MSG)
											   .addGroup(groupLayout.createSequentialGroup().addComponent(WORLD_SELECTION).addComponent(EDIT).addComponent(NEW)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
											 .addComponent(LBL_MSG)
											 .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(WORLD_SELECTION).addComponent(EDIT).addComponent(NEW)));
		groupLayout.linkSize(SwingConstants.VERTICAL, WORLD_SELECTION, EDIT, NEW);

		WORLDS = DataLoader.listFiles(Paths.RESOURCES);
		for(String world:WORLDS)
		{
			WORLD_SELECTION.addItem(world);
		}
		WORLD_SELECTION.addActionListener(this);
		MAIN.setLayout(groupLayout);
		add(MAIN);
	}

	private void selectWorld()
	{
		try
		{
			SELECTED_WORLD = WORLD_SELECTION.getSelectedItem().toString();
			//Then pass to the dataHandler which world to  load data  from
			DATA_HANDLER.setWorld(SELECTED_WORLD);
			System.out.println(SELECTED_WORLD);
			EDITOR_WINDOW.populateData(SELECTED_WORLD);
			dispose();
		}catch(NullPointerException n)
		{
			LBL_MSG.setText("No world selected");
			LBL_MSG.setForeground(Color.RED);
			System.out.println("No world selected");
		}
	}

	private void newWorld()
	{
		NewWorldWindow newWorldWindow = new NewWorldWindow();
		newWorldWindow.setVisible(true);
		newWorldWindow.setSize(newWorldWindow.getPreferredSize());
		newWorldWindow.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == EDIT)
			selectWorld();

		if(e.getSource() == NEW)
		newWorld();
	}
}

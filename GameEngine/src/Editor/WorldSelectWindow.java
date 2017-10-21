package Editor;

import Data.DataHandler;
import Data.DataLoader;
import Data.Paths;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WorldSelectWindow extends JFrame implements ActionListener
{
	private JComboBox<String> WORLDS_LIST;
	private JButton EDIT;
	private JButton NEW;
	private List<String> WORLDS;

	public String SELECTED_WORLD = "";
	private WorldEditorWindow EDITOR_WINDOW;
	private DataHandler DATA_HANDLER;

	public WorldSelectWindow(WorldEditorWindow editorWindow, DataHandler dataHandler)
	{
		EDITOR_WINDOW = editorWindow;
		DATA_HANDLER = dataHandler;
		WORLDS_LIST = new JComboBox<>();
		EDIT = new JButton("edit");
		EDIT.addActionListener(this);
		NEW = new JButton("new");
		NEW.addActionListener(this);

		JPanel MAIN = new JPanel();
		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(WORLDS_LIST).addComponent(EDIT).addComponent(NEW));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(WORLDS_LIST).addComponent(EDIT).addComponent(NEW));
		groupLayout.linkSize(SwingConstants.VERTICAL, WORLDS_LIST, EDIT, NEW);

		WORLDS = DataLoader.listFiles(Paths.RESOURCES);
		for(String world:WORLDS)
		{
			WORLDS_LIST.addItem(world);
		}
		WORLDS_LIST.addActionListener(this);
		MAIN.setLayout(groupLayout);
		add(MAIN);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == EDIT)
		{
			SELECTED_WORLD = WORLDS_LIST.getSelectedItem().toString();
			//Then pass to the dataHandler which world to  load data  from
            DATA_HANDLER.setWorld(SELECTED_WORLD);
			System.out.println(SELECTED_WORLD);
			EDITOR_WINDOW.populateData(SELECTED_WORLD);
			dispose();
		}
	}
}

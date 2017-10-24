package Editor;

import Data.DataHandler;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.PatternSyntaxException;

public class WorldEditorWindow extends JFrame implements ActionListener
{
    private DataHandler DATA_HANDLER;
	private JMenuBar MENU_BAR;
	private JMenuItem MENU_ADD;
	private JMenuItem MENU_BTN_ADD_MODULE;
	private JMenuItem MENU_FILE;
	private JMenuItem MENU_BTN_SAVE;
    private JMenuItem MENU_BTN_NEW;
	private JMenuItem MENU_EDIT;
	private JMenuItem MENU_BUILD;
	private JMenuItem MENU_HELP;

	private JPanel MAIN;
	private JPanel WORLD_EDITOR;
	private JLabel LBL_NAME;
	private JTextField TXT_FLD_NAME;
	private JLabel LBL_AUTHOR;
	private JTextField TXT_FLD_AUTHOR;
	private JLabel LBL_STARTING_ROOM;
	private JTextField TXT_FLD_STARTING_ROOM;
	private JLabel LBL_DESCRIPTION;
	private JTextArea TXT_AREA_DESCRIPTION;
	private JScrollPane DESCRIPTION_SCROLL_PANE;

	private JPanel MODULE_EDITOR;
	private JComboBox<String> MODULE_FILTER;
	private JScrollPane MODULES_SCROLL_PANE;
	private JTextField MODULE_SEARCH_BAR;
	private JTable MODULE_TABLE;
	private ModuleTableModel MODULE_TABLE_MODEL;
	private TableRowSorter<ModuleTableModel> SORTER;

	public WorldEditorWindow(DataHandler dataHandler)
	{
	    DATA_HANDLER = dataHandler;
		MAIN = new JPanel();

		createMenuBar();
		setJMenuBar(MENU_BAR);
		createWorldInfo();
		createModulePanel();

		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(WORLD_EDITOR).addComponent(MODULE_EDITOR));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(WORLD_EDITOR).addComponent(MODULE_EDITOR));
		MAIN.setLayout(groupLayout);

		add(MAIN);
	}

	//todo Line all of these up to look nicer. But for now it works.
	public void createWorldInfo()
	{
		WORLD_EDITOR = new JPanel();
		LBL_NAME = new JLabel("Name");
		TXT_FLD_NAME = new JTextField();
		LBL_AUTHOR = new JLabel("Author");
		TXT_FLD_AUTHOR = new JTextField();
		LBL_STARTING_ROOM = new JLabel("Starting Room");
		TXT_FLD_STARTING_ROOM = new JTextField();
		LBL_DESCRIPTION = new JLabel("Description");
		TXT_AREA_DESCRIPTION = new JTextArea();
		DESCRIPTION_SCROLL_PANE = new JScrollPane(TXT_AREA_DESCRIPTION);
		DESCRIPTION_SCROLL_PANE.setMinimumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);

		GroupLayout groupLayout = new GroupLayout(WORLD_EDITOR);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.linkSize(SwingConstants.VERTICAL, LBL_NAME, TXT_FLD_NAME, LBL_AUTHOR, TXT_FLD_AUTHOR, LBL_STARTING_ROOM, TXT_FLD_STARTING_ROOM, LBL_DESCRIPTION);
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(LBL_NAME).addComponent(TXT_FLD_NAME)
											 							.addComponent(LBL_AUTHOR).addComponent(TXT_FLD_AUTHOR)
											 							.addComponent(LBL_STARTING_ROOM).addComponent(TXT_FLD_STARTING_ROOM)
																		.addComponent(LBL_DESCRIPTION).addComponent(DESCRIPTION_SCROLL_PANE));
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(LBL_NAME).addComponent(TXT_FLD_NAME)
											   							.addComponent(LBL_AUTHOR).addComponent(TXT_FLD_AUTHOR)
											   							.addComponent(LBL_STARTING_ROOM).addComponent(TXT_FLD_STARTING_ROOM)
											   							.addComponent(LBL_DESCRIPTION).addComponent(DESCRIPTION_SCROLL_PANE));
		WORLD_EDITOR.setLayout(groupLayout);
		WORLD_EDITOR.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "World Info"));
	}

	public void createMenuBar()
	{
		MENU_BAR = new JMenuBar();
		MENU_ADD = new JMenuItem("Add");
		MENU_FILE = new JMenuItem("File");
		MENU_EDIT = new JMenuItem("Edit");
		MENU_BUILD = new JMenuItem("Build");
		MENU_HELP = new JMenuItem("Help");

		MENU_BAR.add(MENU_FILE);
		MENU_BAR.add(MENU_ADD);
		MENU_BAR.add(MENU_EDIT);
		MENU_BAR.add(MENU_BUILD);
		MENU_BAR.add(MENU_HELP);
	}

	public void createModulePanel()
	{
		MODULE_EDITOR = new JPanel();
		MODULE_FILTER = new JComboBox<>(GlobalGameConstants.ENTITY_TYPE_FILTERS);
		MODULE_SEARCH_BAR = new JTextField();
		MODULE_SEARCH_BAR.addActionListener(this);
		MODULE_SEARCH_BAR.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				searchEntities(MODULE_SEARCH_BAR.getText());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				searchEntities(MODULE_SEARCH_BAR.getText());
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				searchEntities(MODULE_SEARCH_BAR.getText());
			}
		});
		MODULE_TABLE_MODEL = new ModuleTableModel(new Object[][]{});
		MODULE_TABLE = new JTable(MODULE_TABLE_MODEL);
		MODULES_SCROLL_PANE = new JScrollPane();
		MODULES_SCROLL_PANE.setViewportView(MODULE_TABLE);
		MODULES_SCROLL_PANE.setColumnHeaderView(MODULE_TABLE.getTableHeader());
		MODULES_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		SORTER = new TableRowSorter<ModuleTableModel>(MODULE_TABLE_MODEL);
		MODULE_TABLE.setRowSorter(SORTER);
		MODULE_FILTER.addActionListener(e -> filterEntities(GlobalGameConstants.getFilter(MODULE_FILTER.getSelectedItem().toString())));

		GroupLayout groupLayout = new GroupLayout(MODULE_EDITOR);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(MODULE_SEARCH_BAR).addComponent(MODULE_FILTER).addComponent(MODULES_SCROLL_PANE));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(MODULE_SEARCH_BAR).addComponent(MODULE_FILTER).addComponent(MODULES_SCROLL_PANE));

		MODULE_EDITOR.setLayout(groupLayout);

		MODULE_EDITOR.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Modules"));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}

	private void filterEntities(String filter)
	{
		try
		{
			RowFilter<ModuleTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 0);
			SORTER.setRowFilter(rf);
		}
		catch(PatternSyntaxException p){}
	}

	private void searchEntities(String filter)
	{
		try
		{
			RowFilter<ModuleTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 1,2);
			SORTER.setRowFilter(rf);
		}
		catch(PatternSyntaxException p){}
	}

	public void populateData(String world)
	{
		MODULE_TABLE_MODEL.updateData(DATA_HANDLER.getData(DATA_HANDLER.MODULES));
	}
}

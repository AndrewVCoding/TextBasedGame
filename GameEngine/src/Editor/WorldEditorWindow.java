package Editor;

import Data.DataHandler;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class WorldEditorWindow extends JFrame implements ActionListener
{
	// Menu
	private JMenuBar MENU_BAR;
	private JMenu    MENU_ADD, MENU_FILE, MENU_EDIT, MENU_BUILD, MENU_HELP;
	private JMenuItem MENU_BTN_ADD_MODULE, MENU_BTN_OPEN, MENU_BTN_SAVE, MENU_BTN_NEW, MENU_BTN_TEST, MENU_BTN_HELP, MENU_BTN_ADD_ENTITY;
	// Main Panel
	private JPanel MAIN;
	// World Information Panel
	private JPanel WORLD_INFO_PANEL;
	private JLabel LBL_NAME, LBL_CREATOR, LBL_STARTING_ROOM, LBL_DESCRIPTION;
	private JTextField TXT_FLD_NAME, TXT_FLD_CREATOR, TXT_FLD_STARTING_ROOM;
	private JTextArea        TXT_AREA_DESCRIPTION;
	private JScrollPane      DESCRIPTION_SCROLL_PANE;
	// Modules Panel
	private JPanel           MODULE_EDITOR;
	private JScrollPane      MODULES_SCROLL_PANE;
	private JTable           MODULE_TABLE;
	private ModuleTableModel MODULE_TABLE_MODEL;
	private JButton          BTN_MODULE_ADD, BTN_MODULE_DEL;
	// Entity Table Panel
	private JPanel                           ENTITY_EDITOR;
	private EntityTableModel                 ENTITY_TABLE_MODEL;
	private JTable                           ENTITY_TABLE;
	private JScrollPane                      ENTITY_SCROLL_PANE;
	private JComboBox                        ENTITY_FILTER_BOX;
	private JTextField                       ENTITY_SEARCH_BAR;
	private TableRowSorter<EntityTableModel> ENTITY_SORTER;
	// Descriptor Panel
	private JPanel                           DESCRIPTION_EDITOR;
	private JScrollPane                      DESCRIPTION_TABLE_SCROLL_PANE;
	private JTable                           DESCRIPTION_TABLE;

	// Resources
	private String      SELECTED_MODULE;
	private DataHandler DATA_HANDLER;

	public WorldEditorWindow(DataHandler dataHandler)
	{
		DATA_HANDLER = dataHandler;
		MAIN = new JPanel();

		createMenuBar();
		setJMenuBar(MENU_BAR);
		createWorldInfo();
		createModulePanel();
		createEntityPanel();

		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup().addComponent(WORLD_INFO_PANEL).addComponent(MODULE_EDITOR))
											   .addGroup(groupLayout.createParallelGroup().addComponent(ENTITY_EDITOR)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addGroup(groupLayout.createSequentialGroup().addComponent(WORLD_INFO_PANEL).addComponent(MODULE_EDITOR))
											 .addGroup(groupLayout.createSequentialGroup().addComponent(ENTITY_EDITOR)));
		MAIN.setLayout(groupLayout);

		add(MAIN);

		ENTITY_TABLE_MODEL.updateData(GlobalGameConstants.ENTITY_TABLE_TEST_DATA);
	}

	private void createMenuBar()
	{
		MENU_BAR = new JMenuBar();
		MENU_ADD = new JMenu("Add");
		MENU_BTN_ADD_MODULE = new JMenuItem("Add Module");
		MENU_BTN_ADD_ENTITY = new JMenuItem("Add Entity");
		MENU_FILE = new JMenu("File");
		MENU_BTN_OPEN = new JMenuItem("Open World");
		MENU_BTN_SAVE = new JMenuItem("Save World");
		MENU_BTN_NEW = new JMenuItem("New World");
		MENU_EDIT = new JMenu("Edit");
		MENU_BUILD = new JMenu("Build");
		MENU_BTN_TEST = new JMenuItem("Test World");
		MENU_HELP = new JMenu("Help");
		MENU_BTN_HELP = new JMenuItem("Help");

		MENU_BTN_ADD_MODULE.addActionListener(this);
		MENU_BTN_ADD_ENTITY.addActionListener(this);
		MENU_BTN_OPEN.addActionListener(this);
		MENU_BTN_SAVE.addActionListener(this);
		MENU_BTN_NEW.addActionListener(this);
		MENU_BTN_TEST.addActionListener(this);
		MENU_BTN_HELP.addActionListener(this);

		MENU_BAR.add(MENU_FILE);
		MENU_FILE.add(MENU_BTN_SAVE);
		MENU_FILE.add(MENU_BTN_OPEN);
		MENU_FILE.add(MENU_BTN_NEW);
		MENU_BAR.add(MENU_ADD);
		MENU_ADD.add(MENU_BTN_ADD_MODULE);
		MENU_ADD.add(MENU_BTN_ADD_ENTITY);
		MENU_BAR.add(MENU_EDIT);
		MENU_BAR.add(MENU_BUILD);
		MENU_BUILD.add(MENU_BTN_TEST);
		MENU_BAR.add(MENU_HELP);
		MENU_HELP.add(MENU_BTN_HELP);
	}

	private void createWorldInfo()
	{
		WORLD_INFO_PANEL = new JPanel();
		LBL_NAME = new JLabel("Name");
		TXT_FLD_NAME = new JTextField();
		LBL_CREATOR = new JLabel("Creator");
		TXT_FLD_CREATOR = new JTextField();
		LBL_STARTING_ROOM = new JLabel("Starting Room");
		TXT_FLD_STARTING_ROOM = new JTextField();
		LBL_DESCRIPTION = new JLabel("Description");
		TXT_AREA_DESCRIPTION = new JTextArea();
		TXT_AREA_DESCRIPTION.setMinimumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);
		TXT_AREA_DESCRIPTION.setLineWrap(true);
		DESCRIPTION_SCROLL_PANE = new JScrollPane(TXT_AREA_DESCRIPTION);
		DESCRIPTION_SCROLL_PANE.setMinimumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);
		DESCRIPTION_SCROLL_PANE.setMaximumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);
		DESCRIPTION_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		GroupLayout groupLayout = new GroupLayout(WORLD_INFO_PANEL);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.linkSize(SwingConstants.VERTICAL, LBL_NAME, TXT_FLD_NAME, LBL_CREATOR, TXT_FLD_CREATOR, LBL_STARTING_ROOM, TXT_FLD_STARTING_ROOM, LBL_DESCRIPTION);

		GroupLayout.ParallelGroup top_labels = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(LBL_NAME).addComponent(LBL_CREATOR).addComponent(LBL_STARTING_ROOM);
		GroupLayout.ParallelGroup top_text_fields = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(TXT_FLD_NAME).addComponent(TXT_FLD_CREATOR)
				.addComponent(TXT_FLD_STARTING_ROOM);

		GroupLayout.ParallelGroup   col_one   = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(LBL_NAME).addComponent(TXT_FLD_NAME);
		GroupLayout.ParallelGroup   col_two   = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(LBL_CREATOR).addComponent(TXT_FLD_CREATOR);
		GroupLayout.ParallelGroup   col_three = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(LBL_STARTING_ROOM).addComponent(TXT_FLD_STARTING_ROOM);
		GroupLayout.SequentialGroup top       = groupLayout.createSequentialGroup().addGroup(col_one).addGroup(col_two).addGroup(col_three);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGroup(top_labels).addGroup(top_text_fields).addComponent(LBL_DESCRIPTION).addComponent(DESCRIPTION_SCROLL_PANE));
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addGroup(top).addComponent(LBL_DESCRIPTION).addComponent(DESCRIPTION_SCROLL_PANE));

		WORLD_INFO_PANEL.setLayout(groupLayout);
		WORLD_INFO_PANEL.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "World Info"));
	}

	private void createModulePanel()
	{
		MODULE_EDITOR = new JPanel();
		MODULE_TABLE_MODEL = new ModuleTableModel(new Object[][]{});
		MODULE_TABLE = new JTable(MODULE_TABLE_MODEL);
		MODULES_SCROLL_PANE = new JScrollPane();
		BTN_MODULE_ADD = new JButton("Add");
		BTN_MODULE_DEL = new JButton("Delete");
		MODULES_SCROLL_PANE.setViewportView(MODULE_TABLE);
		MODULES_SCROLL_PANE.setColumnHeaderView(MODULE_TABLE.getTableHeader());
		MODULES_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		GroupLayout groupLayout = new GroupLayout(MODULE_EDITOR);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup().addComponent(MODULES_SCROLL_PANE).addGroup(groupLayout.createSequentialGroup().addComponent(BTN_MODULE_ADD).addComponent(BTN_MODULE_DEL)));
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup().addComponent(MODULES_SCROLL_PANE).addGroup(groupLayout.createParallelGroup().addComponent(BTN_MODULE_ADD).addComponent(BTN_MODULE_DEL)));

		MODULE_EDITOR.setLayout(groupLayout);

		MODULE_EDITOR.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Modules"));
	}

	private void createEntityPanel()
	{
		ENTITY_EDITOR = new JPanel();
		ENTITY_FILTER_BOX = new JComboBox<>(GlobalGameConstants.ENTITY_TYPE_FILTERS);
		ENTITY_TABLE_MODEL = new EntityTableModel(new Object[][]{});
		ENTITY_TABLE = new JTable(ENTITY_TABLE_MODEL);
		ENTITY_SCROLL_PANE = new JScrollPane(ENTITY_TABLE);
		ENTITY_SCROLL_PANE.setViewportView(ENTITY_TABLE);
		ENTITY_SCROLL_PANE.setColumnHeaderView(ENTITY_TABLE.getTableHeader());
		ENTITY_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ENTITY_SORTER = new TableRowSorter<>(ENTITY_TABLE_MODEL);
		ENTITY_FILTER_BOX.addActionListener(e -> filterEntities(GlobalGameConstants.getFilter(ENTITY_FILTER_BOX.getSelectedItem().toString()), ENTITY_SEARCH_BAR.getText()));
		ENTITY_TABLE.setRowSorter(ENTITY_SORTER);
		ENTITY_SEARCH_BAR = new JTextField();
		ENTITY_SEARCH_BAR.addKeyListener(new KeyListener()
		{
			@Override public void keyTyped(KeyEvent e)
			{
				filterEntities(GlobalGameConstants.getFilter(ENTITY_FILTER_BOX.getSelectedItem().toString()), ENTITY_SEARCH_BAR.getText());
			}

			@Override public void keyPressed(KeyEvent e)
			{
				filterEntities(GlobalGameConstants.getFilter(ENTITY_FILTER_BOX.getSelectedItem().toString()), ENTITY_SEARCH_BAR.getText());
			}

			@Override public void keyReleased(KeyEvent e)
			{
				filterEntities(GlobalGameConstants.getFilter(ENTITY_FILTER_BOX.getSelectedItem().toString()), ENTITY_SEARCH_BAR.getText());
			}
		});

		GroupLayout groupLayout = new GroupLayout(ENTITY_EDITOR);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.linkSize(SwingConstants.VERTICAL, ENTITY_FILTER_BOX, ENTITY_SEARCH_BAR);
		ENTITY_FILTER_BOX.setMaximumSize(GlobalGameConstants.COMBOBOX_SIZE);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(ENTITY_SEARCH_BAR).addComponent(ENTITY_FILTER_BOX).addComponent(ENTITY_SCROLL_PANE));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(ENTITY_SEARCH_BAR).addComponent(ENTITY_FILTER_BOX).addComponent(ENTITY_SCROLL_PANE));

		ENTITY_EDITOR.setLayout(groupLayout);

		ENTITY_EDITOR.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Entities"));
	}

	/**
	 * Filters the rows of the entity table based on the inputs
	 * @param filterBox Filters entities by type
	 * @param searchBar Filters entities by matching ID, Instance, and Name
	 */
	private void filterEntities(String filterBox, String searchBar)
	{

		ArrayList<RowFilter<EntityTableModel, Object>> filters  = new ArrayList<>(2);
		RowFilter<EntityTableModel, Object>            filterC1 = RowFilter.regexFilter(".*" + filterBox + ".*", 0);
		RowFilter<EntityTableModel, Object>            filterC2 = RowFilter.regexFilter(".*" + searchBar + ".*", 1, 2, 3);
		filters.add(filterC1);
		filters.add(filterC2);
		RowFilter<EntityTableModel, Object> filter = RowFilter.andFilter(filters);
		ENTITY_SORTER.setRowFilter(filter);
	}

	@Override public void actionPerformed(ActionEvent e)
	{

	}

	void populateData(String world)
	{
		setTitle(world);
		TXT_FLD_NAME.setText(world);
		MODULE_TABLE_MODEL.updateData(DATA_HANDLER.getData(DATA_HANDLER.MODULES));
		ENTITY_TABLE_MODEL.updateData(GlobalGameConstants.ENTITY_TABLE_TEST_DATA);
	}
}

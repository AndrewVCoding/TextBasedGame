package Editor;

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
	private JMenuBar MENU_BAR;
	private JMenuItem MENU_ADD;
	private JMenuItem MENU_FILE;
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

	private JPanel ENTITY_EDITOR;
	private JComboBox<String> ENTITY_FILTER;
	private JScrollPane ENTITY_SCROLL_PANE;
	private JTextField SEARCH_BAR;
	private JTable ENTITY_TABLE;
	private EntityTableModel ENTITY_TABLE_MODEL;
	private TableRowSorter<EntityTableModel> SORTER;

	public WorldEditorWindow()
	{
		MAIN = new JPanel();

		createMenuBar();
		setJMenuBar(MENU_BAR);
		createWorldInfo();
		createEntityList();

		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(WORLD_EDITOR).addComponent(ENTITY_EDITOR));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(WORLD_EDITOR).addComponent(ENTITY_EDITOR));
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

	public void createEntityList()
	{
		ENTITY_EDITOR = new JPanel();
		ENTITY_FILTER = new JComboBox<>(GlobalGameConstants.ENTITY_TYPE_FILTERS);
		SEARCH_BAR = new JTextField();
		SEARCH_BAR.addActionListener(this);
		SEARCH_BAR.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				searchEntities(SEARCH_BAR.getText());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				searchEntities(SEARCH_BAR.getText());
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				searchEntities(SEARCH_BAR.getText());
			}
		});
		ENTITY_TABLE_MODEL = new EntityTableModel(new Object[][]{});
		ENTITY_TABLE = new JTable(ENTITY_TABLE_MODEL);
		ENTITY_SCROLL_PANE = new JScrollPane();
		ENTITY_SCROLL_PANE.setViewportView(ENTITY_TABLE);
		ENTITY_SCROLL_PANE.setColumnHeaderView(ENTITY_TABLE.getTableHeader());
		ENTITY_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		SORTER = new TableRowSorter<EntityTableModel>(ENTITY_TABLE_MODEL);
		ENTITY_TABLE.setRowSorter(SORTER);
		ENTITY_FILTER.addActionListener(e -> filterEntities(GlobalGameConstants.getFilter(ENTITY_FILTER.getSelectedItem().toString())));

		GroupLayout groupLayout = new GroupLayout(ENTITY_EDITOR);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(SEARCH_BAR).addComponent(ENTITY_FILTER).addComponent(ENTITY_SCROLL_PANE));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(SEARCH_BAR).addComponent(ENTITY_FILTER).addComponent(ENTITY_SCROLL_PANE));

		ENTITY_EDITOR.setLayout(groupLayout);

		ENTITY_EDITOR.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Entities"));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}

	private void filterEntities(String filter)
	{
		try
		{
			RowFilter<EntityTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 0);
			SORTER.setRowFilter(rf);
		}
		catch(PatternSyntaxException p){}
	}

	private void searchEntities(String filter)
	{
		try
		{
			RowFilter<EntityTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 1,2);
			SORTER.setRowFilter(rf);
		}
		catch(PatternSyntaxException p){}
	}

	public void populateData(String world)
	{
		ENTITY_TABLE_MODEL.updateData(GlobalGameConstants.TEST_ENTITY_DATA);
	}
}

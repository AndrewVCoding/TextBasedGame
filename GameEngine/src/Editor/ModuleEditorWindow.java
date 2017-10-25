package Editor;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class ModuleEditorWindow extends JFrame implements ActionListener
{
	// Menu
	private JMenuBar MENU_BAR;
	private JMenu MENU_FILE, MENU_ADD_ENTITY;
	private JMenuItem ADD;

	// Module Information Panel
	private JPanel MOD_INFO_PANEL;
	private JLabel LBL_NAME, LBL_DESCRIPTION, LBL_TAGS;
	private JTextField TXT_NAME;
	private JTextArea TXT_AREA_DESCRIPTION, TXT_AREA_TAGS;

	// Entity List Panel
	private JPanel ENTITY_PANEL;
	private JComboBox<String> ENTITY_FILTER;
	private JScrollPane ENTITY_SCROLL_PANE;
	private JTextField ENTITY_SEARCH_BAR;
	private JTable ENTITY_TABLE;
	private EntityTableModel ENTITY_TABLE_MODULE;
	private TableRowSorter<EntityTableModel> SORTER;
	private ArrayList<RowFilter<EntityTableModel, Object>> FILTERS = new ArrayList<>(2);

	// Entity Editor panels
	private JPanel EDITOR_PANEL, TYPE_PANEL;
	private JLabel LBL_ENTITY_ATTRIBUTES, LBL_ENTITY_USES, LBL_ENTITY_DESCRIPTION, LBL_ENTITY_CONTENTS;
	private JScrollPane ATTRIBUTES_SCROLL_PANE;
	private JTable ATTRIBUTE_TABLE;

	public ModuleEditorWindow(String module)
	{
		setTitle(module);
	}

	public void createEntityEditorPanel()
	{

	}

	public void createModulePanel()
	{
		ENTITY_PANEL = new JPanel();
		ENTITY_FILTER = new JComboBox<>(GlobalGameConstants.ENTITY_TYPE_FILTERS);
		ENTITY_SEARCH_BAR = new JTextField();
		ENTITY_SEARCH_BAR.addActionListener(this);
		ENTITY_SEARCH_BAR.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				setFilter(1, ENTITY_SEARCH_BAR.getText());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				setFilter(1, ENTITY_SEARCH_BAR.getText());
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				setFilter(1, ENTITY_SEARCH_BAR.getText());
			}
		});
		ENTITY_TABLE_MODULE = new EntityTableModel(new Object[][]{});
		ENTITY_TABLE = new JTable(ENTITY_TABLE_MODULE);
		ENTITY_SCROLL_PANE = new JScrollPane();
		ENTITY_SCROLL_PANE.setViewportView(ENTITY_TABLE);
		ENTITY_SCROLL_PANE.setColumnHeaderView(ENTITY_TABLE.getTableHeader());
		ENTITY_SCROLL_PANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		SORTER = new TableRowSorter<EntityTableModel>(ENTITY_TABLE_MODULE);
		ENTITY_TABLE.setRowSorter(SORTER);
		ENTITY_FILTER.addActionListener(e -> setFilter(0, GlobalGameConstants.getFilter(ENTITY_FILTER.getSelectedItem().toString())));

		GroupLayout groupLayout = new GroupLayout(ENTITY_PANEL);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(ENTITY_SEARCH_BAR).addComponent(
				ENTITY_FILTER).addComponent(ENTITY_SCROLL_PANE));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(ENTITY_SEARCH_BAR).addComponent(
				ENTITY_FILTER).addComponent(ENTITY_SCROLL_PANE));

		ENTITY_PANEL.setLayout(groupLayout);

		ENTITY_PANEL.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Entities"));
	}

	/**
	 * Sets the filter for the table of entities
	 * @param index 0 to filter by type, 1 to filter by everything else
	 * @param filter The string to filter by
	 */
	private void setFilter(int index, String filter)
	{
		try
		{
			RowFilter<EntityTableModel, Object> rf;
			if(index == 0)
				rf = RowFilter.regexFilter(".*" + filter + ".*", 0);
			else
				rf = RowFilter.regexFilter(".*" + filter + ".*", 1, 2, 3);
			FILTERS.set(index, rf);
		}
		catch(PatternSyntaxException p){}
	}

	private void filterEntities()
	{
		try
		{
			SORTER.setRowFilter(RowFilter.andFilter(FILTERS));
		}
		catch(PatternSyntaxException p){}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}
}

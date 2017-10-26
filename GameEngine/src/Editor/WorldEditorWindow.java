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

public class WorldEditorWindow extends JFrame implements ActionListener {
    // Menu
    private JMenuBar                         MENU_BAR;
    private JMenu                            MENU_ADD;
    private JMenuItem                        MENU_BTN_ADD_MODULE;
    private JMenu                            MENU_FILE;
    private JMenuItem                        MENU_BTN_OPEN;
    private JMenuItem                        MENU_BTN_SAVE;
    private JMenuItem                        MENU_BTN_NEW;
    private JMenu                            MENU_EDIT;
    private JMenu                            MENU_BUILD;
    private JMenuItem                        MENU_BTN_TEST;
    private JMenu                            MENU_HELP;
    private JMenuItem                        MENU_BTN_HELP;
    // Main Panel
    private JPanel                           MAIN;
    // World Information Panel
    private JPanel                           WORLD_INFO_PANEL;
    private JLabel                           LBL_NAME;
    private JTextField                       TXT_FLD_NAME;
    private JLabel                           LBL_CREATOR;
    private JTextField                       TXT_FLD_CREATOR;
    private JLabel                           LBL_STARTING_ROOM;
    private JTextField                       TXT_FLD_STARTING_ROOM;
    private JLabel                           LBL_DESCRIPTION;
    private JTextArea                        TXT_AREA_DESCRIPTION;
    private JScrollPane                      DESCRIPTION_SCROLL_PANE;
    // Modules Panel
    //todo ADD_MODULE and DEL_MODULE, and also make module columns editable
    private JPanel                           MODULE_EDITOR;
    private JComboBox<String>                MODULE_FILTER;
    private JScrollPane                      MODULES_SCROLL_PANE;
    private JTextField                       MODULE_SEARCH_BAR;
    private JTable                           MODULE_TABLE;
    private ModuleTableModel                 MODULE_TABLE_MODEL;
    private TableRowSorter<ModuleTableModel> SORTER;
    //todo integrate the entity table and editor into the main editor window. Entities shown should be filtered by the selected module.
    // Entity Table Panel
    private JPanel                           ENTITY_EDITOR;
    private JTable                           ENTITY_ATTRIBUTES_TABLE;
    private JScrollPane                      ENTITY_ATTRIBUTES_SCROLL_PANE;
    // Entity Editor Panel
    private JLabel                           LBL_ENTITY_DESCRIPTION;
    private JTextArea                        TXT_AREA_ENTITY_DESCRIPTION;
    // Descriptor Panel
    private JPanel                           DESCRIPTION_EDITOR;
    private JScrollPane                      DESCRIPTION_TABLE_SCROLL_PANE;
    private JTable                           DESCRIPTION_TABLE;

    // Resources
    private String      SELECTED_MODULE;
    private DataHandler DATA_HANDLER;

    public WorldEditorWindow(DataHandler dataHandler) {
        DATA_HANDLER = dataHandler;
        MAIN = new JPanel();

        createMenuBar();
        setJMenuBar(MENU_BAR);
        createWorldInfo();
        createModulePanel();

        GroupLayout groupLayout = new GroupLayout(MAIN);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(WORLD_INFO_PANEL).addComponent(MODULE_EDITOR));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(WORLD_INFO_PANEL).addComponent(MODULE_EDITOR));
        MAIN.setLayout(groupLayout);

        add(MAIN);
    }

    public void createMenuBar() {
        MENU_BAR = new JMenuBar();
        MENU_ADD = new JMenu("Add");
        MENU_BTN_ADD_MODULE = new JMenuItem("Add Module");
        MENU_FILE = new JMenu("File");
        MENU_BTN_OPEN = new JMenuItem("Open World");
        MENU_BTN_SAVE = new JMenuItem("Save World");
        MENU_BTN_NEW = new JMenuItem("New World");
        MENU_EDIT = new JMenu("Edit");
        MENU_BUILD = new JMenu("Build");
        MENU_BTN_TEST = new JMenuItem("Test World");
        MENU_HELP = new JMenu("Help");
        MENU_BTN_HELP = new JMenuItem("Help");

        MENU_BAR.add(MENU_FILE);
        MENU_FILE.add(MENU_BTN_SAVE);
        MENU_FILE.add(MENU_BTN_OPEN);
        MENU_FILE.add(MENU_BTN_NEW);
        MENU_BAR.add(MENU_ADD);
        MENU_ADD.add(MENU_BTN_ADD_MODULE);
        MENU_BAR.add(MENU_EDIT);
        MENU_BAR.add(MENU_BUILD);
        MENU_BUILD.add(MENU_BTN_TEST);
        MENU_BAR.add(MENU_HELP);
        MENU_HELP.add(MENU_BTN_HELP);
    }

    //todo Line all of these up to look nicer. But for now it works.
    public void createWorldInfo() {
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
        DESCRIPTION_SCROLL_PANE = new JScrollPane(TXT_AREA_DESCRIPTION);
        DESCRIPTION_SCROLL_PANE.setMinimumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);

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

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGroup(top_labels).addGroup(top_text_fields).addComponent(LBL_DESCRIPTION).addComponent(TXT_AREA_DESCRIPTION));
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addGroup(top).addComponent(LBL_DESCRIPTION).addComponent(TXT_AREA_DESCRIPTION));

        WORLD_INFO_PANEL.setLayout(groupLayout);
        WORLD_INFO_PANEL.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "World Info"));
    }

    public void createModulePanel() {
        MODULE_EDITOR = new JPanel();
        MODULE_FILTER = new JComboBox<>(GlobalGameConstants.ENTITY_TYPE_FILTERS);
        MODULE_SEARCH_BAR = new JTextField();
        MODULE_SEARCH_BAR.addActionListener(this);
        MODULE_SEARCH_BAR.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                searchEntities(MODULE_SEARCH_BAR.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                searchEntities(MODULE_SEARCH_BAR.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {
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

    private void searchEntities(String filter) {
        try {
            RowFilter<ModuleTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 1, 2);
            SORTER.setRowFilter(rf);
        } catch (PatternSyntaxException p) {
        }
    }

    private void filterEntities(String filter) {
        try {
            RowFilter<ModuleTableModel, Object> rf = RowFilter.regexFilter(".*" + filter + ".*", 0);
            SORTER.setRowFilter(rf);
        } catch (PatternSyntaxException p) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void populateData(String world) {
        setTitle(world);
        TXT_FLD_NAME.setText(world);
        MODULE_TABLE_MODEL.updateData(DATA_HANDLER.getData(DATA_HANDLER.MODULES));
    }
}

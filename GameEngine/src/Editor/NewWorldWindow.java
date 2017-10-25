package Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWorldWindow extends JFrame implements ActionListener
{
	private JPanel MAIN;
	private JLabel LBL_NAME, LBL_DESCRIPTION, LBL_CREATOR;
	private JTextField TXT_NAME, TXT_CREATOR;
	private JScrollPane DESCRIPTION_SCROLL_PANE;
	private JTextArea TXT_AREA_DESCRIPTION;
	private JButton BTN_CREATE, BTN_CANCEL;

	public NewWorldWindow()
	{
		setTitle("New World");
		setAlwaysOnTop(true);

		initElements();
		initActionListeners();

		MAIN.setLayout(createLayout());
		add(MAIN);
	}

	private void initElements()
	{
		MAIN = new JPanel();
		LBL_NAME = new JLabel("Name");
		LBL_DESCRIPTION = new JLabel("Description");
		LBL_CREATOR = new JLabel("Created by:");
		TXT_NAME = new JTextField();
		TXT_CREATOR = new JTextField();
		TXT_AREA_DESCRIPTION = new JTextArea();
		DESCRIPTION_SCROLL_PANE = new JScrollPane(TXT_AREA_DESCRIPTION);
		DESCRIPTION_SCROLL_PANE.setMinimumSize(GlobalGameConstants.DESCRIPTION_TXT_AREA_SIZE);
		BTN_CREATE = new JButton("Create");
		BTN_CANCEL = new JButton("Cancel");
	}

	private void initActionListeners()
	{
		BTN_CREATE.addActionListener(this);
		BTN_CANCEL.addActionListener(this);
	}

	private GroupLayout createLayout()
	{
		GroupLayout groupLayout = new GroupLayout(MAIN);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		GroupLayout.ParallelGroup label_name_creator = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
				LBL_NAME).addComponent(LBL_CREATOR);
		GroupLayout.ParallelGroup text_name_creator = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
				TXT_NAME).addComponent(TXT_CREATOR);
		GroupLayout.ParallelGroup button_create_cancel = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
				BTN_CREATE).addComponent(BTN_CANCEL);

		GroupLayout.ParallelGroup col_one = groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(LBL_NAME).addComponent(TXT_NAME).addComponent(LBL_DESCRIPTION);
		GroupLayout.ParallelGroup col_two = groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(LBL_CREATOR).addComponent(TXT_CREATOR);
		GroupLayout.SequentialGroup buttons = groupLayout.createSequentialGroup().addComponent(BTN_CREATE).addComponent(BTN_CANCEL);
		GroupLayout.SequentialGroup top = groupLayout.createSequentialGroup().addGroup(col_one).addGroup(col_two);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGroup(label_name_creator).addGroup(text_name_creator).addComponent(
				LBL_DESCRIPTION).addComponent(DESCRIPTION_SCROLL_PANE).addGroup(button_create_cancel));
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(top).addComponent(DESCRIPTION_SCROLL_PANE).addGroup(buttons));

		return groupLayout;
	}

	private void createWorld()
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == BTN_CANCEL)
			dispose();
		if(e.getSource() == BTN_CREATE)
			createWorld();
	}
}

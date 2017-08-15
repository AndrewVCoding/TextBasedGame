import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener
{
	//Seperate panels for layout
	final private JPanel panel_main = new JPanel();
	final private JPanel panel_interaction = new JPanel();
	final private JPanel panel_input = new JPanel();
	final private JPanel panel_display = new JPanel();
	final private JPanel panel_location = new JPanel();
	final private JPanel panel_player = new JPanel();

	//Input components
	final private JTextArea display = new JTextArea();
	final private JTextField input = new JTextField();
	final private JButton enter = new JButton();

	//Player panel components
	final private JLabel label_player_name = new JLabel("Name: ");
	final private JLabel label_player_class = new JLabel("Race: ");
	final private JLabel label_player_hp = new JLabel("HP: ");

	//Location panel components
	final private JLabel label_location_name = new JLabel("location: ");

	final private Dimension dimension_display = new Dimension(600, 400);
	final private Dimension dimension_input = new Dimension(320, 20);
	final private Dimension dimension_enter = new Dimension(120, 20);
	final private Dimension dimension_infoLabels = new Dimension(150, 20);

	public MainWindow()
	{
		display.setVisible(true);
		display.setEditable(false);
		display.setLineWrap(true);
		input.setVisible(true);
		input.addActionListener(this);
		enter.setVisible(true);
		enter.addActionListener(this);
		label_location_name.setVisible(true);
		label_player_name.setVisible(true);
		label_player_class.setVisible(true);
		label_player_hp.setVisible(true);
		enter.setText("Enter");

		display.setMinimumSize(dimension_display);
		input.setMinimumSize(dimension_input);
		enter.setMinimumSize(dimension_enter);
		label_player_class.setMinimumSize(dimension_infoLabels);
		label_player_name.setMinimumSize(dimension_infoLabels);
		label_location_name.setMinimumSize(dimension_infoLabels);
		label_player_hp.setMinimumSize(dimension_infoLabels);

		//Input layout
		GroupLayout inputLayout = new GroupLayout(panel_input);
		inputLayout.setAutoCreateGaps(true);
		inputLayout.linkSize(SwingConstants.VERTICAL, input, enter);
		inputLayout.setHorizontalGroup(inputLayout.createSequentialGroup().addComponent(input).addComponent(enter));
		inputLayout.setVerticalGroup(inputLayout.createParallelGroup().addComponent(input).addComponent(enter));
		panel_input.setLayout(inputLayout);
		panel_input.setVisible(true);

		//Player layout
		GroupLayout playerInfoLayout = new GroupLayout(panel_player);
		playerInfoLayout.setHorizontalGroup(playerInfoLayout.createParallelGroup().addComponent(label_player_name).addComponent(label_player_class).addComponent(label_player_hp));
		playerInfoLayout.setVerticalGroup(playerInfoLayout.createSequentialGroup().addComponent(label_player_name).addComponent(label_player_class).addComponent(label_player_hp));
		panel_player.setLayout(playerInfoLayout);
		panel_player.setVisible(true);
		//Location Layout
		GroupLayout locationLayout = new GroupLayout(panel_location);
		locationLayout.setHorizontalGroup(locationLayout.createParallelGroup().addComponent(label_location_name));
		locationLayout.setVerticalGroup(locationLayout.createSequentialGroup().addComponent(label_location_name));
		panel_location.setLayout(locationLayout);
		panel_location.setVisible(true);
		//Information panel layout
		GroupLayout informationLayout = new GroupLayout(panel_display);
		informationLayout.setHorizontalGroup(informationLayout.createParallelGroup().addComponent(panel_location).addComponent(panel_player));
		informationLayout.setVerticalGroup(informationLayout.createSequentialGroup().addComponent(panel_location).addComponent(panel_player));
		panel_display.setLayout(informationLayout);
		panel_display.setVisible(true);

		//Group together the interaction panel
		GroupLayout interactionLayout = new GroupLayout(panel_interaction);
		interactionLayout.linkSize(SwingConstants.HORIZONTAL, panel_input, display);
		interactionLayout.setAutoCreateContainerGaps(true);
		interactionLayout.setHorizontalGroup(interactionLayout.createParallelGroup().addComponent(panel_input).addComponent(display));
		interactionLayout.setVerticalGroup(interactionLayout.createSequentialGroup().addComponent(display).addComponent(panel_input));
		panel_interaction.setLayout(interactionLayout);
		panel_interaction.setVisible(true);

		//Put the two top level panel together
		GroupLayout mainLayout = new GroupLayout(panel_main);
		mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(panel_interaction).addComponent(panel_display));
		mainLayout.setVerticalGroup(mainLayout.createParallelGroup().addComponent(panel_display).addComponent(panel_interaction));
		panel_main.setLayout(mainLayout);
		panel_main.setVisible(true);

		add(panel_main);
	}

	public void updateDisplay()
	{
		display.setText(Interface.DISPLAY);
		if(Player.LOCATION != null)
			label_location_name.setText("Location: " + Player.LOCATION.NAME);
		label_player_name.setText("Name: " + Player.NAME);
		label_player_class.setText("Class: " + Player.CLASS);
		label_player_hp.setText("HP: " + Player.HP);
	}

	public void setDisplay(String s)
	{
		Interface.display(s);
		updateDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if((e.getSource() == enter || e.getSource() == input) && !(input.getText().equals("")))
		{
			CommandHandler.command(input.getText());
			input.setText("");
		}
		updateDisplay();
	}
}

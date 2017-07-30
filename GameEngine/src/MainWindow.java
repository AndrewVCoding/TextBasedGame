import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener
{
	final private JPanel panel_main = new JPanel();
	final private JPanel panel_input = new JPanel();
	final private JPanel panel_display = new JPanel();
	final private JPanel panel_location = new JPanel();
	final private JPanel panel_player = new JPanel();

	final private JTextArea display = new JTextArea();
	final private JTextField input = new JTextField();
	final private JButton enter = new JButton();

	final private Dimension dimension_display = new Dimension(600, 400);
	final private Dimension dimension_input = new Dimension(320, 20);
	final private Dimension dimension_enter = new Dimension(120, 20);

	public MainWindow()
	{
		display.setVisible(true);
		display.setEditable(false);
		input.setVisible(true);
		input.addActionListener(this);
		enter.setVisible(true);
		enter.addActionListener(this);
		enter.setText("Enter");

		display.setMinimumSize(dimension_display);
		input.setMinimumSize(dimension_input);
		enter.setMinimumSize(dimension_enter);

		GroupLayout inputLayout = new GroupLayout(panel_input);
		inputLayout.setAutoCreateGaps(true);
		inputLayout.linkSize(SwingConstants.VERTICAL, input, enter);
		inputLayout.setHorizontalGroup(inputLayout.createSequentialGroup().addComponent(input).addComponent(enter));
		inputLayout.setVerticalGroup(inputLayout.createParallelGroup().addComponent(input).addComponent(enter));
		panel_input.setLayout(inputLayout);
		panel_input.setVisible(true);

		GroupLayout mainLayout = new GroupLayout(panel_main);
		mainLayout.setAutoCreateGaps(true);
		mainLayout.linkSize(SwingConstants.HORIZONTAL, panel_input, display);
		mainLayout.setAutoCreateContainerGaps(true);
		mainLayout.setHorizontalGroup(mainLayout.createParallelGroup().addComponent(panel_input).addComponent(display));
		mainLayout.setVerticalGroup(mainLayout.createSequentialGroup().addComponent(display).addComponent(panel_input));
		panel_main.setLayout(mainLayout);

		add(panel_main);
	}

	public void updateDisplay()
	{
		display.setText(Interface.DISPLAY);
	}

	public void setDisplay(String s)
	{
		Interface.setDISPLAY(s);
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

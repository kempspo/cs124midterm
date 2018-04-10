package cs124midterm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UI extends JFrame
{

	String output,inventory, input;
	PrintWriter printer;

	private JLabel topLabel;

	private JTextArea textArea, textArea2;

	private JTextField textField;

	private JPanel topPanel, centerPanel, bottomArea, centerArea, bottomPanel;
	
	private JScrollPane scrollPane;

	public UI() throws FileNotFoundException
	{
		printer = new PrintWriter("log.txt");

		setLayout(new BorderLayout());

		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		add(topPanel, "North");
		createTopLabel();

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,1));
		add(centerPanel,"Center");

		centerArea = new JPanel();
		centerArea.setLayout(new GridLayout(1,0));
		centerPanel.add( centerArea );
		createTextArea();
		createInventoryArea();

		bottomArea = new JPanel();
		bottomArea.setLayout(new FlowLayout());
		centerPanel.add( bottomArea );
		createInput();

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2));
		add(bottomPanel,"South");
		createButtons();
	}

	/**
	 * Creates label for the window idk
	 */
	public void createTopLabel()
	{
		topLabel = new JLabel("Welcome"); //placeholder
		topPanel.add(topLabel);
	}

	/**
	 * Creates text area where output is printed.
	 */
	public void createTextArea()
	{
			textArea = new JTextArea();
			scrollPane = new JScrollPane(textArea); 
			textArea.setEditable(false);
			output = "";
			textArea.setText(output);
			centerArea.add(scrollPane);
	}

	/**
	 * Creates inventory text area
	 */
	public void createInventoryArea()
	{
		 textArea2 = new JTextArea(25,20);
		 textArea2.setEditable(false);
		 inventory = "";
		 String stand = "INVENTORY";
		 textArea2.setText(stand + "\n" + inventory);
		 centerArea.add(textArea2);
	}

	/**
	 * Creates field for user inputs
	 */
	public void createInput()
	{
		textField = new JTextField(20);
		bottomArea.add(new JLabel(" Input here: "));
		bottomArea.add( textField );
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				input = textField.getText();
			}
		});
	}

	/**
	 * Creates the ff buttons:
	 *	- Go North, South, East, West
	 *	-
	 */
	public void createButtons()
	{
		JButton moveUp = new JButton("Go North");
		class moveUpListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				printer.println("You went north");
				output += "You went north. \n";
				printer.println(); // current room
				output += "something \n"; // current room

				textArea.setText(output);
				printer.flush();
			}
		}
		moveUp.addActionListener(new moveUpListener());
		bottomPanel.add(moveUp);

		JButton moveDown = new JButton("Go South");
		class moveDownListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				printer.println("You went south");
				output += "You went south. \n";
				printer.println(); // current room
				output += "something \n"; // current room

				textArea.setText(output);
				printer.flush();
			}
		}
		moveDown.addActionListener(new moveDownListener());
		bottomPanel.add(moveDown);

		JButton moveLeft = new JButton("Go West");
		class moveLeftListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				printer.println("You went west");
				output += "You went west. \n";
				printer.println(); // current room
				output += "something \n"; // current room

				textArea.setText(output);
				printer.flush();
			}
		}
		moveLeft.addActionListener(new moveLeftListener());
		bottomPanel.add(moveLeft);

		JButton moveRight = new JButton("Go East");
		class moveRightListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				printer.println("You went east");
				output += "You went east. \n";
				printer.println(); // current room
				output += "something \n"; // current room

				textArea.setText(output);
				printer.flush();
			}
		}
		moveRight.addActionListener(new moveRightListener());
		bottomPanel.add(moveRight);
		
		
	}

    public void closer()
    {
        printer.close();
    }
}

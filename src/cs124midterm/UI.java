package cs124midterm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UI extends JFrame
{

	String output,inventory;
	PrintWriter printer;

	private JLabel topLabel;

	private JTextArea textArea, textArea2;

	private JTextField textField;

	private JPanel topPanel, centerPanel, bottomArea, centerArea, bottomPanel;

	public UI() throws FileNotFoundException
	{
		printer = new PrintWriter("log.txt");

		setLayout(new BorderLayout());

		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		add(topPanel, "North");
		createTopLabel();

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,2));
		add(centerPanel,"Center");

		centerArea = new JPanel();
		centerArea.setLayout(new FlowLayout());
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
			textArea = new JTextArea(10,20);
			textArea.setEditable(false);
			output = "";
			textArea.setText(output);
			centerArea.add(textArea);
	}

	/**
	 * Creates inventory text area
	 */
	public void createInventoryArea()
	{
		 textArea2 = new JTextArea();
		 textArea2.setEditable(false);
		 inventory = "";
		 textArea2.setText(inventory);
		 centerArea.add(textArea2);
		 centerArea.add(new JLabel("Inventory"));
	}

	/**
	 * Creates field for user inputs
	 */
	public void createInput()
	{
		textField = new JTextField();
		bottomArea.add( textField );
		bottomArea.add(new JLabel(" PLACEHOLDER "));
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

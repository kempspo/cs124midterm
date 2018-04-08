package cs124midterm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UI {

	String output;
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
		centerPanel.setLayout(new GridLayout(2,0));
		add(centerPanel,"Center");

		centerArea = new JPanel();
		centerArea.setLayout(new FlowLayout());
		centerPanel.add( centerArea );
		createTextArea();

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
	 * Creates label for the window
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
			textArea.setEditable(false);
			output = "";
			textArea.setText(output);
			centerArea.add(textArea);
	}

	public void createInventoryArea()
	{
		 textArea2 = new JTextArea();
		 textArea2.setEditable(false);
		 inventory = "";
		 textArea2.setText(inventory);
		 centerArea.add(textArea2);
		 centerArea.add(new JLabel("Inventory"));
	}

	public void createInput()
	{
		textField = new JTextField();
		bottomArea.add( textField );
		bottomArea.add(new JLabel(" PLACEHOLDER "));
	}

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
				output += "something"; // current room

				textArea.setText(output);
				printer.flush();
			}
		}
		moveUp.addActionListener(new moveUpListener());
		bottomPanel.add(moveUp);
	}
}

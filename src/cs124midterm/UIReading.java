package cs124midterm;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import anno.CheckEnter;
import anno.Command;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import cs124midterm.*;
import items.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UIReading extends JFrame
{
	private JLabel topLabel;

	public JTextArea textArea = new JTextArea();
	public JTextArea textArea2 = new JTextArea();

	private JTextField textField;

	private JPanel topPanel, centerPanel, bottomArea, centerArea, bottomPanel;
	
	private JScrollPane scrollPane;
	public UIReading ui;
	public Controller c;
	public doStuff ds = new doStuff();

	public void createUIRead() throws Exception {
		ui = new UIReading(c);
		ui.setSize( 1280, 720 );
		ui.setVisible( true );
		ui.setTitle( "UI Reading" );
		ui.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				windowEvent.getWindow().dispose();
			}
		});
	}
	
	public UIReading(Controller control) throws Exception
	{
		c = control;

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

		setTextArea(ds.load());

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
		scrollPane = new JScrollPane(textArea); 
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setEditable(false);
		textArea.setText("");
		centerArea.add(scrollPane);
	}
	
	public void setTextArea(String s)
	{
		textArea.append(s);
	}
	
	public void setInventory(String s)
	{
		textArea2.setText("INVENTORY\n" + s);
	}

	/**
	 * Creates inventory text area
	 */
	public void createInventoryArea()
	{
		 textArea2.setEditable(false);
		 String stand = "INVENTORY";
		 textArea2.setText(stand + "\n");
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
		textField.requestFocus();
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				setTextArea("> " + textField.getText() + "\n");
				setTextArea(ds.execute(textField.getText()));
				textField.setText("");
				textField.requestFocus();
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

				setTextArea("> go north\n");
				try {
					setTextArea(ds.move("north"));
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		moveUp.addActionListener(new moveUpListener());
		bottomPanel.add(moveUp);

		JButton moveDown = new JButton("Go South");
		class moveDownListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{

				setTextArea("> go south\n");
				try {
					setTextArea(ds.move("south"));
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		moveDown.addActionListener(new moveDownListener());
		bottomPanel.add(moveDown);

		JButton moveLeft = new JButton("Go West");
		class moveLeftListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{

				setTextArea("> go west\n");
				try {
					setTextArea(ds.move("west"));
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		moveLeft.addActionListener(new moveLeftListener());
		bottomPanel.add(moveLeft);

		JButton moveRight = new JButton("Go East");
		class moveRightListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				setTextArea("> go east\n");
				try {
					setTextArea(ds.move("east"));
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		moveRight.addActionListener(new moveRightListener());
		bottomPanel.add(moveRight);	
		
		JButton help = new JButton("Help");
		class helpListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				try {
					setTextArea(ds.help());
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		help.addActionListener(new helpListener());
		bottomPanel.add(help);	
		
		JButton save = new JButton("Save");
		class inventoryListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				try {
					ds.save();
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		save.addActionListener(new inventoryListener());
		bottomPanel.add(save);	
	}
}

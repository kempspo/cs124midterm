package cs124midterm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class UI extends JFrame {
	private JPanel topPanel, centerPanel, bottomArea, centerArea, bottomPanel;

	public JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane;	
	private JLabel topLabel;
	private JTextField textField;
	private static Controller c;
	private static UI ui;

	public void createUI() throws FileNotFoundException {
		ui = new UI(c);
		ui.setSize( 1280, 720 );
		ui.setVisible( true );
		ui.setTitle( "Mazes are cool" );
		ui.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				windowEvent.getWindow().dispose();
			}
		});
	}
	
	public UI(Controller control) throws FileNotFoundException 
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

		bottomArea = new JPanel();
		bottomArea.setLayout(new FlowLayout());
		centerPanel.add( bottomArea );
		createInput();

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2));
		add(bottomPanel,"South");
		createButtons();
	}

	public void createTopLabel()
	{
		topLabel = new JLabel("Welcome"); //placeholder
		topPanel.add(topLabel);
	}

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

	public void createInput()
	{
		textField = new JTextField(20);
		bottomArea.add(new JLabel(" Input here: "));
		bottomArea.add( textField );
		textField.requestFocus();

	}

	public void createButtons()
	{
		JButton fileRead = new JButton("File Reading Mode");
		class fileReadListener implements ActionListener
		{
			public void actionPerformed(ActionEvent ae) 
			{
				setTextArea("Switching to File Reading Mode\n");
				c.showFileRead();
			}
		}
		fileRead.addActionListener(new fileReadListener());
		bottomPanel.add(fileRead);

		JButton register = new JButton("Register User");
		class registerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent ae) 
			{
				try {
					c.showUIRead(textField.getText());
					ui.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		register.addActionListener(new registerListener());
		bottomPanel.add(register);
		
		JButton quit = new JButton("Quit");
		class quitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent ae) 
			{
				ui.dispose();
			}	
		}
		quit.addActionListener(new quitListener());
		bottomPanel.add(quit);
	}
}

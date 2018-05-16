package cs124midterm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class UIMainMenu extends JFrame implements State {
		private JLabel topLabel;
	
		public JTextArea textArea = new JTextArea();
		public JTextArea textArea2 = new JTextArea();
	
		private JTextField textField;
	
		private JPanel topPanel, centerPanel, bottomArea, centerArea, bottomPanel;
		
		private JScrollPane scrollPane;
		private Commands commands;

		public UIMainMenu(Commands c)
		{				
			commands = c;
			
			setLayout(new BorderLayout());

			topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout());
			add(topPanel, "North");
			
			centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(0,1));
			add(centerPanel,"Center");

			centerArea = new JPanel();
			centerArea.setLayout(new GridLayout(1,0));
			centerPanel.add( centerArea );
			
			bottomArea = new JPanel();
			bottomArea.setLayout(new FlowLayout());
			centerPanel.add( bottomArea );
			
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new GridLayout(2,2));
			add(bottomPanel,"South");
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

		public void createInventoryArea()
		{
			textArea2.setEditable(false);
			textArea2.setText("");
			centerArea.add(textArea2);
		}
		
		public void setTextArea(String s)
		{
			textArea.append(s);
		}
		
		public void setInventory(String s)
		{
			
		}

		public void createInput()
		{
			textField = new JTextField(20);
			bottomArea.add(new JLabel(" Input here: "));
			bottomArea.add( textField );
			textField.requestFocus();
		}

		public void createButtons(Controller c)
		{
			JButton fileRead = new JButton("File Reading Mode");
			class fileReadListener implements ActionListener
			{
				public void actionPerformed(ActionEvent ae) 
				{
					setTextArea("Switching to File Reading Mode\n");
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
						if(textField.getText().equals(""))
							setTextArea("Please input a name.\n");
						else
						{
							commands.register(textField.getText());
							c.setState(new UIReading(commands));
							dispose();
						}
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
					dispose();
				}	
			}
			quit.addActionListener(new quitListener());
			bottomPanel.add(quit);
		}
		
		public void make()
		{
			this.setSize(1280, 720);
			this.setVisible(true);
			this.setTitle("Mazes r kewl");
			this.addWindowListener((new java.awt.event.WindowAdapter()
			{
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent)
				{
					windowEvent.getWindow().dispose();
				}
			}));
		}
		
	}


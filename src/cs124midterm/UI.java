package cs124midterm;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import anno.CheckEnter;
import anno.Command;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import cs124midterm.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class UI extends JFrame
{
	private String inventory;
	private PrintWriter printer;

	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();
	private Object currentRoom;
	private Player player = new Player();;
	
	private JLabel topLabel;

	public JTextArea textArea = new JTextArea();
	public JTextArea textArea2 = new JTextArea();

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
		textField.requestFocus();
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				setTextArea("> " + textField.getText() + "\n");
				execute(textField.getText());
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
				printer.println("You went north");
				setTextArea("> go north\n");
				try {
					move("north");
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
				setTextArea("> go south\n");
				try {
					move("south");
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
				setTextArea("> go west\n");
				try {
					move("west");
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
				setTextArea("> go east\n");
				try {
					move("east");
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				printer.flush();
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
					help();
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				printer.flush();
			}
		}
		help.addActionListener(new helpListener());
		bottomPanel.add(help);	
		
		JButton inventory = new JButton("Inventory");
		class inventoryListener implements ActionListener
		{
			public void actionPerformed( ActionEvent ae )
			{
				try {
					setInventory(player.showInventory());
					textField.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				printer.flush();
			}
		}
		inventory.addActionListener(new inventoryListener());
		bottomPanel.add(inventory);	
	}

    public void closer()
    {
        printer.close();
    }

	public void load() throws Exception
	{
		// load all names
		FastClasspathScanner scanner = new FastClasspathScanner("room");
		ScanResult result = scanner.scan();
		
		List<String> allClasses = result.getNamesOfAllStandardClasses();		
		
		// instantiate
		for (String className : allClasses){
			Class<?> clazz = Class.forName(className);
			Object instance;
			
			if(clazz.getName().contains("room.Room")){
				if(clazz.isAnnotationPresent(CheckEnter.class)){
					instance = new Interceptor().run(clazz);
				}else instance = clazz.newInstance();
				roomMap.put(clazz, instance);
			}		
		}
		
		// associate fields

		for (Class roomClazz : roomMap.keySet())
		{
			Object currentRoom = roomMap.get(roomClazz);
			
			for (Field f : roomClazz.getDeclaredFields())
			{
				
				if (f.isAnnotationPresent(Direction.class))
				{
					Class fieldClazz = f.getType();
					Object roomInstance = roomMap.get(fieldClazz);
					f.setAccessible(true);
					f.set(currentRoom, roomInstance);
				}
			}
		}
		
		// set the first room
		currentRoom = roomMap.get(room.Room1.class);
		printDescription();
	}
	
	public void printDescription() throws Exception
	{
		Class<? extends Object> c = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			c = c.getSuperclass();
		}
		Method m = c.getDeclaredMethod("getDescription");
		String description = (String) m.invoke(roomMap.get(c));
		setTextArea(description + "\n");
	}
	
	public void move(String direction) throws Exception{
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			boolean somethingHappened = false;
			Field[] fields = clazz.getDeclaredFields();		
			for (Field f : fields)
			{
				if (f.isAnnotationPresent(Direction.class))
				{
					Direction d = f.getAnnotation(Direction.class);
					if (d.command().equals(direction)){
						Class<?> fieldClass = f.getType();
						Object o = roomMap.get(fieldClass);
						if(o instanceof EnterCondition /*o.toString().contains("ByteBuddy")*/)
						{
							if(((EnterCondition) o).canEnter(player))
							{
								setTextArea(((EnterCondition) o).enterMessage());
								currentRoom = o.getClass().getSuperclass().newInstance();
								somethingHappened = true;
							}
							else
							{
								setTextArea(((EnterCondition) o).unableToEnterMessage());
								somethingHappened = true;
							}
						}
						else
						{
							currentRoom = o;
							somethingHappened = true;
						}
						printDescription();
						break;
					}
				}
			}
			if(!somethingHappened)
				setTextArea("You can't go that way.\n");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void execute(String command){
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			boolean somethingHappened = false;
			Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods){
				if(m.isAnnotationPresent(Command.class)){
					Command c = m.getAnnotation(Command.class);
					if(command.contains(" ")){
						String[] methodParams = command.split(" ");
						if(c.command().equals(methodParams[0])){
							if(methodParams[0].equals("password")){
								Method passIt = clazz.getDeclaredMethod("password", String.class);
								passIt.setAccessible(true);
								String pass = (String) passIt.invoke(roomMap.get(clazz), methodParams[1]);		
								setTextArea(pass);
								somethingHappened = true;
							}
							if(methodParams[0].equals("take")){
								Method passIt = clazz.getDeclaredMethod("removeItem", String.class, Player.class);
								passIt.setAccessible(true);
								String take = (String) passIt.invoke(roomMap.get(clazz), methodParams[1], player);		
								setTextArea(take + "\n");
								setInventory(player.showInventory());
								somethingHappened = true;
							}	
							if(methodParams[0].equals("drop")){
								Method passIt = clazz.getDeclaredMethod("addItem", String.class, Player.class);
								passIt.setAccessible(true);
								String drop = (String) passIt.invoke(roomMap.get(clazz), methodParams[1], player);	
								setTextArea(drop + "\n");
								setInventory(player.showInventory());
								somethingHappened = true;
							}
							if(methodParams[0].equals("use")){
								Method passIt = clazz.getDeclaredMethod("useItem", String.class, Player.class);
								passIt.setAccessible(true);
								String use = (String) passIt.invoke(roomMap.get(clazz), methodParams[1], player);	
								setTextArea(use + "\n");
								somethingHappened = true;
							}
						}
					}
					else{
						if(c.command().equals(command)){
							Method ex = clazz.getDeclaredMethod(command);
							ex.setAccessible(true);
							String some = (String) ex.invoke(roomMap.get(clazz));
							setTextArea(some + "\n");
							somethingHappened = true;
						}
					}
				}
			}
			if(!somethingHappened)
				setTextArea("You can't do that.\n");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void help()
	{
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			setTextArea("Available Commands/Directions:\n");
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods){
				if(m.isAnnotationPresent(Command.class)){
					Command c = m.getAnnotation(Command.class);
					setTextArea(c.command() + "\n");
				}
			}
			for(Field f: fields) {
				if(f.isAnnotationPresent(Direction.class)) {
					Direction d = f.getAnnotation(Direction.class);
					setTextArea(f.getName() + "\n");
				}
			}
		}catch(Exception e){
			System.out.println("Something went wrong.");
			System.out.println(e);
		}
	}
	
}

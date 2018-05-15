package cs124midterm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import anno.CheckEnter;
import anno.Command;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class Commands {
	//
	String inventory;

	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();
	private Object currentRoom;
	private Player player;
	private boolean newGame = true;
	private boolean invChange = false;
	private boolean textChange = false;
	private File file;
	private SaveData save;
	
	public void register(String name) throws Exception
	{
		System.out.println("happening");
		file = new File(name +".json");
		if(file.exists())
		{
			try { 
				newGame = false;
				FileInputStream fileIn = new FileInputStream(file);
		        ObjectInputStream objIn = new ObjectInputStream(fileIn);
		        save = (SaveData) objIn.readObject();
		        objIn.close();
		        fileIn.close();
		    } catch (Exception e) {
		    	System.out.println("hello" + e);
		    }
		}
		else
		{
			player = new Player(name);
		}
	}
	
	public String load() throws Exception
	{
		if(newGame)
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
		}
		
		else
		{
			roomMap = save.getRoomMap();
			player = save.getPlayer();
			currentRoom = save.getCurrentRoom();
		}
		return printDescription();
	}
	
	public String printDescription() throws Exception
	{
		Class<? extends Object> c = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			c = c.getSuperclass();
		}
		Method m = c.getDeclaredMethod("getDescription");
		String description = (String) m.invoke(roomMap.get(c));
		return (description + "\n");
	}
	
	public String move(String direction) throws Exception{
		String m = "";
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
								m = ((EnterCondition) o).enterMessage();
								currentRoom = o.getClass().getSuperclass().newInstance();
								somethingHappened = true;
							}
							else
							{
								m = (((EnterCondition) o).unableToEnterMessage());
								somethingHappened = true;
							}
						}
						else
						{
							currentRoom = o;
							somethingHappened = true;
						}
						m = printDescription();
						break;
					}
				}
			}
			if(!somethingHappened)
				m = ("You can't go that way.\n");
			return m;
		}catch(Exception e){e.printStackTrace();}
		return m;
	}
	
	public String execute(String command){
		String exec = "";
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			boolean somethingHappened = false;
			invChange = false;
			textChange = false;
			Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods){
				if(m.isAnnotationPresent(Command.class)){
					Command c = m.getAnnotation(Command.class);
					if(command.contains(" ")){
						String[] methodParams = command.split(" ");
						if(c.command().equals(methodParams[0])){
								Method passIt = clazz.getDeclaredMethod(methodParams[0], String.class, Player.class);
								passIt.setAccessible(true);
								String pass = (String) passIt.invoke(roomMap.get(clazz), methodParams[1], player);
								exec = (pass + "\n");
								if(methodParams[0].equals("drop") || methodParams[0].equals("take")) {
									invChange = true;
								}
								else
									textChange = true;
								somethingHappened = true;
						}
					}
					else{
						if(c.command().equals(command)){
							Method ex = clazz.getDeclaredMethod(command);
							ex.setAccessible(true);
							String some = (String) ex.invoke(roomMap.get(clazz));
							exec = (some + "\n");
							somethingHappened = true;
						}
					}
				}
			}
			if(!somethingHappened)
				exec = ("You can't do that.\n");
			return exec;
		}catch(Exception e){
			return exec;
		}
	}
	
	public String help()
	{
		String help = "";
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			help = ("Available Commands/Directions:\n");
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods){
				if(m.isAnnotationPresent(Command.class)){
					Command c = m.getAnnotation(Command.class);
					help = (help + c.command() + "\n");
				}
			}
			for(Field f: fields) {
				if(f.isAnnotationPresent(Direction.class)) {
					Direction d = f.getAnnotation(Direction.class);
					help = (help + f.getName() + "\n");
				}
			}
			return help;
		}catch(Exception e){
			help = ("Something went wrong.");
			System.out.println(e);
		return help;
		}
	}
	
	public void save() throws Exception
	{
		try {
			save.setRoomMap(roomMap);
			save.setPlayer(player);
			save.setCurrentRoom(currentRoom);
			
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(save);
			objOut.close();
			fileOut.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
		
	public String getInventory()
	{
		return player.showInventory();
	}
	
	public boolean getInvChange()
	{
		return invChange;	
	}
	
	public boolean getTextChange()
	{
		return textChange;
	}
}

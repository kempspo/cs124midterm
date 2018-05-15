package cs124midterm;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import room.*;
import anno.CheckEnter;
import anno.Command;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class doStuff {
	String inventory;

	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();
	private Object currentRoom;
	private Player player = new Player();
	
	private File file;

	public String load() throws Exception
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
								exec = (methodParams[0] + "\n");
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
					help = (c.command() + "\n");
				}
			}
			for(Field f: fields) {
				if(f.isAnnotationPresent(Direction.class)) {
					Direction d = f.getAnnotation(Direction.class);
					help = (f.getName() + "\n");
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
		HashMap<String, Item> playerInv = player.getInventory();
		
		FileWriter fw = new FileWriter("save.txt"); // must change to username to allow
		PrintWriter pw = new PrintWriter(fw);
		
		try {
			pw.println("PLAYER");		
			for(String key : playerInv.keySet())
				pw.println(key);
			pw.println();
			
			for(Class clazz : roomMap.keySet())
			{
				Object lul = roomMap.get(clazz);
				Class<? extends Object> lul2 = lul.getClass();
				if(lul instanceof EnterCondition) {
					lul2 = lul2.getSuperclass();
				}
				Method lul3 = lul2.getDeclaredMethod("getItems");
				lul3.setAccessible(true);
			
				HashMap<String, Item> roomInv = (HashMap<String, Item>) lul3.invoke(roomMap.get(lul2));
				
				pw.println(lul2.getName());
				for(String key : roomInv.keySet())
					pw.println(key);
				pw.println();
			}
		} catch(Exception e){
			System.out.println(e);
		}
		pw.close();
	}
	
	public void register(String string) throws Exception
	{
		file = new File(string +".txt");
		if(file.exists())
		{
			load();
		}
		else
		{
			
		}
	}
	
	public void loadPlayer() throws Exception
	{
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			String next = sc.nextLine();
			if(next.contains("PLAYER"))
			{
				//player.setInventory(inv);
			}
			else {
				for(Class clazz : roomMap.keySet())
				{
					Object lul = roomMap.get(clazz);
					Class<? extends Object> lul2 = lul.getClass();
					if(lul instanceof EnterCondition) {
						lul2 = lul2.getSuperclass();
					}
					Method lul3 = lul2.getDeclaredMethod("getItems");
					lul3.setAccessible(true);
				
					HashMap<String, Item> roomInv = (HashMap<String, Item>) lul3.invoke(roomMap.get(lul2));
					
					for(String key : roomInv.keySet()) {
						
					}
						
				}
			}
		}
	}
	
	public HashMap getInventory()
	{
		return player.getInventory();	
	}
}

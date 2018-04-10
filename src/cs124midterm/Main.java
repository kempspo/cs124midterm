package cs124midterm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import anno.CheckEnter;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import cs124midterm.*;

public class Main
{
	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();
	private Object currentRoom;
	public static UI ui;

	public void load() throws Exception
	{
		// load all names
		FastClasspathScanner scanner = new FastClasspathScanner("room");
		ScanResult result = scanner.scan();
		
		List<String> allClasses = result.getNamesOfAllStandardClasses();		
		//System.out.println(allClasses);
		
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
		ui.setTextArea(description);
	}
	
	public void move(String direction) throws Exception{
		Class<? extends Object> clazz = currentRoom.getClass();
		if(currentRoom instanceof EnterCondition) {
			clazz = clazz.getSuperclass();
		}
		try{
			Field[] fields = clazz.getDeclaredFields();		
			for (Field f : fields){
				if (f.isAnnotationPresent(Direction.class)){
					Direction d = f.getAnnotation(Direction.class);
					if (d.command().equals(direction)){
						Class<?> fieldClass = f.getType();
						Object o = roomMap.get(fieldClass);
						if(o instanceof EnterCondition /*o.toString().contains("ByteBuddy")*/){
							if(((EnterCondition) o).canEnter()){
								ui.setTextArea(((EnterCondition) o).enterMessage());
								currentRoom = o.getClass().getSuperclass().newInstance();
							}else ui.setTextArea(((EnterCondition) o).unableToEnterMessage());
						}else currentRoom = o;
						printDescription();
						break;
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
    public static void main(String[] args) throws Exception
    {    	
        ui = new UI();
        ui.setSize( 1280, 720 );
        ui.setVisible( true );
        ui.setTitle( "Mazes are cool" );
        ui.addWindowListener(new java.awt.event.WindowAdapter()
        {
          @Override
          public void windowClosing(java.awt.event.WindowEvent windowEvent)
          {
            ui.closer();
            windowEvent.getWindow().dispose();
          }
        });
        
    	Main maze = new Main();
    	maze.load();
    }
}

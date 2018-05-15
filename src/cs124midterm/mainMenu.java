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

public class mainMenu implements State{
	private Player player;
	private File file;
	private HashMap<Class, Object> roomMap = new HashMap<Class, Object>();

	public void register(String string) throws Exception
	{
		file = new File(string +".txt");
		if(file.exists())
		{
			loadPlayer();
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

	public void change(Controller c) 
	{
		c.setState(new doStuff());
	}
}

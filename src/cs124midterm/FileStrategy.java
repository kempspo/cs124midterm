package cs124midterm;

import java.io.*;
import java.util.*;


public class FileStrategy implements Strategy {
	private Commands c;

	public void read(String string, Commands com, Controller con) throws Exception 
	{
		c = com;
		File file = new File(string);
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine())
		{
			String next = sc.nextLine();
			if(next.contains("register")) 
			{
				c.register(next);
				c.load();
			}
			if(next.contains("move")) 
			{
				c.move(next);
			}
			if(next.contains("execute"))
			{
				c.execute(next);
			}
		}
		sc.close();
	}
	
}

package cs124midterm;

import java.io.*;
import java.util.*;


public class FileStrategy implements Strategy {
	private Commands c;

	public void read(String string, Commands com, Controller con) throws Exception 
	{
		File file = new File(string);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			String next = sc.nextLine();
			String[] stuff = next.split(" ");
			if(next.contains("register")) 
			{
				com.register(stuff[1]);
				com.load();
			}
			if(next.contains("move")) 
			{
				com.move(stuff[1]);
			}
			if(next.contains("execute"))
			{
				com.execute(stuff[1]);
			}
			com.save();
		}
		sc.close();
		con.setState(new UIReading(c));
	}
	
}

package cs124midterm;

import java.io.*;
import java.util.*;

public class FileStrategy implements Strategy {
	public doStuff ds;
	public UIReading uiread;

	public void read(String string) throws Exception 
	{
		File file = new File(string);
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine())
		{
			String next = sc.nextLine();
			
			if(next.contains("move")) 
			{
				uiread.ds.move(next);
			}
			if(next.contains("execute"))
			{
				uiread.ds.execute(next);
			}
		}
		sc.close();
	}
}

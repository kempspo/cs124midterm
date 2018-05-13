package cs124midterm;

import java.io.*;
import java.util.*;

public class FileStrategy implements Strategy {
	public static UIReading ui;

	@Override
	public void read(String string) throws Exception 
	{
		File file = new File(string);
		Scanner sc = new Scanner(file);
		ui = new UIReading();
		
		while(sc.hasNextLine())
		{
			String next = sc.nextLine();
			
			if(next.contains("move")) 
			{
				ui.move(next);
			}
			if(next.contains("execute"))
			{
				ui.execute(next);
			}
		}
		sc.close();
	}

}

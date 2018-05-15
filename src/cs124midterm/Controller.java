package cs124midterm;

import java.io.FileNotFoundException;

public class Controller{
	UI ui;
	UIReading uiread;
	FileStrategy fs;
	//doStuff ds;

	public Controller() throws Exception
	{
		ui = new UI(this);
		uiread = new UIReading(this);
		//fileread = new FileReading(this);
		
		showUI();
	}
	
	public void showUI() throws Exception
	{
		ui.createUI();
	}
	
	public void showUIRead(String string) throws Exception
	{
		uiread.createUIRead();
		uiread.ds.register(string);
	}
	
	public void showFileRead()
	{
		//fs.read();
	}


}
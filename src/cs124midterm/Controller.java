package cs124midterm;

import java.io.FileNotFoundException;

public class Controller{
	UI ui;
	UIReading uiread;
	FileReading fileread;

	public Controller() throws Exception
	{
		ui = new UI(this);
		uiread = new UIReading(this);
		//fileread = new FileReading(this);
		
		showUI();
	}
	
	public void showUI() throws FileNotFoundException
	{
		ui.createUI();
	}
	
	public void showUIRead() throws FileNotFoundException
	{
		uiread.createUIRead();
	}
	
	public void showFileRead()
	{
		//fileread.createFileRead();
	}


}
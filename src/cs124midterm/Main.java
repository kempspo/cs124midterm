package cs124midterm;

public class Main
{	
	public static Controller control;
	public static UIReading ui;
	public static void main(String[] args) throws Exception
	{
		int temp = 0;
		Controller control = new Controller();
		
		while(true)
		{	
			if(!control.getChanged() && temp == 0)
			{
				control.createTopLabel();
				control.createTextArea();
				control.createInventoryArea();
				control.createInput();
				control.createButtons();
				control.make();
				temp++;
			}
			if(control.getChanged() && temp == 1)
			{
				control.createTopLabel();
				control.createTextArea();
				control.createInventoryArea();
				control.createInput();
				control.createButtons();
				control.make();
				temp--;
			}
		}
		
		
	}
}	
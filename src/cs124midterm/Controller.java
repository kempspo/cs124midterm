package cs124midterm;

import java.io.FileNotFoundException;

public class Controller{

	private State currentState;

	public Controller()
	{
		currentState = new mainMenu();
	}
	
	public void setState(State s)
	{
		currentState = s;
	}
	
	public void change()
	{
		currentState.change(this);
	}
	
	public State getCurrent() 
	{
		return currentState;
	}
}
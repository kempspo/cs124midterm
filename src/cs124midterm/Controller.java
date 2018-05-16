package cs124midterm;


public class Controller{

	private State currentState;
	private Commands c;
	private volatile boolean changed;

	public Controller()
	{
		c = new Commands();
		currentState = new UIMainMenu(c);
		changed = false;
	}
	
	public State getState() 
	{
		return currentState;
	}
	
	public void setState(State s)
	{
		currentState = s;
		changed = !changed;
	}
	
	public void createTopLabel()
	{
		currentState.createTopLabel();
	}
	
	public void createTextArea()
	{
		currentState.createTextArea();
	}
	
	public void createInventoryArea()
	{
		currentState.createInventoryArea();
	}
	
	public void setTextArea(String s)
	{
		currentState.setTextArea(s);
	}
	
	public void setInventory(String s)
	{
		currentState.setInventory(s);
	}
	
	public void createInput()
	{
		currentState.createInput();
	}
	
	public void createButtons()
	{
		currentState.createButtons(this);
	}
	
	public void make() throws Exception
	{
		try {
			currentState.make();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getChanged()
	{
		return changed;
	}
}
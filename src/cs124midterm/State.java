package cs124midterm;

public interface State 
{
	public void createTopLabel();
	public void createTextArea();
	public void createInventoryArea();
	public void setTextArea(String s);
	public void setInventory(String s);
	public void createInput();
	public void createButtons(Controller c);
	public void make();
}

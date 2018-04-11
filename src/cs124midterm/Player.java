package cs124midterm;

import java.util.*;
import room.Room;

public class Player 
{
	public Map<String, Item> inventory = new HashMap<String, Item>();
	public boolean alive = true;
	
	public String take(String itemName, Item item)
	{
		inventory.put(itemName, item);
		return "You took the " + item + " and placed it in your inventory.";
	}

	public String drop(String item)
	{
		inventory.remove(item);
		return "You dropped the " + item + " from your inventory.";
	}
	
	public boolean hasItem(String item)
	{
		if(inventory.containsKey(item))
			return true;
		else
			return false;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setDead() {
		alive = false;
	}
}

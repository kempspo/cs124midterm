package cs124midterm;

import java.util.*;
import room.Room;

public class Player 
{
	Map<String, Item> inventory = new HashMap<String, Item>();
	
	public String takeItem(String itemName, Item item)
	{
		inventory.put(itemName, item);
		return "You took the " + item + " and placed it in your inventory.";
	}

	public String dropItem(String item)
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
}

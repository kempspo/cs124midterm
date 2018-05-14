package cs124midterm;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Player 
{
	public HashMap<String, Item> inventory = new HashMap<String, Item>();
	public boolean alive = true;
	
	public String take(String itemName, Item item)
	{
		inventory.put(itemName, item);
		return "You took the " + itemName + " and placed it in your inventory.";
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
	
	
	public String showInventory()
	{
		String output = "";
		
		if(!inventory.isEmpty())
		{			
			for(String key: inventory.keySet())
			{
				output += key + "\n";
			}
		}
		return output;
	}
	
	public HashMap getInventory()
	{
		return inventory;
	}
	
	public void setInventory(String inv)
	{
		//inventory.put(inv);
	}
}

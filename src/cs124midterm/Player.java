package cs124midterm;

import anno.*;
import cs124midterm.*;
import items.*;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable
{
	private String name;
	private HashMap<String, Item> inventory;
	private boolean alive;
	private boolean win;
	
	public Player(String n)
	{
		name = n;
		inventory = new HashMap<String, Item>();
		alive = true;
		win = false;
	}
	
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

	public String getName() {
		return name;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setDead() {
		alive = false;
	}
	
	public boolean getWin() {
		return win;
	}
	
	public void setWin() {
		win = true;
	}
	
	public HashMap<String, Item> getInventory()
	{
		return inventory;
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
	
	public boolean hasItem(String item)
	{
		if(inventory.containsKey(item))
			return true;
		else
			return false;
	}
}

package room;

import java.util.*;
import cs124midterm.Item;

public class Room
{
	public Map<String, Item> itemsInRoom = new HashMap<String, Item>();

	public String getDescription()
	{
		return null;
	}

	public boolean hasItem(String item) //check if item is in room hashmap
	{
		if(itemsInRoom.containsKey(item))
			return true;
		else
			return false;
	}

	public void checkItem(String item)
	{
		if(hasItem(item)) //if item is in room hashmap
			System.out.println(itemsInRoom.get(item).getDescription()); //print description of item
		else
			System.out.println("There is no " + item + " in the room.");
	}

	public void takeItem(String item)
	{
		if(hasItem(item))
		{
			itemsInRoom.remove(item); //remove item from room hashmap
			System.out.println("You took the " + item + ".");
		}
		else
			System.out.println("There is no " + item + " in the room.");
	}

	@Command(command="look")
	public String look()
	{
		return null;
	}
}

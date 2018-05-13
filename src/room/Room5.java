package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room5 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	{
		itemsInRoom.put("knife", new Key());
	}
	
	@Direction(command="north")
	private Room3 north;
	@Direction(command="east")
	private Room6 east;

	public String getDescription()
	{
		return "You are now in Room 5 \nYou enter the room. There's a rack of knives.";
	}

	@Command(command="look")
	public String look()
	{
		String output = "There's nothing except knives.";
		
		if(!itemsInRoom.isEmpty())
		{
			int temp = 1;
			output = "Perhaps you can take the following items:\n";
			for(String key : itemsInRoom.keySet())
			{
				if(temp < itemsInRoom.size())
					output += key + "\n";
				else
					output += key;
				temp++;
			}
		}
		
		return output;
	}

	public boolean hasItem(String item)
	{
		if(itemsInRoom.containsKey(item))
			return true;
		else
			return false;
	}
  
	@Command(command="take")
	public String removeItem(String item, Player player)
	{
		if(hasItem(item))
		{
			String text = player.take(item, itemsInRoom.get(item));
			itemsInRoom.remove(item);
			return text;
		}
		else
			return "There is no " + item + " anywhere around you.";
	}
  
	@Command(command="drop")
	public String addItem(String item, Player player)
	{
		if(player.hasItem(item))
		{
			itemsInRoom.put(item, player.inventory.get(item));
			String text = player.drop(item);
			return text;
		}
		else
			return "You don't have a " + item + " in your inventory.";
	}
	
	public HashMap getItems()
	{
		return itemsInRoom;
	}
}

package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

@CheckEnter
public class Room7 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	static boolean canEnter = false;
	
	@Direction(command="north")
	private Room6 north;
	@Direction(command="west")
	private Room8 west;

	public String getDescription()
	{
		return "You are now in Room 7 \nNot much here";
	}

	@Command(command="look")
	public String look()
	{
		String output = "There's nothing of use.";
		
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
	public String take(String item, Player player)
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
	public String drop(String item, Player player)
	{
		if(player.hasItem(item))
		{
			itemsInRoom.put(item, player.getInventory().get(item));
			String text = player.drop(item);
			return text;
		}
		else
			return "You don't have a " + item + " in your inventory.";
	}

	public static boolean canEnter(Player player) 
	{
	    if(player.hasItem("key")) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}

	public static String enterMessage()
	{
		return "You got inside!\n";
	}

	public static String unableToEnterMessage()
	{
		return "You cannot go inside!\n";
	}
	
	public HashMap getItems()
	{
		return itemsInRoom;
	}
}

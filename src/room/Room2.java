package room;

import anno.*;
import java.util.*;
import cs124midterm.*;
import items.*;

public class Room2 implements Room
{
	private Map<String, Item> itemsInRoom = new HashMap<String, Item>(); 
	{
		itemsInRoom.put("pan",new Pan());
	}
	private boolean beenHere = false;
	
	@Direction(command="south")
	private Room1 south;
	@Direction(command="west")
	private Room3 west;
	@Direction(command="east")
	private Room4 east;

	public String getDescription()
	{
		if(!beenHere)
		{
			beenHere = true;
			return "You are now in Room 2. \nYou hear screams from an adjacent room, but there are 2 other doors to your left and right, and you don't know from which it came from.";
		}
		else
			return "You are now in Room 2. Many many doors.";
	}

	@Command(command="look")
	public String look()
	{
		String output = "You look around. Looks like a storage room of some sort. There's a bunch of pots and pans.\n";
		
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
}

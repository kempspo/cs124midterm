package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room4 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	{
		itemsInRoom.put("key", new Key());
	}
	
	
	@Direction(command="west")
	private Room2 west;

	public String getDescription()
	{
		return "You are now in Room 4 \nThe room's pretty empty. Just a bunch of chairs and tables.";
	}

	@Command(command="look")
	public String look()
	{
		return "You look around and find a set of keys on the rack.";
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

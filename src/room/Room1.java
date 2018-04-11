package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room1
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="north")
	private Room2 north;

	public String getDescription()
	{
		return "You wake up. You're on the floor. Your head's throbbing. You can:";
	}

	@Command(command="look")
	public String look()
	{
		return "There's nothing here move on\n";
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

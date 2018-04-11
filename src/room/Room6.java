package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room6 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="west")
	private Room5 west;
	@Direction(command="south")
	private Room7 south;

	public String getDescription()
  	{
		return "You are now in Room 6 \nThe room is full of trash.";
  	}

	@Command(command="look")
	public String look()
	{
		return "";
	}

	public boolean hasItem(String item)
	{
		if(itemsInRoom.containsKey(item))
			return true;
		else
			return false;
	}
  
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

package room;

import anno.*;
import cs124midterm.*;

import java.util.*;

public class Room5 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="north")
	private Room3 north;
	@Direction(command="east")
	private Room6 east;

	public String getDescription()
	{
		return "You enter the room. There's a rack of knives.";
	}

	@Command(command="look")
	public String look()
	{
		return "There's nothing except knives.";
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

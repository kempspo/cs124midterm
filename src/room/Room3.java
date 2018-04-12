package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room3 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="south")
	private Room5 south;
	@Direction(command="east")
	private Room2 east;

	public String getDescription()
	{
		return "You are now in Room 3 \nYou enter the room. You see a body tied up on the table.";
	}

	@Command(command="look")
	public String look()
	{
		return "You take a closer look around. Nothing but the corpse in here.";
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

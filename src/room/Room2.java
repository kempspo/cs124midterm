package room;

import anno.*;
import java.util.*;
import cs124midterm.*;

public class Room2 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="Go South")
	private Room1 south;
	@Direction(command="Go West")
	private Room3 west;
	@Direction(command="Go East")
	private Room4 east;

	public String getDescription()
	{
		return "You hear screams from an adjacent room,"
		+ "but there are 2 other doors and you don't know from"
		+ "which it came from.";
	}

	@Command(command="look")
	public String look()
	{
		return "You look around. Looks like a storage room of some sort."
			+ " There's a bunch of pots and pans. You may use command 'take pan'";
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

package room;

import anno.*;
import java.util.*;
import cs124midterm.*;

public class Room3 implements Room
{
	Map<String, Item> itemsInInventory = new HashMap<String, Item>();
	
	@Direction(command="Go South")
	private Room5 south;
	@Direction(command="Go East")
	private Room2 east;

	public String getDescription()
	{
		return "You enter the room. You see a girl tied up on the table.";
	}

	@Command(command="look")
	public String look()
	{
		return "You take a closer look around."
		+ " The girl on the table is your friend.";
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

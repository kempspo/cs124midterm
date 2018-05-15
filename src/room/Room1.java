package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room1 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	private boolean beenHereBefore = false;
	
	@Direction(command="north")
	private Room2 north;

	public String getDescription()
	{
		if(!beenHereBefore)
		{
			beenHereBefore = true;
			return "You wake up. You're on the floor. Your head's throbbing. Maybe you should 'look' around.";
		}
		else
			return "You are back where you started.\nYou still can't remember how you ended up here.";
	}

	@Command(command="look")
	public String look()
	{
		String output = "";
		if(itemsInRoom.isEmpty())
			output = "There's nothing here move on.";
		else
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

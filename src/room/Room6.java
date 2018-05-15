package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room6 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	private boolean cutTrash = false;
	
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
		String output = "There is a mirror in the room. You are one with the trash.";
		
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
			itemsInRoom.put(item, player.inventory.get(item));
			String text = player.drop(item);
			return text;
		}
		else
			return "You don't have a " + item + " in your inventory.";
	}
	
	@Command(command="use")
	public String useItem(String item, Player player)
	{
		if(player.hasItem(item))
		{
			String text ="";
			if(item.equals("knife"))
			{
				if(!cutTrash)
				{
					text = "In an episode of boredom, you begin cutting slips of paper with the knife. Just when you think this is pointless, you find a paper with 'Gen' scribbled on it.";
					cutTrash = true;
				}
				else
				{
					text = "You continue where you left off. Definitely a worthwhile way to spend the time.";
					cutTrash = true;
				}
			}
			else if(item.equals("pan"))
				text = "You put the slips of paper in the pan and pretend to cook.";
			else
				text = "That didn't do anything.";
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

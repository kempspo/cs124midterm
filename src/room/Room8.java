package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room8 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="east")
	private Room7 east;
	@Direction(command="south")
	private Room9 south;

	public String getDescription()
	{
		return "You are now in Room 8 \nIt's all condiments here. There's some salt, pepper, "
		+ "some other stuff I don't really know.";
	}

	@Command(command="look")
	public String look()
	{
		String output = "There's a bunch of salt spilled in the corner. Inscribed in it is 'Password: 3,2,1'. Who draws with salt?";
		
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
			itemsInRoom.put(item, player.getInventory().get(item));
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

package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room3 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	private boolean friendSaved = false;
	
	@Direction(command="south")
	private Room5 south;
	@Direction(command="east")
	private Room2 east;

	public String getDescription()
	{
		return "You are now in Room 3 \nYou enter the room. You see somebody tied up on the table.";
	}

	@Command(command="look")
	public String look()
	{
		String output = "";
		
		if(!friendSaved)
			output = "You take a closer look around. It's your friend. Sad.";
		else
			output = "It's the table your friend was tied up on. It says 'Ess'.";
		
		if(!itemsInRoom.isEmpty())
		{
			int temp = 1;
			output += "Perhaps you can take the following items:\n";
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
	
	@Command(command="use")
	public String useItem(String item, Player player)
	{
		if(player.hasItem(item))
		{
			String text ="";
			if(!friendSaved)
			{
				if(item.equals("knife"))
				{
					text = "You cut your friend free using the knife. Underneath where she was is written " + "Ess.";
					friendSaved = true;
				}
				else if(item.equals("pan"))
				{
					text = "You try to free your friend using the pan. You realize this is a stupid idea and you knock yourself out. You sustain a serious concussion and die. The End.";
					player.setDead();
				}
			}
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

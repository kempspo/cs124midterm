package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room9 implements Room
{
	private HashMap<String, Item> itemsInRoom = new HashMap<String, Item>();
	private boolean brotherSaved = false;
	
	@Direction(command="north")
	private Room8 north;
	@Direction(command="west")
	private Room10 west;

	public String getDescription()
	{
		return "You are now in Room 9\nYou see a man in white. He's about to throw something into a pot.";
	} 

	@Command(command="look")
	public String look()
	{
		String output = "";
		if(!brotherSaved)
			output = "You look closer at what the man is throwing into the pot. It's your brother.";
		else
			output = "You imagine what would've happened if your brother got cooked. You can't bear the thought.";
		
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
			if(!brotherSaved)
			{
				if(item.equals("knife"))
				{
					text = "You realize that killing is bad. In that split second of hesitation, your brother gets cooked. You kill yourself cause you're useless.";
					player.setDead();
				}
				else if(item.equals("pan"))
				{
					text = "You knocked him out. He tells you 'Let's get out of this DUNgeon.'";
					brotherSaved = true;
				}
				else if(item.equals("key"))
				{
					text = "You attack the man with the key. Your stupidity dawns on you, but it's too late and you've thrown into the pot and cooked with your brother. The End.";
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

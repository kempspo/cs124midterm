package room;

import anno.*;
import cs124midterm.*;
import items.*;
import java.util.*;

public class Room9 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	
	@Direction(command="north")
	private Room8 north;
	@Direction(command="west")
	private Room10 west;

	public String getDescription()
	{
		return "You are now in Room 9 \nYou see a man in white. "
		+ "He's about to throw something into a pot.";
	}

	@Command(command="look")
	public String look()
	{
		return "You look closer at what the man is throwing into the pot. "
    +  "It's your brother.";
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
	
	@Command(command="use")
	public String useItem(String item, Player player)
	{
		if(player.hasItem(item))
		{
			String text ="";
			if(item == "knife")
				text = "Don't kill that's bad";
			else if(item == "pan")
				text = "You knocked him out.";
			return text;
		}
		else
			return "You don't have a " + item + " in your inventory.";
	
	}
}

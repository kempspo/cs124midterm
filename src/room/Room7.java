package room;

import anno.*;
import cs124midterm.*;

import java.util.*;

@CheckEnter
public class Room7 implements Room
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	static boolean canEnter = false;
	
	@Direction(command="north")
	private Room6 north;
	@Direction(command="west")
	private Room8 west;

	public String getDescription()
	{
		return "You are now in Room 7 \nNot much here";
	}

	@Command(command="look")
	public String look()
	{
		return "There's nothing of use.";
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

	public static boolean canEnter() 
	{
	    if(canEnter == true) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public static void check(String item, Player player)
	{
	    if(player.hasItem(item)) {
	    	canEnter = true;
	    }
	    else {
	    	canEnter = false;
	    }
	}

	public static String enterMessage()
	{
		return "You got inside!";
	}

	public static String unableToEnterMessage()
	{
		return "You cannot go inside!";
	}
}

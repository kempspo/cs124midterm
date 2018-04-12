package room;

import anno.*;
import cs124midterm.*;

import java.io.StringWriter;
import java.util.*;

public class Room10 implements Room
{	
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	String response;
	
	public String getDescription()
	{
		return "You are now in Room 10 \nYou see a door locked via keypad. "
		+ "Enter the password on the keypad.";
	}
  
	@Command(command="look")
	public String look()
	{
		return "It's only the keypad.";
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
	
	@Command(command = "password")
    public String password(String word, Player player)
    {
        if ((word.equalsIgnoreCase("DunGenEss")))
        {
            if(player.hasItem("knife"))
            {
                response = ("That is correct. But you accidentally stabbed yourself and died. The End.");
                player.setDead();
            }
            else
            {
                response = ("That is correct. The door opens and you're free. If you haven't realize yet you're a crab. The End.");
            }
        }
        else
        {
            response = ("That is incorrect. The men in white catch up to you. They tie you up and throw you into a\r\n" + 
            		"    boiling pot. You're dead. You're now a delicious steamed Dungeness crab.\r\n" + 
            		"The end.");
            player.setDead();
        }
        
        return response;
    }
}

package cs124midterm;

import java.util.*;
import room.Room;

public class Player 
{
	Map<String, Item> inventory = new HashMap<String, Item>();
	Room currRoom = new Room();
	
	public void takeItem(String item)
	{
		if(currRoom.hasItem(item)) //check if currRoom has item
		{
			inventory.put(item, currRoom.itemsInRoom.get(item)); //put the item object into player inventory hashmap
			System.out.println(item + " put into inventory.");
		}
	}
	
	public void useItem(String item)
	{
		if(inventory.containsKey(item))
		{
			inventory.
		}
		else
		{
			System.out.println("You're not carrying a " + item + " in your inventory.");
		}
	}
	
	public void dropItem(String item)
	{
		if(inventory.containsKey(item))
		{
			currRoom.itemsInRoom.put(item, inventory.get(item));
			inventory.remove(item);
		}
		else
			System.out.println("You're not carrying a " + item + " in your inventory.");
	}
}

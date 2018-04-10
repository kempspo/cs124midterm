package room;

import java.util.*;
import cs124midterm.Item;

public interface Room 
{
	Map<String, Item> itemsInRoom = new HashMap<String, Item>();
	public String getDescription();
	public String look();
	public boolean hasItem(String item);
	public String addItem(String item);
	public String removeItem(String item);
}
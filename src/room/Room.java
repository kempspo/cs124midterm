package room;

import java.util.*;
import cs124midterm.*;

public interface Room 
{
	public String getDescription();
	public String look();
	public boolean hasItem(String item);
	public String take(String item, Player player);
	public String drop(String item, Player player);
	public HashMap getItems();
}
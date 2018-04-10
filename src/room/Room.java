package room;

import java.util.*;
import cs124midterm.*;

public interface Room 
{
	public String getDescription();
	public String look();
	public boolean hasItem(String item);
	public String removeItem(String item, Player player);
	public String addItem(String item, Player player);
}
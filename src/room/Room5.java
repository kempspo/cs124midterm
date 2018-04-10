package room;

import anno.*;
import java.util.*;

public class Room5
{
	@Direction(command="Go North")
	private Room3 north;
	@Direction(command="Go East")
	private Room6 east;

  public String getDescription()
  {
	   return "You enter the room. There's a rack of knives.";
  }

	@Command(command="look")
	public String look()
	{
		return "There's nothing except knives.";
	}

}

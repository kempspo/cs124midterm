package room;

import anno.*;
import java.util.*;

public class Room2 extends Room
{
	@Direction(command="Go South")
	private Room1 south;
	@Direction(command="Go West")
	private Room3 west;
	@Direction(command="Go East")
	private Room4 east;

  public String getDescription()
  {
	   return "You hear screams from an adjacent room,"
		  + "but there are 2 other doors and you don't know from"
			+ "which it came from.";
  }

	@Command(command="look")
	public String look()
	{
		return "You look around. Looks like a storage room of some sort."
			+ " There's a bunch of pots and pans. You may take one";
	}

}

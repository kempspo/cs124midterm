package room;

import anno.*;
import java.util.*;

public class Room4 extends Room
{
	@Direction(command="Go West")
	private Room3 west;

  public String getDescription()
  {
	   return "The room's pretty empty. Just a bunch of chairs and tables.";
  }

	@Command(command="look")
	public String look()
	{
		return "You look around and find a set of keys on the rack.";
	}

}

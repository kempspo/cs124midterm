package room;

import anno.Direction;
import java.util.*;

public class Room3 extends Room
{
	@Direction(command="Go South")
	private Room5 south;
	@Direction(command="Go East")
	private Room2 east;

  public String getDescription()
  {
	   return "You enter the room. You see a girl tied up on the table.";
  }

	@Command(command="look")
	public String look()
	{
		return "You take a closer look around."
		+ " The girl on the table is your friend.";
	}

}

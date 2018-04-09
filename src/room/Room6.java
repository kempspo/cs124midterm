package room;

import anno.Direction;
import java.util.*;

public class Room6 extends Room
{
	@Direction(command="Go West")
	private Room5 west;
	@Direction(command="Go South")
	private Room7 south;

  public String getDescription()
  {
	   return "The room is full of trash.";
  }

	@Command(command="look")
	public String look()
	{
		return "";
	}

}

package room;

import anno.Direction;
import java.util.*;

public class Room8 extends Room
{
	@Direction(command="Go East")
	private Room7 east;
	@Direction(command="Go South")
	private Room9 south;

  public String getDescription()
  {
	   return "It's all condiments here. There's some salt, pepper, "
      + "some other stuff I don't really know.";
  }

	@Command(command="look")
	public String look()
	{
		return "You find a piece of paper saying 'Password: 3,2,1'";
	}

}

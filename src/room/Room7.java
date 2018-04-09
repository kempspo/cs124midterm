package room;

import anno.*;
import java.util.*;

public class Room7 extends Room
{
	@Direction(command="Go North")
	private Room6 north;
	@Direction(command="Go West")
	private Room8 west;

  public String getDescription()
  {
	   return ".";
  }

	@Command(command="look")
	public String look()
	{
		return "";
	}

}

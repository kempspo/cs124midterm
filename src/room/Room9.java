package room;

import anno.Direction;
import java.util.*;

public class Room9 extends Room
{
	@Direction(command="Go North")
	private Room8 north;
	@Direction(command="Go West")
	private Room10 west;

  public String getDescription()
  {
	   return "You see a man in white. "
     + "He's about to throw something into a pot.";
  }

	@Command(command="look")
	public String look()
	{
		return "You look closer at what the man is throwing into the pot. "
    +  "It's your brother.";
	}

}

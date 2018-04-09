package room;

import anno.Direction;
import java.util.*;

public class Room2 extends Room
{
	@Direction(command="Go South")
	private Room1 south;

  public String getDescription()
  {
	   return "You wake up. You're on the floor. Your head's throbbing. You can:";
  }
}

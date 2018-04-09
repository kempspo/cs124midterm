package room;

import anno.*;
import java.util.*;

public class Room1 extends Room
{
	@Direction(command="Go North")
	private Room2 north;

  public String getDescription()
  {
	   return "You wake up. You're on the floor. Your head's throbbing. You can:";
  }

  @Command(command="look")
  public String look()
  {
    return "There's nothing here move on";
  }
}

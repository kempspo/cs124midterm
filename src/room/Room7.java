package room;

import anno.*;
import java.util.*;

@CheckEnter
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

  public static boolean canEnter() {
	// if player has key
	  	return true;
	 // else
	  	//return false
  }

  public static String enterMessage(){
	  return "You got inside!";
  }

  public static String unableToEnterMessage(){
	  return "You cannot go inside!";
  }
}

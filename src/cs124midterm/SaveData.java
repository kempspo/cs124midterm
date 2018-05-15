package cs124midterm;

import java.io.Serializable;
import java.util.HashMap;

public class SaveData implements Serializable
{
	private HashMap<Class, Object> roomMap;
	private Player player;
	private Object currentRoom;
	
	public SaveData(HashMap<Class, Object> r, Player p, Object c)
	{
		roomMap = r;
		player = p;
		currentRoom = c;
	}
	
	public HashMap<Class, Object> getRoomMap()
	{
		return roomMap;
	}
	
	public void setRoomMap(HashMap<Class, Object> r)
	{
		roomMap = r;
	}

	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player p)
	{
		player = p;
	}
	
	public Object getCurrentRoom()
	{
		return currentRoom;
	}
	
	public void setCurrentRoom(Object c)
	{
		currentRoom = c;
	}
}

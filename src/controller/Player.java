package controller;

public class Player extends Lifeform
{
	private boolean visited[] = new boolean[59];
	
	public Player(String name, Room room)
	{
		super(name, room);
	}

	/**
	 * Method: getVisited
	 * Purpose: gets whether a player has visited a room
	 * @param index
	 * @return visited
	 */
	public boolean getVisited(int index)
	{
		if (index >= visited.length)
		{
			System.out.println("HEY DUDE: You need to increase the array size in Player.java");
		}
		if(index < visited.length)
		{
			return visited[index];
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Method: setVisited
	 * Purpose: sets visited
	 * @param index
	 * @param value
	 */
	public void setVisited(int index, boolean value)
	{
		if(index < visited.length)
		{
			visited[index] = value;
		}
	}
	
}

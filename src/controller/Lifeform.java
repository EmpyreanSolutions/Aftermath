package controller;

public class Lifeform
{
	private String name;
	private Room room;
//	private int location;
	public Lifeform(String name, Room room)
	{
		this.name = name;
		this.room = room;
//		this.location = location;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
//	public int getLocation()
//	{
//		return location;
//	}
//	public void setLocation(int location)
//	{
//		this.location = location;
//	}

	public Room getRoom()
	{
		return room;
	}
	public void setRoom(Room room)
	{
		this.room = room;
	}
	@Override
	public String toString()
	{
		return name + " " + room.getRoomID();
	}	
	
	
}

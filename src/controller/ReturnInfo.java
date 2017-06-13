package controller;

public class ReturnInfo
{
	private String message;
	private Room playerRoom;
	private Room preyRoom;
	
	public ReturnInfo()
	{
		message = "";
		playerRoom = new Room();
		preyRoom = new Room();
	}
	
	public ReturnInfo(String message, Room playerRoom, Room preyRoom)
	{
		this.message = message;
		this.playerRoom = playerRoom;
		this.preyRoom = preyRoom;
	}



	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Room getPlayerRoom()
	{
		return playerRoom;
	}

	public void setPlayerRoom(Room playerRoom)
	{
		this.playerRoom = playerRoom;
	}

	public Room getPreyRoom()
	{
		return preyRoom;
	}

	public void setPreyRoom(Room preyRoom)
	{
		this.preyRoom = preyRoom;
	}
	
	
	
}

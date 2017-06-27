package controller;

public class ReturnInfo
{
	private String message;
	private Room playerRoom;
	private Room predatorRoom;
	
	public ReturnInfo()
	{
		message = "";
		playerRoom = new Room();
		predatorRoom = new Room();
	}
	
	public ReturnInfo(String message, Room playerRoom, Room predatorRoom)
	{
		this.message = message;
		this.playerRoom = playerRoom;
		this.predatorRoom = predatorRoom;
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

	public Room getPredatorRoom()
	{
		return predatorRoom;
	}

	public void setPredatorRoom(Room preyRoom)
	{
		this.predatorRoom = preyRoom;
	}
	
	
	
}

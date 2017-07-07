package controller;

//import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import model.RoomDB;

public class Room
{
	private int roomID;
	private String roomName;
	

	private int northRoom;
	private int eastRoom;
	private int southRoom;
	private int westRoom;
	private int upRoom;
	private int downRoom;
	
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	
	private int visited;
	private String imageName;
	private String roomDescription;
	
	public Room()
	{
		roomID = 0;
		roomName = "";

		northRoom = 0;
		eastRoom = 0;
		southRoom = 0;
		westRoom = 0;
		upRoom = 0;
		downRoom = 0;
		
		xCoordinate = 0;
		yCoordinate = 0;
		zCoordinate = 0;
		
		visited = 0;		
		imageName = "";		
		roomDescription = "";
	}
	
	public Room(int roomID, String roomName, 
			    int northRoom, int eastRoom, int southRoom, int westRoom, int upRoom, int downRoom, 
			    int xCoordiante, int yCoordinate, int zCoordinate, int visited, 
			    String imageName, String roomDescription)
	{
		this.roomID = roomID;
		this.roomName = roomName;
		this.northRoom = northRoom;
		this.eastRoom = eastRoom;
		this.southRoom = southRoom;
		this.westRoom = westRoom;
		this.upRoom = upRoom;
		this.downRoom = downRoom;
		this.xCoordinate = xCoordiante;
		this.yCoordinate = yCoordinate;
		this.zCoordinate = zCoordinate;
		this.visited = visited;
		this.imageName = imageName;
		this.roomDescription = roomDescription;
	}

	public Room getRoom(int id) //throws SQLException
	{
		RoomDB rdb = new RoomDB();
		return rdb.getRoom(id);
	}

	public int getRoomID()
	{
		return roomID;
	}

	// Do not allow developer to revise roomID!
//	public void setRoomID(int roomID)
//	{
//		this.roomID = roomID;
//	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	
	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getRoomDescription()
	{
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription)
	{
		this.roomDescription = roomDescription;
	}

	public int getVisited()
	{
		return visited;
	}

	public void setVisited(int visited)
	{
		this.visited = visited;
	}

	public int getNorthRoom()
	{
		return northRoom;
	}

	public void setNorthRoom(int northRoom)
	{
		this.northRoom = northRoom;
	}

	public int getEastRoom()
	{
		return eastRoom;
	}

	public void setEastRoom(int eastRoom)
	{
		this.eastRoom = eastRoom;
	}

	public int getSouthRoom()
	{
		return southRoom;
	}

	public void setSouthRoom(int southRoom)
	{
		this.southRoom = southRoom;
	}

	public int getWestRoom()
	{
		return westRoom;
	}

	public void setWestRoom(int westRoom)
	{
		this.westRoom = westRoom;
	}
	
	public int getUpRoom()
	{
		return upRoom;
	}

	public void setUpRoom(int upRoom)
	{
		this.upRoom = upRoom;
	}

	public int getDownRoom()
	{
		return downRoom;
	}

	public void setDownRoom(int downRoom)
	{
		this.downRoom = downRoom;
	}

	public int getxCoordinate()
	{
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate)
	{
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate()
	{
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate)
	{
		this.yCoordinate = yCoordinate;
	}

	public int getzCoordinate()
	{
		return zCoordinate;
	}

	public void setzCoordinate(int zCoordinate)
	{
		this.zCoordinate = zCoordinate;
	}


	public boolean hasNorthRoom()
	{
		if (northRoom > 0) return true;
		else return false;
	}

	public boolean hasEastRoom()
	{
		if (eastRoom > 0) return true;
		else return false;
	}

	public boolean hasSouthRoom()
	{
		if (southRoom > 0) return true;
		else return false;
	}

	public boolean hasWestRoom()
	{
		if (westRoom > 0) return true;
		else return false;
	}

	public boolean hasUpRoom()
	{
		if (upRoom > 0) return true;
		else return false;
	}

	public boolean hasDownRoom()
	{
		if (downRoom > 0) return true;
		else return false;
	}



	public void upDateVisited(int num)
	{
		RoomDB rdb = new RoomDB();
		rdb.upDateVisited(roomID, num);
		this.visited = num;  // IS THIS NECESSARY?
	}

	
	public Point3D getCenter()
	{
		Point3D pt = new Point3D((double)xCoordinate,(double)yCoordinate,(double)zCoordinate);
		return pt;
	}
	
}

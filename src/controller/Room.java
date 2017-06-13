package controller;

//import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import model.RoomDB;

public class Room
{
	private int roomID;
	private String roomName;
	private String imageName;
	private int visited;
	private int northRoom;
	private int eastRoom;
	private int southRoom;
	private int westRoom;
	private String roomDescription;
	
	private boolean hasNorthRoom;
	private boolean hasEastRoom;
	private boolean hasSouthRoom;
	private boolean hasWestRoom;

	public Room()
	{
		roomID = 0;
		roomName = "";
		imageName = "";
		visited = 0;
		northRoom = 0;
		eastRoom = 0;
		southRoom = 0;
		westRoom = 0;
		roomDescription = "";
		hasNorthRoom = false;
		hasEastRoom = false;
		hasSouthRoom = false;
		hasWestRoom = false;

	}
	
	public Room(int roomID, String roomName, String imageName, int visited, int northRoom, int eastRoom, int southRoom,
			int westRoom, String roomDescription)
	{
		this.roomID = roomID;
		this.roomName = roomName;
		this.imageName = imageName;
		this.visited = visited;
		this.northRoom = northRoom;
		this.eastRoom = eastRoom;
		this.southRoom = southRoom;
		this.westRoom = westRoom;
		this.roomDescription = roomDescription;
		if (northRoom > 0)
		{
			hasNorthRoom = true;
		}
		else
		{
			hasNorthRoom = false;
		}
		
		if (eastRoom > 0)
		{
			hasEastRoom = true;
		}
		else
		{
			hasEastRoom = false;
		}
		
		if (southRoom > 0)
		{
			hasSouthRoom = true;
		}
		else
		{
			hasSouthRoom = false;
		}
		
		if (westRoom > 0)
		{
			hasWestRoom = true;
		}
		else
		{
			hasWestRoom = false;
		}
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

	public boolean getHasNorthRoom()
	{
		return hasNorthRoom;
	}

	public void setHasNorthRoom(boolean hasNorthRoom)
	{
		this.hasNorthRoom = hasNorthRoom;
	}

	public boolean getHasEastRoom()
	{
		return hasEastRoom;
	}

	public void setHasEastRoom(boolean hasEastRoom)
	{
		this.hasEastRoom = hasEastRoom;
	}

	public boolean getHasSouthRoom()
	{
		return hasSouthRoom;
	}

	public void setHasSouthRoom(boolean hasSouthRoom)
	{
		this.hasSouthRoom = hasSouthRoom;
	}

	public boolean getHasWestRoom()
	{
		return hasWestRoom;
	}

	public void setHasWestRoom(boolean hasWestRoom)
	{
		this.hasWestRoom = hasWestRoom;
	}
	public void upDateVisited(int num)
	{
		RoomDB rdb = new RoomDB();
		rdb.upDateVisited(roomID, num);
		this.visited = num;  // IS THIS NECESSARY?
	}

	@Override
	public String toString()
	{
		return "Room [roomID=" + roomID + ", roomName=" + roomName + ", imageName=" + imageName + ", northRoom="
				+ northRoom + ", eastRoom=" + eastRoom + ", southRoom=" + southRoom + ", westRoom=" + westRoom
				+ ", roomDescription=" + roomDescription + ", hasNorthRoom=" + hasNorthRoom + ", hasEastRoom="
				+ hasEastRoom + ", hasSouthRoom=" + hasSouthRoom + ", hasWestRoom=" + hasWestRoom + "]";
	}
	
	public Point3D getCenter()
	{
		String column = roomName.substring(0,1);
		String row = roomName.substring(1,2);
		double x = 5.0;
		double y = 45.0;
		double z = 0.0;
		if (column.equals("B"))
		{
			x += 10.0;
		}
		else if (column.equals("C"))
		{
			x += 20.0;
		}
		else if (column.equals("D"))
		{
			x += 30.0;
		}
		else if (column.equals("E"))
		{
			x += 40.0;
		}
		
		if (row.equals("2"))
		{
			y = 35.0; 
		}
		else if (row.equals("3"))
		{
			y = 25.0;
		}
		else if (row.equals("4"))
		{
			y = 15.0;
		}
		else if (row.equals("5"))
		{
			y = 5.0;
		}
		
		Point3D pt = new Point3D(x,y,z);
		return pt;
	}
	
}

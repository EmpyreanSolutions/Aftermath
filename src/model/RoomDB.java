package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.GameController;
import controller.Room;

public class RoomDB
{
	public Room getRoom(int ID)
	{
        SQLiteDB sdb = GameController.getDB();
		ResultSet rs;
		Room r = null;
		try
		{
			rs = sdb.queryDB("SELECT * FROM rooms WHERE RoomID=" + ID);
//			System.out.println(rs.next());
//			System.out.println(rs.getInt(1));
//			System.out.println(rs.getString(2));
	        r = new Room(rs.getInt(1), rs.getString(2), 
	        		     rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), 
	        		     rs.getInt(7), rs.getInt(8),
	        		     rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
	        		     rs.getInt(13),rs.getString(14));
	        sdb.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        return r;
	}
	public void upDateVisited(int roomID, int num) //throws SQLException
	{
		SQLiteDB sdb = GameController.getDB();

		try
		{
			String sql = "UPDATE rooms SET visited = " + num + " WHERE roomID = " + roomID;
			sdb.updateDB(sql);
			sdb.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("ERROR: Inside RoomDB upDateVisited");
		}
	}
	
	/** Method: getAllRooms
	 * Purpose: gets all rooms
	 * @return ArrayList<Room>
	 * @throws SQLException
	 */
	public ArrayList<Room> getAllRooms()
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		SQLiteDB sdb = GameController.getDB();
		String sql = "Select * from rooms";
		
		try
		{
			ResultSet rs = sdb.queryDB(sql);
			while(rs.next())
			{
				rooms.add(new Room(rs.getInt(1), rs.getString(2), 
	        		     rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), 
	        		     rs.getInt(7), rs.getInt(8),
	        		     rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
	        		     rs.getInt(13),rs.getString(14)));
			}
	        sdb.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error in getAllRooms");
			e.printStackTrace();
			System.exit(0);
		}		

		return rooms;
	}
}

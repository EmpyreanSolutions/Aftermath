package controller;

import java.sql.SQLException;
import javafx.geometry.Point3D;
import model.SQLiteDB;

/**
 * Class : GameController.java
 * @author: Kenneth Sales
 * @version: 1.0
 * Empyrean Software
 * Written: May 2017
 *
 * This class is the main controller.
 * Will hold utility functions as well as central functionality
 */
public class GameController
{
	private static SQLiteDB sdb;
	private Player player;
	private Lifeform predator;
	private Room playerRoom;
	private Room predatorRoom;
	private Room targetRoom;
	private String returnMessage;
	private ReturnInfo returnInfo;
	
	private Point3D ptPlayer;
	private Point3D ptPredator;
	private Point3D ptTarget;
	private Point3D ptNorth;
	private Point3D ptEast;
	private Point3D ptSouth;
	private Point3D ptWest;

	public GameController()
	{
		playerRoom = new Room().getRoom(1011);
		predatorRoom = new Room().getRoom(1035);
		targetRoom = new Room().getRoom(playerRoom.getRoomID());
		player = new Player("Kenneth",playerRoom);
		predator = new Lifeform("Shiva",predatorRoom);
		returnMessage = "";
		ptPlayer = playerRoom.getCenter();
		ptPredator = predatorRoom.getCenter();
		ptTarget = playerRoom.getCenter();
		ptNorth = new Point3D(0,1,0);
		ptEast = new Point3D(1,0,0);
		ptSouth = new Point3D(0,-1,0);
		ptWest = new Point3D(-1,0,0);
		
		returnInfo = new ReturnInfo(returnMessage,playerRoom,predatorRoom);
	
	}

	/**
	 * Method: getDB
	 * @return the db
	 */
	public static SQLiteDB getDB()
	{
		try
		{
			sdb = new SQLiteDB();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println("ERROR!\nThere was a problem opening the database \n" + e.getMessage());
		}
		return sdb;
	}

	private void changeRoom(int nextRoomID)
	{
		playerRoom = playerRoom.getRoom(nextRoomID);
		returnInfo.setPlayerRoom(playerRoom);
		player.setRoom(playerRoom);

		if (playerRoom.getVisited() == 1)  // visited
		{
			returnMessage += playerRoom.getRoomName() + "\n";	
//			System.out.println("returnMessage visited: " + returnMessage);
		}
		else // not visited
		{
			playerRoom.upDateVisited(1);
			returnMessage += playerRoom.getRoomName() + "  " + playerRoom.getRoomDescription() + "\n";
//			System.out.println("returnMessage not visited: " + returnMessage);			
		}
	}

	public ReturnInfo navigateControl(String command)
	{
		returnMessage = "";
		
		switch(command)
		{
		case "exit":
		{
			System.exit(0);
		}
			break;
		case "north":
		{
			if(playerRoom.getNorthRoom() > 0)
			{
				changeRoom(playerRoom.getNorthRoom());
			}
			else
			{
				returnMessage = "There is no north room" + "\n";
			}
		}
			break;
		case "east":
		{
			if(playerRoom.getEastRoom() > 0)
			{
				changeRoom(playerRoom.getEastRoom());
			}
			else
			{
				returnMessage = "There is no east room" + "\n";
			}
		}
			break;
		case "south":
		{
			if(playerRoom.getSouthRoom() > 0)
			{
				changeRoom(playerRoom.getSouthRoom());
			}
			else
			{
				returnMessage = "There is no South room" + "\n";
			}
		}
			break;
		case "west":
		{
			if(playerRoom.getWestRoom() > 0)
			{
				changeRoom(playerRoom.getWestRoom());
			}
			else
			{
				returnMessage = "There is no west room" + "\n";
			}
		}
			break;
		case "up":
		{
			if(playerRoom.getUpRoom() > 0)
			{
				changeRoom(playerRoom.getUpRoom());
			}
			else
			{
				returnMessage = "There is no up room" + "\n";
			}
		}
			break;
		case "down":
		{
			if(playerRoom.getDownRoom() > 0)
			{
				changeRoom(playerRoom.getDownRoom());
			}
			else
			{
				returnMessage = "There is no down room" + "\n";
			}
		}
			break;
			
		default:
		{
			returnMessage += "Invalid Command. Type \"Commands\" for a list of valid commands." + "\n";
		}
			break;

		}
		returnInfo.setMessage(returnMessage);
		return returnInfo;
	}
	
	public ReturnInfo mapControl()
	{
		returnMessage = "Player: " + player.getRoom().getRoomName() + "    Shiva: " + predator.getRoom().getRoomName() 
				+ "   Target: " + targetRoom.getRoomName()+ "\n";
		returnInfo.setMessage(returnMessage);
		return returnInfo;
	}
	
	public ReturnInfo targetControl()
	{;
		targetRoom = targetRoom.getRoom(playerRoom.getRoomID());
		returnMessage = targetRoom.getRoomName() + "\n";
		returnInfo.setMessage(returnMessage);
		return returnInfo;		
	}

	public ReturnInfo movePredator()
	{
		ptTarget = targetRoom.getCenter();
		ptPlayer = playerRoom.getCenter();
		ptPredator = predatorRoom.getCenter();
//		System.out.println("ptPlayer:   " + ptPlayer);
//		System.out.println("ptPredator: " + ptPredator);
//		System.out.println("ptTarget:   " + ptTarget);

		int iAngleNorth = (int)Math.round(ptPredator.angle(ptTarget,ptPredator.add(ptNorth)));
		int iAngleEast  = (int)Math.round(ptPredator.angle(ptTarget,ptPredator.add(ptEast)));
		int iAngleSouth = (int)Math.round(ptPredator.angle(ptTarget,ptPredator.add(ptSouth)));
		int iAngleWest  = (int)Math.round(ptPredator.angle(ptTarget,ptPredator.add(ptWest)));
		
		int minAngle = 400;
		int nextRoomID = 0;
		
		if (predatorRoom.getHasNorthRoom() && (iAngleNorth < minAngle))
		{
			minAngle = iAngleNorth;
			nextRoomID = predatorRoom.getNorthRoom();
//			System.out.println("Going North " + nextRoomID);
		}
		
		if (predatorRoom.getHasEastRoom() && (iAngleEast < minAngle))
		{
			minAngle = iAngleEast;
			nextRoomID = predatorRoom.getEastRoom();
//			System.out.println("Going East");
		}
		
		if (predatorRoom.getHasSouthRoom() && (iAngleSouth < minAngle))
		{
			minAngle = iAngleSouth;
			nextRoomID = predatorRoom.getSouthRoom();
//			System.out.println("Going South");
		}
		
		if (predatorRoom.getHasWestRoom() && (iAngleWest < minAngle))
		{
			minAngle = iAngleWest;
			nextRoomID = predatorRoom.getWestRoom();
			System.out.println("Going West");
		}
				
		predatorRoom = predatorRoom.getRoom(nextRoomID);
		predator.setRoom(predatorRoom);
		returnInfo.setPredatorRoom(predatorRoom);
		
		returnMessage = "Player: " + player.getRoom().getRoomName() + "    Shiva: " + predator.getRoom().getRoomName() 
				+ "   Target: " + targetRoom.getRoomName();
		returnMessage += "  iAngleNorth: " + iAngleNorth + "  iAngleEast: " + iAngleEast;
		returnMessage += "  iAngleSouth: " + iAngleSouth + "  iAngleWest: " + iAngleWest 
				       + "  minAngle: " + minAngle + "   nextRoomID: " + nextRoomID + "\n";
		returnInfo.setMessage(returnMessage);

		return returnInfo;
	}
	

}

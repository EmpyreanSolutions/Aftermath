package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
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

	private Point3D ptPredator;
	private Point3D ptTarget;
	private Point3D ptNorth;
	private Point3D ptEast;
	private Point3D ptSouth;
	private Point3D ptWest;

	boolean monsterAggresive;
	private ArrayList<Integer> possibleMonsterRooms;
	private int previousRoomID;
	private Random ran;

	public GameController()
	{
		playerRoom = new Room().getRoom(1011);
		predatorRoom = new Room().getRoom(1035);
		targetRoom = new Room().getRoom(playerRoom.getRoomID());
		player = new Player("Kenneth");
		predator = new Lifeform("Specimen #0089");
		returnMessage = "";
		ptPredator = predatorRoom.getCenter();
		ptTarget = playerRoom.getCenter();
		ptNorth = new Point3D(0, 1, 0);
		ptEast = new Point3D(1, 0, 0);
		ptSouth = new Point3D(0, -1, 0);
		ptWest = new Point3D(-1, 0, 0);

		monsterAggresive = false;
		previousRoomID = 0;
		possibleMonsterRooms = new ArrayList<Integer>();
		ran = new Random();
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
		// player.setRoom(playerRoom);

		if(playerRoom.getVisited() == 1) // visited
		{
			returnMessage += playerRoom.getRoomName() + "\n";
		}
		else // not visited
		{
			playerRoom.upDateVisited(1);
			returnMessage += playerRoom.getRoomName() + "  " + playerRoom.getRoomDescription() + "\n";
		}
	}

	public String navigateControl(String command)
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
		return returnMessage;
	}

	public String mapControl()
	{
		returnMessage = player.getName() + ": " + playerRoom.getRoomName() + "    " + predator.getName() + ": "
				+ predatorRoom.getRoomName() + "     Target: " + targetRoom.getRoomName() + "\n";
		return returnMessage;
	}

	public String targetControl()
	{
		int targetZ = playerRoom.getzCoordinate();
		int predatorZ = predatorRoom.getzCoordinate();
//		System.out.println("targetZ: " + targetZ + "   predatorZ: " + predatorZ);
		if (targetZ == predatorZ)
		{
			targetRoom = targetRoom.getRoom(playerRoom.getRoomID());
			System.out.println("Predator on same deck as sound");
		}
		else if (predatorZ == 9)
		{
			targetRoom = targetRoom.getRoom(1005);
			previousRoomID = 1004;
//			System.out.println("Predator is on Deck1; Sound is not");
		}
		else if (predatorZ == 6)
		{
			targetRoom = targetRoom.getRoom(1005);
			previousRoomID = 1004;			
		}
		monsterAggresive = true;
		returnMessage = "TargetRoom: " + targetRoom.getRoomName() + "\n";
		return returnMessage;
	}

	public String movePredator()
	{
		ptTarget = targetRoom.getCenter();
		ptPredator = predatorRoom.getCenter();

		if(monsterAggresive)
		{

			int iAngleNorth = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptNorth)));
			int iAngleEast = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptEast)));
			int iAngleSouth = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptSouth)));
			int iAngleWest = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptWest)));

			int minAngle = 400;
			int nextRoomID = 0;

			if(predatorRoom.hasNorthRoom() && (iAngleNorth < minAngle))
			{
				minAngle = iAngleNorth;
				nextRoomID = predatorRoom.getNorthRoom();
			}

			if(predatorRoom.hasEastRoom() && (iAngleEast < minAngle))
			{
				minAngle = iAngleEast;
				nextRoomID = predatorRoom.getEastRoom();
			}

			if(predatorRoom.hasSouthRoom() && (iAngleSouth < minAngle))
			{
				minAngle = iAngleSouth;
				nextRoomID = predatorRoom.getSouthRoom();
			}

			if(predatorRoom.hasWestRoom() && (iAngleWest < minAngle))
			{
				minAngle = iAngleWest;
				nextRoomID = predatorRoom.getWestRoom();
			}

			predatorRoom = predatorRoom.getRoom(nextRoomID);
			// predator.setRoom(predatorRoom);

			returnMessage = predator.getName() + ": "
					+ predatorRoom.getRoomName() + "     Target: " + targetRoom.getRoomName() + "\n";
			if (predatorRoom.getRoomID() == targetRoom.getRoomID())
			{
				returnMessage += "   Found targetRoom" + "\n";				
				monsterAggresive = false;
			}		
			
		}
		else // monster not aggressive, wandering the allowedMonsterRoom(s)
		{
			possibleMonsterRooms.clear();

			if(predatorRoom.hasNorthRoom() && predatorRoom.getRoom(predatorRoom.getNorthRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getNorthRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getNorthRoom());
			}

			if(predatorRoom.hasEastRoom() && predatorRoom.getRoom(predatorRoom.getEastRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getEastRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getEastRoom());
			}
			
			if(predatorRoom.hasSouthRoom() && predatorRoom.getRoom(predatorRoom.getSouthRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getSouthRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getSouthRoom());
			}
			
			if(predatorRoom.hasWestRoom() && predatorRoom.getRoom(predatorRoom.getWestRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getWestRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getWestRoom());
			}
			
			if(predatorRoom.hasUpRoom() && predatorRoom.getRoom(predatorRoom.getUpRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getUpRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getUpRoom());
			}
			
			if(predatorRoom.hasDownRoom() && predatorRoom.getRoom(predatorRoom.getDownRoom()).isAllowedMonsterRoom()
					&& (predatorRoom.getDownRoom() != previousRoomID))
			{
				possibleMonsterRooms.add(predatorRoom.getDownRoom());
			}
			
			int index = ran.nextInt(possibleMonsterRooms.size());

			previousRoomID = predatorRoom.getRoomID();

			predatorRoom = predatorRoom.getRoom(possibleMonsterRooms.get(index));

			returnMessage = predator.getName() + "   " + predatorRoom.getRoomName() + "\n";
//			if (previousRoomID == 2040 || previousRoomID == 2039)
//			{
//				System.out.println("previousRoomID: " + previousRoomID);
//				System.out.println(possibleMonsterRooms);
//				System.out.println("index: " + index + "   room: " + possibleMonsterRooms.get(index));
//			}
												
		}

		return returnMessage;

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

	public void setPredatorRoom(Room predatorRoom)
	{
		this.predatorRoom = predatorRoom;
	}

	public Room getTargetRoom()
	{
		return targetRoom;
	}

	public void setTargetRoom(Room targetRoom)
	{
		this.targetRoom = targetRoom;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Lifeform getPredator()
	{
		return predator;
	}

	public void setPredator(Lifeform predator)
	{
		this.predator = predator;
	}

}

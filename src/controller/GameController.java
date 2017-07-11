package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
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

//	private Point3D ptPredator;
//	private Point3D ptTarget;
//	private Point3D ptNorth;
//	private Point3D ptEast;
//	private Point3D ptSouth;
//	private Point3D ptWest;

	boolean monsterAggresive;
	private ArrayList<Integer> possibleMonsterRooms;
	private int previousRoomID;
	private Random ran;
	private ArrayList<Node> nodes;
	Node sourceNode = null;
	Node targetNode = null;
	int targetIndex;

	public GameController()
	{
		playerRoom = new Room().getRoom(1011);
		predatorRoom = new Room().getRoom(1035);
		targetRoom = new Room().getRoom(playerRoom.getRoomID());
		player = new Player("Kenneth");
		predator = new Lifeform("Specimen #0089");
		returnMessage = "";
//		ptPredator = predatorRoom.getCenter();
//		ptTarget = playerRoom.getCenter();
//		ptNorth = new Point3D(0, 1, 0);
//		ptEast = new Point3D(1, 0, 0);
//		ptSouth = new Point3D(0, -1, 0);
//		ptWest = new Point3D(-1, 0, 0);

		monsterAggresive = false;
		previousRoomID = 0;
		possibleMonsterRooms = new ArrayList<Integer>();
		ran = new Random();

		nodes = new Node().getAllNodes();
		targetIndex = 0;

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
		// System.out.println("targetZ: " + targetZ + " predatorZ: " +
		// predatorZ);
		if(targetZ == predatorZ)
		{
			targetRoom = targetRoom.getRoom(playerRoom.getRoomID());
			// System.out.println("Predator on same deck as sound");
		}
		else if(predatorZ == 9)
		{
			targetRoom = targetRoom.getRoom(1005);
			previousRoomID = 1004;
			// System.out.println("Predator is on Deck1; Sound is not");
		}
		else if(predatorZ == 6)
		{
			targetRoom = targetRoom.getRoom(1005);
			previousRoomID = 1004;
		}
		monsterAggresive = true;	// ********************************************
		returnMessage = "TargetRoom: " + targetRoom.getRoomName() + "\n";

		// Nodes

		for(int index = 0; index < nodes.size(); index++)
		{
			if(nodes.get(index).hasNorthRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getNorthRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
			if(nodes.get(index).hasEastRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getEastRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
			if(nodes.get(index).hasSouthRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getSouthRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
			if(nodes.get(index).hasWestRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getWestRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
			if(nodes.get(index).hasUpRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getUpRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
			if(nodes.get(index).hasDownRoom())
			{
				for(int index2 = 0; index2 < nodes.size(); index2++)
				{
					if(nodes.get(index2).getRoomID() == nodes.get(index).getDownRoom())
					{
						nodes.get(index).addDestination(nodes.get(index2),
								(int) nodes.get(index2).getPoint().distance(nodes.get(index).getPoint()));
					}
				}

			}
		}

//		for(int index = 0; index < nodes.size(); index++)
//		{
//			Set<Node> keySet = nodes.get(index).getAdjacentNodes().keySet();
//			System.out.print("Room: " + nodes.get(index).getRoomID() + "   ");
//			for(Node key : keySet)
//			{
//				int d = nodes.get(index).getAdjacentNodes().get(key);
//				System.out.print(key.getRoomID() + "  " + d);
//				System.out.print("   ");
//			}
//			System.out.println();
//		}
		
		Graph graph = new Graph();

		for (int index = 0; index < nodes.size(); index++)
		{
			graph.addNode(nodes.get(index));
			if (nodes.get(index).getRoomID() == predatorRoom.getRoomID())
			{
				sourceNode = nodes.get(index);
			}
			if (nodes.get(index).getRoomID() == targetRoom.getRoomID())
			{
				targetNode = nodes.get(index);
			}
		}
		
		graph = this.calculateShortestPathFromSource(graph, sourceNode);
		
		Set<Node> nodes2 = graph.getNodes();
		for (Node node : nodes2)
		{
			if (node.getRoomID() == targetRoom.getRoomID())
			{
				targetNode = node;
			}
		}
		
		targetNode.getShortestPath().add(targetNode);
//		for (int index = 0; index < targetNode.getShortestPath().size(); index++)
//		{
//			System.out.println(targetNode.getShortestPath().get(index).getRoomID()
//					+ "  " + targetNode.getShortestPath().get(index).getRoomName());
//		}

		return returnMessage;
	}
	
	private Graph calculateShortestPathFromSource(Graph graph, Node source)
	{
		source.setDistance(0);
		
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();
		
		unsettledNodes.add(source);
		
		while (unsettledNodes.size() != 0)
		{
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry < Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet())
			{
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode))
				{
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private Node getLowestDistanceNode(Set <Node> unsettledNodes)
	{
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node: unsettledNodes)
		{
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance)
			{
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}
	
	private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode)
	{
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeight < evaluationNode.getDistance())
		{
			evaluationNode.setDistance(sourceDistance + edgeWeight);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
	
	
	public String movePredator()
	{
//		ptTarget = targetRoom.getCenter();
//		ptPredator = predatorRoom.getCenter();

		if(monsterAggresive)
		{
			int nextRoomID = targetNode.getShortestPath().get(targetIndex).getRoomID();
			targetIndex++;
			
//			int iAngleNorth = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptNorth)));
//			int iAngleEast = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptEast)));
//			int iAngleSouth = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptSouth)));
//			int iAngleWest = (int) Math.round(ptPredator.angle(ptTarget, ptPredator.add(ptWest)));
//
//			int minAngle = 400;
//			int nextRoomID = 0;
//
//			if(predatorRoom.hasNorthRoom() && (iAngleNorth < minAngle))
//			{
//				minAngle = iAngleNorth;
//				nextRoomID = predatorRoom.getNorthRoom();
//			}
//
//			if(predatorRoom.hasEastRoom() && (iAngleEast < minAngle))
//			{
//				minAngle = iAngleEast;
//				nextRoomID = predatorRoom.getEastRoom();
//			}
//
//			if(predatorRoom.hasSouthRoom() && (iAngleSouth < minAngle))
//			{
//				minAngle = iAngleSouth;
//				nextRoomID = predatorRoom.getSouthRoom();
//			}
//
//			if(predatorRoom.hasWestRoom() && (iAngleWest < minAngle))
//			{
//				minAngle = iAngleWest;
//				nextRoomID = predatorRoom.getWestRoom();
//			}
//
			predatorRoom = predatorRoom.getRoom(nextRoomID);
//			// predator.setRoom(predatorRoom);

			returnMessage = predator.getName() + ": " + predatorRoom.getRoomName() + "     Target: "
					+ targetRoom.getRoomName() + "\n";
			if(predatorRoom.getRoomID() == targetRoom.getRoomID())
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
			// if (previousRoomID == 2040 || previousRoomID == 2039)
			// {
			// System.out.println("previousRoomID: " + previousRoomID);
			// System.out.println(possibleMonsterRooms);
			// System.out.println("index: " + index + " room: " +
			// possibleMonsterRooms.get(index));
			// }

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

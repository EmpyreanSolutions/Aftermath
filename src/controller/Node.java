package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node extends Room
{
	private List<Node> shortestPath = new LinkedList<>();
	
	private Integer distance = Integer.MAX_VALUE;
	
	Map<Node, Integer> adjacentNodes = new HashMap<>();
	
	public Node()
	{
		super();
	}
	
	public Node(Room rm)
	{
		super(rm.getRoomID(), rm.getRoomName(), rm.getNorthRoom(), rm.getEastRoom(), 
			  rm.getSouthRoom(), rm.getWestRoom(), rm.getUpRoom(), rm.getDownRoom(),
			  rm.getxCoordinate(), rm.getyCoordinate(),	rm.getzCoordinate(), 
			  rm.getVisited(), rm.getAllowedMonsterRoom(), rm.getRoomDescription());
	}

	public void addDestination(Node destination, int distance)
	{
		adjacentNodes.put(destination, distance);
	}


	public ArrayList<Node> getAllNodes()
	{
		ArrayList<Room> rooms = new ArrayList<>();
		ArrayList<Node> nodes = new ArrayList<>();
		rooms = this.getAllRooms();
		for (int index = 0; index < rooms.size(); index++)
		{
			nodes.add(new Node(rooms.get(index)));
		}
		return nodes;
	}

	public List<Node> getShortestPath()
	{
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath)
	{
		this.shortestPath = shortestPath;
	}

	public Integer getDistance()
	{
		return distance;
	}

	public void setDistance(Integer distance)
	{
		this.distance = distance;
	}

	public Map<Node, Integer> getAdjacentNodes()
	{
		return adjacentNodes;
	}

//	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes)
//	{
//		this.adjacentNodes = adjacentNodes;
//	}
	
	
}

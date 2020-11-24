package Assignment1;

import java.util.PriorityQueue;

public class aStar {
	private static PriorityQueue<hNode> open;
	private static boolean closed[][];
	int f = 0;	//total cost
	int g = 0;	//cost to get to current node
	int h = 0;	//cost to traverse to next node
	
	
	public boolean search(GameBoard grid, hNode target) {
		//f = g + h
		//first node will be 0 (already set)
		hNode start = new hNode(0, 0);
		open.add(start);
		hNode current;
		while(true) {
			current = open.poll();
			if(current == null) break;
			//mark this node as visited
			closed[current.row][current.col] = true;
			//if we are at the target, then end method
			if(current.equals(target)) return true;
			
			
		}
		
		return true;
	}

}

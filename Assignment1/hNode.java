package Assignment1;

public class hNode extends Node{
	int g;	//cost to get to this node
	int h;	//heuristic cost
	hNode parent;
	
	public hNode(int row, int col) {
		super(row, col);
		this.row = row;
		this.col = col;
	}
	
	public boolean equals(hNode hNode) {
		if((this.row == hNode.row) && (this.col == hNode.col))
			return true;
		return false;
	}
}
package Assignment1;


class Node {
	int row, col;
	int distance = -1;
	Node next;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
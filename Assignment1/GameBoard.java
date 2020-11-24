package Assignment1;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/*
 * Introduction to Artificial Intelligence 
 * CS 440
 * 2/9/2020
 */

 /*
 * @author David Testa
 */
public class GameBoard {
    
    //initialize private variables
    private int size;
    private int functionValue;
    private int matrix[][];
    private int currentRow;
    private int currentCol;

    
    /** 
     * GameBoard constructor takes in an integers for the n x n , sets the 
     * current row and column to 0 and calls the matrixGrid method to create the
     * gameboard.
      * @param matrixSize integer from keyboard.
     */
    public GameBoard(int matrixSize) {
        size = matrixSize;
        currentRow = 0;
        currentCol = 0;

        matrixGrid();
    }

    /**
     * Private method that creates the gameboard. This is executed once for every
     * gameboard object.
     */
    private void matrixGrid() {
        matrix = new int[size][size];
        Random randomNum = new Random();
        int jumpSize;
        int maxCell, rowMax, rowMin, colMax, colMin;
        
        for(int row=0; row<size; row++) {
            currentRow = row+1;
            for(int col=0; col<size; col++) {
                currentCol = col+1;
                rowMax = size - currentRow; //ex: 5-0 = 5
                rowMin = currentRow - 1; //ex: 3 - 1 = 2
                colMax = size - currentCol;
                colMin = currentCol - 1;
                
                if (row == size - 1 && col == size - 1) 
                {
                    jumpSize = 0;
                }
                else {
                    maxCell = Math.max(Math.max(rowMax, rowMin), Math.max(colMax, colMin));
                    jumpSize = randomNum.nextInt(maxCell)+1;

                    while(jumpSize == 0) {
                       jumpSize = randomNum.nextInt(maxCell)+1; 
                    }
                }
                matrix[row][col] = jumpSize;
            } //end col for-loop
        } //end row for-loop
    }
    
    
    /**
     * 
     * @return 
     */
    public void hillClimb(){
        //declaring
        //int base[][];
        Random randomNum = new Random();
        int maxCell, rowMax, rowMin, colMax, colMin, jumpSize;
        int oldCellValue;
        //initiate base to be original grid
        //base = matrix;
        
        //generate randomNum1 to any random number between 0 and size (ex 5)
        //generate randomNum2 to any random number between 0 and size (ex 5)
        //call that position base[randomNum1][randomNum2]
        //do whatever you need to that position
        
        int randomCellrow = new Random().nextInt(size);
        int randomCellcol = new Random().nextInt(size);
        
        //set random number can NOT be goal or source ie 00 or size size
        
        //if (randomCellrow == 0 || randomCellrow == size-1){
        //    randomCellrow = new Random().nextInt(size);          
        //}
        while(randomCellrow == 0 || randomCellrow == size-1) {
            randomCellrow = new Random().nextInt(size);
        }
        while(randomCellcol == 0 || randomCellcol == size-1) {
            randomCellcol = new Random().nextInt(size);
        }
            
        
        //if (randomCellcol == 0 || randomCellcol == size-1){
        //    randomCellcol = new Random().nextInt(size);          
        //}
        
        /**change random cell value in original matrix
         * Then run it through populate method to see if function value has changed
         */
        
        oldCellValue = matrix[randomCellrow][randomCellcol];
        

        rowMax = size - randomCellrow; //ex: 5-0 = 5
        rowMin = randomCellrow - 1; //ex: 3 - 1 = 2
        colMax = size - randomCellcol;
        colMin = randomCellcol - 1;

        maxCell = Math.max(Math.max(rowMax, rowMin), Math.max(colMax, colMin));
        jumpSize = randomNum.nextInt(maxCell)+1;
        
        matrix[randomCellrow][randomCellcol] = jumpSize;
        
        displayCell(oldCellValue,jumpSize,randomCellrow, randomCellcol);

    }
    
    
    //delete this later
    public void displayCell(int cellValue, int newCellValue, int row, int col) {
        System.out.println("The old cell value: " + cellValue);
        System.out.println("The new cell value: " + newCellValue);
        System.out.println("At position: (" + row + "," + col + ")");
    }
      
    
    
    /**
     * Returns jumpValue of the current node
     */
    public int getJumpValue(int row, int col) {
    return this.matrix[row][col];
    }
    
    
    
    /**
     * Returns the gameboard to the screen.
     * @return stringToReturn contains the gameboard.
     */
    public String toString() {
        
        String stringToReturn = "";
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                stringToReturn = stringToReturn + matrix[row][col] + "\t";
            }
            stringToReturn = stringToReturn + "\n";
        }
        return stringToReturn;
    }
    
    /**
     * Gets the last value from the populate method.
     * @param value
     * @return 
     */
    public void functionValue(int value) {
        functionValue = value;
    }
    
    /**
     * Shows the last value from the populate method.
     * @return 
     */
    public int showFunctionValue() {
        return functionValue;
    }
    
    
    
    /**
     * 
     * @param grid
     * @param row
     * @param col 
     */
    public void populate(GameBoard grid, int row, int col) {
        //start of timer for populate method
        long startTime = System.currentTimeMillis();
        
    //1: create LL of nodes and pointer for each node
    Node head = generateLL(this);
    Node pointer = head;
        //Node pointer2 = tail;
    
    //2: traverse to neighbors of current node and then add distances
    Queue<Node> q = new ArrayDeque<>();
        // DAVID ADD-ON TO SEE IF WE CAN PRINT OUT *ANY* DIRECTION
        String direction = "";
        
    q.add(pointer);
    int count;
    do {
    //2.1 check if node has already been visited
Node current = q.poll();
if(current.distance < 0)
count = 0;
else
count = current.distance;
//check if visited	THE FOLLOWING IS ONLY TABBED FOR VISUAL CLAIRITY. DOES NOT NEED TO BE TABBED
if(visited(current, head)) {
continue;
}
   	//2.2:get neighbors
int jumpValue = this.matrix[current.row][current.col];	//getJumpValue(current.row, current.col);
//can we move down:
if(current.row + jumpValue <= (currentRow - 1)) {
Node neighbor = new Node(current.row + jumpValue, current.col);
neighbor.distance = count + 1;
q.add(neighbor);
}
//can we move up:
if(current.row - jumpValue > -1) {
Node neighbor = new Node(current.row - jumpValue, current.col);
neighbor.distance = count + 1;
q.add(neighbor);
}
//can we move left:
if(current.col - jumpValue > -1) {
Node neighbor = new Node(current.row, current.col - jumpValue);
neighbor.distance = count + 1;
q.add(neighbor);
}
//can we move right
if(current.col + jumpValue <= (currentCol - 1)) {
Node neighbor = new Node(current.row,  current.col+ jumpValue);
neighbor.distance = count + 1;
q.add(neighbor);
}
//2.3:traverse to neighbors and set distance
while(pointer != null) {
//if(pointer.row == current.row && pointer.col == current.col) {
if(pointer.distance < 0 && (pointer.row == current.row && pointer.col == current.col)) {
pointer.distance = count;
break;
}
pointer = pointer.next;
}
//reset pointer for the next time we need it
pointer = head;
    } while(!q.isEmpty());
    
    //3:print in array format DELETE
    Node current = head;
    int current_row = current.row;
        int tail = 0;
        int xCounter = 0;
        System.out.println("Jumps per Cell:\n");
    while(current != null) {
                //Davids Add-Ons
                if(current.distance == -1) {
                    System.out.print("X" + "\t");
                    xCounter++;
                }
                else {
                    System.out.print(current.distance + "\t");
                }
                tail = current.distance;
    current = current.next;
    if((current != null) && (current.row > current_row)) {
    System.out.println("");
    current_row++;
    }
                
    }
        
        System.out.println("\n");
        
        if (tail == -1) {
            System.out.println("The value function is -" + xCounter++);
            functionValue(-1*xCounter);
        }
        else {
            System.out.println("The value function is " + tail + " moves");
            System.out.println("The Shortest path is " + tail);
            functionValue(tail);
        }
        
        
        
        
        //quantify time it takes to do the method in milliseconds
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");        
    }
    
    /**
     * Returns the head of a linked list, which then can be iterated through in order to print all nodes'
     * coordinates and values
     * 
     * @param grid	nxn-matrix randomly generated
     * @return	head to linked list of all elements
     */
    private Node generateLL(GameBoard grid) {
    Node head = new Node(0,0);
    Node pointer = head;
    for(int row = 0; row < size; row++) {
    for(int col = 0; col < size; col++) {
    if(row == 0 && col == 0)
    continue;
    Node element = new Node(row, col);
    pointer.next = element;
    pointer = pointer.next;
    }
    }
    return head;
    }
    
    /**
     * This method is used to determine whether a node in the queue has already been visited
     * If this method did not exist, the populate function would not end
     * 
     * @param target
     * @param head
     * @return
     */
    private boolean visited(Node target, Node head) {
    Node pointer = head;
    while((pointer.row != target.row) || (pointer.col != target.col)) {
    pointer = pointer.next;
    }
    if(pointer.distance > -1)
    return true;
    else return false;
    }
    
    public void aStarSearch(GameBoard grid, hNode current, int count, Queue<hNode> q) {
    	System.out.println("(" + current.row + ", " + current.col + ")");
    	//start from goal
    	//do actions that get closer to start
    	//if current is top left square, then found
    	//count = depth: this is value for heuristic
    	if(current.row == 0 && current.col == 0) {
    		System.out.println("path found. Heuristic value: " + count);
    		return;
    	}
    	//get neighbors and traverse to whichever is closer to beginning
    	for(int col = size - 1; col > -1; col--) {
    		hNode temp = new hNode(current.row, col);
    		if(temp.row == current.row && temp.col == current.col) continue;
    		temp.distance = grid.matrix[temp.row][temp.col];
    		if(temp.distance + temp.col == current.col) {
    			//neighbor found in column
    			aStarSearch(grid, temp, count++, q);
    		}
    	}
    	for(int row = size - 1; row > -1; row--) {
    		hNode temp = new hNode(row, current.col);
    		if(temp.row == current.row && temp.col == current.col) continue;
    		temp.distance = grid.matrix[temp.row][temp.col];
    		if(temp.distance + temp.row == current.row) {
    			//neighbor found in row
    			aStarSearch(grid, temp, count++, q);
    		}
    	}
    	System.out.println(count);
    	
    	
    	
    	
    	
    	
//    	int f = 0;
//    	int g = 0;
//    	int h = 0;
//    	PriorityQueue<hNode> closed = new PriorityQueue<>();
//    	hNode temp = new hNode(0, 0);	//create node of the first element
//    	temp.h = populatedGrid.matrix[temp.row][temp.col];	//set heuristic value as jumpSize
//    	Queue<hNode> q = new ArrayDeque<>();
//    	q.add(temp);
//    	while(q != null) {
//    		//get neighbors
//    		//can we move down:
//    		hNode current = q.poll();
//    		boolean visited = false;
//    		for(hNode check: closed) {
//    			if(check.equals(current))
//    				visited = true;
//    		}
//    		if(visited == true) continue;
//    		h = current.h;
//    		if(current.row + h <= (currentRow - 1)) {
//	    		hNode neighbor = new hNode(current.row + h, current.col);
//	    		neighbor.h = populatedGrid.matrix[current.row + h][current.col];
//	    		neighbor.distance = g + 1;
//	    		q.add(neighbor);
//    		}
//    		//can we move up:
//    		if(current.row - h > -1) {
//	    		hNode neighbor = new hNode(current.row - h, current.col);
//	    		neighbor.h = populatedGrid.matrix[current.row - h][current.col];
//	    		neighbor.distance = g + 1;
//	    		q.add(neighbor);
//    		}
//    		//can we move left:
//    		if(current.col - h > -1) {
//	    		hNode neighbor = new hNode(current.row, current.col - h);
//	    		neighbor.h = populatedGrid.matrix[current.row][current.col - h];
//	    		neighbor.distance = g + 1;
//	    		q.add(neighbor);
//    		}
//    		//can we move right:
//    		if(current.col + h <= (currentCol - 1)) {
//	    		hNode neighbor = new hNode(current.row,  current.col+ h);
//	    		neighbor.h = populatedGrid.matrix[current.row][current.col + h];
//	    		neighbor.distance = g + 1;
//	    		q.add(neighbor);
//    		}
//    		closed.add(current);
//    		g++;
//    		//get f value
//    		//have to compare current f with f from last iteration
//    	}
//    	System.out.println("path is " + g + "");
    }
    
    
}
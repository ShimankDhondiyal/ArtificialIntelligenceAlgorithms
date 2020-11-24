package Assignment1;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import javax.annotation.Generated;

/*
 * [ description of driver ]
 */

/**
 *
 * @author David Testa
 */
public class Driver {
    public static void main(String[] args) {
        

        Scanner keyboard = new Scanner(System.in);
        int size;
        int valueInput;
        String input;

        System.out.println("Input matrix size (5, 7, 9, or 11)");
        size = keyboard.nextInt();

        //Commented out to allow any size matrix.
        //UPDATE THIS LATER!
//        System.out.println("Size entry: " + size + "\n");
//        while (size != 5 && size != 7 && size != 9 && size != 11) {
//        	System.out.print("Invalid entry" + "\n\n"
//        	+ "Please enter a valid size for the square grid" + "\n"
//        	+ "5, 7, 9, or 11: ");
//        	size = keyboard.nextInt();
//        }
//        GameBoard newGameBoard = new GameBoard(size);
//        System.out.println(newGameBoard.toString());
        
        //create populatedBoard;
        //call populate method
//        newGameBoard.populate(newGameBoard, 0, 0);
      
        
        
//        size = keyboard.nextInt();
        //while (size!=0) {
        //Creating an object of type GameBoard
        GameBoard newGameBoard = new GameBoard(size);

       System.out.println("\n" + "Welcome to the gameboard!" + "\n"
               + "How many moves to get to zero?!" + "\n");

        System.out.println(newGameBoard.toString());
        
        //create populatedBoard;
        //call populate method
        //newGameBoard.callPop();
        
        newGameBoard.populate(newGameBoard,0,0);
        //save newGameBoard goal jump
        int saveFunctionValue = newGameBoard.showFunctionValue();
        
        //-------NEW OBJECT---------
        
        GameBoard secondGameBoard = newGameBoard;
        
        System.out.println("\n" + "Welcome to the NEW gameboard!" + "\n"
               + "How many moves to get to zero?!" + "\n");

        System.out.println(secondGameBoard.toString());
        
        
        
        //System.out.println("\n" + "enter 0 to stop, any number to continue");
        //size = keyboard.nextInt();
        //}
        
        /** Evaluate the functionValues
         * 1. If value 2 is >= value 1
         * Update newGameBoard matrix 
         * Re-populate the gameBoard
         * 2. Else
         * continue with the same gameBoard in newGameBoard
         * 
         * Allow for multiple iterations...potentially make this an input in 
         * the functionValue method.
         */
        
        System.out.print("How many interations would you like to perform? ");
        valueInput = keyboard.nextInt();
        
        int counter = valueInput;
        
        //generate a new random number.
        secondGameBoard.hillClimb();
        //Populate new Jumps Gameboard
        System.out.println(secondGameBoard.toString());
        System.out.println("\n");
        secondGameBoard.populate(secondGameBoard,0,0);
        //save secondGameBoard goal jump
        int newFunctionValue = secondGameBoard.showFunctionValue();

        while (counter!=0) {
            
            //if the new value is larger or equal to the old value
            if(newFunctionValue >= saveFunctionValue) {
                //save the new matrix to the old one.
                newGameBoard = secondGameBoard;
                //populate new jumps
                newGameBoard.populate(newGameBoard,0,0);
                //display old jump value
                System.out.println("Old Function Value: " + saveFunctionValue);
                //save the new value in the saveFunctionValue
                saveFunctionValue = newGameBoard.showFunctionValue();
                //display new jump value
                System.out.println("New Function Value: " + saveFunctionValue);
            }
            else {
                System.out.println("No Value Updated.");
                System.out.println("\n");
                System.out.println("Current Function Value in original matrix: " +
                        saveFunctionValue);
            }
            
            counter--;
            
            if(counter!=0) {
                System.out.println("\n");
                System.out.println("\n");
                //Generate new random number in random cell
                secondGameBoard.hillClimb();
                System.out.println("\n");
                System.out.println("POPULATED JUMPS OF NEW BOARD");
                //populate new jump board
                secondGameBoard.populate(secondGameBoard,0,0);
                //save secondGameBoard goal jump
                newFunctionValue = secondGameBoard.showFunctionValue();
            }
            
        }
        System.out.println("\n" + "Final Matrix Below: ");
        
        
        System.out.println(newGameBoard.toString());
        //System.out.println("Shortest path: " + newGameBoard.showFunctionValue());
        
        Queue<hNode> q = new ArrayDeque();
        hNode current = new hNode(size - 1, size - 1);
        newGameBoard.aStarSearch(newGameBoard, current, 0, q);
    }
}

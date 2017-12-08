import java.util.ArrayList;

/** This class implements the Recursive Backtracking algorithm for creating mazes 
 */

public class RecursiveBacktracker extends RecursiveStrategy { 
	
	public Cell handleCases(ArrayList<Cell> neighbours, Cell current){
				
		//if the cell has neighbours, that have not been visited yet
		if(neighbours.size()>0){
		 return basicMode(neighbours,current);
		}
		
		else {
		 return huntMode(neighbours,current);
		}		
	}
	
	Cell huntMode(ArrayList<Cell> neighbours,Cell current){
		//System.out.println("Dead End!"); //debug
		backTrackingStack.pop();
		current=backTrackingStack.peek();
		return current;
	}
	
	}

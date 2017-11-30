import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Kruskal implements MazeCreator {
	
	//stack of all edges between the cells, edge is defined as a set including the cell "from" and the cell "to"
	Stack<Set<Cell>> allEdges;
	
	//the set saves sets that include cells
	Set<Set<Cell>> setsOfCells;
	
	public void setUpStructure(Cell[][] mazeFields){
		allEdges = new Stack<Set<Cell>>();
		setsOfCells = new HashSet<Set<Cell>>();
		
		//go over all cells
		for(int i = 0; i<mazeFields.length; i++){
			for (int j = 0; j<mazeFields.length; j++){
				
				//create new set with cell in it
				Set<Cell> cellSet = new HashSet<Cell>();
				cellSet.add(mazeFields[i][j]);
				//by default setsOfCells contains every Cell in a separate set
				setsOfCells.add(cellSet);
				
				//find the neighbours
				for(Cell c : mazeFields[i][j].getNeighbours()){
				
				//add the edge to stack
				Set<Cell> current = new HashSet<Cell>();
				
				current.add(mazeFields[i][j]);
				current.add(c);
				if(!allEdges.contains(current)){
				allEdges.add(current); }
				
				}
			}
		}
		//stack is shuffled to randomize order of edges
		Collections.shuffle(allEdges);
	}

	
	public void createMaze(Cell startingField, Cell endpoint){
		
		//go over all edges
		while(!allEdges.empty()){
			
			//select first edge
			Set<Cell> current = allEdges.peek();
			
			//pop edge from stack
			allEdges.pop();
			
			
			Cell[] contents = new Cell[2];
			
			//saves the two cells in edge into Array
			contents = current.toArray(contents);
			
			//temporary variable to save the sets in which the Cells can be currently found
			Set<Cell> firstSet = new HashSet<Cell>();
			Set<Cell> secondSet = new HashSet<Cell>();
			
			//iterate over all sets in setsOfCells to find the one including the cell of edge
			for(Set<Cell> iterator : setsOfCells){
				if(iterator.contains(contents[0])){
					firstSet=iterator;
				}
			}
			
			for(Set<Cell> iterator : setsOfCells){
				if(iterator.contains(contents[1])){
					secondSet=iterator;
				}
			}
			
			//if cells are not in one set already:
			if(!secondSet.equals(firstSet)){
				
				//create set to combine the two sets
				Set<Cell> combinedSet = new HashSet<Cell>();
				
				//add both sets to the combined set
				combinedSet.addAll(secondSet);
				combinedSet.addAll(firstSet);
				
				//set the connection between the cells in the cell objects
				contents[0].addConnection(contents[1]);
				contents[1].addConnection(contents[0]);
				
				//set the shared wall to unsolid
				for(int i = 0; i<4; i++){
					for(int j=0;j<4;j++){
						if(contents[0].walls[i].equals(contents[1].walls[j])){
							contents[0].walls[i].setPath();
						}
					}
				}
				
				//debugging
				System.out.println("Connected cell " + contents[1].toString() + " and " + contents[0].toString());
				
				//remove the two seperate cells from setsOfCells
				setsOfCells.remove(secondSet);
				setsOfCells.remove(firstSet);
				
				//add the combinedSet to setsOfCells
				setsOfCells.add(combinedSet);
				
				//debugging
				System.out.println("Combined sets " + firstSet.toString() + " and " + secondSet.toString());
			}
		}
		endpoint.walls[1].setPath();
	}

}

	import java.util.ArrayList;
	import java.util.concurrent.ThreadLocalRandom;

	//implements hunt and kill strategy for maze creation
	
	public class HuntAndKill extends RecursiveStrategy { 
		
		//handles the two cases basic mode and hunting mode
		public Cell handleCases(ArrayList<Cell> neighbours, Cell current){
			int random = ThreadLocalRandom.current().nextInt(0,10);
			
			//if the cell has neighbours, that have not been visited yet and no random initializing of hunting mode
			if(neighbours.size()>0 && !(random%5==0)){
				return basicMode(neighbours,current);
			}
			
			//handle hunting mode
			else {
				return huntMode(neighbours,current);								
			}
			
		}
		
		public Cell huntMode(ArrayList<Cell> neighbours, Cell current){
			//System.out.println("Dead End! Moving into hunting mode");
			
			mainLoop:
				
			//iterate over all cells until one is found, which has a already visited neighbour
			for(int i = 0; i<mazeFields.length; i++){
				for(int j = 0; j<mazeFields.length; j++){
					
					if(mazeFields[i][j].getStatus()==Status.UNVISITED){
						
					ArrayList<Cell> neighbourList = mazeFields[i][j].getNeighbours();
					for(Cell c : neighbourList){
						if(c.getStatus()==Status.VISITED){
							c.addConnection(mazeFields[i][j]);
							mazeFields[i][j].addConnection(c);
							
							current=mazeFields[i][j];						
							
							//set status of current cell to visited
							current.setStatus(Status.VISITED); 
							
							//delete cell from unvisited
							unvisitedCells.remove(current); 
							
							
							break mainLoop;
							
							
							}
						}
					}
					
				}
			}
			return current;
		}
			

		}

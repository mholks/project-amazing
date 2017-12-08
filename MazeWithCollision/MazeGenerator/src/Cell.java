import java.util.ArrayList;
import processing.core.PApplet;

public class Cell {
	private boolean visited; // enum consists of the two states: visited || unvisited
	private int widthPosition; // cell's width position in the grid
	private int heightPosition; // cell's height position in the grid
	private ArrayList<Cell> neighbours; //stores between two and four neighboring cells of the Cell
	private ArrayList<Cell> connectedCells; //array of cells to which the cell has a connecting path to
	private PApplet parent; //PApplet for processing window
	private int size; //size of a cell in pixels
	private Wall[] walls; //walls[0]: up; walls[1]: right; walls[2]:down; walls[3]:left
	
	//constructor; arguments: position of cell, PApplet
	public Cell(int width, int height, PApplet par, int newSize){
		
		walls = new Wall[4];
		size = newSize;
		
		//set position
		widthPosition=width;
		heightPosition=height;
		
		//set cell's status to the default value of unvisited
		visited = false;
		
		parent = par;
		
		//empty ArrayList for storing neighbors is initialized
		neighbours = new ArrayList<Cell>();
		
		//empty ArrayList for storing connected cells is initialized
		connectedCells = new ArrayList<Cell>();
	}
	
	//setter-method for setting status of cell (visited/ unvisited)
	public void setVisited(boolean visited){
		this.visited=visited;
		
		//DEBUG
		//System.out.println("Set status of cell " + this.toString() + " to " + newstate);
		
		}
	
	//getter-method for reading status of cell
	public boolean getStatus(){
		return visited;
	}
	
	//getter-method returning all neighbors of a cell
	public ArrayList<Cell> getNeighbours(){
		return neighbours;
	}
	
	//setter-method for adding one neighbor to the list of neighboring cells
	public void setNeighbour(Cell neighbour){
		neighbours.add(neighbour);
	}
	
	//getter-method, returns connected cells	
	public ArrayList<Cell> getConnectedCells(){
		return connectedCells;
	}
	
	//setter-method, adds one connection
	public void addConnection(Cell connectedCell){
		connectedCells.add(connectedCell);
		
		//the wall between the cells is set as unsolid
		for(int i = 0; i<4; i++){
			for(int j=0;j<4;j++){
				if(this.walls[i].equals(connectedCell.walls[j])){
				this.walls[i].setPath();
				}
			}
		}
		
	}
	
	public Wall[] getWalls(){
		return walls;
	}
	
	//setter-method, set all walls around cell
	public void setWalls(Wall up,  Wall down, Wall left, Wall right){
		walls[0]=up;
		walls[1]=right;
		walls[3]=left;
		walls[2]=down;
		
	}
		
	//DEBUG overriding default toString-method
	public String toString(){
		return "[" + widthPosition + "][" + heightPosition + "]";
	}
	
	//output of cell in console output	
	public void printCell(){
		parent.stroke(0,0,0);
		parent.rect(widthPosition*size,heightPosition*size,size,size);
	}
			

	}
	

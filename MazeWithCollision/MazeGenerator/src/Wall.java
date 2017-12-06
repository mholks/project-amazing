import processing.core.PApplet;

public class Wall {
	int posX; // x index of the wall 
	int posY; // y index
	boolean solid; //wall solid or passage
	PApplet parent;
	int wallWidth; //width of wall
	int wallHeight;//height of wall
	int cellSize; //size of a cell
	
	public Wall(int widthPos, int heightPos, PApplet p, int w, int h, int cellS){
		solid=true; //wall is initialized as solid
		posX = widthPos; //position of wall is passed by constructor
		posY= heightPos;
		parent = p;
		wallWidth = w; //width is passed by constructor
		wallHeight = h; //height is passed by constructor
		cellSize = cellS; //size of a cell passed by constructor
	}
	
	public String toString(){
		String s = "Wall: " + posX + " ," + posY;
		return s;
	}
	//sets a wall to be possible to walk through
	public void setPath(){
		solid = false;
	}
	
	public void printWall(){
		parent.stroke(0,0,0);
		parent.fill(0,255,0);
		parent.rect(posX*cellSize,posY*cellSize,wallWidth,wallHeight);
	}
}

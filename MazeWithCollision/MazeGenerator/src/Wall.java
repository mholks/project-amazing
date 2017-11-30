import processing.core.PApplet;

public class Wall {
	int posX;
	int posY;
	boolean solid;
	PApplet parent;
	int wallWidth;
	int wallHeight;
	int cellSize;
	
	public Wall(int widthPos, int heightPos, PApplet p, int w, int h, int cellS){
		solid=true;
		posX = widthPos;
		posY= heightPos;
		parent = p;
		wallWidth = w;
		wallHeight = h;
		cellSize = cellS;
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

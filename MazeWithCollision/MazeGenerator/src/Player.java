import processing.core.PApplet;
import processing.core.*;

public class Player {
	private PVector velocity; //player's velocity
	private float maxVelocity; //maximal possible velocity
	private PVector position; //position of the player in pixel
	private PApplet parent; //PApplet window for graphical output
	public  boolean up; //key control, up pressed
	public boolean down; //key control, down pressed
	public boolean right; //key control, right pressed
	public boolean left; //key control, left pressed
	private float acceleration; 
	private float radius; //radius of player icon
	private float deceleration;
	private int cellSize;
	
	public Player(PApplet par, int cellSize) {
		velocity = new PVector(0, 0); //set initial velocity to zero
		parent = par; //connect PApplet
		acceleration = 0.5f; //set acceleration
		deceleration = 0.7f; //set deceleration
		this.cellSize = cellSize;
		radius = cellSize/3; //set radius				
		maxVelocity = 0.012f * cellSize + 1.4195f;

		
	}

	//set initial position of player in pixel
	public void setStartPosition(float posW, float posH) {
		position = new PVector(0, 0);
		position.x = posW;
		position.y=posH;
	}
	
	public void drawPlayer() {
		parent.fill(255, 255, 0);
		parent.ellipse(position.x, position.y, radius, radius); 	  
	}

	//process key control
	public void move() {
		
		if (up) {
			velocity.y = velocity.y - acceleration;
		}
		if (down) {
			velocity.y = velocity.y + acceleration;
		}
		if (left) {
			velocity.x = velocity.x - acceleration;
		}
		if (right) {
			velocity.x = velocity.x + acceleration;
		}
		if (!left && !right) {
			velocity.x = (velocity.x * deceleration);
		}
		if (!up && !down) {
			velocity.y = (velocity.y * deceleration);
		}
		velocity.limit(maxVelocity);
		position.add(velocity);

	}

	//check collision with horizontal walls
		void collisionHorizontal(Wall wall){
			if (wall.solid) {
				if(velocity.y>0){
					if (position.y + radius >= wall.posY * cellSize && position.y < wall.posY * cellSize + wall.wallHeight) {
						if(position.x > wall.posX*cellSize && position.x < wall.posX *cellSize + wall.wallWidth) {
							position.y = wall.posY * cellSize + wall.wallHeight-radius;
						}
					}
				}
				if(velocity.y<0){
					if (position.y - radius >= wall.posY * cellSize && position.y <= wall.posY * cellSize + wall.wallHeight + radius) {
						if(position.x > wall.posX*cellSize && position.x < wall.posX *cellSize + wall.wallWidth) {
							position.y = wall.posY * cellSize + wall.wallHeight + radius+2;
						}
					}
				}
			}
		}
		
		//check collision with vertical walls
		void collisionVertical(Wall wall) {
			if (wall.solid) {
				if(velocity.x>0){
					if (position.x + radius >= wall.posX * cellSize && position.x < wall.posX * cellSize + wall.wallWidth) {
						if(position.y > wall.posY*cellSize && position.y < wall.posY *cellSize + wall.wallHeight) {
							position.x = wall.posX * cellSize + wall.wallWidth-radius;
						}
					}
				}
				if(velocity.x<0){
					if (position.x - radius >= wall.posX * cellSize && position.x <= wall.posX * cellSize + wall.wallWidth + radius) {
						if(position.y > wall.posY*cellSize && position.y < wall.posY *cellSize + wall.wallHeight) {
							position.x = wall.posX * cellSize + wall.wallWidth + radius+2;
						}
					}
				}
			}

		}
	
	//check whether player escaped the maze
	public boolean goalReached(int size){
		if(position.x>size+10|| position.y>size+10){
			return true;
		}
		else{
			return false;
		}
	}
	
	//set maximum velocity of player
	  void setMaxVelocity(float f)
	  {
	    maxVelocity = f; 
	  }
	
	public float getPositionX(){
		return position.x;
	}

	public float getPositionY(){
		return position.y;
	}
}
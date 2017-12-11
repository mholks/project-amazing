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
	
	public Player(PApplet par, int cellSize) {
		velocity = new PVector(0, 0); //set initial velocity to zero
		parent = par; //connect PApplet
		radius = cellSize/3; //set radius				
		maxVelocity = 0.012f * cellSize + 1.4195f;
		acceleration = 0.5f; //set acceleration
		deceleration = 0.7f; //set deceleration		
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
			if (wall.isSolid()) {
				if(velocity.y>0){
					if (position.y + radius >= wall.getPosY() && position.y < wall.getPosY()+ wall.getWallHeight()) {
						if(position.x > wall.getPosX() && position.x < wall.getPosX()  + wall.getWallWidth()) {
							position.y = wall.getPosY()  + wall.getWallHeight()-radius;
						}
					}
				}
				if(velocity.y<0){
					if (position.y - radius >= wall.getPosY() && position.y <= wall.getPosY()  + wall.getWallHeight() + radius) {
						if(position.x > wall.getPosX() && position.x < wall.getPosX()  + wall.getWallWidth()) {
							position.y = wall.getPosY()  + wall.getWallHeight() + radius+2;
						}
					}
				}
			}
		}
		
		//check collision with vertical walls
		void collisionVertical(Wall wall) {
			if (wall.isSolid()) {
				if(velocity.x>0){
					if (position.x + radius >= wall.getPosX() && position.x < wall.getPosX() + wall.getWallWidth()) {
						if(position.y > wall.getPosY()&& position.y < wall.getPosY()  + wall.getWallHeight()) {
							position.x = wall.getPosX()  + wall.getWallWidth()-radius;
						}
					}
				}
				if(velocity.x<0){
					if (position.x - radius >= wall.getPosX()  && position.x <= wall.getPosX()  + wall.getWallWidth() + radius) {
						if(position.y > wall.getPosY() && position.y < wall.getPosY()  + wall.getWallHeight()) {
							position.x = wall.getPosX()  + wall.getWallWidth() + radius+2;
						}
					}
				}
			}
		}
	
	//check whether player escaped the maze
	public boolean goalReached(int size){		
		if(position.x>size+10|| position.y>size+10){
			parent.fill(0,255,0);
			parent.rect(0,0,200,200);		
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
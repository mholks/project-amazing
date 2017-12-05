import processing.core.*;
import java.util.concurrent.ThreadLocalRandom;
import controlP5.*;

public class UsingProcessing extends PApplet {

	boolean goalReached = false; // goal of the current level reached

	int size = 5; // size of maze in cells

	int cellSize = 450 / size; // size of one cell

	MazeCreator creat = new RecursiveBacktracker(); // chosen maze creation
													// strategy

	Player p = new Player(this, cellSize); // initialize player

	Maze maze = new Maze(size, size, this, creat, cellSize); // initialize new
																// maze

	int allowedTimeForLevel = 20;
	
	ControlP5 cp5; // library for buttons and timer

	Bang startButton; // button to start game
	boolean started = false; // has game been started

	Bang continueButton; // button to continue game
	Bang pauseButton; // button to pause game
	boolean paused = false; // is game paused

	int timeOnPause = 0; // how long was game paused

	ControlTimer time = new ControlTimer();

	// set starting cell for maze creation
	int startingIndex1 = 0;
	int startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);;

	// set end point -> goal
	int endIndex1 = size - 1;
	int endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

	int complexityClass = 5;
	
	// create a new maze and set player to its start
	public void initializeNewMaze(int complexity) {
		goalReached = false;
		// set time to zero
		time = new ControlTimer();
		
		
		if (complexity == 1){
			creat = new Kruskal();// set strategy for maze creation
			size=5;
			allowedTimeForLevel = 11;
			
		}
		
		
		if (complexity == 2){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=5;
			allowedTimeForLevel = 17;
		}
		
		if (complexity == 3){
			creat = new Kruskal();// set strategy for maze creation
			size = 7;
			allowedTimeForLevel = 9;
		}
		
		if (complexity == 4){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size= 9;
			allowedTimeForLevel = 16;
		}
		if (complexity == 5){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=7;
			allowedTimeForLevel = 25;
		}
		
		if (complexity == 6){
			creat = new HuntAndKill();// set strategy for maze creation
			size=7;
			allowedTimeForLevel = 27;
		}
	
		if (complexity == 7){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 5;
			allowedTimeForLevel = 15;
		}
		if (complexity == 8){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=11;
			allowedTimeForLevel = 15;
		}
		
		if (complexity == 9){
			creat = new Kruskal();// set strategy for maze creation
			size = 9;
			allowedTimeForLevel = 20;
		}
		
		if (complexity == 10){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size= 13;
			allowedTimeForLevel = 23;
		}
		if (complexity == 11){
			creat = new Kruskal();// set strategy for maze creation
			size=11;
			allowedTimeForLevel = 33;
		}
		
		if (complexity == 12){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=15;
			allowedTimeForLevel = 14;
		}
	
		if (complexity == 13){
			creat = new Kruskal();// set strategy for maze creation
			size = 13;
			allowedTimeForLevel = 32;
		}
		if (complexity == 14){
			creat = new Kruskal();// set strategy for maze creation
			size=15;
			allowedTimeForLevel = 36;
		}
	
		if (complexity == 15){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 13;
			allowedTimeForLevel = 39;
		}
		
		if (complexity == 16){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 9;
			allowedTimeForLevel = 36;
		}
		
		if (complexity == 17){
			creat = new HuntAndKill();// set strategy for maze creation
			size=11;
			allowedTimeForLevel = 35;
		}
	
		if (complexity == 18){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 15;
			allowedTimeForLevel = 44;
		}
		if (complexity == 19){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 17;
			allowedTimeForLevel = 55;
		}
		
		if (complexity == 20){
			creat = new Kruskal();// set strategy for maze creation
			size=17;
			allowedTimeForLevel = 43;
		}
	
		if (complexity == 21){
			creat = new Kruskal();// set strategy for maze creation
			size = 19;
			allowedTimeForLevel = 50;
		}
		
		
		cellSize = 450 / size;
		p = new Player(this, cellSize);// initialize player
		
		maze = new Maze(size,size, this, creat, cellSize); // initialize new maze grid
		
		startingIndex1 = 0;
		startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);
		// set end point -> goal
		endIndex1 = size - 1;
		endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);
		// create maze with initialized criteria
		maze.creator.createMaze(maze.mazeFields[startingIndex1][startingIndex2],
				maze.mazeFields[endIndex1][endIndex2]);
			
		// set startingPosition of player
			p.setStartPosition(startingIndex1 * maze.cellSize + maze.cellSize / 3 + 2,
					startingIndex2 * maze.cellSize + maze.cellSize / 2);
		
		} 


	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}

	// set size of output window, P3D is used for enabling lightening effects
	public void settings() {
		size(600, 600, P3D);

	}

	// set up buttons
	public void setup() {
		cp5 = new ControlP5(this);
		startButton = new Bang(cp5, "Start");
		startButton.setPosition(300, 300).setSize(60, 60);
		continueButton = new Bang(cp5, "Continue");
		continueButton.setPosition(-20, -20).setSize(5, 5);
		pauseButton = new Bang(cp5, "Pause");
		pauseButton.setPosition(-10, -10).setSize(5, 5);

	}

	// process button click on start
	public void Start() {
		started = true;

		// initialize first level's maze
		initializeNewMaze(complexityClass);
	}

	// process button click on pause
	public void Pause() {
		paused = true;
		
		pauseButton.setPosition(-10, -10).setSize(5, 5);
		continueButton.setPosition(200, 200).setSize(5, 5);
	}

	// process button click on continue
	public void Continue() {
		paused = false;
		continueButton.setPosition(-20, -20).setSize(5, 5);
		//reset time to zero (only for testing)
		time = new ControlTimer();
	}

	// process key press to move player
	public void keyPressed() {
		if (key == 'd' || key == 'D') {
			p.right = true;
		}
		if (key == 's' || key == 'S') {
			p.down = true;
		}
		if (key == 'a' || key == 'A') {
			p.left = true;
		}
		if (key == 'w' || key == 'W') {
			p.up = true;
		}
		p.move();
	}

	public void keyReleased() {
		if (key == 'd' || key == 'D') {
			p.right = false;
		}
		if (key == 's' || key == 'S') {
			p.down = false;
		}
		if (key == 'a' || key == 'A') {
			p.left = false;
		}
		if (key == 'w' || key == 'W') {
			p.up = false;
		}
		p.move();

	}

	// draw on output screen
	public void draw() {
		fill(0);
		rect(0, 0, 600, 600);

		// if paused, show pause screen
		if (paused) {
			time = new ControlTimer();
			rect(0, 0, 600, 600);
			continueButton.setPosition(300, 300).setSize(60, 60);
		}

		else {

			// when game is started
			if (started) {

				cp5.remove("Start"); // remove start button

				// if goal is not reached yet
				if (!(goalReached)) {
					
					fill(255, 255, 255);
					
					textSize(20);
					text(complexityClass, 500,500);

					// show counting time in minutes and seconds
					text(Math.round(time.time() / 1000 / 60 - timeOnPause)// minutes
							+ ":" + Math.round((time.time() / 1000) % 60 - timeOnPause), 500, 20);// seconds

				// spotlight on pause button
					spotLight(255.0f, 255.0f, 255.0f, // color of the spotlight
														// in RGB
							280, 580, 1000, // position of spotlight (follows
											// player position)
							0, 0, -1, // direction in which the light point
							PI / 2, // angle of the light
							600); // concentration of the light 

					// view pause button
					pauseButton.setPosition(250, 540).setSize(60, 40);

					// spotlight following player
					spotLight(255.0f, 255.0f, 255.0f, // color of the spotlight
														// in RGB
							p.position.x, p.position.y, 1000, // position of
																// spotlight
																// (follows
																// player
																// position)
							0, 0, -1, // direction in which the light point
							PI / 2, // angle of the light
							600); // concentration of the light

					// display maze

					fill(255, 255, 255);
					stroke(255, 255, 255);
					maze.printMaze();

					fill(0);
					rect((endIndex1+1)*cellSize,endIndex2*cellSize,4,cellSize);
					
					// print starting point
					fill(255, 0, 0);
					triangle(startingIndex1, // edge 1
							startingIndex2 * cellSize, startingIndex1, // edge 2
							startingIndex2 * cellSize + cellSize - 2, startingIndex1 + cellSize - 5, // edge
																										// 3
							startingIndex2 * cellSize + cellSize / 2);

					// print end point
					fill(0, 255, 0);
					triangle(endIndex1 * cellSize + 4, endIndex2 * cellSize, endIndex1 * cellSize + 4,
							endIndex2 * cellSize + cellSize - 2,

							endIndex1 * cellSize + cellSize - 5, endIndex2 * cellSize + cellSize / 2);

					// check for collision with horizontal walls
					for (int i = 0; i < maze.horizontalWalls.length; i++) {
						for (int j = 0; j < maze.horizontalWalls[0].length; j++) {
							p.collisionHorizontal(maze.horizontalWalls[i][j]);

						}

					}

					// check for collision with vertical walls
					for (int i = 0; i < maze.verticalWalls.length; i++) {
						for (int j = 0; j < maze.verticalWalls[0].length; j++) {
							p.collisionVertical(maze.verticalWalls[i][j]);
						}

					}

					// move player
					p.move();

					// draw player on current position
					p.drawPlayer();

					goalReached = p.goalReached(size * cellSize);
				}

				// if goal is reached, initialize new maze
				else {
					
					int neededTime = Math.round((time.time() / 1000)); // time player needed
																		// to finish level
					
					// in case player needs more than X seconds, next level should be more
					// difficult
					if (neededTime > allowedTimeForLevel && complexityClass>1) {
					complexityClass--;
					initializeNewMaze(complexityClass);
					}
					else{
						complexityClass++;
						initializeNewMaze(complexityClass);
					}
				}
			}
		}
	}
	
	
	//game version only with RecursiveBacktracker
		public void initializeAllRecursive(int complexity) {
			goalReached = false;
			
			// set time to zero
			time = new ControlTimer();
			
			float maxVel = 2.0f;
			
			if (complexity == 1){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=5;
					//allowedTimeForLevel = 20;
					maxVel = 2.5f;
					}
			
			if (complexity == 2){
				creat = new RecursiveBacktracker();// set strategy for maze creation
				size=5;
				//allowedTimeForLevel = 20;
				maxVel = 2.5f;
				}
			if (complexity == 3){
				maxVel = 2.5f;
				creat = new RecursiveBacktracker();// set strategy for maze creation
				size=5;
				//allowedTimeForLevel = 20;
				}
				
			if (complexity == 4){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=7;
					maxVel = 2.2f;
					//allowedTimeForLevel = 20;
				}
			if (complexity == 5){
				maxVel = 2.2f;
				creat = new RecursiveBacktracker();// set strategy for maze creation
				size=7;
				//allowedTimeForLevel = 20;
				}
				
			if (complexity == 6){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=7;
					//allowedTimeForLevel = 20;
					maxVel = 2.2f;
				}
			if (complexity == 7){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=9;
					//allowedTimeForLevel = 20;
					maxVel = 2f;
				}
		
			if (complexity == 8){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=9;
					maxVel = 2f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 9){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=9;
					maxVel = 2f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 10){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=11;
					maxVel = 1.95f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 11){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=11;
					maxVel = 1.95f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 12){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=11;
					maxVel = 1.95f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 13){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
				}
	
			if (complexity == 14){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
			}
			if (complexity == 15){	
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
			}
		
			if (complexity == 16){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=15;
					//allowedTimeForLevel = 20;
			}
			
			if (complexity == 17){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=15;
					maxVel = 1.75f;
					//allowedTimeForLevel = 20;
			}
		
			if (complexity == 18){
					creat = new RecursiveBacktracker();// set strategy for maze creation
					size=15;
					maxVel = 1.75f;
					//allowedTimeForLevel = 20;
			}
			if (complexity == 19) {
				
				fill(0);
				stroke(20);
				text("Thank you for playing", 250,250);
				
			}
			
			
			
			cellSize = 450 / size;
			p = new Player(this, cellSize);// initialize player
			p.setMaxVelocity(maxVel);
			maze = new Maze(size,size, this, creat, cellSize); // initialize new maze grid
			
			startingIndex1 = 0;
			startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// set end point -> goal
			endIndex1 = size - 1;
			endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// create maze with initialized criteria
			maze.creator.createMaze(maze.mazeFields[startingIndex1][startingIndex2],
					maze.mazeFields[endIndex1][endIndex2]);
				
			// set startingPosition of player
				p.setStartPosition(startingIndex1 * maze.cellSize + maze.cellSize / 3 + 2,
						startingIndex2 * maze.cellSize + maze.cellSize / 2);

			
			}
	
		//game version only with Kruskal
		public void initializeAllKruskal(int complexity) {
			goalReached = false;

			float maxVel = 2;
			// set time to zero
			time = new ControlTimer();
			
				
		if (complexity == 1){
				creat = new Kruskal();// set strategy for maze creation
				size=5;
				maxVel = 2.5f;
				//allowedTimeForLevel = 20;
				}
		
		if (complexity == 2){
			creat = new Kruskal();// set strategy for maze creation
			size=5;
			//allowedTimeForLevel = 20;
			maxVel = 2.5f;
			}
		if (complexity == 3){			
			creat = new Kruskal();// set strategy for maze creation
			size=5;
			maxVel = 2.5f;
			//allowedTimeForLevel = 20;
			}
			
		if (complexity == 4){
				creat = new Kruskal();// set strategy for maze creation
				size=7;
				//allowedTimeForLevel = 20;
			}
		if (complexity == 5){
			maxVel = 2.2f;
			creat = new Kruskal();// set strategy for maze creation
			size=7;
			//allowedTimeForLevel = 20;
			}
			
		if (complexity == 6){
				creat = new Kruskal();// set strategy for maze creation
				size=7;
				maxVel = 2.2f;
				//allowedTimeForLevel = 20;
			}
		if (complexity == 7){
				creat = new Kruskal();// set strategy for maze creation
				size=9;
				//allowedTimeForLevel = 20;
			}
	
		if (complexity == 8){
				creat = new Kruskal();// set strategy for maze creation
				size=9;
				maxVel = 2f;
				//allowedTimeForLevel = 20;
			}
		
		if (complexity == 9){
				creat = new Kruskal();// set strategy for maze creation
				size=9;
				maxVel = 2f;
				//allowedTimeForLevel = 20;
			}
		
		if (complexity == 10){
				creat = new Kruskal();// set strategy for maze creation
				size=11;
				maxVel = 1.95f;
				//allowedTimeForLevel = 20;
			}
		
		if (complexity == 11){
				creat = new Kruskal();// set strategy for maze creation
				size=11;
				maxVel = 1.95f;
				//allowedTimeForLevel = 20;
			}
		
		if (complexity == 12){
				creat = new Kruskal();// set strategy for maze creation
				size=11;
				maxVel = 1.95f;
				//allowedTimeForLevel = 20;
			}
		
		if (complexity == 13){
				creat = new Kruskal();// set strategy for maze creation
				size=13;
				maxVel = 1.85f;
				//allowedTimeForLevel = 20;
			}

		if (complexity == 14){
				creat = new Kruskal();// set strategy for maze creation
				size=13;
				maxVel = 1.85f;
				//allowedTimeForLevel = 20;
		}
		if (complexity == 15){	
				creat = new Kruskal();// set strategy for maze creation
				size=13;
				maxVel = 1.85f;
				//allowedTimeForLevel = 20;
		}
	
		if (complexity == 16){
				creat = new Kruskal();// set strategy for maze creation
				size=15;
				maxVel = 1.75f;
				//allowedTimeForLevel = 20;
		}
		
		if (complexity == 17){
				creat = new Kruskal();// set strategy for maze creation
				size=15;
				maxVel = 1.75f;
				//allowedTimeForLevel = 20;
		}
	
		if (complexity == 18){
				creat = new Kruskal();// set strategy for maze creation
				size=15;
				maxVel = 1.75f;
				//allowedTimeForLevel = 20;
		}
		if (complexity == 19) {
			
			fill(0);
			stroke(20);
			text("Thank you for playing", 250,250);
			
		}
			
			cellSize = 450 / size;
			p = new Player(this, cellSize);// initialize player
			
			p.setMaxVelocity(maxVel);
			maze = new Maze(size,size, this, creat, cellSize); // initialize new maze grid
			
			startingIndex1 = 0;
			startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// set end point -> goal
			endIndex1 = size - 1;
			endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// create maze with initialized criteria
			maze.creator.createMaze(maze.mazeFields[startingIndex1][startingIndex2],
					maze.mazeFields[endIndex1][endIndex2]);
				
			// set startingPosition of player
				p.setStartPosition(startingIndex1 * maze.cellSize + maze.cellSize / 3 + 2,
						startingIndex2 * maze.cellSize + maze.cellSize / 2);

			
			}

		// game version all HuntAndKill
		public void initializeAllHuntAndKill(int complexity) {
			goalReached = false;

			float maxVel = 2.0f;
			// set time to zero
			time = new ControlTimer();
			
			
			if (complexity == 1){
				creat = new HuntAndKill();// set strategy for maze creation
				size=5;
				maxVel = 2.5f;
				//allowedTimeForLevel = 20;
				}
		
			if (complexity == 2){
				creat = new HuntAndKill();// set strategy for maze creation
				size=5;
				maxVel = 2.5f;
				//allowedTimeForLevel = 20;
				}
			if (complexity == 3){
				maxVel = 2.5f;
				creat = new HuntAndKill();// set strategy for maze creation
				size=5;
				//allowedTimeForLevel = 20;
				}
				
			if (complexity == 4){
					creat = new HuntAndKill();// set strategy for maze creation
					size=7;
					maxVel = 2.2f;
					//allowedTimeForLevel = 20;
				}
			if (complexity == 5){
				maxVel = 2.2f;
				creat = new HuntAndKill();// set strategy for maze creation
				size=7;
				//allowedTimeForLevel = 20;
				}
				
			if (complexity == 6){
					creat = new HuntAndKill();// set strategy for maze creation
					size=7;
					maxVel = 2.2f;
					//allowedTimeForLevel = 20;
				}
			if (complexity == 7){
					creat = new HuntAndKill();// set strategy for maze creation
					size=9;
					maxVel = 2f;
					//allowedTimeForLevel = 20;
				}
		
			if (complexity == 8){
					creat = new HuntAndKill();// set strategy for maze creation
					size=9;
					maxVel = 2f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 9){
					creat = new HuntAndKill();// set strategy for maze creation
					size=9;
					maxVel = 2f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 10){
					creat = new HuntAndKill();// set strategy for maze creation
					size=11;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 11){
					creat = new HuntAndKill();// set strategy for maze creation
					size=11;
					maxVel = 1.95f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 12){
					creat = new HuntAndKill();// set strategy for maze creation
					size=11;
					maxVel = 1.95f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 13){
					creat = new HuntAndKill();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
				}
	
			if (complexity == 14){
					creat = new HuntAndKill();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 15){	
					creat = new HuntAndKill();// set strategy for maze creation
					size=13;
					maxVel = 1.85f;
					//allowedTimeForLevel = 20;
				}
		
			if (complexity == 16){
					creat = new HuntAndKill();// set strategy for maze creation
					size=15;
					maxVel = 1.75f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 17){
					creat = new HuntAndKill();// set strategy for maze creation
					size=15;
					maxVel = 1.75f;
					//allowedTimeForLevel = 20;
				}
		
			if (complexity == 18){
					creat = new HuntAndKill();// set strategy for maze creation
					size=15;
					maxVel = 1.75f;
					//allowedTimeForLevel = 20;
				}
			
			if (complexity == 19) {
				
				fill(0);
				stroke(20);
				text("Thank you for playing", 250,250);
				}
			
			
			cellSize = 450 / size;
			p = new Player(this, cellSize);// initialize player
			
			p.setMaxVelocity(maxVel);
			maze = new Maze(size,size, this, creat, cellSize); // initialize new maze grid
			
			startingIndex1 = 0;
			startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// set end point -> goal
			endIndex1 = size - 1;
			endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.height);

			// create maze with initialized criteria
			maze.creator.createMaze(maze.mazeFields[startingIndex1][startingIndex2],
					maze.mazeFields[endIndex1][endIndex2]);
				
			// set startingPosition of player
				p.setStartPosition(startingIndex1 * maze.cellSize + maze.cellSize / 3 +2,
						startingIndex2 * maze.cellSize + maze.cellSize / 2);
				
			}
}

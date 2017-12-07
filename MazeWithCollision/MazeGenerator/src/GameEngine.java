import processing.core.*;
import java.util.concurrent.ThreadLocalRandom;
import controlP5.*;

public class GameEngine extends PApplet {

	boolean goalReached = false; // goal of the current level reached

	private int size = 5; // size of maze in cells
	private int width = 1200;
	private int height = 900;
	private int gameSize = 890;
	
	
	private int cellSize = 450 / size; // size of one cell

	private MazeCreator creat = new RecursiveBacktracker(); // chosen maze creation
													// strategy

	private Player p = new Player(this, cellSize); // initialize player

	private Maze maze = new Maze(size, size, this, creat, cellSize); // initialize new
																// maze

	private int allowedTimeForLevel = 20;
	
	private ControlP5 cp5; // library for buttons and timer

	private Bang startButton; // button to start game
	private boolean started = false; // has game been started

	private Bang continueButton; // button to continue game
	private Bang pauseButton; // button to pause game
	private boolean paused = false; // is game paused

	private int timeOnPause = 0; // how long was game paused

	private ControlTimer time = new ControlTimer();

	// set starting cell for maze creation
	private int startingIndex1 = 0;
	private int startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.getHeight());;

	// set end point -> goal
	private int endIndex1 = size - 1;
	private int endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.getHeight());

	private int complexityClass = 12;
	
	// create a new maze and set player to its start
	public void initializeNewMaze(int complexity) {
		goalReached = false;
		// set time to zero
		time = new ControlTimer();
		
		
		if (complexity == 1){
			creat = new Kruskal();// set strategy for maze creation
			size=5;
			allowedTimeForLevel = 10;
		}
		
		
		if (complexity == 2){
			creat = new Kruskal();// set strategy for maze creation
			size=7;
			allowedTimeForLevel = 17;
		}
		
		if (complexity == 3){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size = 5;
			allowedTimeForLevel = 9;
		}
		
		if (complexity == 4){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size= 9;
			allowedTimeForLevel = 16;
		}
		if (complexity == 5){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=11;
			allowedTimeForLevel = 25;
		}
		
		if (complexity == 6){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=13;
			allowedTimeForLevel = 27;
		}
	
		if (complexity == 7){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size = 7;
			allowedTimeForLevel = 15;
		}
		if (complexity == 8){
			creat = new HuntAndKill();// set strategy for maze creation
			size=7;
			allowedTimeForLevel = 15;
		}
		
		if (complexity == 9){
			creat = new Kruskal();// set strategy for maze creation
			size = 9;
			allowedTimeForLevel = 20;
		}
		
		if (complexity == 10){
			creat = new Kruskal();// set strategy for maze creation
			size= 11;
			allowedTimeForLevel = 23;
		}
		if (complexity == 11){
			creat = new RecursiveBacktracker();// set strategy for maze creation
			size=15;
			allowedTimeForLevel = 33;
		}
		
		if (complexity == 12){
			creat = new HuntAndKill();// set strategy for maze creation
			size=5;
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
			size = 11;
			allowedTimeForLevel = 39;
		}
		
		if (complexity == 16){
			creat = new HuntAndKill();// set strategy for maze creation
			size = 9;
			allowedTimeForLevel = 36;
		}
		
		if (complexity == 17){
			creat = new HuntAndKill();// set strategy for maze creation
			size=13;
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
		
		
		cellSize = gameSize / size;
		p = new Player(this, cellSize);// initialize player
		
		maze = new Maze(size,size, this, creat, cellSize); // initialize new maze grid
		
		startingIndex1 = 0;
		startingIndex2 = ThreadLocalRandom.current().nextInt(0, maze.getHeight());
		// set end point -> goal
		endIndex1 = size - 1;
		endIndex2 = ThreadLocalRandom.current().nextInt(0, maze.getHeight());
		// create maze with initialized criteria
		maze.creator.createMaze(maze.mazeFields[startingIndex1][startingIndex2],
				maze.mazeFields[endIndex1][endIndex2]);
			
		// set startingPosition of player
			p.setStartPosition(startingIndex1 * maze.getCellSize() + maze.getCellSize() / 3 + 2,
					startingIndex2 * maze.getCellSize() + maze.getCellSize() / 2);
		
		} 


	public static void main(String[] args) {
		PApplet.main("GameEngine");
	}

	// set size of output window, P3D is used for enabling lightening effects
	public void settings() {
		size(width, height, P3D);

	}

	// set up buttons and place them on their initial position at the screen
	public void setup() {
		background(0);
		cp5 = new ControlP5(this);
		startButton = new Bang(cp5, "Start");
		startButton.setPosition(width/2-64, height/2-32).setSize(128, 64);
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
		if (key == 'd' || key == 'D' || key == RIGHT) {
			p.right = true;
		}
		if (key == 's' || key == 'S'|| key == DOWN) {
			p.down = true;
		}
		if (key == 'a' || key == 'A'|| key == LEFT) {
			p.left = true;
		}
		if (key == 'w' || key == 'W' || key == RIGHT) {
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

		
		// if paused, show pause screen
		if (paused) {
			time = new ControlTimer();

			fill(0);
			rect(0, 0, gameSize, gameSize);
			fill(255,0,0);
			textSize(76);
			text("PAUSED", gameSize/2-116,gameSize/2-150);
			continueButton.setPosition(gameSize/2-75, gameSize/2-80).setSize(150, 80);

		}

		else {
			
			// when game is started
			if (started) {
				
				fill(255);
				rect(gameSize,0,gameSize-width,height);
				

				cp5.remove("Start"); // remove start button

				// if goal is not reached yet
				if (!(goalReached)) {
					fill(255);
					rect(gameSize,0,300,height);
					

					
					fill(255, 255, 255);
					
					textSize(20);
					text(complexityClass, 500,500);
					
					// show counting time in minutes and seconds
					textSize(32);
					fill(0);
					
					

					
					
					int levelTimer = Math.round((time.time() / 1000) % 60 - timeOnPause);
					int maxBarTimer = 500;
					int barTimer = maxBarTimer/allowedTimeForLevel;
					int timer = barTimer*levelTimer;

					if (levelTimer < allowedTimeForLevel)
					{
					text(allowedTimeForLevel-levelTimer,950,350);
					fill(0,255,0);
					rect(1042,100+barTimer+timer,64, maxBarTimer-(timer));
					}
					else
					{
						text("Try again!",1000,350);
					}
				

					// view pause button
					pauseButton.setPosition(1000, 700).setSize(150, 80);
					
					
					
					spotLight(255.0f, 255.0f, 255.0f, // color of the spotlight
							// in RGB
					1075, 740, 1000, // position of
					0, 0, -1, // direction in which the light point
					PI / 2, // angle of the light
					400); // concentration of the light
					


					// spotlight following player
					spotLight(255.0f, 255.0f, 255.0f, // color of the spotlight
														// in RGB
							p.getPositionX(), p.getPositionY(), 1000, // position of
																		// spotlight
																		// (follows
																		// player
																		// position)
							0, 0, -1, // direction in which the light point
							PI / 2, // angle of the light
							300); // concentration of the light

					// display maze
					

					fill(255, 255, 255);
					stroke(255, 255, 255);
					maze.printMaze();

					fill(0);
					rect((endIndex1+1)*cellSize,endIndex2*cellSize,4,cellSize);
					
					// print starting point
					//fill(255, 0, 0);
					
					triangle(startingIndex1, // edge 1
							startingIndex2 * cellSize, startingIndex1, // edge 2
							startingIndex2 * cellSize + cellSize - 2, startingIndex1 + cellSize - 5, // edge
																										// 3
							startingIndex2 * cellSize + cellSize / 2);
					fill(255);
					textSize(cellSize/3);
					text("Start", startingIndex1+cellSize/8,startingIndex2 * cellSize+cellSize/2);

					// print end point
					fill(0, 255, 0);
					triangle(endIndex1 * cellSize + 4, endIndex2 * cellSize, endIndex1 * cellSize + 4,
							endIndex2 * cellSize + cellSize - 2,

							endIndex1 * cellSize + cellSize - 5, endIndex2 * cellSize + cellSize / 2);
					textSize(cellSize/3);
					fill(255);
					text("Exit", endIndex1*cellSize+cellSize/8,endIndex2 * cellSize+cellSize/2);
					textSize(12);//Placeholder

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
					rect(gameSize,0,20,height);
					
					
				}

				// if goal is reached, initialize new maze
				else {
					
					int neededTime = Math.round((time.time() / 1000)); // time player needed
																		// to finish level
					
					System.out.println("Completed level: " + complexityClass + " in " + neededTime + " seconds.");
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
}
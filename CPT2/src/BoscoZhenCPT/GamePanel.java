package BoscoZhenCPT;

import java.awt.*;
import javax.swing.*;
import entity.Player;
import tile.tileManager;

/*
 * Bosco Zhen
 * Purpose: Main panel used to hold global variables for different panels and classes. Contains runnable and is what starts the game 
 */

public class GamePanel extends JPanel implements Runnable{
	
	//setting up screen settings for frame/window
	final int originalTileSize = 16; //16 pixels by 16 tile, this sets the default size of characters or objects in the game
	final int scale = 3;//16 x 3, makes it look like 48 by 48 on screen but is still 16x16 pixels
	public final int tileSize = originalTileSize * scale; // 16 x 3 = 48
	public final int maxScreenCol = 20; // amount of tiles in width   _
	public final int maxScreenRow = 16; // amount of tiles in height |
	public final int screenWidth = tileSize*maxScreenCol; // total size of width with size of tile multiply size of width (960 Pixels)
	public final int screenHeight = tileSize*maxScreenRow; // total size of height with size of tile multiply size of height (768 Pixels)
	
	//global variables for panel classes/menus
	public static int[] inventory = new int [5];//inventory for player (0 = carrot seeds, 1 = strawberry seeds, 2 = carrots, 3= strawberries, 4 = coins)
	public static boolean strawberry = false; // for player check if they want to plant strawberry 
	public static boolean carrot = false; // for player check if they want to plant carrot 
	public static boolean move = true;//makes player stop moving when in shop
	
	// FPS	
	int FPS = 60; //we need to have 60 fps or else when we press a key with movement, the object updates the screen to fast and goes out of the screen
	
	tileManager tileM = new tileManager(this);//import tileManager into game panel
	KeyHandler keyH = new KeyHandler();//get key input or release from class KeyHandler
	Thread gameThread; //allows game to repeat multiple actions at once 
	Player player = new Player (this,keyH);// call player class
	HomePanel homePanel = new HomePanel (this);// calls homePanel class
	public CollisionChecker cChecker = new CollisionChecker(this);//calls CollisionChecker constructor 
	
	/* Purpose: constructor for game
	 */
	public GamePanel () {
		
		this.setPreferredSize (new Dimension (screenWidth, screenHeight));// set window size
		this.setBackground(Color.BLUE);//set background colour
		this.setDoubleBuffered(true);// improves game rendering 
		this.addKeyListener(keyH);//input from KeyHandler
		this.setFocusable(true);//allows game panel to receive key inputs
		Main.centerPanel.add(new HomePanel(this), "Home"); //adds home panel
		Main.centerPanel.add(new ShopPanel(), "Shop"); // adds shop panel
		Main.centerPanel.add(new QuestPanel(this), "Quest");// adds quest panel
		gameThread = new Thread(this);//thread to allow game to run
		gameThread.start(); // calls run method
		
	}
	
	/* Purpose: called when we call thread, used to run the game 
	 */
	@Override
	public void run() {
		move = true;//allow player to move
		double drawInterval = 1000000000/FPS; // gives us 60 FPS, 0.01666 seconds
		double delta = 0;//check if interval time is reached
		long lastTime = System.nanoTime();//check last time
		long currentTime;// current time 
		long timer = 0;//timer for thread to check whether it is time to reset fps 
		int drawCount = 0;//counter for how many times the frames are reset
		
		while(gameThread != null) { //  allows game/commands to always be running
			
			currentTime = System.nanoTime();//check current time
			delta += (currentTime - lastTime)/ drawInterval;// subtract current from last to find how much time has passed,
			timer += (currentTime - lastTime);//timer for FPS check
			lastTime = currentTime;//set the last time as current time
			if (delta >= 1) {//checks if delta reachs the draw interval time and will allow code to update frame
				update ();//update frame
				repaint();//redraw tiles
				delta--;
				drawCount++;//add each time frame is reset
				
			}	
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount =0;//reset frame reset counter 
				timer = 0;//reset timer 
			}
			if(move == false) {//if movement is false
				player.speed = 0;//player cannot move
			}
            requestFocusInWindow();
		}
	}
	
	/* Purpose: to call update methods from classes
	 */
	public void update() {
		player.update();//calls player class to update player MOVEMENT
		
	}
	
	/*Purpose: to redraw things in JPanel
	 * pre: Graphics g
	 */
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
	    tileM.draw(g2);//calls draw method from tileManager class from tile package to redraw tiles
		player.draw(g2);//calls player class to draw redraw player 
		
		g2.dispose();//works without, using this as a garbage collector and save memory
	}
	
}

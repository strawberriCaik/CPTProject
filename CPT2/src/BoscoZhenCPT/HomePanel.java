package BoscoZhenCPT;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Bosco Zhen
 * Purpose: HomePanel to reset floor (plant crops or clear tiles) and show screen of sleeping for user  
 */

public class HomePanel extends BasePanel {
	GamePanel gp;// calls GamePanel class

	public BufferedImage sleeping; //buffered images for class
	public boolean first = false; //this is for the timer where it will first show sleeping picture then return to gamepanel
	
	/* pre: GamePanel gp
	 * purpose: constructor 
	 */
	public HomePanel(GamePanel gp) {
		super();
		GamePanel.move = false;//set movement of entity to false
		this.gp = gp;//bring gamepanel into HomePanel class
		getImage();//get images from folders into this class
		setFocusable(true);// focus on home panel so that you can do stuff on this panel
	}
	
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			sleeping = ImageIO.read(getClass().getResourceAsStream("/panels/sleeping.png"));
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	/* pre: Graphics g
	 * purpose: paintComponent method to allow images to be drawn in class 
	 */
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		drawZ(g2);//calls timer for game to show picture then swap back to game
	}
	
	/*
	 * pre: Graphics g2
	 * Purpose: to have a timer to show sleeping image and then swap back to game panel using card layout 
	 */
	public void drawZ(Graphics g2) {
		long lastTime = System.nanoTime();//check last time
		long currentTime;// current time 
		long timer = 0;//timer for adding to counter
		int counter = 0;//counter to allow picture and sleeping to be similar time
		
		while(gp.gameThread != null && counter < 2 ) { //  allows game/commands to always be running
			currentTime = System.nanoTime();//check current time
			timer += (currentTime - lastTime);//timer for FPS check
			lastTime = currentTime;//set the last time as current time
			if (timer >= 1000000000) {
				timer = 0;//reset timer
				counter++;//add to counter
			}
            requestFocusInWindow();
		}
		
		if (first == false) {//if first
			g2.drawImage(sleeping,0,0,getWidth(),getHeight(),null);//draw sleeping image
			first = true;// set first to true so it would do next statement next time it does the timer
			counter = 0;// reset counter
			checkField();//call check field method 
			repaint();// paints sleeping image 
		}
		else {
			counter = 0;//reset counter
			first = false;//reset first so that you home panel can allow you to sleep again
			GamePanel.move = true;//allow movement true for user
			Main.navigation.show(Main.centerPanel, "Game");//swap back to gamePanel after sleeping 
		}
	}

	/*
	 * purpose: to replace things on the field (seeds with grown plant, holes with clear ground)
	 */
	public void checkField() {
		//variable for checking which tiles have something that can be changed 
		int col = 0;//column variable
		int row = 0;//row variable 
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			while (col < gp.maxScreenCol) {
				if(gp.tileM.mapTileNum [col][row] == 15) {//check if tile is a strawberry seed
				gp.tileM.mapTileNum [col] [row] = 17;//replace with strawberry
				}
				if(gp.tileM.mapTileNum [col][row] == 16) {//check if tile is carrot seed
					gp.tileM.mapTileNum [col] [row] = 18;// replace with carrot
				}
				if(gp.tileM.mapTileNum [col][row] == 8) {//check if tile is carrot seed
					gp.tileM.mapTileNum [col] [row] = 0;// replace with carrot
				}
				col++;//next col
			}
			if (col == gp.maxScreenCol) {//if col hits 16, reset and move to next row
				col = 0;//reset col
				row ++;// next row
			}
		}//end of while 
	}// end of checkfield 
	
}

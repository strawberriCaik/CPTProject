package BoscoZhenCPT;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import BoscoZhenCPT.BasePanel.Button;

public class HomePanel extends BasePanel {
	GamePanel gp;

	public BufferedImage sleeping,Z;
	public boolean first = false;
	
	public HomePanel(GamePanel gp) {
		super();
		this.gp = gp;//bring gamepanel into HomePanel class
		getImage();//get images from folders into this class
		setFocusable(true);// focus on home panel so that you can do stuff on this panel
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			sleeping = ImageIO.read(getClass().getResourceAsStream("/tiles/sleeping.png"));
			Z = ImageIO.read(getClass().getResourceAsStream("/tiles/Z.png"));
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.drawImage(sleeping,0,0,getWidth(),getHeight(),null);
		drawZ(g2);
	}
	
	public void drawZ(Graphics g2) {
		long lastTime = System.nanoTime();//check last time
		long currentTime;// current time 
		long timer = 0;
		int counter = 0;
		
		while(gp.gameThread != null && counter < 2 ) { //  allows game/commands to always be running
			currentTime = System.nanoTime();//check current time
			timer += (currentTime - lastTime);//timer for FPS check
			lastTime = currentTime;//set the last time as current time
			if (timer >= 1000000000) {
				timer = 0;
				counter++;
			}
            requestFocusInWindow();
		}
		if (first == false) {
			g2.drawImage(sleeping,0,0,getWidth(),getHeight(),null);
			first = true;
			counter = 0;
			checkField();
			repaint();
		}
		else {
			System.out.println("checked");
			counter = 0;
			first = false;
			Main.navigation.show(Main.centerPanel, "Game");
		}
	}
	/*
	 * purpose: to replace things on the field (seeds with grown plant, holes with clear ground)
	 */
	public void checkField() {
		int col = 0;
		int row = 0;
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
		}
	}
	
}

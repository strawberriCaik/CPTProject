package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import BoscoZhenCPT.*;

public class Player extends Entity {
	GamePanel gp; //import GamePanel Class
	KeyHandler keyH;// import KeyHandler Class
	
	
	/* pre: GamePanel gp, KeyHandler keyH
	 *  Purpose: constructor
	 */
	public Player (GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		solidArea = new Rectangle();//creates invisible rectangle for collisions
		//initialize rectangle  
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		setDefaultValues();
		getPlayerImage();
		GamePanel.inventory [0] = 0;
		GamePanel.inventory [1] = 0;
		GamePanel.inventory [2] = 0;
		GamePanel.inventory [3] = 0;
		GamePanel.inventory [4] = 500;
		
	}
	/*
	 * purpose: set default values of player
	 */
	public void setDefaultValues () {
		worldX=100;
		worldY=100;
		speed = 3;
		direction = "down";
	}
	/*
	 * purpose: get images from res folder and put them into player class 
	 */
	public void getPlayerImage() {

		try {//scan images from res folder and then goes into player package and take image and create 
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	/* Purpose: to update character position 
	 * 
	 */
	public void update() {
		if (keyH.upP == true || keyH.downP == true || keyH.leftP == true || keyH.rightP == true) {//checked if key is pressed so that character stays still when they aren't moving
			if (keyH.upP == true) {//check if W key was pressed
				direction = "up";//make player image face up
			}
			if (keyH.downP == true) {//check if S key was pressed
				direction = "down";//make player image face down
			}
			if (keyH.leftP == true) {//check if A key was pressed
				direction = "left";//make player image face left
			}
			if (keyH.rightP == true) {//check if D key was pressed
				direction = "right";//make player image face right
			}
			//checking for tile collision
			collisionOn = false;//set collision false
			gp.cChecker.checkTile(this);// check if object has collided with a collision tile
			if (collisionOn == false && GamePanel.move == true) {//if false, player can move
				switch(direction) {
					case "up": 
						worldY  -= speed; // SUBTRACTS playerY direction by speed which is 4 so that the object will go up 4 pixels
						break;
					case "down":
						worldY  += speed; // ADDS playerY direction by speed which is 4 so that the object will go down 4 pixels
						break;
					case "left":
						worldX  -= speed; // SUBTRACTS playerX direction by speed which is 4 so that the object will go left up 4 pixels
						break;
					case "right":
						worldX  += speed; // ADDS playerX direction by speed which is 4 so that the object will go right 4 pixels
						break;
				}//end of switch
			}//end of collision check
			
			spriteCounter++;//this gets called 60 times per second, each frame called will increase by 1 
			if (spriteCounter >12) {// after 10 frames, player sprite will change 
				if(spriteNum == 1 ) {//change to 2 if its 1
					spriteNum =2;
				}
				else if(spriteNum ==2) {//change to 1 if its 2
					spriteNum = 1;
				}
				spriteCounter =0;// reset counter
			}
			
			
		}// end of if 
		
		else {//makes it that character stands still up instead of looking weird when it stops walking left or right
			spriteNum = 1;
		}// end of else 
		if (keyH.Tbreakable == true) {
			gp.cChecker.interactTile(this);
		}
		keyH.Tbreakable = false;
		if (keyH.Plant1 == true) {
			GamePanel.carrot = true;
			gp.cChecker.interactTile(this);
		}
		keyH.Plant1 = false;
		if (keyH.Plant2 == true) {
			GamePanel.strawberry = true;
			gp.cChecker.interactTile(this);
		}
		GamePanel.carrot = false;
		GamePanel.strawberry = false;
		keyH.Plant2 = false;
	}// end of method 
	
	/*Purpose: to redraw things in JPanel
	 * 
	 */
	public void draw (Graphics2D g2) {
		BufferedImage image  = null;//player image
		switch (direction) {// change player image direction based on key pressed
		case "up": //if up, set player looking up
			//character will look like its walking when changing how it moves when walking in this direction
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down"://if down, set player looking down
			//character will look like its walking when changing how it moves when walking in this direction
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left"://if left, set player looking left
			//character will look like its walking when changing how it moves when walking in this direction
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right"://if right, set player looking right
			//character will look like its walking when changing how it moves when walking in this direction
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image,worldX,worldY,gp.tileSize, gp.tileSize, null);//image observer = null
	}
}

package entity;

import java.awt.*;
import java.awt.image.*;

public class Entity {
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;//store and bring images into program
	public String direction;//player character direction of where they're facing
	
	public int spriteCounter = 0;//counter for sprite to change every 10 frames
	public int spriteNum = 1;//orientation of sprite 
	
	public Rectangle solidArea;//creates an invisible rectangle for collision for players
	public boolean collisionOn = false;
	
}

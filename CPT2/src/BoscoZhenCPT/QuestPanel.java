package BoscoZhenCPT;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import BoscoZhenCPT.BasePanel.Button;

public class QuestPanel extends BasePanel{
	GamePanel gp;
	
	Button back,inv0,inv1,inv2,inv3,inv4,finish1,finish2,finish3,finish4,finish5;

	public BufferedImage questM,quest1,quest2,quest3,quest4,quest5,itemsM,check;
	public boolean [] lock = new boolean [5];//set all locks to false 
	
	
	public QuestPanel (GamePanel gp) {
		 super();
		 this.gp = gp;
		 //init quest buttons 
		 finish1= new Button ("", 0,0,0,0);
		 finish2= new Button ("", 0,0,0,0);
		 finish3= new Button ("", 0,0,0,0);
		 finish4= new Button ("", 0,0,0,0);
		 finish5= new Button ("", 0,0,0,0);
		 
		 back = new Button ("Back", 100,650,100,100);
		 buttons = new ArrayList<Button>(Arrays.asList(new Button[]{back}));// button array list for shop menu
		 questButtons = new ArrayList<Button>(Arrays.asList(new Button[]{finish1,finish2,finish3,finish4,finish5}));// button array list for menu
		 
		 
		 
		 getImage();// calls get image method to gather images from folders
		 setFocusable(true);// focus on shop panel so that you can do stuff on this panel
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			questM = ImageIO.read(getClass().getResourceAsStream("/tiles/questM.png"));
			quest1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Quest1.png"));
			quest2 = ImageIO.read(getClass().getResourceAsStream("/tiles/Quest2.png"));
			quest3 = ImageIO.read(getClass().getResourceAsStream("/tiles/Quest3.png"));
			quest4 = ImageIO.read(getClass().getResourceAsStream("/tiles/Quest4.png"));
			quest5 = ImageIO.read(getClass().getResourceAsStream("/tiles/Quest5.png"));
			itemsM = ImageIO.read(getClass().getResourceAsStream("/tiles/itemsM.png"));
			check = ImageIO.read(getClass().getResourceAsStream("/tiles/check.png"));
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.drawImage(questM,100,20,760,600,null);
		g2.drawImage(quest1,120,100,360,150,null);
		g2.drawImage(quest2,120,260,360,150,null);
		g2.drawImage(quest3,120,420,360,150,null);
		g2.drawImage(quest4,480,100,360,150,null);
		g2.drawImage(quest5,480,260,360,150,null);
		
		//drawing items menu in quest panel
		g2.drawImage(itemsM,350,650,500,65,null);
		inv0 = new Button (""+GamePanel.inventory[0], 420,670,20,20);
		inv1 = new Button (""+GamePanel.inventory[1], 510,670,20,20);
		inv2 = new Button (""+GamePanel.inventory[2], 600,670,20,20);
		inv3 = new Button (""+GamePanel.inventory[3], 695,670,20,20);
		inv4 = new Button (""+GamePanel.inventory[4], 770,670,60,20);
		
		inv0.draw(g);
		inv1.draw(g);
		inv2.draw(g);
		inv3.draw(g);
		inv4.draw(g);
		
		generateButtons("Trade", g);// generate buttons
		generateChecks(g2);
		
		back.draw(g);
	}
	
	private void generateChecks(Graphics g2) {
		int x = 345;
		int y = 120;//starting y position
		int m = 0;
		
		for (int i = 0; i <lock.length; i++) {
			if (i > 2) {
				x = 705;
				m = i - 3;
			}
			
			if(lock[i] == true) {
				g2.drawImage(check,x,y+(m*160),30,45,null);
			}
			m++;
		}
	}
	
	/*
	 * pre: String message,Graphics g
	 * purpose: to generate buttons for buy and sell menu with different message on button 
	 */
	private void generateButtons (String message,Graphics g) {
		int x = 390;
		int y = 125;//starting y position
		int m = 0;
		
		for (int i = 0; i< questButtons.size();i++) {//for loop to print buy buttons 
			
			
			if (i > 2) {
				x = 750;
				m = i - 3;
			}
			
			questButtons.get(i).label = message;
			questButtons.get(i).x = x;
			questButtons.get(i).y = y+(m*165);
			questButtons.get(i).width = 80;
			questButtons.get(i).height = 30;
			questButtons.get(i).draw(g);// draw button from array
			m++;
			
			
		}
	}
	
	@Override public void mouseReleased(MouseEvent e) {
		if (activeButton == finish1) {
			if(GamePanel.inventory [4] >= 1500 && GamePanel.inventory [2] >= 200 && lock [0] == false) {
				GamePanel.inventory [4] -= 1500;
				GamePanel.inventory [2] -= 200;
				gp.player.speed = 6;
				lock [0] = true;
			}
			
		}
		if (activeButton == finish2) { 
			if(GamePanel.inventory [4] >= 3500 && GamePanel.inventory [3] >= 250 && lock [1] == false) {
				GamePanel.inventory [4] -= 3500;
				GamePanel.inventory [3] -= 250;
				gp.cChecker.harvest = 2;
				lock [1] = true;
			}
		}
		if (activeButton == finish3) {
			if(GamePanel.inventory [4] >= 7000 && lock [2] == false) {
				GamePanel.inventory [4] -= 6900;
				lock [2] = true;
			}
		}
		// FOR TESTING PURPOSES, QUEST 4 IS NEVER ON LOCK SO THAT USER CAN TEST IF ALL QUESTS WORK
		if (activeButton == finish4) {
			if(GamePanel.inventory [4] >= 1) {// && lock [3] == false
				GamePanel.inventory [4] +=9999;
				GamePanel.inventory [3] +=1000;
				GamePanel.inventory [2] +=1000;
				//lock [3] = true;
			}
		}
		
		if (activeButton == finish5) {
			if(GamePanel.inventory [4] >= 300 && lock [4] == false) {
				GamePanel.inventory [4] -=300;
				GamePanel.inventory [0] +=50;
				GamePanel.inventory [1] +=50;
				lock [4] = true;
			}
		}
		//needs to swap to game panel to allow the shop panel to refresh
        Main.navigation.show(Main.centerPanel, "Game");
        Main.navigation.show(Main.centerPanel, "Quest");
        
		if (activeButton == back) {
			Main.navigation.show(Main.centerPanel, "Game");
		}
	}
}

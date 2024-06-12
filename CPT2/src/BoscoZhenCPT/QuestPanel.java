package BoscoZhenCPT;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import BoscoZhenCPT.BasePanel.Button;

/*
 * Bosco Zhen
 * Purpose: to create a quest menu for user to trade items harvested for items 
 */

public class QuestPanel extends BasePanel{
	GamePanel gp;//call GamePanel class 
	Button back,inv0,inv1,inv2,inv3,inv4,finish1,finish2,finish3,finish4,finish5;//buttons 
	public BufferedImage questM,quest1,quest2,quest3,quest4,quest5,itemsM,check;//images
	public boolean [] lock = new boolean [5];//set all locks to false 
	
	public QuestPanel (GamePanel gp) {
		 super();
		 GamePanel.move = false;//set player movement to 0 
		 this.gp = gp;
		 //init quest buttons 
		 finish1= new Button ("", 0,0,0,0);//trade button for first quest 
		 finish2= new Button ("", 0,0,0,0);//trade button for second quest 
		 finish3= new Button ("", 0,0,0,0);//trade button for third quest 
		 finish4= new Button ("", 0,0,0,0);//trade button for fourth quest 
		 finish5= new Button ("", 0,0,0,0);//trade button for fifth quest 
		 
		 back = new Button ("Back", 100,650,100,100);//create back button so user can return to GamePanel
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
			questM = ImageIO.read(getClass().getResourceAsStream("/panels/questM.png"));
			quest1 = ImageIO.read(getClass().getResourceAsStream("/panels/Quest1.png"));
			quest2 = ImageIO.read(getClass().getResourceAsStream("/panels/Quest2.png"));
			quest3 = ImageIO.read(getClass().getResourceAsStream("/panels/Quest3.png"));
			quest4 = ImageIO.read(getClass().getResourceAsStream("/panels/Quest4.png"));
			quest5 = ImageIO.read(getClass().getResourceAsStream("/panels/Quest5.png"));
			itemsM = ImageIO.read(getClass().getResourceAsStream("/panels/itemsM.png"));
			check = ImageIO.read(getClass().getResourceAsStream("/panels/check.png"));
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	/*
	 * Pre: Graphics g
	 * purpose: to print out images in this class
	 */
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		//drawing quests onto the quest panel
		g2.drawImage(questM,100,20,760,600,null);//drawing quest menu 
		g2.drawImage(quest1,120,100,360,150,null);//drawing quest 1
		g2.drawImage(quest2,120,260,360,150,null);//drawing quest 2
		g2.drawImage(quest3,120,420,360,150,null);//drawing quest 3
		g2.drawImage(quest4,480,100,360,150,null);//drawing quest 4
		g2.drawImage(quest5,480,260,360,150,null);//drawing quest 5
		
		//drawing items menu in quest panel
		g2.drawImage(itemsM,350,650,500,65,null);//drawing inventory image
		inv0 = new Button (""+GamePanel.inventory[0], 420,670,20,20);//init inventory slot 1
		inv1 = new Button (""+GamePanel.inventory[1], 510,670,20,20);//init inventory slot 2
		inv2 = new Button (""+GamePanel.inventory[2], 590,670,40,20);//init inventory slot 3
		inv3 = new Button (""+GamePanel.inventory[3], 685,670,40,20);//init inventory slot 4
		inv4 = new Button (""+GamePanel.inventory[4], 770,670,60,20);//init inventory slot 5
		
		//drawing inventory buttons 
		inv0.draw(g);//drawing inventory slot 1
		inv1.draw(g);//drawing inventory slot 2
		inv2.draw(g);//drawing inventory slot 3
		inv3.draw(g);//drawing inventory slot 4
		inv4.draw(g);//drawing inventory slot 5
		
		generateButtons("Trade", g);// generate buttons
		generateChecks(g2);//creates check boxes for quests complete
		
		back.draw(g);//draws back button 
	}
	
	/*
	 * pre: Graphics g2
	 * purpose: to draw checks for trades completed so that you cannot do more than 1 trade
	 */
	private void generateChecks(Graphics g2) {
		int x = 345;//staring x position
		int y = 120;//starting y position
		int m = 0;//used to change x value and reset y 
		
		for (int i = 0; i <lock.length; i++) {//for loop to add checks
			if (i > 2) {//if checked 3 times for trades then move to next row 
				x = 705;//new x for next row
				m = i - 3;//new m for next row 
			}
			if(lock[i] == true) {//if statement to check if user has finished trade before
				g2.drawImage(check,x,y+(m*160),30,45,null);//draw check
			}
			m++;//add 1 to m 
		}
	}
	
	/*
	 * pre: String message,Graphics g
	 * purpose: to generate buttons for quest menu with a message on button 
	 */
	private void generateButtons (String message,Graphics g) {
		int x = 390;// starting x 
		int y = 125;//starting y position
		int m = 0;//used to change x value and reset y
		
		for (int i = 0; i< questButtons.size();i++) {//for loop to print buy buttons 
			if (i > 2) {
				x = 750;//set new x
				m = i - 3;//reset m
			}
			questButtons.get(i).label = message;//set button to certain message 
			questButtons.get(i).x = x;//set button x location
			questButtons.get(i).y = y+(m*165);//set button y location
			questButtons.get(i).width = 80;//set button width
			questButtons.get(i).height = 30;//set button height 
			questButtons.get(i).draw(g);// draw button from array
			m++;//add 1 to m 
		}
	}
	
	/*
	 * pre: MouseEvent e
	 * purpose: to check when mouse is released on a button so that an action can be performed 
	 */
	@Override public void mouseReleased(MouseEvent e) {
		if (activeButton == finish1) {//if button is the first trade button 
			if(GamePanel.inventory [4] >= 1500 && GamePanel.inventory [2] >= 200 && lock [0] == false) {//check conditions for trade 
				GamePanel.inventory [4] -= 1500;//lose 1500 coins 
				GamePanel.inventory [2] -= 200;//lose 200 carrots
				gp.player.speed = 6;//double player speed
				lock [0] = true;//lock so that user cannot do again
			}
			
		}
		if (activeButton == finish2) {//if button is the second trade button 
			if(GamePanel.inventory [4] >= 3500 && GamePanel.inventory [3] >= 250 && lock [1] == false) {//check conditions for trade 
				GamePanel.inventory [4] -= 3500;//lose 3500 coins
				GamePanel.inventory [3] -= 250;//lose 250 strawberries
				gp.cChecker.harvest = 2;//double harvest amount per plant
				lock [1] = true;//lock so that user cannot do again
			}
		}
		if (activeButton == finish3) {//if button is the third trade button
			if(GamePanel.inventory [4] >= 7000 && lock [2] == false) {//check conditions for trade 
				GamePanel.inventory [4] -= 6900;//lose 6900 coins
				lock [2] = true;//lock so that user cannot do again
			}
		}
		// FOR TESTING PURPOSES, QUEST 4 IS NEVER ON LOCK SO THAT USER CAN TEST IF ALL QUESTS WORK
		if (activeButton == finish4) {//if button is the fourth trade button
			if(GamePanel.inventory [4] >= 1) {// && lock [3] == false    //check conditions for trade 
				GamePanel.inventory [4] +=9999;//gain 9999 coins
				GamePanel.inventory [3] +=1000;//gain 1000 strawberries
				GamePanel.inventory [2] +=1000;//gain 1000 carrots
				//lock [3] = true; //lock so that user cannot do again
			}
		}
		
		if (activeButton == finish5) {//if button is the fifth trade button
			if(GamePanel.inventory [4] >= 300 && lock [4] == false) {//check conditions for trade 
				GamePanel.inventory [4] -=300;//lose 300 coins
				GamePanel.inventory [0] +=50;//gain 50 carrot seeds
				GamePanel.inventory [1] +=50;//gain 50 strawberry seeds
				lock [4] = true;//lock so that user cannot do again
			}
		}
		//needs to swap to game panel to allow the shop panel to refresh
        Main.navigation.show(Main.centerPanel, "Game");//swap to GamePanel
        Main.navigation.show(Main.centerPanel, "Quest");//swap back to QuestPanel
        
		if (activeButton == back) {//if back button is pressed
			GamePanel.move = true;//allow movement for player
			Main.navigation.show(Main.centerPanel, "Game");//swap to GamePanel
		}
	}//end of mouse released
}

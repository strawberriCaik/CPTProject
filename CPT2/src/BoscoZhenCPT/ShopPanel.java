package BoscoZhenCPT;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import BoscoZhenCPT.BasePanel.Button;
import tile.Tile;

/*
 * Bosco Zhen
 * Purpose: shop panel for user to purchase and sell items 
 */
public class ShopPanel extends BasePanel{

	JPanel pnl = new JPanel (); //JPanel for shop
	Button buy,sell,back, inv0,inv1,inv2,inv3,inv4,buyC,buyS,buyCS,buySS; //buttons 
	boolean isShop = true;//boolean to show buy or sell menu
	int [] buyMP = {-16,-18,-5,-6, 1};// buy menu prices (carrot, strawberry, carrot seed, and strawberry seed, num to add a value to an item
	int [] sellMP = {7,8,2,3,-1};// sell menu prices (carrot, strawberry, carrot seed, and strawberry seed, num to subtract a value to an item
	public BufferedImage buyM, sellM, questsM, itemsM;//images
		
	/*
	 * purpose: constructor 
	 */
	public ShopPanel() {
		 super();
		GamePanel.move = false;//stop player movement 
		// buttons for shop menu navigation 
		buy = new Button ("Buy", 100,50,200,200);//buy button
		sell = new Button ("Sell", 100,250,200,200);//sell button 
		back = new Button ("Back", 100,650,100,100);//back button
		
		//init shopButtons
		buyC = new Button ("", 0,0,0,0);//buy carrots
		buyS = new Button ("", 0,0,0,0);//buy strawberries
		buyCS = new Button ("", 0,0,0,0);//buy carrot seeds
		buySS = new Button ("", 0,0,0,0);//buy strawberry seeds 
		
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{buy,sell,back}));// button array list for shop menu
		shopButtons = new ArrayList<Button>(Arrays.asList(new Button[]{buyC,buyS,buyCS,buySS}));// button array list for menu
		getImage();// calls get image method to gather images from folders
		setFocusable(true);// focus on shop panel so that you can do stuff on this panel
		
			
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			buyM = ImageIO.read(getClass().getResourceAsStream("/panels/buyM.png"));
			itemsM = ImageIO.read(getClass().getResourceAsStream("/panels/itemsM.png"));
			sellM = ImageIO.read(getClass().getResource("/panels/sellM.png"));
			
		}catch(IOException e) {//catch 
			e.printStackTrace();// prints errors
		}
	}
	
	/*
	 * pre: Graphics g
	 * purpose: to print images and call generate buttons methods 
	 */
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		
	
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.drawImage(itemsM,350,550,500,65,null);
		
		
		if (isShop) {//if menu is for buy
			g2.drawImage(buyM, 350, 50, 500, 500, this);// drawing buyM image
			//buttons for buy menu
			generateButtons ("Buy",g);//generate buy buttons 
		} else {//if menu is for sell
			g2.drawImage(sellM, 350, 50, 500, 500, this);//drawing sellM image
			generateButtons ("Sell",g);//generate sell buttons 
		}
		
		
		//creating inventory text 
		inv0 = new Button (""+GamePanel.inventory[0], 420,570,20,20);//inventory text for carrot seeds 
		inv1 = new Button (""+GamePanel.inventory[1], 510,570,20,20);//inventory text for strawberry seeds 
		inv2 = new Button (""+GamePanel.inventory[2], 590,570,40,20);//inventory text for carrots
		inv3 = new Button (""+GamePanel.inventory[3], 685,570,40,20);//inventory text for strawberries
		inv4 = new Button (""+GamePanel.inventory[4], 770,570,60,20);//inventory text for money

		//drawing inventory text
		inv0.draw(g);//draw carrot seeds text
		inv1.draw(g);//draw strawberry seeds text
		inv2.draw(g);//draw carrot text
		inv3.draw(g);//draw strawberry text
		inv4.draw(g);//draw money text
		
		// drawing shop menu buttons 
		buy.draw(g);//draw buy button
		sell.draw(g);//draw sell button
		back.draw(g);//draw back button
		
		
	}
	
	/*
	 * pre: String message,Graphics g
	 * purpose: to generate buttons for buy and sell menu with different message on button 
	 */
	private void generateButtons (String message,Graphics g) {
		int y = 190;//starting y position
		for (int i = 0; i< shopButtons.size();i++) {//for loop to print buy buttons 
			int offset = 0;// add extra distance when there is gap in menu
			if (i > 1) {
				offset = 60;//add this much to y to create a gap 
			}
			
			shopButtons.get(i).label = message;//setting button name
			shopButtons.get(i).x = 770;//setting x location
			shopButtons.get(i).y = y+(i*60)+offset;//setting y location
			shopButtons.get(i).width = 60;//setting width size
			shopButtons.get(i).height = 30;//setting height size
			shopButtons.get(i).draw(g);// draw button from array
			
		}
	}
	
	
	/*
	 * pre: MouseEvent e
	 * purpose: to detect when mouse is released on a button to perform an action 
	 */
	
	@Override public void mouseReleased(MouseEvent e) {
	
		int [] tempPriceArray;// temp array for menu prices (for sell and buy)
		
		if (isShop) {//if shop then 
			tempPriceArray = buyMP;//take buy prices
		} else {
			tempPriceArray = sellMP;// take sell prices
		}
		
		//activation buttons 
		if(activeButton == buy) {
			isShop=true;//set shop is true so that buy menu prices are on
		}
		if(activeButton == sell) {
			isShop=false;//set shop is false so that sell menu prices are on
		}
		if (activeButton == buyC) {
			if ((GamePanel.inventory[4] + tempPriceArray[0] >= 0 && isShop) || (GamePanel.inventory[2] > 0 && !isShop)) {//check if user has enough 
				GamePanel.inventory[2]+=tempPriceArray[4];//add or sub carrot 
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[0];//add or sub money
			}
		}
		if (activeButton == buyS) {
			if ((GamePanel.inventory[4] + tempPriceArray[1] >= 0 && isShop) || (GamePanel.inventory[3] > 0 && !isShop)) {//check if user has enough
				GamePanel.inventory[3]+=tempPriceArray[4];//add or sub strawberry
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[1];//add or sub money
			}
		}
		if (activeButton == buyCS) {
			if ((GamePanel.inventory[4] + tempPriceArray[2] >= 0 && isShop) || (GamePanel.inventory[0] > 0 && !isShop)) {//check if user has enough
				GamePanel.inventory[0]+=tempPriceArray[4];//add or sub carrot seeds 
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[2];//add or sub money
			}
		}
		if (activeButton == buySS) {
			if ((GamePanel.inventory[4] + tempPriceArray[3] >= 0 && isShop) || (GamePanel.inventory[1] > 0 && !isShop)) {//check if user has enough
				GamePanel.inventory[1]+=tempPriceArray[4];// add or sub strawberry seeds 
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[3];// add or sub money
			}
		}
		
		//needs to swap to game panel to allow the shop panel to refresh
        Main.navigation.show(Main.centerPanel, "Game");//swap to game panel
        Main.navigation.show(Main.centerPanel, "Shop");//swap back to shop panel
        
        if(activeButton == back) {
        	GamePanel.move = true;//allow player movement 
			Main.navigation.show(Main.centerPanel, "Game");//swap to game panel
		}
	}


}

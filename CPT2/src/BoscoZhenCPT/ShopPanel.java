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


public class ShopPanel extends BasePanel{

	JPanel pnl = new JPanel (); //JPanel for shop
	Button buy,sell,quests,back, inv0,inv1,inv2,inv3,inv4,buyC,buyS,buyCS,buySS; 
	boolean isShop = true;
	int [] buyMP = {-16,-18,-5,-6, 1};// buy menu prices (carrot, strawberry, carrot seed, and strawberry seed, num to add a value to an item
	int [] sellMP = {7,8,2,3,-1};// sell menu prices (carrot, strawberry, carrot seed, and strawberry seed, num to subtract a value to an item
	
	JLabel imgLabel = new JLabel (new ImageIcon("/tiles/buyM.png"));
	
	public BufferedImage buyM, sellM, questsM, itemsM;
		
	
	public ShopPanel() {
		 super();
		// buttons for shop menu navigation 
		buy = new Button ("Buy", 100,50,200,200);
		sell = new Button ("Sell", 100,250,200,200);
		quests = new Button ("Quests", 100,450,200,200);
		back = new Button ("Back", 100,650,100,100);
		
		//init shopButtons
		buyC = new Button ("", 0,0,0,0);
		buyS = new Button ("", 0,0,0,0);
		buyCS = new Button ("", 0,0,0,0);
		buySS = new Button ("", 0,0,0,0);
		
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{buy,sell,quests,back}));// button array list for shop menu
		
		shopButtons = new ArrayList<Button>(Arrays.asList(new Button[]{buyC,buyS,buyCS,buySS}));// button array list for menu
		getImage();// calls get image method to gather images from folders
		setFocusable(true);// focus on shop panel so that you can do stuff on this panel
		
			
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			buyM = ImageIO.read(getClass().getResourceAsStream("/tiles/buyM.png"));
			itemsM = ImageIO.read(getClass().getResourceAsStream("/tiles/itemsM.png"));
			sellM = ImageIO.read(getClass().getResource("/tiles/sellM.png"));
			
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		
	
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
//		g2.drawImage(buyM,350,50,500,500,null);//print out image buyM onto the shop panel 
		g2.drawImage(itemsM,350,550,500,65,null);
		
		
		if (isShop) {
			g2.drawImage(buyM, 350, 50, 500, 500, this);// drawing buyM image
			//buttons for buy menu
			generateButtons ("Buy",g);
		} else {
		
			g2.drawImage(sellM, 350, 50, 500, 500, this);
			generateButtons ("Sell",g);
		}
		
		
		//drawing inventory text 
		inv0 = new Button (""+GamePanel.inventory[0], 420,570,20,20);
		inv1 = new Button (""+GamePanel.inventory[1], 510,570,20,20);
		inv2 = new Button (""+GamePanel.inventory[2], 600,570,20,20);
		inv3 = new Button (""+GamePanel.inventory[3], 695,570,20,20);
		inv4 = new Button (""+GamePanel.inventory[4], 770,570,60,20);

		inv0.draw(g);
		inv1.draw(g);
		inv2.draw(g);
		inv3.draw(g);
		inv4.draw(g);
		
		// drawing shop menu buttons 
		buy.draw(g);
		sell.draw(g);
		quests.draw(g);	
		back.draw(g);
		
		
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
				offset = 60;
			}
			
			//Button tempButton = new Button (message, 770,y+(i*60)+offset,60,30);// drawing button with message 
			shopButtons.get(i).label = message;
			shopButtons.get(i).x = 770;
			shopButtons.get(i).y = y+(i*60)+offset;
			shopButtons.get(i).width = 60;
			shopButtons.get(i).height = 30;
			
			//shopButtons.set(i, tempButton);// updating button in array
			shopButtons.get(i).draw(g);// draw button from array
			
		}
	}
	
	
	/*
	 * 
	 */
	
	@Override public void mouseReleased(MouseEvent e) {
	
		int [] tempPriceArray;// temp array for menu prices (for sell and buy)
		if (isShop) {
			tempPriceArray = buyMP;//take buy prices
		} else {
			tempPriceArray = sellMP;// take sell prices
		}
		
		if(activeButton == buy) {
			isShop=true;
		
		}
		if(activeButton == sell) {
			isShop=false;
		}
		if(activeButton == quests) {
			
		}
		if (activeButton == buyC) {
			if ((GamePanel.inventory[4] + tempPriceArray[0] >= 0 && isShop) || (GamePanel.inventory[2] > 0 && !isShop)) {
				GamePanel.inventory[2]+=tempPriceArray[4];
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[0];
			}
		}
		if (activeButton == buyS) {
			if ((GamePanel.inventory[4] + tempPriceArray[1] >= 0 && isShop) || (GamePanel.inventory[3] > 0 && !isShop)) {
				GamePanel.inventory[3]+=tempPriceArray[4];
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[1];
			}
		}
		if (activeButton == buyCS) {
			if ((GamePanel.inventory[4] + tempPriceArray[2] >= 0 && isShop) || (GamePanel.inventory[0] > 0 && !isShop)) {
				GamePanel.inventory[0]+=tempPriceArray[4];
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[2];
			}
		}
		if (activeButton == buySS) {
			if ((GamePanel.inventory[4] + tempPriceArray[3] >= 0 && isShop) || (GamePanel.inventory[1] > 0 && !isShop)) {
				GamePanel.inventory[1]+=tempPriceArray[4];
				GamePanel.inventory[4] = GamePanel.inventory[4] + tempPriceArray[3];
			}
		}
		
		
        Main.navigation.show(Main.centerPanel, "Game");
        Main.navigation.show(Main.centerPanel, "Shop");
        
        if(activeButton == back) {
			Main.navigation.show(Main.centerPanel, "Game");
		}
	}


}

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
	Button buy,sell,quests,back, inv1; 
	
	boolean state = false;
	
	JLabel imgLabel = new JLabel (new ImageIcon("/tiles/buyM.png"));
	
	public BufferedImage buyM, sellM, questsM, itemsM, boyM;
		
	public static Image loadImage(String name, int width, int height) {
		return new ImageIcon(name).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); 
	}
	
	public ShopPanel() {
		 super();
	
		buy = new Button ("Buy", 100,50,200,200);
		sell = new Button ("Sell", 100,250,200,200);
		quests = new Button ("Quests", 100,450,200,200);
		back = new Button ("Back", 100,650,100,100);
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{buy,sell,quests,back}));
		getImage();// calls get image method to gather images from folders
		setFocusable(true);
		
		pnl.add(imgLabel);
		add(pnl);
		pnl.setVisible(true);
			
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			buyM = ImageIO.read(getClass().getResourceAsStream("/tiles/buyM.png"));
			itemsM = ImageIO.read(getClass().getResourceAsStream("/tiles/itemsM.png"));
			boyM = ImageIO.read(getClass().getResource("/player/boy_down_1.png"));
			
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	@Override public void paintComponent(Graphics g) {
//		g.setColor(BACKGROUND);
//		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

//		instruction_text.setVisible(false);
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
//		g2.drawImage(buyM,350,50,500,500,null);//print out image buyM onto the shop panel 
		g2.drawImage(itemsM,350,550,500,65,null);
		if (state) {
			g2.drawImage(buyM, 350, 50, 500, 500, this);
			// print out image buyM onto the shop panel
		} else {
			g2.drawImage(boyM, 350, 50, 500, 500, this);
			// print out image buyM onto theshop panel
		}
		
		inv1 = new Button (""+GamePanel.inventory[0], 420,570,20,20);
//		instruction_text = new JLabel("<html>"
//				   + GamePanel.inventory[0]+" ");
//		add(instruction_text);
//		instruction_text.setBounds(425, 480, 400, 200);	
		buy.draw(g);
		sell.draw(g);
		quests.draw(g);	
		back.draw(g);
		inv1.draw(g);
		
		
	}
	
	
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == buy) {
			GamePanel.inventory[0]++;
			state=true;
            Main.navigation.show(Main.centerPanel, "Game");
            Main.navigation.show(Main.centerPanel, "Shop");
        	//pnl.repaint();
//			remove(instruction_text);
		
		}
		if(activeButton == sell) {
			state=false;
            Main.navigation.show(Main.centerPanel, "Game");
            Main.navigation.show(Main.centerPanel, "Shop");
        	//pnl.repaint();
		}
		if(activeButton == quests) {
			
		}
		if(activeButton == back) {
			Main.navigation.show(Main.centerPanel, "Game");
		}
		
	}


}

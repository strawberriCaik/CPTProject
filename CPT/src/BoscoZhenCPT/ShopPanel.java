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
	Button buy,sell,quests,back; 
	JLabel instruction_text;
	
	JLabel name = new JLabel ( "Welcome to Joe's Italian Restaurant!" );
	
	JLabel imgLabel = new JLabel (new ImageIcon("/tiles/buyM.png"));
	
	public BufferedImage buyM, sellM, questsM, itemsM;
		
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
		
		pnl.add(name);
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
			
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	@Override public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		buy.draw(g);
		sell.draw(g);
		quests.draw(g);	
		back.draw(g);
		
		instruction_text = new JLabel("<html>"
				   + GamePanel.inventory[0]+" ");
				add(instruction_text);
		instruction_text.setBounds(425, 480, 400, 200);	
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		g2.drawImage(buyM,350,50,500,500,null);//print out image buyM onto the shop panel 
		g2.drawImage(itemsM,350,550,500,65,null);
		
	}
	
	
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == buy) {
			GamePanel.inventory[0]++;
			remove(instruction_text);
			repaint();
		}
		if(activeButton == sell) {
			
		}
		if(activeButton == quests) {
			
		}
		if(activeButton == back) {
			Main.navigation.show(Main.centerPanel, "Game");
		}
		
	}


}

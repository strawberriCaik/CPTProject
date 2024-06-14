package BoscoZhenCPT;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * Bosco Zhen
 * Purpose: BasePanel that allows other classes to inherit and gain attributes that allow all Panel type classes to be the same 
 */
public class MenuPanel extends BasePanel {
	Button start, instructions;//buttons 
	public BufferedImage Menu;//images 
	
	/*
	 * purpose: constructor 
	 */
	public MenuPanel() {
		super();
		start = new Button("Start", (Main.WINDOW_WIDTH - 80)/2, 200, 80, 30);//set start button location 
		instructions = new Button("Instructions", (Main.WINDOW_WIDTH - 160)/2, 240, 160, 30);//set instructions button location
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{start, instructions}));//create buttons array for start and instructions 
		getImage();//call getImage class to get scan images and get them for this class 
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			Menu = ImageIO.read(getClass().getResourceAsStream("/panels/menuS.png"));
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
		g2.drawImage(Menu,0,0,getWidth(),getHeight(),null);//print menu image into menuPanel
		start.draw(g);//draw button for start
		instructions.draw(g);//draw button for instructions
	}
	
	/*
	 * Pre: MouseEvent e
	 * purpose: to check what button the user release their mouse on and perform that action
	 */
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == start) {//if button pressed is start
			Main.centerPanel.add(new GamePanel(), "Game");//create GamePanel
			Main.navigation.show(Main.centerPanel, "Game");//swap to GamePanel
		}
		if(activeButton == instructions) {// if button pressed is instructions 
			Main.navigation.show(Main.centerPanel, "Instructions");//swap to instructions panel
		}
	}
	
}
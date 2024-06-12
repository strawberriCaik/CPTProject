package BoscoZhenCPT;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/*
 * Bosco Zhen
 * Purpose: Panel to display instructions 
 */

public class InstructionsPanel extends BasePanel {
	Button back1;//buttons 
	public BufferedImage IMenu;//images 
	
	public InstructionsPanel() {
		super();
		back1 = new Button("Back",10,700,60,40);
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{back1}));//create buttons array for start and instructions 
		getImage();//call getImage class to get scan images and get them for this class 
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			IMenu = ImageIO.read(getClass().getResourceAsStream("/panels/instructions.png"));
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
		g2.drawImage(IMenu,0,0,getWidth(),getHeight(),null);//print menu image into menuPanel
		back1.draw(g);//draw back button
	}
	
	/*
	 * Pre: MouseEvent e
	 * purpose: to check what button the user release their mouse on and perform that action
	 */
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == back1) {//if button pressed is start
			Main.navigation.show(Main.centerPanel, "Menu");//swap to GamePanel
		}
	}
	
}
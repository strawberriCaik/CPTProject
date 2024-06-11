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

public class MenuPanel extends BasePanel {
	Button start, instructions;
	public BufferedImage Menu;
	
	public MenuPanel() {
		super();
		start = new Button("Start", (Main.WINDOW_WIDTH - 80)/2, 200, 80, 30);
		instructions = new Button("Instructions", (Main.WINDOW_WIDTH - 160)/2, 240, 160, 30);
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{start, instructions}));
		getImage();
	}
	/*
	 * purpose: get images from res folder and put them into shopPanel class 
	 */
	public void getImage() {

		try {//scan images from res folder and then goes into player package and take image and create
			Menu = ImageIO.read(getClass().getResourceAsStream("/tiles/menuS.png"));
		}catch(IOException e) {//catch 
			e.printStackTrace();// handles errors
		}
	}
	
	@Override public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;// convert Graphics g into Graphics 2D
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.drawImage(Menu,0,0,getWidth(),getHeight(),null);
		start.draw(g);
		instructions.draw(g);
	}
	
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == start) {
			Main.centerPanel.add(new GamePanel(), "Game");
			Main.navigation.show(Main.centerPanel, "Game");
		}else
		if(activeButton == instructions) {
			Main.navigation.show(Main.centerPanel, "Instructions");
		}
	}
	
}
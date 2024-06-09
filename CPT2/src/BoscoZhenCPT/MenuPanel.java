package BoscoZhenCPT;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

public class MenuPanel extends BasePanel {
	Button start, instructions;
	public MenuPanel() {
		super();
		start = new Button("Start", (Main.WINDOW_WIDTH - 80)/2, 200, 80, 30);
		instructions = new Button("Instructions", (Main.WINDOW_WIDTH - 160)/2, 240, 160, 30);
		buttons = new ArrayList<Button>(Arrays.asList(new Button[]{start, instructions}));
	}
	@Override public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		start.draw(g);
		instructions.draw(g);
	}
	
	@Override public void mouseReleased(MouseEvent e) {
		if(activeButton == start) {
			/*
			JFrame frame = new JFrame();// frame for displaying game
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.setTitle("Stardew Island");//set the name of the window 
			
			GamePanel gamePanel = new GamePanel();// adding gamePanel to Main
			frame.add(gamePanel);// add gamePanel to frame
			
			frame.pack();//creates the window to be displayed in a size that fits all components in frame
			
			frame.setLocationRelativeTo(null);//allows the window for game to be displaced in the center of the screen
			frame.setVisible(true);//allows us to see frame
			gamePanel.startGameThread();//calls startGameThread Method in gamePanel
		*/
			Main.centerPanel.add(new GamePanel(), "Game");
			Main.navigation.show(Main.centerPanel, "Game");
		}else
		if(activeButton == instructions) {
			Main.navigation.show(Main.centerPanel, "Instructions");
		}
	}
	
}
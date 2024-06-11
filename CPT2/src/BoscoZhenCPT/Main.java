package BoscoZhenCPT;

import java.awt.*;
import javax.swing.*;

/*
 * Bosco Zhen
 * Purpose: Main class for entire game, calls menu screen so that game can be displayed and can start 
 */
public class Main {
		public static final int WINDOW_WIDTH = 970, WINDOW_HEIGHT = 800;// size of frame to display to player
		public static final CardLayout navigation = new CardLayout();// setup card layout (this allows game to swap between classes)
		public static JFrame frame; //JFrame for menu panel
		public static JPanel centerPanel;//JPanel for showing a class from card layout 
		
	public static void main(String[] args) {
		JFrame frame = new JFrame();// frame for displaying game
		frame = new JFrame("Farmer's Island"); //Creating JFrame
		frame.setLayout(new BorderLayout()); //setting frame layout to border 
		
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);//setting frame size with width and height
        frame.setLocationRelativeTo(null); //position the window in the center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //shut down the program if you close the window
        
		centerPanel = new JPanel(navigation); 
        frame.add(centerPanel, BorderLayout.CENTER); //The window contains one panel and it has a card layout, which is a type of layout that switches between content panels i.e. cards.  
        centerPanel.add(new MenuPanel(), "Menu");//adding menu panel to card layout 
        centerPanel.add(new InstructionsPanel(), "Instructions");// adding instruction panel to card layout 
        
        navigation.show(centerPanel, "Menu");// swap to menu panel
        frame.setVisible(true); // frame is visible to user 
	}

}

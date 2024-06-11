package BoscoZhenCPT;

import java.awt.*;

import javax.swing.*;
public class Main {
		public static final int WINDOW_WIDTH = 970, WINDOW_HEIGHT = 800;
		public static final CardLayout navigation = new CardLayout();
		public static JFrame frame;
		public static JPanel centerPanel;
		
	public static void main(String[] args) {
		JFrame frame = new JFrame();// frame for displaying game
		frame = new JFrame("Farmer's Island");
		frame.setLayout(new BorderLayout()); 
		
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null); //position the window in the center of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //shut down the program if you close the window
        
		centerPanel = new JPanel(navigation); 
        frame.add(centerPanel, BorderLayout.CENTER); //The window contains one panel and it has a card layout, which is a type of layout that switches between content panels i.e. cards.  
        centerPanel.add(new MenuPanel(), "Menu");
        centerPanel.add(new InstructionsPanel(), "Instructions");
        
        navigation.show(centerPanel, "Menu");
        frame.setVisible(true); 
	}

}

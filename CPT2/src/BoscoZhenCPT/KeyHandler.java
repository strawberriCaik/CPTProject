package BoscoZhenCPT;

import java.awt.event.*;

/*
 * Bosco Zhen
 * Purpose: to take inputs from a keyboard 
 */
public class KeyHandler implements KeyListener {
	
	public boolean upP,downP,leftP,rightP,Tbreakable,Interact,Plant1,Plant2; // boolean to check if key is pressed, if pressed, method draw will change location 
	
	/* pre: KeyEvent e
	 * Purpose: useless but is required to have when using KeyListener
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/* pre: KeyEvent e
	 * Purpose: to change a boolean (direction) to true and allows method draw to update location 
	 */
	public void keyPressed(KeyEvent e) {
		 
		int code = e.getKeyCode();//returns number of key that was pressed
		
		if (code == KeyEvent.VK_W) {//checks if W key was pressed 
			upP = true;//set up direction true
		}
		if (code == KeyEvent.VK_S) {//checks if S key was pressed 
			downP = true;//set down direction true
		}
		if (code == KeyEvent.VK_D) {//checks if D key was pressed 
			rightP = true;//set right direction true
		}
		if (code == KeyEvent.VK_A) {//checks if A key was pressed 
			leftP = true;//set left direction true
		}
		if (code == KeyEvent.VK_B) {//check if B key is pressed
			Tbreakable = true;//set variable that allows user to break true
			Interact = true;//set variable that allows user to interact true
		}
		if (code == KeyEvent.VK_1) {//check if 1 key is pressed
			Plant1 = true;//set variable that allows user to plant carrot seeds true
			GamePanel.carrot = true;//make sure its a carrot
		}
		if (code == KeyEvent.VK_2) {//check if 2 key is pressed 
			GamePanel.strawberry = true;//make sure its a strawberry
			Plant2 = true;//set variable that alows user to plant strawberry seeds true
		}

		if (GamePanel.move == false) {//forces user to stop moving if in a shop panel 
			//set all directions of movement to false 
			upP = false;
			downP = false;
			rightP = false;
			leftP = false;
		}
	}

	@Override
	/* pre: KeyEvent e
	 * Purpose: to change boolean (direction) if key is no longer pressed to make panel to stop updating 
	 */
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();//returns number of key that was pressed
		
		if (code == KeyEvent.VK_W) {//checks if W key was released 
			upP = false;//set up direction false
		}
		if (code == KeyEvent.VK_S) {//checks if S key was released 
			downP = false;//set down direction false
		}
		if (code == KeyEvent.VK_D) {//checks if D key was released 
			rightP = false;//set right direction false
		}
		if (code == KeyEvent.VK_A) {//checks if A key was released 
			leftP = false;//set left direction false
		}
		if (code == KeyEvent.VK_B) {//checks if B key was released 
			Tbreakable = false;//set breakable to false
			Interact = false;//set interact to false
		}
	}

		
}

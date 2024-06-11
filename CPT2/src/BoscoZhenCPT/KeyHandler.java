package BoscoZhenCPT;

import java.awt.event.*;

public class KeyHandler implements KeyListener {
	
	public boolean upP,downP,leftP,rightP,Tbreakable,Interact,Plant1,Plant2; // boolean to check if key is pressed, if pressed, method draw will change location 
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
			upP = true;
		}
		if (code == KeyEvent.VK_S) {//checks if S key was pressed 
			downP = true;
		}
		if (code == KeyEvent.VK_D) {//checks if D key was pressed 
			rightP = true;
		}
		if (code == KeyEvent.VK_A) {//checks if A key was pressed 
			leftP = true;
		}
		if (code == KeyEvent.VK_B) {
			Tbreakable = true;
			Interact = true;
		}
		if (code == KeyEvent.VK_1) {
			Plant1 = true;
			GamePanel.carrot = true;
		}
		if (code == KeyEvent.VK_2) {
			GamePanel.strawberry = true;
			Plant2 = true;
		}

		if (GamePanel.move == false) {
			System.out.println("how");
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
			upP = false;
		}
		if (code == KeyEvent.VK_S) {//checks if S key was released 
			downP = false;
		}
		if (code == KeyEvent.VK_D) {//checks if D key was released 
			rightP = false;
		}
		if (code == KeyEvent.VK_A) {//checks if A key was released 
			leftP = false;
		}
		if (code == KeyEvent.VK_B) {
			Tbreakable = false;
			Interact = false;
		}
	}

		
}

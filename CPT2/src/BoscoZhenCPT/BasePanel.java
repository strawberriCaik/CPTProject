package BoscoZhenCPT;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BoscoZhenCPT.BasePanel.Button;

public class BasePanel extends JPanel implements MouseListener, MouseMotionListener{
	public static final Color MENU_GREEN = new Color(51, 222, 107);
	public static final Color BACKGROUND = new Color(248, 248, 248);
	public static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20); //Use the default sans serif font
	//Java requires 5 default fonts to be implemented: Serif, SansSerif, Monospaced, Dialog, and DialogInput
	ArrayList<Button> buttons;
	ArrayList<Button> questButtons;// button list array for quest finish buttons
	ArrayList<Button> shopButtons;// button list array for buy buttons 
	Button activeButton;
	
	public BasePanel() {
		super();
		setLayout(null);
		buttons = new ArrayList<Button>();
		shopButtons = new ArrayList<Button>();
		questButtons = new ArrayList<Button>();
		activeButton = null;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	class Button{
		String label;
		int x, y, width, height;
		
		public Button(String label, int x, int y, int width, int height) {
			this.label = label; this.x = x; this.y = y; this.width = width; this.height = height;
		}
		public boolean inRange(int mx, int my) {
			return mx >= x && mx < x + width && my >= y && my < y + height;
		}
		public void draw(Graphics g) {		    
		    g.setColor(MENU_GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.WHITE); //Draw Outline
			g.drawRect(x, y, width, height);
			
			g.setColor(Color.WHITE);
		    g.setFont(MENU_FONT);
		    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
		    //Make text look smooth instead of pixelated
		    
		    FontMetrics metrics = g.getFontMetrics(MENU_FONT); //Draw Centered Text
		    int textX = x + (width - metrics.stringWidth(label)) / 2;
		    int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
		    g.drawString(label, textX, textY);
		}
	}
	@Override public void mouseClicked(MouseEvent e) {} //Mouse clicked requires you to not move the mouse at all
	@Override public void mousePressed(MouseEvent e) { //This is a fancy mouse clicked which allows you to move the mouse within the button while clicking it
		int x = e.getX(), y = e.getY();
		for(Button button: buttons) { //Assuming the buttons don't overlap
			if(button.inRange(x, y)) {
				activeButton = button;
			}
		}
		for(Button button: shopButtons) { //Assuming the buttons don't overlap
			if(button.inRange(x, y)) {
				activeButton = button;
			}
		}
		for(Button button: questButtons) { //Assuming the buttons don't overlap
			if(button.inRange(x, y)) {
				activeButton = button;
			}
		}
	}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {
		activeButton = null;
	}

	@Override public void mouseDragged(MouseEvent e) { 
		if(activeButton != null) {
			int x = e.getX(), y = e.getY();
			if(!activeButton.inRange(x, y)) {
				activeButton = null;
			}
		}
	}
	@Override public void mouseMoved(MouseEvent e) {}
}


package tile;

import java.awt.image.*;

/*
 * Bosco Zhen
 * Purpose: to allow all tiles created to have similar properties or properties adjusted  
 */
public class Tile {
	
	public BufferedImage image;// buffer image for images uploaded
	public boolean collision = false; // boolean for tiles that have collision
	public boolean breakable = false; // boolean for tiles that can break
	public boolean interact = false; // boolean for tiles that can be interacted	
	public boolean plant = false; // boolean for tiles that can have seed on them
}

package tile;

import java.awt.image.*;

public class Tile {
	
	public BufferedImage image;
	public boolean collision = false; // boolean for tiles that have collision
	public boolean breakable = false; // boolean for tiles that can break
	public boolean interact = false; // boolean for tiles that can be interacted	
	public boolean plant = false; // boolean for tiles that can have seed on them
}

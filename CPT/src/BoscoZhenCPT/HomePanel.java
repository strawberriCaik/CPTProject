package BoscoZhenCPT;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import tile.Tile;
import tile.tileManager;

public class HomePanel extends BasePanel {
	GamePanel gp;
	
	public HomePanel(GamePanel gp) {
		this.gp = gp;//bring gamepanel into HomePanel class
	}
	public void checkField() {
		int col = 0;
		int row = 0;
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			while (col < gp.maxScreenCol) {
				if(gp.tileM.mapTileNum [col][row] == 15) {//check if tile is a strawberry seed
				gp.tileM.mapTileNum [col] [row] = 17;//replace with strawberry
				}
				if(gp.tileM.mapTileNum [col][row] == 16) {//check if tile is carrot seed
					gp.tileM.mapTileNum [col] [row] = 18;// replace with carrot
				}
				if(gp.tileM.mapTileNum [col][row] == 8) {//check if tile is carrot seed
					gp.tileM.mapTileNum [col] [row] = 0;// replace with carrot
				}
				col++;//next col
			}
			if (col == gp.maxScreenCol) {//if col hits 16, reset and move to next row
				col = 0;//reset col
				row ++;// next row
			}
		}
	}
	
}

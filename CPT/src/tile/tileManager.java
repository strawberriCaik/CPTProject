package tile;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import BoscoZhenCPT.GamePanel;// import gamepanel from different package
import BoscoZhenCPT.HomePanel;
import BoscoZhenCPT.Main;

public class tileManager {
	
	GamePanel gp; // call Gamepanel
	public Tile [] tile; // array for tiles (buffered image) 
	public int mapTileNum [] []; // map tiles
	/* pre: GamePanel gp
	 * purpose: constructor 
	 */
	public tileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile [20];// we will create an array of 10 for 10 different tiles 
		mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];//create array with tile sizes  
		getTileImage();//calls getTileImage method 
		Main.navigation.show(Main.centerPanel, "Game");
		loadMap("/maps/map01.txt");// call load map method with txt file location as parameter to load in map
		
	}// end of constructor
	
	/* 
	 *  purpose: to scan and get tile images from tile package from res folder 
	 */
	public void getTileImage () {
		
		try {
			
			tile [0] = new Tile(); //create tile 0 for ground
			tile [0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground.png")); // scan image to make new tile for ground 
			tile [0].breakable = true;
			
			tile [1] = new Tile(); //create tile 1 for grass
			tile [1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")); // scan image to make new tile for grass 
			
			tile [2] = new Tile(); //create tile 2 for water
			tile [2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")); // scan image to make new tile for water 
			tile [2].collision = true;//set collision for water
			
			tile [3] = new Tile(); //create tile 3 for door
			tile [3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/doorTop.png")); // scan image to make new tile for door 
			tile [3].collision = true;//set collision for door
			
			tile [4] = new Tile();
			tile [4].image = ImageIO.read(getClass().getResource("/tiles/house.png"));
			tile [4].collision = true;
			
			tile [5] = new Tile(); //create tile 3 for door
			tile [5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/doorBot.png")); // scan image to make new tile for door 
			tile [5].collision = true;//set collision for door
			
			tile [6] = new Tile();
			tile [6].image = ImageIO.read(getClass().getResource("/tiles/window.png"));
			tile [6].collision = true;
			
			tile [7] = new Tile();
			tile [7].image = ImageIO.read(getClass().getResource("/tiles/roof.png"));
			tile [7].collision = true;
			
			tile [8] = new Tile();
			tile [8].image = ImageIO.read(getClass().getResource("/tiles/tilled.png"));
			tile [8].plant = true;

			tile [9] = new Tile();
			tile [9].image = ImageIO.read(getClass().getResource("/tiles/shop1.png"));
			tile [9].collision = true;

			tile [10] = new Tile();
			tile [10].image = ImageIO.read(getClass().getResource("/tiles/shop2.png"));
			tile [10].collision = true;
			
			tile [11] = new Tile();
			tile [11].image = ImageIO.read(getClass().getResource("/tiles/shop3.png"));
			tile [11].collision = true;
			
			tile [12] = new Tile();
			tile [12].image = ImageIO.read(getClass().getResource("/tiles/box.png"));
			tile [12].collision = true;
			
			tile [13] = new Tile();
			tile [13].image = ImageIO.read(getClass().getResource("/tiles/carpet.png"));
			tile [13].interact = true;

			tile [14] = new Tile();
			tile [14].image = ImageIO.read(getClass().getResource("/tiles/carpet2.png"));
			tile [14].interact = true;
			
			tile [15] = new Tile();
			tile [15].image = ImageIO.read(getClass().getResource("/tiles/strawberry.png"));
			tile [15].plant = true;
			
			tile [16] = new Tile();
			tile [16].image = ImageIO.read(getClass().getResource("/tiles/carrots.png"));
			tile [16].plant = true;

			tile [17] = new Tile();
			tile [17].image = ImageIO.read(getClass().getResource("/tiles/strawberryG.png"));
			tile [17].breakable = true;
			
			tile [18] = new Tile();
			tile [18].image = ImageIO.read(getClass().getResource("/tiles/carrotG.png"));
			tile [18].breakable = true;
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}// end of getTileImage
	
	/* pre: String filePath
	 * purpose: to read txt file to load a map into gamePanel
	 */
	public void loadMap(String filePath) {
			try {
				
				InputStream is = getClass().getResourceAsStream(filePath);//imports text file from maps package from res folder
				BufferedReader br = new BufferedReader(new InputStreamReader (is));// read the content inside the text file
				
				int col = 0;
				int row = 0;
				
				while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
					String line = br.readLine();//reads line inside map.txt
					
					while (col < gp.maxScreenCol) {
						String number [] = line.split(" "); //Separate each space from num
						int num = Integer.parseInt(number[col]);//reads number array string to int
						mapTileNum [col] [row] = num;
						col++;
						
					}
					if (col == gp.maxScreenCol) {//if col hits 16, reset and move to next row
						col = 0;//reset col
						row ++;// next row
					}
				}
				br.close();
			}catch(Exception e){
				
			}// end of catch
	}
	
	/*
	 * purpose: to draw tiles into gamepanel
	 */
	public void draw(Graphics2D g2) {
		
		int col = 0; // 20 columns
		int row = 0; // 16 rows 
		int x = 0; // location of tile in x axis
		int y = 0; // location of tile in y axis
		
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {// print entire screen as grass
			int tileNum = mapTileNum[col][row];
			g2.drawImage( tile [tileNum].image, x,y,gp.tileSize, gp.tileSize,null);//print image at x,y with gp size tile (48 x 48 pixels)
			col ++;//increase col so we move to next col
			x += gp.tileSize;// increase x so that we print another tile after 48 pixels (length of 1 tile is 48 pixels)
			
			if (col == gp.maxScreenCol) {//if col reaches the end (16) reset col to 0 and move to next row
				col = 0;//reset col
				x = 0;//reset x 
				row++;// add 1 to row to move to next row
				y+= gp.tileSize;// add 48 to y to make y go into the correct place
			}
		}// end of while loop
		
	}// end of draw
}

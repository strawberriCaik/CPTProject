package BoscoZhenCPT;

import entity.Entity;

/*
 * Bosco Zhen
 * Purpose: detect entity collisions with tiles that don't allow movement or tiles that allow access (shops, quest, etc)
 */

public class CollisionChecker {
	GamePanel gp; //gamePanel variable that allows access to GamePanel Class
	int harvest = 1;//value/num of crops a user gets when harvesting from a plant
	
	/*pre: GamePanel gp
	 * Purpose: Constructor
	 */
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	/* pre: Entity e
	 * Purpose: check collision with all objects (player, npcs, etc)
	 */
	public void checkTile(Entity e) {
		
		//Variables for game to detect whether entity is interacting with a certain tile (finding entity's location of collision box in game)
		int entityLeftWorldX = e.worldX + e.solidArea.x;// finding box for left 
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;// finding box for right 
		int entityTopWorldY = e.worldY + e.solidArea.y;// finding box for top 
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;// finding box for bottom 
		
		//using variables from above, we will find which tile the entity is in 
		int entityLeftCol = entityLeftWorldX/gp.tileSize; //left tile 
		int entityRightCol = entityRightWorldX/gp.tileSize;// right tile
		int entityTopRow = entityTopWorldY/gp.tileSize;// top tile
		int entityBottomRow = entityBottomWorldY/gp.tileSize; // bottom tile
		
		//variables to determine what kind of tile entity is hitting
		int tileNum1 = 0;//first tile
		int tileNum2 = 0;//second tile 
		
		switch(e.direction) {
		case "up"://if direction is up
			entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "down":// if direction is down
			entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "left":// if direction is left
			entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "right"://if direction is right
			entityRightCol = (entityRightWorldX + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		}//end of switch
	}//end of checkTile
	
	/*
	 * pre: Entity e
	 * Purpose: to check if user is interacting with a tile that can be changed with a key pressed
	 */
	public void interactTile (Entity e) {//Variables for game to detect whether entity is interacting with a certain tile (finding entity's location of collision box in game)
		int entityLeftWorldX = e.worldX + e.solidArea.x;// finding box for left 
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;// finding box for right 
		int entityTopWorldY = e.worldY + e.solidArea.y;// finding box for top 
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;// finding box for bottom 
		
		//using variables from above, we will find which tile the entity is in 
		int entityLeftCol = entityLeftWorldX/gp.tileSize; //left tile 
		int entityRightCol = entityRightWorldX/gp.tileSize;// right tile
		int entityTopRow = entityTopWorldY/gp.tileSize;// top tile
		int entityBottomRow = entityBottomWorldY/gp.tileSize; // bottom tile
		
		//variables to determine what kind of tile entity is hitting
		int tileNum1 = 0;//first tile
		int EMid = 0;//finding what tile is the middle 
		
		switch(e.direction) {
		case "up"://if direction is up
			entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			
			 EMid = (entityLeftCol + entityRightCol)/2; //finding middle tile
			tileNum1 = gp.tileM.mapTileNum[EMid][entityTopRow];//finding tile with middle 
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 0) {//check if user pressed break button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.carrot == true && GamePanel.inventory [0] > 0) {//check if user pressed 1 button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 16;//replace tile with carrot seeds tile
				GamePanel.inventory[0]--;//plant a seed 
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.strawberry == true && GamePanel.inventory [1] > 0) {//check if user pressed 2 button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 15;//replace tile with strawberry seeds tile
				GamePanel.inventory[1]--;//plant a seed
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 13) {//check if user pressed break button 
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 14 || gp.tileM.mapTileNum [EMid][entityTopRow] == 9 || gp.tileM.mapTileNum [EMid][entityTopRow] == 10
					|| gp.tileM.mapTileNum [EMid][entityTopRow] == 11 || gp.tileM.mapTileNum [EMid][entityTopRow] == 12) {//check if user pressed break 
				Main.navigation.show(Main.centerPanel, "Shop");// show shop panel	
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace strawberry with hole
				GamePanel.inventory[3]+=harvest;//harvest plant
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace strawberry with hole
				GamePanel.inventory[2]+=harvest;//harvest plant
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 20|| gp.tileM.mapTileNum [EMid][entityTopRow] == 19) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Quest");// show home panel
			}
			break;
		case "down"://if direction is down
			entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player left 
			 EMid = (entityLeftCol + entityRightCol)/2; 
			tileNum1 = gp.tileM.mapTileNum[EMid][entityBottomRow];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 8 && GamePanel.carrot == true && GamePanel.inventory [0] > 0) {//check if user pressed 1 button 
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 16;//replace tile with carrot seeds tile
				GamePanel.inventory[0]--;//plant a seed
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 8 && GamePanel.strawberry == true && GamePanel.inventory [1] > 0) {//check if user pressed 2 button 
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 15;//replace tile with strawberry seeds tile
				GamePanel.inventory[1]--;//plant a seed
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 14) {//check if user pressed break button 
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel	
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace strawberry with hole
				GamePanel.inventory[3]+=harvest;//harvest plant
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace strawberry with hole
				GamePanel.inventory[2]+=harvest;//harvest plant
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityBottomRow] == 20|| gp.tileM.mapTileNum [EMid][entityBottomRow] == 19) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Quest");// show quest panel
			}
			break;
		case "left"://if direction is left
			entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			EMid = (entityTopRow + entityBottomRow)/2; 
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][EMid];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.carrot == true && GamePanel.inventory [0] > 0) {//check if user pressed 1 button
				gp.tileM.mapTileNum [entityLeftCol] [EMid]= 16;//replace tile with carrot seeds tile
				GamePanel.inventory[0]--;
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.strawberry == true && GamePanel.inventory [1] > 0) {//check if user pressed 2 button
				gp.tileM.mapTileNum [entityLeftCol] [EMid]= 15;//replace tile with strawberry seeds tile
				GamePanel.inventory[1]--;
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 14) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[3]+=harvest;
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[2]+=harvest;
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 20 || gp.tileM.mapTileNum [entityLeftCol][EMid] == 19) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Quest");// show quest panel
			}
			break;
		case "right"://if direction is right
			entityRightCol = (entityRightWorldX + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player left 
			EMid = (entityTopRow + entityBottomRow)/2; 
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][EMid];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 8 && GamePanel.carrot == true && GamePanel.inventory [0] > 0) {//check if user pressed 1 button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 16;//replace tile with carrot seeds tile
				GamePanel.inventory[0]--;//planting seed
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 8 && GamePanel.strawberry == true && GamePanel.inventory [1] > 0) {//check if user pressed 2 button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 15;//replace tile with strawberry seeds tile
				GamePanel.inventory[1]--;// plant a seed
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 14|| gp.tileM.mapTileNum [entityRightCol][EMid] == 9 || gp.tileM.mapTileNum [entityRightCol][EMid] == 10
					|| gp.tileM.mapTileNum [entityRightCol][EMid] == 11 || gp.tileM.mapTileNum [entityRightCol][EMid] == 12) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [entityRightCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[3]+=harvest;//harvest a plant
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityRightCol][EMid] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [entityRightCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[2]+=harvest;// harvest a plant
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 20 || gp.tileM.mapTileNum [entityLeftCol][EMid] == 19) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Quest");// show quest panel
			}
			break;
		}//end of switch
	}// end of method
	
}//end of class

package BoscoZhenCPT;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	/*
	 * Purpose: Constructor
	 */
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	/* 
	 * Purpose: check collision with all objects (player, npcs, etc)
	 */
	public void checkTile(Entity e) {
		
		int entityLeftWorldX = e.worldX + e.solidArea.x;
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityTopWorldY = e.worldY + e.solidArea.y;
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1 = 0, tileNum2 = 0;
		
		switch(e.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player right
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {//if left or right is next to a collision tile, player stops
				e.collisionOn = true;//set Collision on
			}
			break;
		case "right":
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
	public void interactTile (Entity e) {
		int entityLeftWorldX = e.worldX + e.solidArea.x;
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityTopWorldY = e.worldY + e.solidArea.y;
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1 = 0, tileNum2 = 0;
		int EMid = 0;
		
		switch(e.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player right
			
			 EMid = (entityLeftCol + entityRightCol)/2; 
			tileNum1 = gp.tileM.mapTileNum[EMid][entityTopRow];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 0) {//check if user pressed break button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.carrot == true) {//check if user pressed 1 button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 16;//replace tile with carrot seeds tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.strawberry == true) {//check if user pressed 2 button 
				gp.tileM.mapTileNum [EMid][entityTopRow] = 15;//replace tile with strawberry seeds tile
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 13) {//check if user pressed break button 
				//Main.navigation.show(Main.centerPanel, "Home");// show home panel
				gp.homePanel.checkField();
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 14) {//check if user pressed break 
				Main.navigation.show(Main.centerPanel, "Shop");// show shop panel	
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace strawberry with hole
				GamePanel.inventory[3]++;
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [EMid][entityTopRow] = 8;//replace strawberry with hole
				GamePanel.inventory[2]++;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//check player right
			 EMid = (entityLeftCol + entityRightCol)/2; 
			tileNum1 = gp.tileM.mapTileNum[EMid][entityBottomRow];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.carrot == true) {//check if user pressed 1 button 
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 16;//replace tile with carrot seeds tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 8 && GamePanel.strawberry == true) {//check if user pressed 2 button 
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 15;//replace tile with strawberry seeds tile
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 14) {//check if user pressed break button 
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel	
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace strawberry with hole
				GamePanel.inventory[3]++;
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [EMid][entityTopRow] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [EMid][entityBottomRow] = 8;//replace strawberry with hole
				GamePanel.inventory[2]++;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//check player right
			EMid = (entityTopRow + entityBottomRow)/2; 
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][EMid];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.carrot == true) {//check if user pressed 1 button
				gp.tileM.mapTileNum [entityLeftCol] [EMid]= 16;//replace tile with carrot seeds tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.strawberry == true) {//check if user pressed 2 button
				gp.tileM.mapTileNum [entityLeftCol] [EMid]= 15;//replace tile with strawberry seeds tile
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 14) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[3]++;
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [entityLeftCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[2]++;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + e.speed)/gp.tileSize; //finds what tile player is trying to step in
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//check player left 
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//check player right
			EMid = (entityTopRow + entityBottomRow)/2; 
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][EMid];
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 0) {//check if user pressed break button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 8;//replace tile with hole tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.carrot == true) {//check if user pressed 1 button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 16;//replace tile with carrot seeds tile
			}
			if (gp.tileM.tile[tileNum1].plant == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 8 && GamePanel.strawberry == true) {//check if user pressed 2 button
				gp.tileM.mapTileNum [entityRightCol] [EMid]= 15;//replace tile with strawberry seeds tile
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 13) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Home");// show home panel
			}
			if (gp.tileM.tile[tileNum1].interact == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 14) {//check if user pressed break button
				Main.navigation.show(Main.centerPanel, "Shop");// show home panel
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 17) {//check if user pressed break button on tile strawberry
				gp.tileM.mapTileNum [entityRightCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[3]++;
			}
			if (gp.tileM.tile[tileNum1].breakable == true && gp.tileM.mapTileNum [entityLeftCol][EMid] == 18) {//check if user pressed break button on tile carrot
				gp.tileM.mapTileNum [entityRightCol][EMid] = 8;//replace tile with hole tile
				GamePanel.inventory[2]++;
			}
			break;
		}//end of switch
	}// end of method
	
}//end of class

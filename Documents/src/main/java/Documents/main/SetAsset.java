package Documents.main;

//list of object class
import Documents.object.KeyCard;
import Documents.object.Door;
import Documents.object.Stair;

import Documents.entity.Ghost;
import Documents.entity.Demon;
import Documents.object.Bloodstain;
import Documents.object.Candy;


/**
 * The SetAsset class is responsible for initializing maze assets such as key cards and doors.
 * It sets the world positions for these assets to be displayed in the maze.
 */
public class SetAsset {
	/** The GamePanel instance associated with the set assets. */
	GamePanel gp;

	/**
     * Constructs a SetAsset object with the specified GamePanel instance.
     * @param gp The GamePanel instance to associate with the SetAsset object.
     */
	public SetAsset (GamePanel gp) {
		this.gp = gp;

	}

	/**
     * Initializes the key cards and door objects in the game.
     * Sets their world positions based on tile coordinates.
     */
	public void setObj() {
		gp.obj[0] = new KeyCard(gp);
		gp.obj[0].worldX = 38 * gp.tileSize;
		gp.obj[0].worldY = 27 * gp.tileSize;

		gp.obj[1] = new KeyCard(gp);
		gp.obj[1].worldX = 19 * gp.tileSize;
		gp.obj[1].worldY = 5 * gp.tileSize;

		gp.obj[2] = new KeyCard(gp);
		gp.obj[2].worldX = 37 * gp.tileSize;
		gp.obj[2].worldY = 12* gp.tileSize;

		gp.obj[3] = new KeyCard(gp);
		gp.obj[3].worldX = 17 * gp.tileSize;
		gp.obj[3].worldY = 28* gp.tileSize;

		gp.obj[4] = new KeyCard(gp);
		gp.obj[4].worldX = 10 * gp.tileSize;
		gp.obj[4].worldY = 42 * gp.tileSize;

		gp.obj[5] = new KeyCard(gp);
		gp.obj[5].worldX = 14 * gp.tileSize;
		gp.obj[5].worldY = 40 * gp.tileSize;

		gp.obj[6] = new KeyCard(gp);
		gp.obj[6].worldX = 43 * gp.tileSize;
		gp.obj[6].worldY = 31* gp.tileSize;

		gp.obj[7] = new KeyCard(gp);
		gp.obj[7].worldX = 5 * gp.tileSize;
		gp.obj[7].worldY = 43* gp.tileSize;


		//door
		gp.obj[8] = new Door(gp);
		gp.obj[8].worldX = 11 * gp.tileSize;
		gp.obj[8].worldY = 10* gp.tileSize;

		//start
		gp.obj[9] = new Stair(gp);
		gp.obj[9].worldX = 11 * gp.tileSize;
		gp.obj[9].worldY = 9* gp.tileSize;


	}


	public void setGhost() { 
		gp.monster[0] = new Ghost(gp);
		gp.monster[0].worldXPos = gp.tileSize*30 - gp.tileSize/2;
		gp.monster[0].worldYPos = gp.tileSize*30 - gp.tileSize/2;
		gp.monster[0].velocity = 1;
		gp.monster[1] = new Ghost(gp);
		gp.monster[1].worldXPos = gp.tileSize*10 - gp.tileSize/2;
		gp.monster[1].worldYPos = gp.tileSize*5 - gp.tileSize/2;
		gp.monster[1].velocity = 2;
	}
	
	public void setTraps(){
		gp.obj[10] = new Bloodstain(gp);
		gp.obj[10].worldX = 15*gp.tileSize;
		gp.obj[10].worldY = 23*gp.tileSize;
		gp.obj[11] = new Bloodstain(gp);
		gp.obj[11].worldX = 20*gp.tileSize;
		gp.obj[11].worldY = 13*gp.tileSize;
		gp.obj[12] = new Bloodstain(gp);
		gp.obj[12].worldX = 24*gp.tileSize;
		gp.obj[12].worldY = 16*gp.tileSize;
		gp.obj[13] = new Bloodstain(gp);
		gp.obj[13].worldX = 32*gp.tileSize;
		gp.obj[13].worldY = 6*gp.tileSize;
		gp.obj[14] = new Bloodstain(gp);
		gp.obj[14].worldX = 9*gp.tileSize;
		gp.obj[14].worldY = 34*gp.tileSize;
		gp.obj[15] = new Bloodstain(gp);
		gp.obj[15].worldX = 11*gp.tileSize;
		gp.obj[15].worldY = 35*gp.tileSize;
		gp.obj[16] = new Bloodstain(gp);
		gp.obj[16].worldX = 13*gp.tileSize;
		gp.obj[16].worldY = 34*gp.tileSize;
		gp.obj[17] = new Bloodstain(gp);
		gp.obj[17].worldX = 28*gp.tileSize;
		gp.obj[17].worldY = 40*gp.tileSize;
	}

	public void setReward(){
		gp.obj[18] = new Candy(gp);
		gp.obj[18].worldX = 31*gp.tileSize;
		gp.obj[18].worldY = 6*gp.tileSize;
		gp.obj[19] = new Candy(gp);
		gp.obj[19].worldX = 43*gp.tileSize;
		gp.obj[19].worldY = 31*gp.tileSize;
		gp.obj[20] = new Candy(gp);
		gp.obj[20].worldX = 11*gp.tileSize;
		gp.obj[20].worldY = 43*gp.tileSize;

	}

	public void setDemon() {
		gp.monster[2] = new Demon(gp);
		gp.monster[2].worldXPos = gp.tileSize*25 - gp.tileSize/2;
		gp.monster[2].worldYPos = gp.tileSize*18 - gp.tileSize/2;
	}
}
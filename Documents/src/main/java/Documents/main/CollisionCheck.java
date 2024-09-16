package Documents.main;

import Documents.entity.Entity;
import Documents.entity.Ghost;

/**
 * Class for checking collisions between entities and tiles/objects in the maze.
 */
public class CollisionCheck {
	/** The GamePanel instance associated with the collision check. */
	GamePanel gp;

	/**
     * Constructs a CollisionCheck object with the specified GamePanel.
     * @param gp The GamePanel instance.
     */
	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}

	/**
     * Checks for collisions between an enti
     * ty and tiles.
     * @param entity The entity to check collisions for.
     */
	public void tileCheck(Entity entity) {
		if(!(entity instanceof Ghost)){
			int left = entity.worldXPos + entity.detectionArea.x;
			int right = entity.worldXPos + entity.detectionArea.x + entity.detectionArea.width;
			int top = entity.worldYPos + entity.detectionArea.y;
			int bottom = entity.worldYPos + entity.detectionArea.y + entity.detectionArea.height;

			int leftCol = left/gp.tileSize;
			int rightCol = right/gp.tileSize;
			int topRow = top/gp.tileSize;
			int bottomRow = bottom/gp.tileSize;

			int tile1, tile2;

			switch(entity.direction) {
			case "up":
				topRow = (top - entity.velocity)/gp.tileSize;
				tile1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
				tile2 = gp.tileFactory.getTileMapNum(rightCol, topRow);
				if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
					entity.isCollision = true;
				}
				break;
			case "down":
				bottomRow = (bottom + entity.velocity)/gp.tileSize;
				tile1 = gp.tileFactory.getTileMapNum(leftCol, bottomRow);
				tile2 = gp.tileFactory.getTileMapNum(rightCol, bottomRow);
				if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
					entity.isCollision = true;
				}
				break;
			case "left":
				leftCol = (left - entity.velocity)/gp.tileSize;
				tile1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
				tile2 = gp.tileFactory.getTileMapNum(leftCol, bottomRow);
				if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
					entity.isCollision = true;
				}
				break;
			case "right":
				rightCol = (right + entity.velocity)/gp.tileSize;
				tile1 = gp.tileFactory.getTileMapNum(rightCol, topRow);
				tile2 = gp.tileFactory.getTileMapNum(rightCol, bottomRow);
				if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
					entity.isCollision = true;
				}
				break;
			} 
		}
	}

	/**
     * Checks for collisions between an entity and objects.
     * @param entity The entity to check collisions for.
     * @param mainChar Indicates whether the entity is the main character.
     * @return The index of the object with which the entity collides.
     */
	public int objCheck(Entity entity, boolean mainChar) {
		int index = 999;

		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//get Entity's detection area pos
				entity.detectionArea.x = entity.worldXPos + entity.detectionArea.x;
				entity.detectionArea.y = entity.worldYPos + entity.detectionArea.y;

				//get the object's detection area pos
				gp.obj[i].detectionArea.x = gp.obj[i].worldX + gp.obj[i].detectionArea.x;
				gp.obj[i].detectionArea.y = gp.obj[i].worldY + gp.obj[i].detectionArea.y;

				switch(entity.direction) {
				case "up":
					entity.detectionArea.y -= entity.velocity;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.detectionArea.y += entity.velocity;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.detectionArea.x -= entity.velocity;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.detectionArea.x += entity.velocity;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				}
				entity.detectionArea.x = entity.detectionDefaultX;
				entity.detectionArea.y = entity.detectionDefaultY;
				gp.obj[i].detectionArea.x = gp.obj[i].detectionDefaultX;
				gp.obj[i].detectionArea.y = gp.obj[i].detectionDefaultY;
			}
		}

		return index;
	}

	//momster
	public int entityCheck(Entity entity, Entity[] target){
		int index = 999;

		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				//get Entity's detection area pos
				entity.detectionArea.x = entity.worldXPos + entity.detectionArea.x;
				entity.detectionArea.y = entity.worldYPos + entity.detectionArea.y;

				//get the object's detection area pos
				target[i].detectionArea.x = target[i].worldXPos + target[i].detectionArea.x;
				target[i].detectionArea.y = target[i].worldYPos + target[i].detectionArea.y;

				switch(entity.direction) {
				case "up":
					entity.detectionArea.y -= entity.velocity;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
							entity.isCollision = true;
							index = i;
					}
					break;
				case "down":
					entity.detectionArea.y += entity.velocity;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				case "left":
					entity.detectionArea.x -= entity.velocity;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				case "right":
					entity.detectionArea.x += entity.velocity;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				}
				entity.detectionArea.x = entity.detectionDefaultX;
				entity.detectionArea.y = entity.detectionDefaultY;
				target[i].detectionArea.x = target[i].detectionDefaultX;
				target[i].detectionArea.y = target[i].detectionDefaultY;
			}
		}

		return index;
	}

	public boolean playerCheck(Entity entity){
		//get Entity's detection area pos
		entity.detectionArea.x = entity.worldXPos + entity.detectionArea.x;
		entity.detectionArea.y = entity.worldYPos + entity.detectionArea.y;

		//get the object's detection area pos
		gp.mainCharacter.detectionArea.x = gp.mainCharacter.worldXPos + gp.mainCharacter.detectionArea.x;
		gp.mainCharacter.detectionArea.y = gp.mainCharacter.worldYPos + gp.mainCharacter.detectionArea.y;

		switch(entity.direction) {
		case "up":
			entity.detectionArea.y -= entity.velocity;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
					entity.isCollision = true;
			}
			break;
		case "down":
			entity.detectionArea.y += entity.velocity;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		case "left":
			entity.detectionArea.x -= entity.velocity;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		case "right":
			entity.detectionArea.x += entity.velocity;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		}
		entity.detectionArea.x = entity.detectionDefaultX;
		entity.detectionArea.y = entity.detectionDefaultY;
		gp.mainCharacter.detectionArea.x = gp.mainCharacter.detectionDefaultX;
		gp.mainCharacter.detectionArea.y = gp.mainCharacter.detectionDefaultY;
		return entity.isCollision;
	}
}
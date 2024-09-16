package Documents.object;
import Documents.main.GamePanel;
import Documents.main.UtilityTools;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Factory class for creating and managing objects in the maze.
 */
public class ObjectFactory {
	/** The image associated with the object. */
    public BufferedImage img;
    
    /** The type of the object. */
    public String type;
    
    /** Indicates if the collision happened. */
    public boolean isCollision = false;
    
    /** The x-coordinate of the object in the world. */
    public int worldX;
    
    /** The y-coordinate of the object in the world. */
    public int worldY;
    
    /** The detection area of the object for collision detection. */
    public Rectangle detectionArea = new Rectangle(0, 0, 48, 48);
    
    /** The default x-coordinate for the detection area. */
    public int detectionDefaultX = 0;
    
    /** The default y-coordinate for the detection area. */
    public int detectionDefaultY = 0;

    // made public
    /** The . */
    public int screenX;

    /** The . */
    public int screenY;

	UtilityTools tools = new UtilityTools();

	/**
     * Draws the object inside the maze.
     * @param g2 The graphics context.
     * @param gp The GamePanel instance.
     */
	public void draw(Graphics2D g2, GamePanel gp) {
		screenX = worldX - gp.mainCharacter.worldXPos + gp.mainCharacter.screenX;
		screenY = worldY - gp.mainCharacter.worldYPos + gp.mainCharacter.screenY;

		//checking if the tile is within the boundary
		if(worldX + gp.tileSize > gp.mainCharacter.worldXPos - gp.mainCharacter.screenX &&
		   worldX - gp.tileSize < gp.mainCharacter.worldXPos + gp.mainCharacter.screenX &&
		   worldY + gp.tileSize > gp.mainCharacter.worldYPos - gp.mainCharacter.screenY &&
		   worldY - gp.tileSize < gp.mainCharacter.worldYPos + gp.mainCharacter.screenY) {
			g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
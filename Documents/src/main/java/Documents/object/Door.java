package Documents.object;

import Documents.main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a door object in the maze.
 */
public class Door extends ObjectFactory {
	GamePanel gp;

	/**
     * Constructs a Door object.
	 * @param gp The GamePanel instance associate with door object.
     */
	public Door(GamePanel gp) {
		this.gp = gp;
		this.type = "door";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			if (img == null) {
                throw new IOException("Failed to load door image");
            }
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		isCollision = true;
	}

}
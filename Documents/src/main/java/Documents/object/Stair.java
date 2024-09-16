package Documents.object;

import Documents.main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a stair object in the maze.
 */
public class Stair extends ObjectFactory {
	GamePanel gp;

	/**
     * Constructs a Stair object.
	 * @param gp The GamePanel instance associate with stair object.
     */
	public Stair(GamePanel gp) {
		this.gp = gp;
		this.type = "stair";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/stair.png"));
			if (img == null) {
                throw new IOException("Failed to load stair image");
            }
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		isCollision = true;
	}

}
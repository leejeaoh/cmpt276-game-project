package Documents.object;

import Documents.main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a keycard object in the maze.
 */
public class KeyCard extends ObjectFactory {
	GamePanel gp;

	/**
     * Constructs a KeyCard object.
	 * @param gp The GamePanel instance associate with key card object.
     */
	public KeyCard(GamePanel gp) {
		this.gp = gp;
		this.type = "keyCard";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/cardkey.png"));
			if (img == null) {
                throw new IOException("Failed to load key card image");
            }
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
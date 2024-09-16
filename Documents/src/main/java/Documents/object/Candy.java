package Documents.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Documents.main.GamePanel;

/**
 * Represents a candy object in the maze.
 */
public class Candy extends ObjectFactory{
    GamePanel gp;

	/**
     * Constructs a Candy object.
	 * @param gp The GamePanel instance associate with candy object.
     */
    public Candy(GamePanel gp) {
		this.gp = gp;
		this.type = "candy";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/reward.png"));
			if (img == null) {
                throw new IOException("Failed to load reward image");
            }
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

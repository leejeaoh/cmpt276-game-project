package Documents.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Documents.main.GamePanel;

/**
 * Represents a blood stain object in the maze.
 */
public class Bloodstain extends ObjectFactory{
    GamePanel gp;

	/**
     * Constructs a BloodStain object.
	 * @param gp The GamePanel instance associate with blood stain object.
     */
    public Bloodstain(GamePanel gp) {
		this.gp = gp;
		this.type = "bloodstain";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/trap.png"));
			if (img == null) {
                throw new IOException("Failed to load trap image");
            }
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

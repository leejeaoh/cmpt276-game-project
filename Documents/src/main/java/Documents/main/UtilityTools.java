package Documents.main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 * the class contains utility methods to aid in image manipulation and sprite animation
 *  */

public class UtilityTools {
	/**
     * Scales an image to the specified width and height.
     *
     * @param original is original BufferedImage to be scaled
     * @param width is target width for the scaled image
     * @param height is target height for the scaled image
     * @return is new BufferedImage object that is the result of scaling the original image to the specified width and height
     */
	public BufferedImage scaleImg(BufferedImage original, int width, int height) {
		BufferedImage scaledImg = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImg.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();

		return scaledImg;
	}

	/**
     * Deprecated. use {@link #updateSpriteAnimation(int, int, int)} for flexible frame reset settings
     * calculates and updates the sprite animation frame number based on the current count
     *
     * @param spriteCount is current frame count before update
     * @param spriteNum is current sprite number 
     * @return the updated sprite number after checking if a reset condition based on the frame count is met
     */
	@Deprecated
	public static int spriteCountCalculations(int spriteCount, int spriteNum) {
		spriteCount++;
		if (spriteCount > 10) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCount = 0; // Reset the sprite count
		}
		return spriteNum; // Return the updated sprite number
	}

	/**
     * Updates the sprite animation counters.
     * 
     * @param spriteCount the current frame count for the sprite
     * @param spriteNum the current sprite number
     * @param resetFrame the frame count at which to reset 
     * @return an array where the first element is the updated spriteCount and the second element is the updated spriteNum
     */
    public static int[] updateSpriteAnimation(int spriteCount, int spriteNum, int resetFrame) {
        spriteCount++;
        if (spriteCount > resetFrame) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCount = 0; // Reset the sprite count
        }
        return new int[] { spriteCount, spriteNum };
    }

}
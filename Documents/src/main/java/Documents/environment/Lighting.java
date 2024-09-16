package Documents.environment;

import Documents.main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * This class manages the lighting effects in the game by creating a dark filter
 * with a radial gradient to simulate light around the main character.
 */
public class Lighting {
    private GamePanel gamePanel;
    private BufferedImage darkFilter;

    /**
     * Constructs a Lighting object with a specified GamePanel and circle size for the light.
     *
     * @param gamePanel the game panel where the lighting effect is applied
     * @param circleSize the diameter of the circle of light
     */
    public Lighting(GamePanel gamePanel, int circleSize) {
        this.gamePanel = gamePanel;

        // Create buffered image
        darkFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = darkFilter.createGraphics();

        // Define the screen area
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gamePanel.screenWidth, gamePanel.screenHeight));

        // Center coordinates of the light circle
        int centerX = gamePanel.mainCharacter.screenX + gamePanel.tileSize / 2;
        int centerY = gamePanel.mainCharacter.screenY + gamePanel.tileSize / 2;

        // Top-left coordinates of the circle
        double x = centerX - circleSize / 2.0;
        double y = centerY - circleSize / 2.0;

        // Create and subtract the light circle from the screen area
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        screenArea.subtract(new Area(circleShape));

        // Define gradient properties
        Color[] colors = {new Color(0, 0, 0, 0), new Color(0, 0, 0, 0.25f), new Color(0, 0, 0, 0.5f),
                          new Color(0, 0, 0, 0.75f), new Color(0, 0, 0, 0.98f)};
        float[] fractions = {0f, 0.25f, 0.5f, 0.75f, 1f};
        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, circleSize / 2, fractions, colors);

        g2.setPaint(gradientPaint);

        // Fill the area with the gradient to simulate darkness with a lighter area
        g2.fill(screenArea);
        g2.dispose();
    }

    /**
     * Draws the dark filter onto a given Graphics2D surface.
     *
     * @param g2 the graphics object used for drawing
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(darkFilter, 0, 0, null);
    }
}

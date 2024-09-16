package Documents.environment;

import Documents.main.GamePanel;

import java.awt.*;

/** 
 * Manages the environment aspects of the game like lighting
 * */

public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;

    /** 
     * Constructs the environmentmanager with a reference to gamepanel
     * @param gp is the game panel that this manager is associated with
     * */
    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * initializes the environmental components, specifically setting up the lighting
     *  */
    public void setup() {
        lighting = new Lighting(gp, 350);
    }

    /** 
     * Darws the environmental components on the map
     * @param g2 is the graphic2d object that provides drawing context
     * */
    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
}

package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.UtilityTools;

/** 
 * The monster class is an abstract representation of the generic monster in the game
 * it handles the updating of the monster's movement and animation
 * */
public class Monster extends Entity{
    /** 
     * Constructor for monster object initializing it with the game panel context
     * @param gp is the gamepanel this monster is associated with
     * */
    public Monster(GamePanel gp){
        super(gp);
    }

    /** 
     * Updates the monster's position based on its current direction and velocity
     * it manages the animation state changes based on the sprite count
     * */
    public void update() {
        switch(direction) {
            case "up": this.worldYPos -= this.velocity; break;
            case "down": this.worldYPos += this.velocity; break;
            case "left": this.worldXPos -= this.velocity; break;
            case "right": this.worldXPos += this.velocity; break;
        }
        //checking collision with the wall tile
        //animation
        //the move() method gets called 60 times per second
        //the spritecount gets increments 1 per frame and every 20 frames the sprite image change
        spriteNum = UtilityTools.spriteCountCalculations(spriteCount,spriteNum);
		
	}
}

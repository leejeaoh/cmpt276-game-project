package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.KeyControl;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import Documents.main.UserInterface;
import Documents.main.UtilityTools;

/**
 * Represents the main character in the maze, extending the Entity class.
 */
public class MainCharacter extends Entity {
	/** The GamePanel instance associated with the character. */
	GamePanel gp;

	/** The KeyControl instance associated with the character. */
	KeyControl keyControl;

	/** The x-coordinate of the character inside the maze. */
	public int screenX;

	/** The y-coordinate of the character inside the maze */
	public int screenY;

	/** The number of keys the character currently holds. */
	public int keyNum = 0;

	int stall = 0;
	/**
	 * Constructs a MainCharacter object with the specified GamePanel and KeyControl instances.
	 * @param gp The GamePanel instance.
	 * @param keyControl The KeyControl instance.
	 */
	public MainCharacter(GamePanel gp, KeyControl keyControl) {
		super(gp);
		this.gp = gp;
		this.keyControl = keyControl;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		detectionArea = new Rectangle();
		detectionArea.x = 8;
		detectionArea.y = 16;

		detectionDefaultX = detectionArea.x;
		detectionDefaultY = detectionArea.y;

		detectionArea.width = 26;
		detectionArea.height = 26;

		settingDefaultValue();
		getMainCharacterImg();
	}

	public int getMainWxPos(){
		return worldXPos;
	}

	public int getMainWyPos(){
		return worldYPos;
	}
	/**
	 * Initializes the default values for the character.
	 */
	public void settingDefaultValue() {
		worldXPos = gp.tileSize * 20 - gp.tileSize/2;
		worldYPos = gp.tileSize * 18 - gp.tileSize/2;
		velocity = 6;

		direction = "down";
	}

	/**
	 * Loads the main character's images from resources folder.
	 */
	public void getMainCharacterImg() {
		upward1 = setup("/mainCharacter/boy_up_1");
		upward2 = setup("/mainCharacter/boy_up_2");
		downward1 = setup("/mainCharacter/boy_down_1");
		downward2 = setup("/mainCharacter/boy_down_2");
		leftward1 = setup("/mainCharacter/boy_left_1");
		leftward2 = setup("/mainCharacter/boy_left_2");
		rightward1 = setup("/mainCharacter/boy_right_1");
		rightward2 = setup("/mainCharacter/boy_right_2");
	}

	/**
	 * Update the character direction based on the key press.
	 */
	public void update() {
		if(keyControl.upPressed == true || keyControl.downPressed == true ||
				keyControl.leftPressed == true || keyControl.rightPressed == true) {
			if(keyControl.upPressed == true) {
				direction = "up";
			}else if(keyControl.downPressed == true) {
				direction = "down";
			}else if(keyControl.leftPressed == true) {
				direction = "left";
			}else if(keyControl.rightPressed == true) {
				direction = "right";
			}

			//checking collision with the wall tile
			isCollision = false;
			gp.checkCollision.tileCheck(this);

			//obj collision
			int objIndex = gp.checkCollision.objCheck(this,true);
			pickUpObj(objIndex);



			//monster collision
			int mobIndex = gp.checkCollision.entityCheck(this,gp.monster);
			interactMob(mobIndex);

			if((int)(UserInterface.score-UserInterface.gameTime)<=0){
				gp.gameState = gp.stopState;
			}
			//if its false, character can move else cannot
			if(isCollision == false) {
				if(stall==0){
					switch(direction) {
						case "up": this.worldYPos -= this.velocity; break;
						case "down": this.worldYPos += this.velocity; break;
						case "left": this.worldXPos -= this.velocity; break;
						case "right": this.worldXPos += this.velocity; break;
					}
				}
			}

			if(stall!=0){
				stall -=1;
			}
			//animation
			//the move() method gets called 60 times per second
			//the spritecount gets increments 1 per frame and every 20 frames the sprite image change
            int[] animationResults = UtilityTools.updateSpriteAnimation(spriteCount, spriteNum, 10);
            spriteCount = animationResults[0];
            spriteNum = animationResults[1];
		}
	}

	/**
	 * Picks up an object at the specified index.
	 * @param i The index of the object to pick up.
	 */
	public void pickUpObj(int i) {
		//if index is not 999 it means the collision has happened
		if(i !=  999) {
			String objName = gp.obj[i].type;

			//depending on obj reaction
			switch(objName) {
				case "keyCard":
					gp.soundEffectObj(1);
					keyNum++;
					//null to remove the object
					gp.obj[i] = null;
					gp.ui.displayMessage("Key Card collected!");
					break;
				case "door":
					if(keyNum == 4) {
						gp.soundEffectObj(2);
						gp.obj[i] = null;
						keyNum = 0;
						gp.ui.displayMessage("The door is now open!");
						break;
					} else {
						gp.ui.displayMessage("You need 4 card keys to open");
						break;
					}
				case "stair":
					gp.gameState = gp.winState;
					gp.ui.gameDone = true;
					gp.musicStop();
					//gp.soundEffectObj();
					break;
				case "bloodstain":
					UserInterface.score -= 10;
					stall = 40;
					gp.obj[i] = null;
					break;
				case "candy":
					UserInterface.score += 20;
					gp.obj[i] = null;
					break;
			}
		}
	}




	public void interactMob(int i){
		if(i != 999) {
			// System.out.println("You are hitting mob");
			gp.gameState = gp.deathState;
		}
	}
	/**
	 * Draws the character inside the maze.
	 * @param g2 The graphics context.
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null;

		switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = upward1;
				}
				if(spriteNum == 2) {
					image = upward2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = downward1;
				}
				if(spriteNum == 2) {
					image = downward2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = leftward1;
				}
				if(spriteNum == 2) {
					image = leftward2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = rightward1;
				}
				if(spriteNum == 2) {
					image = rightward2;
				}
				break;
		}
		//image observer
		g2.drawImage(image, screenX, screenY, null);
	}
}




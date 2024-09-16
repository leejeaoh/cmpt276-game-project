package Documents.entity;

import Documents.main.CollisionCheck;
import Documents.main.GamePanel;
import Documents.main.KeyControl;
import Documents.main.SetAsset;
import Documents.main.UserInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Documents.object.*;

import org.junit.jupiter.api.BeforeAll;

public class MainCharacterTest {

    private static CollisionCheck collisionCheck;
    private static GamePanel gp;
    private static KeyControl keyControl;
    private static MainCharacter mainCharacter;
    private static Graphics2D g2;

    @BeforeAll
    public static void setup(){
        gp = new GamePanel();
        collisionCheck = new CollisionCheck(gp);
        keyControl = new KeyControl(gp);
        mainCharacter = new MainCharacter(gp, keyControl);
        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
    }
    @Test
    public void testUpdate() {
        MainCharacter mainCharacter;
        GamePanel gamePanel;
        KeyControl keyControl;
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        mainCharacter = new MainCharacter(gamePanel, keyControl);

        keyControl.upPressed = true;
        mainCharacter.update();
        assertEquals("up", mainCharacter.direction);
        keyControl.upPressed = false;

        keyControl.downPressed = true;
        mainCharacter.update();
        assertEquals("down", mainCharacter.direction);
        keyControl.downPressed = false;

        keyControl.leftPressed = true;
        mainCharacter.update();
        assertEquals("left", mainCharacter.direction);
        keyControl.leftPressed = false;

        keyControl.rightPressed = true;
        mainCharacter.update();
        assertEquals("right", mainCharacter.direction);
        keyControl.rightPressed = false;
    }

    @Test
    public void drawTest(){
        mainCharacter.spriteNum = 1;
        mainCharacter.direction = "up";
        mainCharacter.draw(g2);
        mainCharacter.direction = "down";
        mainCharacter.draw(g2);
        mainCharacter.direction = "left";
        mainCharacter.draw(g2);
        mainCharacter.direction = "right";
        mainCharacter.draw(g2);
        mainCharacter.spriteNum = 2;
        mainCharacter.direction = "up";
        mainCharacter.draw(g2);
        mainCharacter.direction = "down";
        mainCharacter.draw(g2);
        mainCharacter.direction = "left";
        mainCharacter.draw(g2);
        mainCharacter.direction = "right";
        mainCharacter.draw(g2);
    }

    @Test
    public void pickUpObjTest(){
        KeyCard keycard = new KeyCard(gp);
        gp.obj[0] = keycard;

        Bloodstain bloodstain = new Bloodstain(gp);
        gp.obj[1] = bloodstain;

        Door door = new Door(gp);
        gp.obj[2] = door;


        Candy candy = new Candy(gp);
        gp.obj[3] = candy;

        Stair stair = new Stair(gp);
        gp.obj[4] = stair;

        


        mainCharacter.pickUpObj(0);
        mainCharacter.pickUpObj(1);
        mainCharacter.pickUpObj(2);
        assertNotNull(gp.obj[2]);
        mainCharacter.keyNum = 4;
        mainCharacter.pickUpObj(2);
        mainCharacter.pickUpObj(3);
        mainCharacter.pickUpObj(4);
        for(int i = 0; i < 4; i++){
            assertEquals(null, gp.obj[i]);
        }
        assertNotNull(gp.obj[4]);
    }

}
package Documents.entity;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemonTest {

    private GamePanel gamePanel;
    private Demon demon;
    private static int left;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        demon = new Demon(gamePanel);
        gamePanel.tileSize = 48;
        left = demon.worldXPos + demon.detectionArea.x;
    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = 0;
        demon.worldXPos = initialXPos;
        demon.velocity = 5;
        demon.direction = "right";
        demon.update();
        assertEquals(initialXPos + demon.velocity, demon.worldXPos);
    }

    @Test
    public void testUpdateWithCollision(){
        demon.velocity = 5;
        demon.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        demon.worldYPos = 10;
        demon.update();
        assertEquals(10, demon.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        demon.worldXPos = initialXPos;
        demon.worldYPos = initialYPos;
        demon.velocity = 0;
        demon.direction = "up"; // Direction is set but should not matter due to zero velocityocity
        demon.isCollision = false;
        demon.update();
        assertEquals(initialXPos, demon.worldXPos);
        assertEquals(initialYPos, demon.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        demon.worldXPos = initialXPos;
        demon.worldYPos = initialYPos;
        demon.velocity = 5;
        demon.direction = ""; // Direction is set but should not matter due to zero velocityocity
        demon.isCollision = false;
        demon.update();
        assertEquals(initialXPos, demon.worldXPos);
        assertEquals(initialYPos, demon.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        demon.velocity = 5;
        demon.direction = "up";
        demon.worldYPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(5, demon.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        demon.velocity = 5;
        demon.direction = "down";
        demon.worldYPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(15, demon.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        demon.velocity = 5;
        demon.direction = "left";
        demon.worldXPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(5, demon.worldXPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        demon.velocity = 5;
        demon.direction = "right";
        demon.worldXPos = 10;
        demon.isCollision = false;
        demon.update(); // same as above for all these cases
        assertEquals(15, demon.worldXPos);
    }

    @Test
    public void testUpdateWithoutDistanceExceeding1(){
        demon.velocity = 5;
        demon.direction = "right";
        demon.worldXPos = 2500;
        demon.worldYPos = 2500;
        gamePanel.mainCharacter.worldXPos = 1250;
        gamePanel.mainCharacter.worldYPos = 1250;
        demon.onPath = false;
        demon.isCollision = false;
        demon.update();
        assertFalse(demon.onPath);
    }

    @Test
    public void testUpdateWithoutDistanceExceeding2(){
        demon.velocity = 5;
        demon.direction = "right";
        demon.worldXPos = 1000;
        demon.worldYPos = 1000;
        gamePanel.mainCharacter.worldXPos = 500;
        gamePanel.mainCharacter.worldYPos = 500;
        demon.onPath = true;
        demon.isCollision = false;
        demon.update();
        assertTrue(demon.onPath);
    }

    @Test
    public void testUpdateSpriteCountSame() {
        demon.direction = "up";
        demon.spriteCount = 1;
        demon.update(); // doubling everything
        assertEquals(1, demon.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        demon.direction = "up";
        demon.spriteCount = 11;
        demon.spriteNum = 1;
        demon.update();
        assertEquals(11, demon.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 1;
        demon.update();
        assertEquals(2, demon.spriteNum);
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 2;
        demon.update();
        assertEquals(1, demon.spriteNum);
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 3;
        demon.update();
        assertEquals(1, demon.spriteNum);
    }

    @Test
    public void testSetActionOnPathTrue() {
        demon.direction = "up";
        demon.onPath = true;
        demon.setAction();
        //assertEquals(); // dont know what to check for assert equals. have to pick variables and calculate an outcome for expected.
    }

}


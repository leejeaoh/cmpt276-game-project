package Documents.entity;
import Documents.main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    private GamePanel gamePanel;
    private Monster monster;
    private static int left;

    @BeforeEach
    public void setUp() {

        gamePanel = new GamePanel();
        monster = new Monster(gamePanel);
        gamePanel.tileSize = 48;
        left = monster.worldXPos + monster.detectionArea.x;
    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = 0;
        monster.worldXPos = initialXPos;
        monster.velocity = 5;
        monster.direction = "right";
        monster.update();
        assertEquals(initialXPos + monster.velocity, monster.worldXPos);
    }

    @Test
    public void testUpdateWithCollision(){
        monster.velocity = 5;
        monster.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        monster.worldYPos = 10;
        monster.update();
        assertEquals(10, monster.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        monster.worldXPos = initialXPos;
        monster.worldYPos = initialYPos;
        monster.velocity = 0;
        monster.direction = "up";
        monster.isCollision = false;
        monster.update();
        assertEquals(initialXPos, monster.worldXPos);
        assertEquals(initialYPos, monster.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        monster.worldXPos = initialXPos;
        monster.worldYPos = initialYPos;
        monster.velocity = 5;
        monster.direction = "";
        monster.isCollision = false;
        monster.update();
        assertEquals(initialXPos, monster.worldXPos);
        assertEquals(initialYPos, monster.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        monster.velocity = 5;
        monster.direction = "up";
        monster.worldYPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(5, monster.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        monster.velocity = 5;
        monster.direction = "down";
        monster.worldYPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(15, monster.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        monster.velocity = 5;
        monster.direction = "left";
        monster.worldXPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(5, monster.worldXPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        monster.velocity = 5;
        monster.direction = "right";
        monster.worldXPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(15, monster.worldXPos);
    }

    @Test
    public void testUpdateSpriteCountSame() {
        monster.direction = "up";
        monster.spriteCount = 1;
        monster.update();
        assertEquals(1, monster.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        monster.direction = "up";
        monster.spriteCount = 11;
        monster.spriteNum = 1;
        monster.update();
        assertEquals(11, monster.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 1;
        monster.update();
        assertEquals(2, monster.spriteNum);
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 2;
        monster.update();
        assertEquals(1, monster.spriteNum);
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 3;
        monster.update();
        assertEquals(1, monster.spriteNum);
    }
}

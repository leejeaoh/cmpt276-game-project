package Documents.entity;

import Documents.SearchAI.*;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityTest {

    private static GamePanel gamePanel;
    private static Entity entity;
    private static int left, right, top, bottom;
    private static int leftCol, rightCol, topRow, bottomRow;
    private static int left1, left2, right1, right2, top1, top2, bottom1, bottom2;
    private static Graphics2D g2; 

    @BeforeAll
    public static void setUp() {
        gamePanel = new GamePanel();
        entity = new Entity(gamePanel);
        gamePanel.tileSize = 48;
        gamePanel.tileFactory = new TileFactory(gamePanel);

        left = entity.worldXPos + entity.detectionArea.x;
        right = entity.worldXPos + entity.detectionArea.x + entity.detectionArea.width;
        top = entity.worldYPos + entity.detectionArea.y;
        bottom = entity.worldYPos + entity.detectionArea.y + entity.detectionArea.height;

        leftCol = left/gamePanel.tileSize;
        rightCol = right/gamePanel.tileSize;
        topRow = top/gamePanel.tileSize;
        bottomRow = bottom/gamePanel.tileSize;

        leftCol = (left - entity.velocity)/gamePanel.tileSize;
        left1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        left2 = gamePanel.tileFactory.getTileMapNum(leftCol, bottomRow);

        rightCol = (right + entity.velocity)/gamePanel.tileSize;
        right1 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);
        right2 = gamePanel.tileFactory.getTileMapNum(rightCol, bottomRow);

        topRow = (top - entity.velocity)/gamePanel.tileSize;
        top1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        top2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);

        bottomRow = (bottom + entity.velocity)/gamePanel.tileSize;
        bottom1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        bottom2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);
        
        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
        
    }

    @Test
    public void testEntityNull() {
        assertNotNull(entity.gp);
    }

    @Test
    public void testEntityDefaultPosition() {
        GamePanel gamePanelDefault = new GamePanel();
        Entity entityDefault = new Entity(gamePanelDefault);
        assertEquals(0, entityDefault.worldXPos);
        assertEquals(0, entityDefault.worldYPos);
    }


    @Test
    public void testCheckCollision(){

    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = entity.worldXPos;
        int initialYPos = entity.worldYPos;
        entity.velocity = 5;
        entity.direction = "right";
        entity.update();
        assertEquals(initialXPos + entity.velocity, entity.worldXPos);
        entity.direction = "left";
        entity.update();
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        entity.worldXPos = initialXPos;
        entity.worldYPos = initialYPos;
        entity.velocity = 0;
        entity.direction = "up"; // Direction is set but should not matter due to zero velocityocity
        entity.isCollision = false;
        entity.update();
        assertEquals(initialXPos, entity.worldXPos);
        assertEquals(initialYPos, entity.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        entity.worldXPos = initialXPos;
        entity.worldYPos = initialYPos;
        entity.velocity = 5;
        entity.direction = ""; // Direction is set but should not matter due to zero velocityocity
        entity.isCollision = false;
        entity.update();
        assertEquals(initialXPos, entity.worldXPos);
        assertEquals(initialYPos, entity.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        entity.velocity = 5;
        entity.direction = "up";
        entity.worldYPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(5, entity.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        entity.velocity = 5;
        entity.direction = "down";
        entity.worldYPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(15, entity.worldYPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        entity.velocity = 5;
        entity.direction = "left";
        entity.worldXPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(5, entity.worldXPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        entity.velocity = 5;
        entity.direction = "right";
        entity.worldXPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(15, entity.worldXPos);
    }

    @Test
    public void testUpdateWithCollision(){
        entity.velocity = 5;
        entity.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        entity.worldYPos = 10;
        entity.update();
        assertEquals(10, entity.worldYPos);
        gamePanel.tileFactory.getTile(left).collision = false;
    }

    @Test
    public void testUpdateSpriteCountSame() {
        entity.direction = "up";
        entity.spriteCount = 1;
        entity.update();
        assertEquals(1, entity.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        entity.direction = "up";
        entity.spriteCount = 11;
        entity.spriteNum = 1;
        entity.update();
        assertEquals(11, entity.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 1;
        entity.update();
        assertEquals(2, entity.spriteNum);
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 2;
        entity.update();
        assertEquals(1, entity.spriteNum);
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 3;
        entity.update();
        assertEquals(1, entity.spriteNum);
    }

    @Test
    public void testDraw(){
        //576
        //1296

        entity.worldXPos = 936;
        
        entity.worldYPos = 840;

        gamePanel.tileSize = 361;

        // System.out.println(entity.worldYPos + " " + gamePanel.tileSize + " " + gamePanel.mainCharacter.worldYPos +  " " + gamePanel.mainCharacter.screenY);

        entity.direction = "left";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "right";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "up";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "down";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
    }

    @Test
    public void testSetup(){


    }

    @Test
    public void testSearchPath(){

        

        int goalColumn = 25;
        int goalRow = 25;
        
        int successfulX = -1;
        int successfulY = -1;
        

        entity.worldXPos = 625;
        entity.worldYPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.worldXPos = 0;
        entity.worldYPos = 150;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("down", entity.direction);

        entity.worldXPos = 400;
        entity.worldYPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("left", entity.direction);

        entity.worldXPos = 0;
        entity.worldYPos = 625;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("right", entity.direction);

        entity.worldXPos = 400;
        entity.worldYPos = 875;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.worldXPos = 400;
        entity.worldYPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("left", entity.direction);

        entity.worldXPos = 0;
        entity.worldYPos = 450;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.worldXPos = 450;
        entity.worldYPos = 550;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("right", entity.direction);

        entity.worldXPos = 25;
        entity.worldYPos = 150;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("down", entity.direction);
    
    
    }

}

package Documents.entity;

import Documents.main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

        private GamePanel gamePanel;
        private Ghost ghost;

        @BeforeEach
        public void setUp() {
            gamePanel = new GamePanel();
            ghost = new Ghost(gamePanel);
            gamePanel.tileSize = 48;
        }

        @Test
        public void testUpdateDistance(){
            int initialXPos = 0;
            ghost.worldXPos = initialXPos;
            ghost.velocity = 5;
            ghost.direction = "right";
            ghost.update();
            assertEquals(initialXPos + ghost.velocity, ghost.worldXPos);
        }

        @Test
        public void testUpdateMovementNone(){
            int initialXPos = 10;
            int initialYPos = 10;
            ghost.worldXPos = initialXPos;
            ghost.worldYPos = initialYPos;
            ghost.velocity = 0;
            ghost.update();
            assertEquals(initialXPos, ghost.worldXPos);
            assertEquals(initialYPos, ghost.worldYPos);
        }

        @Test
        public void testUpdateUp(){
            ghost.velocity = 5;
            ghost.worldXPos = 10;
            ghost.worldYPos = 10;
            gamePanel.mainCharacter.worldXPos = 10;
            gamePanel.mainCharacter.worldYPos = 5;
            ghost.update();
            assertEquals(5, ghost.worldYPos);
        }

        @Test
        public void testUpdateDown(){
            ghost.velocity = 5;
            ghost.worldXPos = 10;
            ghost.worldYPos = 10;
            gamePanel.mainCharacter.worldXPos = 10;
            gamePanel.mainCharacter.worldYPos = 15;
            ghost.update();
            assertEquals(15, ghost.worldYPos);
        }

        @Test
        public void testUpdateLeft(){
            ghost.velocity = 5;
            ghost.worldXPos = 10;
            ghost.worldYPos = 10;
            gamePanel.mainCharacter.worldXPos = 5;
            gamePanel.mainCharacter.worldYPos = 10;
            ghost.update();
            assertEquals(5, ghost.worldXPos);
        }

        @Test
        public void testUpdateRight(){
            ghost.velocity = 5;
            ghost.worldXPos = 10;
            ghost.worldYPos = 10;
            gamePanel.mainCharacter.worldXPos = 15;
            gamePanel.mainCharacter.worldYPos = 10;
            ghost.update();
            assertEquals(15, ghost.worldXPos);
        }

        @Test
        public void testUpdateWithoutDistanceExceeding1(){
            ghost.velocity = 5;
            ghost.direction = "right";
            ghost.worldXPos = 2500;
            ghost.worldYPos = 2500;
            gamePanel.mainCharacter.worldXPos = 1250;
            gamePanel.mainCharacter.worldYPos = 1250;
            ghost.onPath = false;
            ghost.isCollision = false;
            ghost.update();
            assertFalse(ghost.onPath);
        }

        @Test
        public void testUpdateWithoutDistanceExceeding2(){
            ghost.velocity = 5;
            ghost.direction = "right";
            ghost.worldXPos = 2500;
            ghost.worldYPos = 2500;
            gamePanel.mainCharacter.worldXPos = 1250;
            gamePanel.mainCharacter.worldYPos = 1250;
            ghost.onPath = true;
            ghost.isCollision = false;
            ghost.update();
            assertTrue(ghost.onPath);
        }

        @Test
        public void testUpdateSpriteCountSame() {
            ghost.direction = "up";
            ghost.spriteCount = 1;
            ghost.update(); // doubling everything
            assertEquals(2, ghost.spriteCount);
        }

        @Test
        public void testUpdateSpriteCountIncrement() {
            ghost.direction = "up";
            ghost.spriteCount = 11;
            ghost.spriteNum = 1;
            ghost.update();
            assertEquals(0, ghost.spriteCount);
        }

        @Test
        public void testUpdateSpriteNumToggles() {
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 1;
            ghost.update();
            assertEquals(2, ghost.spriteNum);
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 2;
            ghost.update();
            assertEquals(1, ghost.spriteNum);
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 3;
            ghost.update();
            assertEquals(3, ghost.spriteNum);
        }

}

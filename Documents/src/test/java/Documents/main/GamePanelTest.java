package Documents.main;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Documents.object.*;
import Documents.entity.*;


class GamePanelTest {
    private GamePanel gamePanel;

    @BeforeEach
    void setup() {
        gamePanel = new GamePanel();
        gamePanel.gameState = gamePanel.playState;
        gamePanel.mainCharacter.keyNum = 5;
    }

    @Test
    void resetGameStateAndCharacterKeyCount() {
        gamePanel.resetGame();

        assertEquals(gamePanel.titleState, gamePanel.gameState, "it should go back to title state");
        assertEquals(0, gamePanel.mainCharacter.keyNum, "main character key should reset to 0");
        assertEquals(101, UserInterface.score, "score should reset");
    }

    @Test
    void threadStartTest() {
        assertNull(gamePanel.gameThread, "gamethread should initially be null");
        
        gamePanel.gameStartThread();
        assertNotNull(gamePanel.gameThread, "gamethread should not be null after startThread");
    }

    // @Test
    void paintTest(){
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = img.getGraphics();

        gamePanel.paintComponent(graphics);
        gamePanel.gameState = gamePanel.titleState;
        gamePanel.paintComponent(graphics);
        Bloodstain bloodstain = new Bloodstain(gamePanel);
        gamePanel.obj[0] = bloodstain;
        Ghost ghost = new Ghost(gamePanel);
        gamePanel.monster[0] = ghost;
        gamePanel.gameState = gamePanel.playState;
        gamePanel.paintComponent(graphics);
    }
}
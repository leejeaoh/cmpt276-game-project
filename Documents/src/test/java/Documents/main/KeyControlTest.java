package Documents.main;

import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class KeyControlTest {

    private KeyControl keyControl;
    private GamePanel gamePanel;

    @Test
    public void testUpKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.upPressed);
    }

    @Test
    public void testUpKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyReleased(keyEvent);
        assertFalse(keyControl.upPressed);
    }

    @Test
    public void testDownKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, 'D');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.downPressed);
    }

    @Test
    public void testDownKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_DOWN, 'D');
        keyControl.keyReleased(keyEvent);
        assertFalse(keyControl.downPressed);
    }

    @Test
    public void testLeftKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT, 'L');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.leftPressed);
    }

    @Test
    public void testLeftKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_LEFT, 'L');
        keyControl.keyReleased(keyEvent);
        assertFalse(keyControl.leftPressed);
    }

    @Test
    public void testRightKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, 'R');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.rightPressed);
    }

    @Test
    public void testRightKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_RIGHT, 'R');
        keyControl.keyReleased(keyEvent);
        assertFalse(keyControl.rightPressed);
    }

    @Test
    public void testPKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_P, 'P');
        keyControl.keyPressed(keyEvent);
        assertEquals(gamePanel.stopState, gamePanel.gameState);
    }

    @Test
    public void testSpaceKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, ' ');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.enterPressed);
    }

    @Test
    public void testKeyTyped() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_TYPED, 0, 0, KeyEvent.VK_UNDEFINED, 'a');
        assertDoesNotThrow(() -> keyControl.keyTyped(keyEvent));
    }

    @Test
    public void testPKeyStop() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.stopState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_P, 'P');
        keyControl.keyPressed(keyEvent);
        assertEquals(gamePanel.playState, gamePanel.gameState);
    }

    @Test
    public void testUpKeyTitle() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.titleState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        assertEquals(2, gamePanel.ui.commandingNumber);
    }

    @Test
    public void testDownKeyTitle() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.titleState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, 'D');
        keyControl.keyPressed(keyEvent);
        assertEquals(1, gamePanel.ui.commandingNumber);
    }

    @Test
    public void testEnterKeyTitle() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.titleState;
        gamePanel.ui.commandingNumber = 0;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, 'E');
        keyControl.keyPressed(keyEvent);
        assertEquals(gamePanel.playState, gamePanel.gameState);
    }

}
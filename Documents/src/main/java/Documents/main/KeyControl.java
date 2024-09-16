package Documents.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for handling keyboard input for main character movement.
 */
public class KeyControl implements KeyListener{
	/** The GamePanel instance associated with the key control. */
	GamePanel gp;
	
	/** Indicates whether the up arrow key is currently pressed. */
	public boolean upPressed;

	/** Indicates whether the down arrow key is currently pressed. */
    public boolean downPressed;
    
    /** Indicates whether the left arrow key is currently pressed. */
    public boolean leftPressed;
    
    /** Indicates whether the right arrow key is currently pressed. */
    public boolean rightPressed;

	/** Indicates whether the enter key is currently pressed. */
    public boolean enterPressed;

	/**
     * Constructs a KeyControl object with the specified GamePanel instance.
     * @param gp The GamePanel instance to associate with the KeyControl object.
     */
	public KeyControl(GamePanel gp) {
		this.gp = gp;
	}

	/**
     * Invoked when a key has been typed.
     * @param e The KeyEvent associated with this event.
     */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
     * Invoked when a key has been pressed.
     * @param e The KeyEvent associated with this event.
     */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();//return int keyCode associated with the key in event

		// Title State and Death State
		if (gp.gameState == gp.titleState||gp.gameState == gp.deathState||gp.gameState == gp.winState) {
			if (code == KeyEvent.VK_UP) {
				gp.ui.commandingNumber--;
				if (gp.ui.commandingNumber < 0) {
					gp.ui.commandingNumber = 2;
				}
			}
			if (code == KeyEvent.VK_DOWN) {
				gp.ui.commandingNumber++;
				if (gp.ui.commandingNumber > 2) {
					gp.ui.commandingNumber = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandingNumber == 0) {
					gp.resetGame();
					gp.gameState = gp.playState;
					gp.musicPlay(0);
				}
				if (gp.ui.commandingNumber == 1) {
					System.exit(0);
				}
			}
		}
		//play state
		if(gp.gameState == gp.playState){
			switch(code) {
			case KeyEvent.VK_UP:
				upPressed = true;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
			case KeyEvent.VK_P:
				gp.gameState = gp.stopState;
				break;
			case KeyEvent.VK_SPACE:
				enterPressed = true;
				break;
				
			default:
				break;
			}
		}
		// pause state
		else if(gp.gameState == gp.stopState){
			if(code == KeyEvent.VK_P){
				gp.gameState = gp.playState;
			}
		}
	}

	/**
     * Invoked when a key has been released.
     * @param e The KeyEvent associated with this event.
     */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();//return int keyCode associated with the key in event

		switch(code) {
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		default:
			break;
		}
	}
}
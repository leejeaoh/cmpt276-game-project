package Documents.main;

/*
    The png used for tiles were created by Jea Oh Lee (301351043)

    List of created *.png used in tile:
    voidspace
    rightsideWallEnd
    blueFloor
    upWallRight
    orangeWallRight
    leftsideWallEnd
    cornerRightBottomToUp
    bottomWallCenter
    connector1
    connector2
    blueWallCenter
    blueWallLeft
    upWallCen
    roomFloor
    leftsideWall
    orangeWallCenter
    blueWallRight
    upWallLeft
    orangeWallLeft
    grayFloor
    rightsideWallcornerLeftBottomToup

    List of created *.png used in object:
    door
    stair
    cardkey

*/



import javax.swing.JFrame;

/**
 * The main class of the game. It creates a JFrame window and adds a GamePanel into it.
 * The GamePanel contains the game's graphical components and logic.
 */
public class Main {
    /**
     * The main method that starts the game.
     * It creates a JFrame window, sets up the GamePanel, and starts the game thread.
     * @param args variable to take input from user.
     */
    public static void main(String[] args) {

        JFrame window = new JFrame ();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("CMPT 276 Project Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //to make the window to be the sized fit of the preferred size and the layouts of
        //its subcomponents GamePanel
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setGame();
        gamePanel.gameStartThread();
    }
}
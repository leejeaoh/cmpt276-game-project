package Documents.main;

import Documents.object.KeyCard;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import java.awt.BasicStroke;

/**
 * The UserInterface class handles the display of the game's user interface elements.
 * It includes the key card image and the number of keys collected by the main character.
 */
public class UserInterface {
	/** The GamePanel associated with this user interface. */
	GamePanel gp;
	int gpTileSize;

	Graphics2D g2; //maybe rename
	Font arialFont_40,arialFont_80;

	/** The image representing the key card in the user interface. */
	BufferedImage cardKeyImg;

	int msgCount = 0;
	int spriteCount = 0;
	int spriteNum = 1;
	//printing
	public boolean printMessage = false;
	public String msg = "";
	public boolean gameDone = false;

	public static double gameTime;
	public static double score = 101;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public int commandingNumber = 0;

	/**
     * Constructs a new UserInterface object with the specified GamePanel.
     * @param gp The GamePanel associated with this user interface.
     */
	public UserInterface(GamePanel gp) {
		this.gp = gp;
		arialFont_40 = new Font("Arial", Font.PLAIN, 40);
		arialFont_80 = new Font("Arial", Font.BOLD, 80);
		KeyCard keyCard = new KeyCard(gp);
		cardKeyImg = keyCard.img;
		gpTileSize = this.gp.tileSize;
	}

	public void displayMessage(String text) {
		msg = text;
		printMessage = true;
	}

	/**
     * Draws the user interface elements inside the maze.
     * @param g2 The graphics context to draw on.
     */
	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arialFont_40);
		g2.setColor(Color.white);

		// Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}

		if (gp.gameState == gp.deathState) {
			drawDeathScreen();
		}

		if (gp.gameState == gp.winState) {
			drawWinScreen();
		}
		//play state
		if (gp.gameState == gp.playState) {
			g2.setFont(arialFont_40);
			g2.setColor(Color.white);
			g2.drawImage(cardKeyImg, gpTileSize/2, gpTileSize/6, gpTileSize, gpTileSize, null);
			g2.drawString(" x " + gp.mainCharacter.keyNum + " / 8", 74, 50);
			gameTime += (double)1/60;
			g2.drawString("Time:" + dFormat.format(gameTime), gpTileSize*11, 50);
			g2.drawString("Score:" + (int) (score-gameTime), gpTileSize*11, 100);

			//displaying message
			if(printMessage == true) {
				g2.setFont(g2.getFont().deriveFont(20));
				g2.drawString(msg,gpTileSize,gpTileSize * 5); 

				msgCount++;

				if(msgCount > 120) {
					msgCount = 0;
					printMessage = false;
				}
			}
		}
		//pause state
		if (gp.gameState == gp.stopState) {
			drawPauseScreen();
		}

	}

	public void drawTitleScreen() {

		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Haunted Hospital";
		int x = getXCenterText(text);
		int y = gpTileSize*3;

		// Shadow
		g2.setColor(Color.gray);
		g2.drawString(text,x+5,y+5);

		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// Boy Image
		x = gp.screenWidth/2- (gpTileSize*2)/2;
		y += gpTileSize*2;
		g2.drawImage(gp.mainCharacter.downward1, x, y, gpTileSize*2, gpTileSize*2, null);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

		text = "NEW GAME";
		x = getXCenterText(text);
		y += gpTileSize*4;
		g2.drawString(text,x,y);
		if (commandingNumber == 0) {
			g2.drawString(">", x-gpTileSize, y);
		}

		text = "QUIT";
		x = getXCenterText(text);
		y += gpTileSize;
		g2.drawString(text, x, y);
		if (commandingNumber == 1) {
			g2.drawString(">", x-gpTileSize, y);
		}
	}

	public void drawDeathScreen() {

		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "You Died!";
		int x = getXCenterText(text);
		int y = gpTileSize*3;

		// Shadow
		g2.setColor(Color.gray);
		g2.drawString(text,x+5,y+5);

		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// Boy Image
		x = gp.screenWidth/2- (gpTileSize*2)/2;
		y += gpTileSize*2;
		
		drawSprite("/skull/skull", x, y, gpTileSize*2, gpTileSize*2);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

		text = "TRY AGAIN";
		x = getXCenterText(text);
		y += gpTileSize*4;
		g2.drawString(text,x,y);
		if (commandingNumber == 0) {
			g2.drawString(">", x-gpTileSize, y);
		}

		text = "GIVE UP";
		x = getXCenterText(text);
		y += gpTileSize;
		g2.drawString(text, x, y);
		if (commandingNumber == 1) {
			g2.drawString(">", x-gpTileSize, y);
		}
	}

	public void drawWinScreen(){
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "You Won!";
		int x = getXCenterText(text);
		int y = gpTileSize*3;

		// Shadow
		g2.setColor(Color.gray);
		g2.drawString(text,x+5,y+5);

		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// Boy Image
		x = gp.screenWidth/2- (gpTileSize*2)/2;
		y += gpTileSize*2;
		
		drawSprite("/trophy/trophy", x, y, gpTileSize*2, gpTileSize*2);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

		text = "PLAY AGAIN";
		x = getXCenterText(text);
		y += gpTileSize*4;
		g2.drawString(text,x,y);
		if (commandingNumber == 0) {
			g2.drawString(">", x-gpTileSize, y);
		}

		text = "QUIT";
		x = getXCenterText(text);
		y += gpTileSize;
		g2.drawString(text, x, y);
		if (commandingNumber == 1) {
			g2.drawString(">", x-gpTileSize, y);
		}

	}

	private void drawSprite(String imgDirectory, int x, int y, int width, int height) {
		spriteCount++;
		if(spriteCount > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 3;
			}else if(spriteNum == 3) {
				spriteNum = 4;
			}else if(spriteNum == 4) {
				spriteNum = 5;
			}else if(spriteNum == 5) {
				spriteNum = 1;
			}

			spriteCount = 0;
		}
		if(spriteNum==1){
			g2.drawImage(setup(imgDirectory+"0"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
		if(spriteNum==2){
			g2.drawImage(setup(imgDirectory+"1"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
		if(spriteNum==3){
			g2.drawImage(setup(imgDirectory+"2"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
		if(spriteNum==4){
			g2.drawImage(setup(imgDirectory+"3"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
		if(spriteNum==5){
			g2.drawImage(setup(imgDirectory+"2"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
		if(spriteNum==6){
			g2.drawImage(setup(imgDirectory+"1"), x, y, gp.tileSize*2, gp.tileSize*2, null);
		}
    }

	public BufferedImage setup(String imgPath) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
		    //ensure the image supports alpha (transparency)
		    BufferedImage newImg = new BufferedImage(gpTileSize, gpTileSize, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = newImg.createGraphics();
		    g2.drawImage(img, 0, 0, gpTileSize, gpTileSize, null);
		    g2.dispose();
		    img = newImg;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXCenterText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}
	public void drawSubWindow (int x, int y, int width, int height){
		Color c = new Color(0,0,0, 220);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

	}
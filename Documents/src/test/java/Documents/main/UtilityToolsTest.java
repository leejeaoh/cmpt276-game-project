package Documents.main;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityToolsTest {
    

    private BufferedImage img;
    private static GamePanel gp;
    private static UtilityTools tools;

    @BeforeAll
    public static void setup(){
        gp = new GamePanel();
        tools = new UtilityTools();
    }
    @Test
    public void testScaleImg(){
        BufferedImage img2 = null;
        try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/trap.png"));
			img2 = tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}

        assertNotNull(img2);
    }
}

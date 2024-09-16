package Documents.object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class StairTest {

    @Test
    public void testConstructor() {
        GamePanel gp;
        Stair stair;
        gp = new GamePanel();
        stair = new Stair(gp);
        assertEquals("stair", stair.type);
        assertNotNull(stair.img);
    }
}

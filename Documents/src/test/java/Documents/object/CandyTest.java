package Documents.object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class CandyTest {

    @Test
    public void testConstructor() {
        GamePanel gp;
        Candy candy;
        gp = new GamePanel();
        candy = new Candy(gp);
        assertEquals("candy", candy.type);
        assertNotNull(candy.img);
    }
}

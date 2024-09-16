package Documents.object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class BloodStainTest {

    @Test
    public void testConstructor() {
        GamePanel gp;
        Bloodstain bloodstain;
        gp = new GamePanel();
        bloodstain = new Bloodstain(gp);
        assertEquals("bloodstain", bloodstain.type);
        assertNotNull(bloodstain.img);
    }
}

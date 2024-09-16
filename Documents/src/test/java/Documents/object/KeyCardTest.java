package Documents.object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class KeyCardTest {

    @Test
    public void testConstructor() {
        GamePanel gp;
        KeyCard keyCard;
        gp = new GamePanel();
        keyCard = new KeyCard(gp);
        assertEquals("keyCard", keyCard.type);
        assertNotNull(keyCard.img);
    }
}

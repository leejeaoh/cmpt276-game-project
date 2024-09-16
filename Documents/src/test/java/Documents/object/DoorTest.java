package Documents.object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class DoorTest {

    @Test
    public void testConstructor() {
        GamePanel gp;
        Door door;
        gp = new GamePanel();
        door = new Door(gp);
        assertEquals("door", door.type);
        assertNotNull(door.img);
    }
}

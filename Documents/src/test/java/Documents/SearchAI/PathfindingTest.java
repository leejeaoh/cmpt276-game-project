package Documents.SearchAI;

import Documents.main.GamePanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathfindingTest {

    private static Pathfinding pathfinding;
    private static GamePanel gamePanel;

    @BeforeAll
    public static void setUp() {
        gamePanel = new GamePanel();
        pathfinding = new Pathfinding(gamePanel);
    }

    @Test
    public void testPathfindingNull() {
        assertNotNull(pathfinding);
    }
}

package Documents.SearchAI;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    private Node node;
    private int nodeColumn;
    private int nodeRow;

    @Test
    public void testNode() {
        nodeColumn = 10;
        nodeRow = 10;
        node = new Node(nodeColumn, nodeRow);
        assertEquals(10, node.column);
        assertEquals(10, node.row);
    }

    @Test
    public void testNodeNeg() {
        nodeColumn = -10;
        nodeRow = -10;
        node = new Node(nodeColumn, nodeRow);
        assertEquals(-10, node.column);
        assertEquals(-10, node.row);
    }
}

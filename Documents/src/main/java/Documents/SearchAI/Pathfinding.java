package Documents.SearchAI;

import Documents.main.GamePanel;
import java.util.ArrayList;

/** 
 * Implements pathfinding method using grid based map
 * The class uses A* search algorithm to determine the shortest path
 * from start node to goal node in 2 dimensional gird.
 * */

public class Pathfinding {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    /** 
     * Constructs pathfinding object associated with gamepanel
     * @param gp the game panel which the pathfinding is asscoiated with
     *           it is used to access the game's tile amp and setting
     * */
    public Pathfinding(GamePanel gp) {
        this.gp = gp;
        instantiateNodes(); //potential error #1
    }

    /** 
     * instantiates the grid of nodes that will be utilized for pathfinding
     * */
    public void instantiateNodes() {
        node = new Node[gp.maxWCol][gp.maxWRow];

        int column = 0;
        int row = 0;

        while(column < gp.maxWCol && row < gp.maxWRow) {
            node[column][row] = new Node(column, row);

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }
    }

    /** 
     * resetting the state of all nodoes to their initial state
     * */
    public void resetNodes() {
        resetNodeStates();
    }

    /** 
     * resets the nodes in the grid
     * */
    private void resetNodeStates() {
        int column = 0;
        int row = 0;

        while (column < gp.maxWCol && row < gp.maxWRow) {
            // Reset open, checked and solid state
            node[column][row].open = false;
            node[column][row].checked = false;
            node[column][row].solid = false;

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }
        resetSearchSettings();
    }

    /** 
     * resets the setting used for the search process
     * */
    private void resetSearchSettings() {
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    /** 
     * initialize the start and goal node and sets the grid state for the pathfinding
     * 
     * @param startCol is the column index of the start node
     * @param startRow is the row index of the start node
     * @param goalCol is the column index of the goal node
     * @param goalRow is the row index of the goal node
     * */
    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        initializeStartAndGoalNodes(startCol, startRow, goalCol, goalRow);
        initializeNodeStates();
    }

    /** 
     * initializes the satrt and goal based on provided indices
     * @param startCol is the column index of the start node
     * @param startRow is the row index of the start node
     * @param goalCol is the column index of the goal node
     * @param goalRow is the row index of the goal node
     * */
    public void initializeStartAndGoalNodes(int startCol, int startRow, int goalCol, int goalRow) {
        // Set Start and Goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
    }



    /** 
     * Initializes the state of each node based on the game panel's tile map
     * setting nodes as solid if they are not passable
     * */
    public void initializeNodeStates() {
        int column = 0;
        int row = 0;

        while (column < gp.maxWCol && row < gp.maxWRow) {

            int tileNum = gp.tileFactory.tileMapNum[column][row];
            if (gp.tileFactory.getTile(tileNum).collision == true) {
                node[column][row].solid = true;
            }

            getCost(node[column][row]);

            column++;
            if (column == gp.maxWCol) {
                column = 0;
                row++;
            }
        }
    }

    /** 
     * calculates the cost for moving to specific node from start node and the estimated
     * cost from that node to the goal node
     * 
     * @param node is the node for which costs are calculated
     * */
    public void getCost(Node node) {

        //G cost
        int sum = 0;
        int difference = 0;
        int xDist = Math.abs(node.column - startNode.column);
        int yDist = Math.abs(node.row - startNode.row);
        sum = xDist + yDist;
        node.gCost = sum;
        // H Cost
        xDist = Math.abs(node.column - goalNode.column);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;
        // F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int column = currentNode.column;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            openAdjacentNodes(column, row);

            if (!findBestNodeIndex()) {
                break;
            }

            step++;
        }
        return goalReached;
    }

    public void openAdjacentNodes(int column, int row) {
        // Open the Up node
        if (row - 1 >= 0) {
            openNode(node[column][row - 1]);
        }
        // Open the left node
        if (column - 1 >= 0) {
            openNode(node[column - 1][row]);
        }
        // Open the down node
        if (row + 1 >= 0) {
            openNode(node[column][row + 1]);
        }
        // Open the right node
        if (column + 1 >= 0) {
            openNode(node[column + 1][row]);
        }
    }

    public boolean findBestNodeIndex() {
        if (openList.isEmpty()) {
            return false;
        }

        int bestNodeIndex = 0;
        int bestNodefCost = Integer.MAX_VALUE;

        for (int i = 0; i < openList.size(); i++) {
            Node thisNode = openList.get(i);
            if (thisNode.fCost < bestNodefCost) {
                bestNodeIndex = i;
                bestNodefCost = thisNode.fCost;
            } else if (thisNode.fCost == bestNodefCost && thisNode.gCost < openList.get(bestNodeIndex).gCost) {
                bestNodeIndex = i;
            }
        }

        currentNode = openList.get(bestNodeIndex);
        openList.remove(bestNodeIndex); // Optionally remove the node from open list
        return checkGoalReached();
    }

    public boolean checkGoalReached() {
        if (currentNode.equals(goalNode)) {
            goalReached = true;
            trackPath();
            return false;
        }
        return true;
    }



    public void openNode(Node node) {

        if (node.open == false && node.checked == false && node.solid == false) {

            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackPath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
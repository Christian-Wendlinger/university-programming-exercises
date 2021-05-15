package utils;

import java.util.LinkedList;

public class Node {
    protected int name;
    protected int weight;
    protected LinkedList<Node> adj;

    /**
     * Constructor for Node, adj = []
     *
     * @param name
     */
    public Node(int name) {
        this.name = name;
        this.adj = new LinkedList<>();
    }

    /**
     * @return Name of Node
     */
    public int getName() {
        return this.name;
    }

    /**
     * @return Weight of Node
     */
    public int getWeight() {
        return this.weight;
    }
}

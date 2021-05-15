package utils;

import java.util.HashMap;
import java.util.HashSet;

public class Tree {
    private HashMap<Integer, Node> nodes;
    private HashMap<String, Integer> edges;
    private Node maxNode;

    /**
     * Construct empty Tree
     */
    public Tree() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * DFS to calculate Weights for all Node from starting Node
     *
     * @param name
     */
    public void assignWeights(int name) {
        assignWeights(new HashSet<>(), this.getNode(name), this.getNode(name));
    }

    /**
     * helper function to calculate Weights
     *
     * @param visited
     * @param prevNode
     * @param curNode
     */
    private void assignWeights(HashSet<Integer> visited, Node prevNode, Node curNode) {

        //check if it is the first Node
        if (visited.isEmpty()) {
            //mark Node as visited and adjust maxNode
            visited.add(curNode.name);
            curNode.weight = 0;
            this.maxNode = curNode;

            // recursive Call for Neighbours
            for (Node tmp : curNode.adj) {
                assignWeights(visited, curNode, tmp);
            }
        } else {
            // if Node is not visited yet
            if (visited.add(curNode.name)) {
                //calculate weight of the Node
                String key = prevNode.name < curNode.name ? prevNode.name + " " + curNode.name : curNode.name + " " + prevNode.name;
                curNode.weight = prevNode.weight + edges.get(key);

                // check if new maxNode is found
                if (curNode.weight > this.maxNode.weight) {
                    this.maxNode = curNode;
                }

                // recursive Call for Neighbours
                for (Node tmp : curNode.adj) {
                    assignWeights(visited, curNode, tmp);
                }
            }
        }
    }

    /**
     * Add new Node into Tree
     *
     * @param name
     */
    public void addNode(int name) {
        this.nodes.put(name, new Node(name));
    }

    /**
     * add new Edge into Tree and add Neighbours for the Node that are contained in the Edge
     *
     * @param name1
     * @param name2
     * @param weight
     */
    public void addEdge(int name1, int name2, int weight) {
        String key = name1 < name2 ? name1 + " " + name2 : name2 + " " + name1;
        this.edges.put(key, weight);
        this.addNeighbours(name1, name2);
    }

    /**
     * Add Neighbours for two specified Nodes
     *
     * @param name1
     * @param name2
     */
    private void addNeighbours(int name1, int name2) {
        this.nodes.get(name1).adj.add(nodes.get(name2));
        this.nodes.get(name2).adj.add(nodes.get(name1));
    }

    /**
     * get Node from Tree by name
     *
     * @param name
     * @return
     */
    public Node getNode(int name) {
        return this.nodes.get(name);
    }

    /**
     * get Node with highest weight
     *
     * @return Node with max. Weight in Tree
     */
    public Node getMaxNode() {
        return this.maxNode;
    }
}

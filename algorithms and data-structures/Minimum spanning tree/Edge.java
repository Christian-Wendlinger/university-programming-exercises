import java.util.Objects;

public class Edge implements Comparable {
    private int node1;
    private int node2;
    private int weight;

    /**
     * Constructor
     *
     * @param node1
     * @param node2
     * @param weight
     */
    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    /**
     * @return Node1
     */
    public int getNode1() {
        return node1;
    }

    /**
     * @return Node 2
     */
    public int getNode2() {
        return node2;
    }

    /**
     * @return Weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Equals function
     *
     * @param o
     * @return Comparing node1, node2 and weight
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return node1 == edge.node1 &&
                node2 == edge.node2 &&
                weight == edge.weight;
    }

    /**
     * hashCode function
     *
     * @return hash of node1, node2 and weight
     */
    @Override
    public int hashCode() {
        return Objects.hash(node1, node2, weight);
    }

    /**
     * @param o
     * @return Compares Weights of Edges if two Edges, else compares hashCodes
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Edge) {
            return this.getWeight() - ((Edge) o).getWeight();
        } else {
            return this.hashCode() - o.hashCode();
        }
    }

    /**
     *
     * @return String with information about all attributes.
     */
    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }
}

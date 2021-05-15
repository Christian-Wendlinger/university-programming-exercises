/*
5
7
0 1 1
1 2 2
1 3 6
4 1 1
4 0 3
3 4 2
2 3 3
*/

/**
 * @author: Meriton Dzemaili, Christian Wendlinger
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /**
     * Parses Input (Graph) and prints the weight of the minimum-spanning-tree.
     *
     * @param args
     */
    public static void main(String[] args) {

        //read input
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int m = Integer.parseInt(scan.nextLine());

        LinkedList<Edge> edges = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            String[] parts = scan.nextLine().split("\\s+");
            edges.add(new Edge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        //sort Edges
        Collections.sort(edges);
        System.out.println(kruskal(edges, n));
    }

    /**
     * Uses Kruskal's algorithm on a graph with n nodes edges in edges
     *
     * @param edges
     * @param n
     * @return weight of the minimum-spanning-tree
     */
    private static int kruskal(LinkedList<Edge> edges, int n) {
        UnionFind unionFind = new UnionFind(n);
        int weight = 0;

        // terminates when every edge has been processed or all the vertices are unified
        while (edges.size() > 0 && !unionFind.isAllUnified()) {
            Edge tmp = edges.pop();

            // don't include edge if nodes are unified otherwise include edge and unify nodes
            if (!unionFind.isUnified(tmp.getNode1(), tmp.getNode2())) {
                unionFind.union(tmp.getNode1(), tmp.getNode2());
                weight += tmp.getWeight();
            }
        }
        return weight;
    }
}

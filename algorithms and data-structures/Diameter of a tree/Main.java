/*
6
0 1 3
1 2 4
1 5 2
5 3 6
5 4 5
 */

/**
 * @author Meriton Dzemaili, Christian Wendlinger
 */

import utils.Node;
import utils.Tree;

import java.util.Scanner;

public class Main {

    /**
     * Algorithm to find Diameter of a given Tree in O(n) time
     *
     * @param args
     */
    public static void main(String[] args) {
        //parse Input
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        // create Node and them to the Tree
        Tree tree = new Tree();
        for (int i = 0; i < n; i++) {
            tree.addNode(i);
        }

        // parse Input edges and add them to the Tree
        for (int i = 0; i < n - 1; i++) {
            String[] input = scan.nextLine().split("\\s+");
            int name1 = Integer.parseInt(input[0]);
            int name2 = Integer.parseInt(input[1]);

            tree.addEdge(name1, name2, Integer.parseInt(input[2]));
        }

        // find longest path from root
        tree.assignWeights(0);

        // first Node = maxNode after weights were assigned
        Node resNode1 = tree.getMaxNode();
        int resName1 = resNode1.getName();

        //assign weight again starting from first Node
        tree.assignWeights(resName1);

        // result = path from first Node to second Node with weight from second Node
        String result = resName1 < tree.getMaxNode().getName() ? resName1 + " " + tree.getMaxNode().getName() + " " + tree.getMaxNode().getWeight() : tree.getMaxNode().getName() + " " + resName1 + " " + tree.getMaxNode().getWeight();
        System.out.println(result);
    }
}

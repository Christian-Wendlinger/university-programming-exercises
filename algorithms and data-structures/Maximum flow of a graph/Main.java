/*
6
9
1 2 7
1 3 7
1 5 3
2 4 8
3 4 5
3 5 2
4 0 7
4 5 7
5 0 7
 */

import utils.Graph;

import java.util.Scanner;

/**
 * @author Meriton Dzemaili, Christian Wendlinger
 */
public class Main {
    /**
     * Takes information about a Graph as input and prints its maximum flow
     *
     * @param args
     */
    public static void main(String[] args) {
        //Scanner to read input
        Scanner scan = new Scanner(System.in);

        //create necessary data structures
        int vertices = Integer.parseInt(scan.nextLine());
        int[][] graph = new int[vertices][vertices];

        //read edges and insert values into graph
        int edgeCount = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < edgeCount; i++) {
            graph[Integer.parseInt(scan.next())][Integer.parseInt(scan.next())] = Integer.parseInt(scan.next());
        }

        //print maximum flow
        System.out.println(new Graph(vertices, graph).findMaxFlow(1, 0));
    }
}
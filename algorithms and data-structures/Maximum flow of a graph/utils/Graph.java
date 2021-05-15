package utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Code based on https://algorithms.tutorialhorizon.com/max-flow-problem-ford-fulkerson-algorithm/
 */
public class Graph {
    int vertices;
    int[][] graph;

    /**
     * Constructor
     *
     * @param vertex
     * @param graph
     */
    public Graph(int vertex, int[][] graph) {
        this.vertices = vertex;
        this.graph = graph;
    }

    /**
     * Ford-Fulkerson algorithm to find the maximum flow
     *
     * @param source
     * @param sink
     * @return Integer value of maximum flow in Graph
     */
    public int findMaxFlow(int source, int sink) {
        int[][] residualGraph = new int[vertices][vertices];

        //initialize residual graph same as original graph
        for (int i = 0; i < vertices; i++) {
            System.arraycopy(graph[i], 0, residualGraph[i], 0, vertices);
        }

        //initialize parent [] to store the path Source to destination
        int[] parent = new int[vertices];

        //initialize the max flow
        int max_flow = 0;

        while (isPathExist_BFS(residualGraph, source, sink, parent)) {
            //if here means still path exist from source to destination
            //parent [] will have the path from source to destination
            //find the capacity which can be passed though the path (in parent[])
            int flow_capacity = Integer.MAX_VALUE;
            int t = sink;
            while (t != source) {
                int s = parent[t];
                flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
                t = s;
            }

            //update the residual graph
            //reduce the capacity on fwd edge by flow_capacity
            //add the capacity on back edge by flow_capacity
            t = sink;
            while (t != source) {
                int s = parent[t];
                residualGraph[s][t] -= flow_capacity;
                residualGraph[t][s] += flow_capacity;
                t = s;
            }

            //add flow_capacity to max value
            max_flow += flow_capacity;
        }
        return max_flow;
    }

    /**
     * Check if there is a path from a source to a destination
     *
     * @param residualGraph
     * @param src
     * @param dest
     * @param parent
     * @return if path from src to dest exists
     */
    public boolean isPathExist_BFS(int[][] residualGraph, int src, int dest, int[] parent) {
        boolean pathFound;
        //create visited array [] to
        //keep track of visited vertices
        boolean[] visited = new boolean[vertices];

        //Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        //insert the source vertex, mark it visited
        queue.add(src);
        parent[src] = -1;
        visited[src] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            //visit all the adjacent vertices
            for (int v = 0; v < vertices; v++) {
                //if vertex is not already visited and u-v edge weight >0
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        //check if dest is reached during BFS
        pathFound = visited[dest];
        return pathFound;
    }

}

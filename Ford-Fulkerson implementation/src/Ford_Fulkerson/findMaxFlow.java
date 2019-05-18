//Source Code taken from -https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
//improved by -S3Reuis, princiraj1992
package Ford_Fulkerson;


import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;
import java.lang.*;


public class findMaxFlow {

    private int V; //Number of nodes in graph


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = "Y";
        while (str.equals("Y") || str.equals("y")) {
            System.out.println(" Number of Nodes including Source and Target");
            // create random number of nodes btween 4-10 excluding source and target
            int numberOfNodes = (int) (Math.random() * 7 + 6);
            // setting up the multidimensional array for the graph
            int[][] graph = new int[numberOfNodes + 1][numberOfNodes + 1];
            System.out.println(numberOfNodes);

            System.out.println("The Graph Matrix : ");
            for (int row = 1; row <= numberOfNodes; row++) {
                for (int column = 1; column <= numberOfNodes; column++) {

                    if ((row == 1 && column == numberOfNodes) || (row == numberOfNodes && column == 1)) {
                        graph[row][column] = 0;

                    } else {
                        if (row == column) {
                            graph[row][column] = 0;
                        } else if (column == 1) {
                            graph[row][column] = 0;
                        } else if (row == numberOfNodes) {
                            graph[row][column] = 0;
                        } else if (column < row) {
                            graph[row][column] = 0;
                        } else {
                            graph[row][column] = (int) (Math.random() * 21 + 0);
                        }

                    }

                }
            }


            for (int row = 1; row <= numberOfNodes; row++) {
                for (int column = 1; column <= numberOfNodes; column++) {
                    System.out.print(" " + graph[row][column] + " ");
                }
                System.out.println(" ");
            }

            System.out.println("Source of the graph");
            int source = 1;
            System.out.println(source);

            System.out.println("Target of the graph");
            int sink = numberOfNodes;
            System.out.println(sink);

            findMaxFlow m = new findMaxFlow();

            System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, source, sink, numberOfNodes));
            System.out.println("do you want more flow network graph? Y/N");
            str = scanner.next();
        }


    }

    // Returns tne maximum flow
    public int fordFulkerson(int graph[][], int s, int t, int node) {
        int u, v;
        //creating the residual graph
        V = node + 1;
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        // This array is to store path
        int parent[] = new int[V];

        int max_flow = 0;

        //to traverse through graph to find the path
        while (bfs(rGraph, s, t, parent, V)) {
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to max flow
            max_flow += path_flow;
        }

        // Return the max flow
        return max_flow;
    }

    // To check the augment path is present?
    public boolean bfs(int rGraph[][], int s, int t, int parent[], int V) {
        //to check the path found earlier or not
        boolean visit[] = new boolean[V];
        for (int i = 0; i < V; ++i) {
            visit[i] = false;
        }


        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visit[s] = true;
        parent[s] = -1;


        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visit[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visit[v] = true;
                }
            }
        }


        return (visit[t] == true);
    }


}



package org.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
    private ArrayList<ArrayList<Integer>> createGraph(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        return adj;
    }
    @Test
    public void test_bfsOfGraph() {
        int V = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 0}, {2, 3}, {3, 3}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        // Instantiate the class containing bfsOfGraph
        App graphBFS = new App();
        assertEquals(expectedOutput, graphBFS.bfsOfGraph(V, adj));
    }

    @Test
    public void testDisconnectedGraph() {
        // Graph with disconnected components
        int V = 5;
        int[][] edges = {{0, 1}, {0, 2}, {3, 4}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 2));

        // Instantiate the class containing bfsOfGraph
        App graphBFS = new App();
        assertEquals(expectedOutput, graphBFS.bfsOfGraph(V, adj));
    }
    @Test
    public void testSingleNodeGraph() {
        // Single node graph
        int V = 1;
        int[][] edges = {};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0));

        // Instantiate the class containing bfsOfGraph
        App graphBFS = new App();
        assertEquals(expectedOutput, graphBFS.bfsOfGraph(V, adj));
    }
}
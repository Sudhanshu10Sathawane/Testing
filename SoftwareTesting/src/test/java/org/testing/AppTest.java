package org.testing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
    @Test
    public void test_dfsOfGraph() {
        int V = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 0}, {2, 3}, {3, 3}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        // Instantiate the class containing dfsOfGraph
        App graphDFS = new App();
        assertEquals(expectedOutput, graphDFS.dfsOfGraph(adj));
    }

    @Test
    public void testConnectedGraph() {
        int V = 5;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 3, 2, 4));

        // Instantiate the class containing dfsOfGraph
        App graphDFS = new App();
        assertEquals(expectedOutput, graphDFS.dfsOfGraph(adj));
    }

    @Test
    public void testDisconnectedGraph1() {
        // Graph with disconnected components
        int V = 6;
        int[][] edges = {{0, 1}, {0, 2}, {3, 4}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 2));

        // Instantiate the class containing dfsOfGraph
        App graphDFS = new App();
        assertEquals(expectedOutput, graphDFS.dfsOfGraph(adj));
    }

    @Test
    public void testSingleNodeGraph1() {
        // Single node graph
        int V = 1;
        int[][] edges = {};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0));

        // Instantiate the class containing dfsOfGraph
        App graphDFS = new App();
        assertEquals(expectedOutput, graphDFS.dfsOfGraph(adj));
    }

    @Test
    public void testGraphWithCycle() {
        // Graph with a cycle
        int V = 4;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}};
        ArrayList<ArrayList<Integer>> adj = createGraph(V, edges);

        ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        // Instantiate the class containing dfsOfGraph
        App graphDFS = new App();
        assertEquals(expectedOutput, graphDFS.dfsOfGraph(adj));
    }

    @Test
    public void testConnectedGraphDijkstra() {
        App app = new App();
        int v = 5;
        int[][] edges = {
                {0, 1, 2}, {0, 2, 4}, {1, 2, 1}, {1, 3, 7}, {2, 3, 3}, {3, 4, 1}
        };
        int src = 0;

        int[] expectedOutput = {0, 2, 3, 6, 7}; // Shortest paths from node 0
        assertArrayEquals(expectedOutput, app.dijkstra(v, edges, src));
    }

    @Test
    public void testDisconnectedGraphDijkstra() {
        App app = new App();
        int v = 6;
        int[][] edges = {
                {0, 1, 2}, {1, 2, 4}, {3, 4, 1}, {4, 5, 3}
        };
        int src = 0;

        int[] expectedOutput = {0, 2, 6, -1, -1, -1}; // Nodes 3, 4, 5 are unreachable
        assertArrayEquals(expectedOutput, app.dijkstra(v, edges, src));
    }

    @Test
    public void testSingleNodeGraphDijkstra() {
        App app = new App();
        int v = 1;
        int[][] edges = {};
        int src = 0;

        int[] expectedOutput = {0}; // Only one node, itself
        assertArrayEquals(expectedOutput, app.dijkstra(v, edges, src));
    }

    @Test
    public void testMultiplePathsGraphDijkstra() {
        App app = new App();
        int v = 4;
        int[][] edges = {
                {0, 1, 1}, {1, 2, 1}, {0, 2, 2}, {2, 3, 1}
        };
        int src = 0;

        int[] expectedOutput = {0, 1, 2, 3}; // Optimal path to each node
        assertArrayEquals(expectedOutput, app.dijkstra(v, edges, src));
    }

    @Test
    public void testNoEdgesGraphDijkstra() {
        App app = new App();
        int v = 3;
        int[][] edges = {};
        int src = 0;

        int[] expectedOutput = {0, -1, -1}; // No connections, all unreachable except the source
        assertArrayEquals(expectedOutput, app.dijkstra(v, edges, src));
    }

    @Test
    public void testSingleNodeGraphPrims() {
        App app = new App();
        int v = 1;
        int[][] edges = {};  // No edges

        int expectedOutput = 0;  // No edges, MST weight is 0
        assertEquals(expectedOutput, app.primsMST(v, edges));
    }

    @Test
    public void testShortestPathBinaryMatrix1() {
        int[][] grid = {
                {0, 1},
                {1, 0}
        };

        App app = new App();
        int result = app.shortestPathBinaryMatrix(grid);
        assertEquals(2, result);  // Expected output is 2
    }

    @Test
    public void testShortestPathBinaryMatrix2() {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        App app = new App();
        int result = app.shortestPathBinaryMatrix(grid);
        assertEquals(-1, result);  // Expected output is 4
    }

    @Test
    public void testShortestPathBinaryMatrix3() {
        int[][] grid = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        App app = new App();
        int result = app.shortestPathBinaryMatrix(grid);
        assertEquals(-1, result);  // Expected output is -1 as there is no clear path
    }

    @Test
    public void testShortestPathBinaryMatrixEdgeCase() {
        int[][] grid = {
                {0}
        };

        App app = new App();
        int result = app.shortestPathBinaryMatrix(grid);
        assertEquals(1, result);  // Expected output is 1 as there's only one cell, which is the destination
    }

    @Test
    public void testShortestPathBinaryMatrixNoPath() {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };

        App app = new App();
        int result = app.shortestPathBinaryMatrix(grid);
        assertEquals(-1, result);  // Expected output is -1 as there's no valid path
    }

    @Test
    public void testBellmanFord1() {
        int[][] edges = {
                {0, 1, 9}
        };
        int src = 0;
        int v = 2;
        int e = 1;

        App app = new App();
        int[] result = app.bellmanFord(v, e, edges, src);
        int[] expected = {0, 9};
        assertArrayEquals(expected, result);  // Expected output is [0, 9]
    }

    @Test
    public void testBellmanFord2() {
        int[][] edges = {
                {0, 1, 5}, {1, 0, 3}, {1, 2, -1}, {2, 0, 1}
        };
        int src = 2;
        int v = 3;
        int e = 4;

        App app = new App();
        int[] result = app.bellmanFord(v, e, edges, src);
        int[] expected = {1, 6, 0};
        assertArrayEquals(expected, result);  // Expected output is [1, 6, 0]
    }

    @Test
    public void testBellmanFordNegativeCycle() {
        int[][] edges = {
                {0, 1, 9}, {1, 2, -10}, {2, 0, -10}
        };
        int src = 0;
        int v = 3;
        int e = 3;

        App app = new App();
        int[] result = app.bellmanFord(v, e, edges, src);
        int[] expected = {-1};
        assertArrayEquals(expected, result);  // Expected output is [-1] due to negative cycle
    }

    @Test
    public void testBellmanFordUnreachableNodes() {
        int[][] edges = {
                {0, 1, 5}, {1, 2, 3}
        };
        int src = 0;
        int v = 4;
        int e = 2;

        App app = new App();
        int[] result = app.bellmanFord(v, e, edges, src);
        int[] expected = {0, 5, 8, 100000000};  // Node 3 is unreachable, so distance is 10^8
        assertArrayEquals(expected, result);  // Expected output is [0, 5, 8, 100000000]
    }
    @Test
    public void testCycleInGraph1() {
        // Graph with a cycle: 3 -> 3
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1); // 0 -> 1
        adj.get(1).add(2); // 1 -> 2
        adj.get(2).add(3); // 2 -> 3
        adj.get(3).add(3); // 3 -> 3 (Cycle)

        App app = new App();
        int result = app.isCycle(4, adj);
        assertEquals(1, result);  // Expected output is 1 (cycle detected)
    }

    @Test
    public void testCycleInGraph2() {
        // Graph without a cycle
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1); // 0 -> 1
        adj.get(1).add(2); // 1 -> 2
        adj.get(2).add(3); // 2 -> 3

        App app = new App();
        int result = app.isCycle(4, adj);
        assertEquals(0, result);  // Expected output is 0 (no cycle)
    }

    @Test
    public void testCycleInGraph3() {
        // Graph with a cycle: 0 -> 1 -> 2 -> 0
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1); // 0 -> 1
        adj.get(1).add(2); // 1 -> 2
        adj.get(2).add(0); // 2 -> 0 (Cycle)

        App app = new App();
        int result = app.isCycle(3, adj);
        assertEquals(1, result);  // Expected output is 1 (cycle detected)
    }

    @Test
    public void testCycleInGraph4() {
        // Graph with a single node, no cycle
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>());

        App app = new App();
        int result = app.isCycle(1, adj);
        assertEquals(0, result);  // Expected output is 0 (no cycle)
    }
    @Test
    public void testCanFinishCourses1() {
        // Test case: 2 courses, prerequisites [[1, 0]]
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        App app = new App();
        boolean result = app.canFinish(numCourses, prerequisites);
        assertTrue(result);  // Expected: true, because we can take course 0 first, then course 1
    }

    @Test
    public void testCanFinishCourses2() {
        // Test case: 2 courses, prerequisites [[1, 0], [0, 1]]
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        App app = new App();
        boolean result = app.canFinish(numCourses, prerequisites);
        assertFalse(result);  // Expected: false, because there's a cycle: 0 -> 1 -> 0
    }

    @Test
    public void testCanFinishCourses3() {
        // Test case: 3 courses, prerequisites [[1, 0], [2, 1]]
        int numCourses = 3;
        int[][] prerequisites = {{1, 0}, {2, 1}};

        App app = new App();
        boolean result = app.canFinish(numCourses, prerequisites);
        assertTrue(result);  // Expected: true, no cycle, we can complete all courses
    }

    @Test
    public void testCanFinishCourses4() {
        // Test case: 4 courses, prerequisites [[1, 0], [2, 1], [3, 2]]
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};

        App app = new App();
        boolean result = app.canFinish(numCourses, prerequisites);
        assertTrue(result);  // Expected: true, no cycle, we can complete all courses
    }

    @Test
    public void testCanFinishCourses5() {
        // Test case: 4 courses, prerequisites [[1, 0], [2, 0], [3, 1], [3, 2]]
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        App app = new App();
        boolean result = app.canFinish(numCourses, prerequisites);
        assertTrue(result);  // Expected: true, no cycle, all courses can be finished
    }

    @Test
    public void testMergeSort1() {
        int[] arr = {4, 1, 3, 9, 7};
        App.mergeSort(arr, 0, arr.length - 1);
        int[] expected = {1, 3, 4, 7, 9};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMergeSort2() {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        App.mergeSort(arr, 0, arr.length - 1);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMergeSort3() {
        int[] arr = {1, 3, 2};
        App.mergeSort(arr, 0, arr.length - 1);
        int[] expected = {1, 2, 3};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort1() {
        int[] arr = {4, 1, 3, 9, 7};
        App.quickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 3, 4, 7, 9};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testQuickSort2() {
        int[] arr = {2, 1, 6, 10, 4, 1, 3, 9, 7};
        App.quickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 1, 2, 3, 4, 6, 7, 9, 10};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testCharacterReplacement1() {
        // Test case 1: Input: "ABAB", k = 2
        assertEquals(4, App.characterReplacement("ABAB", 2));
    }

    @Test
    public void testCharacterReplacement2() {
        // Test case 2: Input: "AABABBA", k = 1
        assertEquals(4, App.characterReplacement("AABABBA", 1));
    }

    @Test
    public void testCharacterReplacement3() {
        // Test case 3: Input: "AAABBB", k = 2
        assertEquals(5, App.characterReplacement("AAABBB", 2));
    }

    @Test
    public void testCharacterReplacement4() {
        // Test case 4: Input: "AAAA", k = 0
        assertEquals(4, App.characterReplacement("AAAA", 0));
    }

    @Test
    public void testCharacterReplacement5() {
        // Test case 5: Input: "ABCDE", k = 1
        assertEquals(2, App.characterReplacement("ABCDE", 1));

    }




}
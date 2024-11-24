package org.testing;


import java.util.*;

class UtilityMethods {


}

public class App {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n = V;
        int m = adj.size();

        ArrayList<Integer> bfs = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        int[] visit = new int[n];

        q.offer(0);
        visit[0] = 1;

        while(!q.isEmpty()){
            Integer ele = q.poll();
            bfs.add(ele);

            for(Integer it : adj.get(ele)){
                if(visit[it]==0){
                    q.offer(it);
                    visit[it] = 1;
                }
            }
        }

        return bfs;

    }

    public static void dfs(int node,boolean visit[] , ArrayList<Integer> ls,ArrayList<ArrayList<Integer>> adj){
        ls.add(node);
        visit[node]=true;

        for(Integer it : adj.get(node)){
            if(!visit[it]){
                dfs(it,visit,ls,adj);
            }
        }
    }
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        int size=adj.size();
        boolean[] vist = new boolean[size];
        ArrayList<Integer> ls = new ArrayList<>();
        dfs(0,vist,ls,adj);
        return ls;
    }

    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int[] dijkstra(int v, int[][] edges, int src) {
        // Create adjacency list
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Edge(edge[1], edge[2]));
            adj.get(edge[1]).add(new Edge(edge[0], edge[2])); // For undirected graph
        }

        // Distance array
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Priority queue to store {distance, node}
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currNode = current.node;
            int currWeight = current.weight;

            // Traverse neighbors
            for (Edge neighbor : adj.get(currNode)) {
                int nextNode = neighbor.node;
                int edgeWeight = neighbor.weight;

                if (currWeight + edgeWeight < dist[nextNode]) {
                    dist[nextNode] = currWeight + edgeWeight;
                    pq.add(new Edge(nextNode, dist[nextNode]));
                }
            }
        }

        // Replace unreachable nodes with -1
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    public int primsMST(int v, int[][] edges) {
        if (v == 1) {
            return 0; // If there's only one node, return 0 as there are no edges
        }

        // Step 1: Prepare adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        // Step 2: Min-heap for selecting the edge with the minimum weight
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[v];
        int mstWeight = 0;
        int edgesUsed = 0;

        // Start from node 0
        pq.add(new int[]{0, 0}); // {node, weight}

        while (!pq.isEmpty() && edgesUsed < v - 1) { // Stop when MST is complete (v-1 edges)
            int[] current = pq.poll();
            int node = current[0];
            int weight = current[1];

            if (visited[node]) continue; // Skip if already included in MST

            mstWeight += weight; // Add edge weight to MST weight
            visited[node] = true;
            edgesUsed++;

            // Add adjacent edges to the priority queue
            for (int[] neighbor : adj.get(node)) {
                if (!visited[neighbor[0]]) {
                    pq.add(new int[]{neighbor[0], neighbor[1]});
                }
            }
        }

        // If there are less than v-1 edges used, the graph is disconnected
        if (edgesUsed != v - 1) {
            return -1; // MST not possible (graph is disconnected)
        }

        return mstWeight;
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // If the starting or ending point is blocked, return -1 immediately
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        // Directions for 8 possible moves (up, down, left, right, diagonals)
        int[] dirs = {-1, 0, 1, 0, -1, -1, 1, 1}; // 8 directions
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});  // Start from the top-left corner
        grid[0][0] = 1;  // Mark the starting point as visited

        int pathLength = 1;  // Length of the current path

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                // If we have reached the bottom-right corner
                if (x == n - 1 && y == n - 1) {
                    return pathLength;
                }

                // Explore all 8 possible directions
                for (int d = 0; d < dirs.length; d += 2) {
                    int nx = x + dirs[d];
                    int ny = y + dirs[d + 1];

                    // Check if the new position is within bounds and is a free cell (0)
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;  // Mark the cell as visited
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            pathLength++;  // Increase the path length for each level of BFS
        }

        return -1;  // Return -1 if there's no path to the bottom-right corner
    }

    static final int INF = 100000000; // A large number representing infinity

    public int[] bellmanFord(int x, int e, int[][] edges, int src) {
        int[] distance = new int[x];
        Arrays.fill(distance, INF);
        distance[src] = 0; // Distance to source is 0

        // Relax edges v-1 times
        for (int i = 1; i <x; i++) {
            for (int j = 0; j < e; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];

                // If the distance to u is not INF, relax the edge (u, v)
                if (distance[u] != INF && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative weight cycle
        for (int i = 0; i < e; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];

            // If we can still relax the edge, it means there's a negative cycle
            if (distance[u] != INF && distance[u] + weight < distance[v]) {
                return new int[] {-1}; // Negative cycle detected
            }
        }

        // Convert unreachable nodes from INF to a large number (10^8)
        for (int i = 0; i < x; i++) {
            if (distance[i] == INF) {
                distance[i] = 100000000; // Mark as unreachable
            }
        }

        return distance;
    }

    public int isCycle(int v, List<List<Integer>> adj) {
        int[] visited = new int[v];  // visited array to store 0: Unvisited, 1: Visiting, 2: Visited

        // Perform DFS for each unvisited node
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                if (dfsCycleDetection(i, adj, visited)) {
                    return 1;  // Cycle detected
                }
            }
        }
        return 0;  // No cycle
    }

    private boolean dfsCycleDetection(int node, List<List<Integer>> adj, int[] visited) {
        visited[node] = 1;  // Mark the current node as "Visiting"

        // Recur for all adjacent nodes
        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 1) {
                // If the neighbor is already in "Visiting" state, a cycle is detected
                return true;
            }
            if (visited[neighbor] == 0 && dfsCycleDetection(neighbor, adj, visited)) {
                return true;
            }
        }

        visited[node] = 2;  // Mark the current node as "Visited"
        return false;  // No cycle found
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
        }

        // Step 2: DFS to detect cycle
        int[] visited = new int[numCourses];  // 0: unvisited, 1: visiting, 2: visited
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (dfs(i, adj, visited)) {
                    return false;  // Cycle detected
                }
            }
        }
        return true;  // No cycle, all courses can be completed
    }

    private boolean dfs(int node, List<List<Integer>> adj, int[] visited) {
        if (visited[node] == 1) {  // Node is in the current DFS stack, cycle found
            return true;
        }
        if (visited[node] == 2) {  // Node already fully processed, no cycle
            return false;
        }

        visited[node] = 1;  // Mark as visiting

        // Visit all adjacent nodes
        for (int neighbor : adj.get(node)) {
            if (dfs(neighbor, adj, visited)) {
                return true;  // Cycle detected
            }
        }

        visited[node] = 2;  // Mark as visited (processed)
        return false;  // No cycle found
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;  // Find middle point

            // Recursively sort the first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // Function to merge two halves
    private static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two sub-arrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays L[] and R[]
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        // Merge the temporary arrays back into arr[l..r]
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements of L[], if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy any remaining elements of R[], if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Find pivot such that arr[pivot] is in correct position
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the left and right subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Function to partition the array and return the pivot index
    private static int partition(int[] arr, int low, int high) {
        // Pivot is the last element in the array
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        // Traverse through the array and rearrange elements
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i + 1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the pivot index
        return i + 1;
    }
    public static int characterReplacement(String s, int k) {
        // Map to store the frequency of characters in the current window
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        int left = 0, maxLength = 0, maxCount = 0;

        // Iterate through the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            // Update the frequency map with the character at the right pointer
            frequencyMap.put(s.charAt(right), frequencyMap.getOrDefault(s.charAt(right), 0) + 1);

            // Find the count of the most frequent character in the current window
            maxCount = Math.max(maxCount, frequencyMap.get(s.charAt(right)));

            // If the number of replacements needed exceeds k, shrink the window from the left
            if (right - left + 1 - maxCount > k) {
                frequencyMap.put(s.charAt(left), frequencyMap.get(s.charAt(left)) - 1);
                left++;
            }

            // Update the result with the length of the current valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }




    public static void main(String[] args) {
    }
}
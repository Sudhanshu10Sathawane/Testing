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
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Step 1: Initialize the queue with all initially rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) return 0; // No fresh oranges to begin with

        int minutes = -1; // Tracks the number of minutes elapsed
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Step 2: Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++; // Each level in BFS represents 1 minute

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0], col = current[1];

                // Visit all 4 adjacent cells
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check if the adjacent cell is within bounds and contains a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Mark the orange as rotten
                        queue.add(new int[]{newRow, newCol});
                        freshOranges--; // Decrease the count of fresh oranges
                    }
                }
            }
        }

        return freshOranges == 0 ? minutes : -1; // If no fresh oranges are left, return minutes; otherwise, -1
    }
    // Entry method for flood fill
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // Edge case: If the color is already the same as the new color, return the image
        if (image[sr][sc] == color) {
            return image;
        }

        // Store the original color of the starting pixel
        int originalColor = image[sr][sc];

        // Perform the fill operation using a helper method
        fill(image, sr, sc, originalColor, color);

        // Return the modified image
        return image;
    }

    // Recursive helper method for flood fill
    private void fill(int[][] image, int row, int col, int originalColor, int newColor) {
        // Check if the current position is out of bounds
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }

        // Check if the current pixel does not match the original color
        if (image[row][col] != originalColor) {
            return;
        }

        // Change the color of the current pixel to the new color
        image[row][col] = newColor;

        // Recursive calls to fill adjacent pixels
        fill(image, row + 1, col, originalColor, newColor); // Down
        fill(image, row - 1, col, originalColor, newColor); // Up
        fill(image, row, col + 1, originalColor, newColor); // Right
        fill(image, row, col - 1, originalColor, newColor); // Left
    }

    // Iterative version of flood fill (BFS approach)
    public int[][] floodFillIterative(int[][] image, int sr, int sc, int color) {
        // Edge case: If the color is already the same as the new color, return the image
        if (image[sr][sc] == color) {
            return image;
        }

        // Initialize the queue for BFS
        Queue<int[]> queue = new LinkedList<>();

        // Store the original color of the starting pixel
        int originalColor = image[sr][sc];

        // Add the starting pixel to the queue
        queue.add(new int[]{sr, sc});

        // Directions for moving up, down, left, and right
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Process each pixel in the queue
        while (!queue.isEmpty()) {
            // Remove the front element from the queue
            int[] current = queue.poll();

            // Get the row and column of the current pixel
            int row = current[0];
            int col = current[1];

            // Change the color of the current pixel to the new color
            image[row][col] = color;

            // Explore all adjacent pixels
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the adjacent pixel is valid and matches the original color
                if (isValid(image, newRow, newCol, originalColor)) {
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        // Return the modified image
        return image;
    }

    // Helper method to check if a pixel is valid
    private boolean isValid(int[][] image, int row, int col, int originalColor) {
        // Check if the position is within bounds
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return false;
        }

        // Check if the pixel matches the original color
        return image[row][col] == originalColor;
    }
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Mark boundary connected 'O's with a temporary marker ('T')
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            dfs(board, 0, j);
            dfs(board, rows - 1, j);
        }

        // Replace all remaining 'O's with 'X' and revert 'T' back to 'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'T'; // Mark as visited
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Eliminate land cells connected to the boundary
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][cols - 1] == 1) dfs(grid, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[rows - 1][j] == 1) dfs(grid, rows - 1, j);
        }

        // Count remaining land cells
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) count++;
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) return;

        grid[i][j] = 0; // Mark the cell as visited
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // Array to store colors of nodes (-1 means uncolored)
        Arrays.fill(colors, -1); // Initialize all nodes as uncolored

        // Try to color each uncolored node (in case the graph is disconnected)
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1 && !dfs(graph, i, 0, colors)) {
                return false; // If any component is not bipartite, return false
            }
        }
        return true; // If no conflicts, the graph is bipartite
    }

    // Function to perform DFS and try to color the graph
    private boolean dfs(int[][] graph, int node, int color, int[] colors) {
        if (colors[node] != -1) {
            return colors[node] == color; // If node is already colored, check if it matches the desired color
        }
        colors[node] = color; // Color the node with the current color
        // Recurse for all adjacent nodes
        for (int neighbor : graph[node]) {
            if (!dfs(graph, neighbor, 1 - color, colors)) {
                return false; // If a conflict occurs, return false
            }
        }
        return true;
    }
    public int perfectSum(int[] arr, int target) {
        int n = arr.length;
        int MOD = 1000000007;
        Integer[][] memo = new Integer[n + 1][target + 1];  // Memoization table
        return findWays(arr, n, target, memo, MOD);
    }

    // Recursive function with memoization
    private int findWays(int[] arr, int index, int target, Integer[][] memo, int MOD) {
        // Base cases
        if (target == 0) {
            return 1;  // There's one way to form sum 0: by choosing the empty subset
        }
        if (index == 0) {
            return (arr[0] == target) ? 1 : 0;  // If only one element is left, check if it matches the target
        }

        // If the result is already computed, return it
        if (memo[index][target] != null) {
            return memo[index][target];
        }

        // Option 1: Do not pick the current element, move to the next element
        int notPick = findWays(arr, index - 1, target, memo, MOD);

        // Option 2: Pick the current element (if it's less than or equal to the target)
        int pick = 0;
        if (arr[index - 1] <= target) {
            pick = findWays(arr, index - 1, target - arr[index - 1], memo, MOD);
        }

        // Store the result in the memoization table and return it
        memo[index][target] = (pick + notPick) % MOD;
        return memo[index][target];
    }
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;

        // Arrays to store the left_max and right_max values
        int[] left_max = new int[n];
        int[] right_max = new int[n];

        // Initialize the left_max array
        left_max[0] = height[0];
        for (int i = 1; i < n; i++) {
            left_max[i] = Math.max(left_max[i - 1], height[i]);
        }

        // Initialize the right_max array
        right_max[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], height[i]);
        }

        // Calculate the total amount of water trapped
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(left_max[i], right_max[i]) - height[i];
        }

        return totalWater;
    }





    public static void main(String[] args) {
    }
}
/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is 
not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

import java.util.SortedMap;

// Solution : Construct graph and find longest path 
// O(n^2)
public class Solution {
    // n - row
    // m - col
    int n, m;
    List<List<Integer>> graph;
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int[] dp;
    
    public int getLongestPath(int node){
        if(dp[node] != -1)
            return dp[node];
        
        int longestPath = 1;
        List<Integer> adj = graph.get(node);
        if(adj != null){
            for(int neighbor : adj){
                longestPath = Math.max(longestPath, getLongestPath(neighbor) + 1);
            }
        }
        dp[node] = longestPath;
        return longestPath;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        n = matrix.length;
        if(n == 0)
            return 0;
        m = matrix[0].length;
        int nodes = n*m;
        // Construct the graph
        graph = new ArrayList<>();
        for(int i=0; i<nodes; ++i)
            graph.add(new ArrayList<>());
            
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                int id = r*m + c;
                for(int i=0; i<4; i++){
                    int r1 = r + move[i][0];            
                    int c1 = c + move[i][1];
                    if(r1 < 0 || r1 >= n || c1 < 0 || c1 >= m || matrix[r1][c1] <= matrix[r][c])
                        continue;
                    int id1 = r1*m + c1;
                    // directed graph
                    graph.get(id).add(id1);
                }
            }
        }
        // Find the longest path
        dp = new int[nodes];
        Arrays.fill(dp, -1);
        
        int maxLen = 0;
        for(int i=0; i<nodes; ++i){
            maxLen = Math.max(maxLen, getLongestPath(i));
        }
        return maxLen;
    }
}

// Time complexity: O(rc)
// longest path (No need to construct the graph before)
// longest path DP
class Solution2 {
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int longestPath(int[][] matrix, int r, int c, int[][] d){
        if(d[r][c] != 0)
            return d[r][c];
        
        int len = 0;
        for(int i=0; i<4; i++){
            int r1 = r+move[i][0];            
            int c1 = c+move[i][1];
            if(r1 < 0 || r1 >= matrix.length || c1 < 0 || c1 >= matrix[0].length || matrix[r][c] >= matrix[r1][c1])
                continue;
            len = Math.max(len, longestPath(matrix, r1, c1, d));
        } 
        d[r][c] = len + 1;
        return d[r][c]; 
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) 
            return 0;

        int[][] d = new int[matrix.length][matrix[0].length];
        int maxLength = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLength = Math.max(maxLength, longestPath(matrix, i, j, d));
            }
        }
        return maxLength;
    }
}

// O(mn) Solution (Similar as Solution 2)
class Solution3 {
    // n - row
    // m - col
    int n, m;
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int getLongestPath(int[][] matrix, int nodeId, int[] dp){
        if(dp[nodeId] != -1)
            return dp[nodeId];
        
        // Get row and col
        int r = nodeId / m;
        int c = nodeId % m;
        int longestPath = 1;
        for(int i=0; i<4; i++){
            int r1 = r + move[i][0];            
            int c1 = c + move[i][1];
            if(r1 < 0 || r1 >= n || c1 < 0 || c1 >= m || matrix[r1][c1] <= matrix[r][c])
                continue;
            longestPath =  Math.max(longestPath, getLongestPath(matrix, r1*m + c1, dp) + 1);
        }        
        dp[nodeId] = longestPath;
        return longestPath;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        n = matrix.length;
        if(n == 0)
            return 0;
        m = matrix[0].length;
        int nodes = n*m;

        // Find the longest path
        int[] dp = new int[nodes];
        Arrays.fill(dp, -1);

        int maxLen = 0;
        for(int i=0; i<nodes; ++i){
            maxLen = Math.max(maxLen, getLongestPath(matrix, i, dp));
        }
        return maxLen;
    }
}

// Topological Sort + DP
class Solution3 {
    class Pair{
        public int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        int nRow = matrix.length;
        if(nRow == 0)
            return 0;
        int nCol = matrix[0].length;
        
        // TreeMap Sorted by key value
        SortedMap<Integer, ArrayList<Pair>> mapping = new TreeMap<Integer, ArrayList<Pair>>();
        // Construct the graph
        for(int i=0; i<nRow; i++){
            for(int j=0; j<nCol; j++){
                if(mapping.containsKey(matrix[i][j])){
                    ArrayList<Pair> l = mapping.get(matrix[i][j]);
                    Pair p = new Pair(i, j);
                    l.add(p);
                    mapping.put(matrix[i][j], l);
                }
                else{
                    ArrayList<Pair> l = new ArrayList<Pair>();
                    l.add(new Pair(i, j));
                    mapping.put(matrix[i][j], l);
                }
            }
        }
        
        int[][] move= {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int x, y, x1, y1;
        int cnt = 0;
        int[][] dp = new int[nRow][nCol]; 
        int maxLength = 0;
        for(Map.Entry<Integer, ArrayList<Pair>> entry : mapping.entrySet()){
            int key = entry.getKey();
            List<Pair> indices = entry.getValue();
            for(Pair p : indices){
                x = p.x;
                y = p.y;
                if(cnt == 0){
                    dp[x][y] = 1;             
                }
                else{
                    int maxValue = 0;
                    for(int i=0; i<4; i++){
                       x1 =  x + move[i][0];     
                       y1 =  y + move[i][1];
                       if(!(x1 >= 0 && x1 < nRow && y1 >= 0 && y1 < nCol))
                            continue;
                       // All numbers adjacent to position (x, y) >= matrix[x][y]
                       if(maxValue < dp[x1][y1]  && matrix[x1][y1] != matrix[x][y]){
                           maxValue = dp[x1][y1];
                       }
                    }
                    dp[x][y] = maxValue + 1;
                }
                if(maxLength < dp[x][y])
                    maxLength = dp[x][y];
            }
            cnt++;
        }
        return maxLength;
    }
}

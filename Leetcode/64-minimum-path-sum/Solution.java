/*
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/
public class Solution {
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        if(r == 0)
            return 0;

        int c = grid[0].length;
        int[][] dp = new int[r][c];
        // init
        dp[0][0] = grid[0][0];
        // first col
        for(int i=1; i<r; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0]; 

        // first row
        for(int j=1; j<c; j++)
            dp[0][j] = dp[0][j-1] + grid[0][j]; 
            
        for(int i=1; i<r; i++)
            for(int j=1; j<c; j++)
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];    

        return dp[r-1][c-1];
    }
}

// Memoization
class Solution2 {
    int m, n;
    int[][] dp;
    int[][] grid;

    public int getMinPathSum(int r, int c) {
        if(r < 0 || c < 0)
            return Integer.MAX_VALUE;
        if(r == 0 && c == 0)
            return grid[r][c];
        if(dp[r][c] != -1)
            return dp[r][c];
        
        dp[r][c] = Math.min(getMinPathSum(r-1, c), getMinPathSum(r, c-1)) + grid[r][c];
        return dp[r][c];
    }

    public int minPathSum(int[][] g) {
        grid = g;
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return getMinPathSum(m-1, n-1);
    }
}

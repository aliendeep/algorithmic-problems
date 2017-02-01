/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the 
diagram below).

The robot can only move either down or right at any point in time. The robot is 
trying to reach the bottom-right corner of the grid (marked 'Finish' in the 
diagram below).

How many possible unique paths are there?

Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/
public class Solution {
    //dp[i][j] = dp[i-1][j] + dp[i][j-1];
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0)    return 0;
        int dp[][] = new int[m][n];

        for(int i=0; i<m; i++)
            dp[i][0] = 1;

        for(int j=0; j<n; j++)
            dp[0][j] = 1;

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
            }
        }
        return dp[m-1][n-1];
    }
}

// Memoization
class Solution2 {
    public int cntPaths(int m, int n, int[][] dp) {
        if(m < 0 || n < 0)      return 0;
        if(m == 0 || n == 0)    return 1;
        if(dp[m][n] != -1)
            return dp[m][n];
        dp[m][n] = cntPaths(m-1, n, dp) + cntPaths(m, n-1, dp);
        return dp[m][n];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return cntPaths(m-1, n-1, dp);
    }
}


// Reduced space
class Solution3 {
    //dp[i][j] = dp[i-1][j] + dp[i][j-1];
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0)    return 0;
        int dp[][] = new int[2][n];

        for(int i=0; i<m; i++)
            dp[i%2][0] = 1;

        for(int j=0; j<n; j++)
            dp[0][j] = 1;

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i%2][j] = dp[(i-1)%2][j] + dp[i%2][j-1]; 
            }
        }
        return dp[(m-1)%2][n-1];
    }
}

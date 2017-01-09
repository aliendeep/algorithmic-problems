/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths 
would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int nRows = obstacleGrid.length;
        if(nRows == 0)    
            return 0;
        int nCols =  obstacleGrid[0].length;
        // if any obstacle is in starting or ending position, number of unique paths = 0
        if(obstacleGrid[0][0] == 1 || obstacleGrid[nRows-1][nCols-1] == 1)  
            return 0;
            
        int dp[][] = new int[nRows][nCols]; 
        
        // initialization
        for(int i=0; i<nRows; i++)
            for(int j=0; j<nCols; j++)
                dp[i][j] = 0;
                
        dp[0][0] = 1; 
        for(int i=1; i<nRows; i++)
            if(obstacleGrid[i][0] == 0)    
                dp[i][0] = dp[i-1][0];

        for(int j=1; j<nCols; j++)
            if(obstacleGrid[0][j] == 0)    
                dp[0][j] = dp[0][j-1];

        for(int i=1; i<nRows; i++){
            for(int j=1; j<nCols; j++){
                if(obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
            }
        }
        return dp[nRows-1][nCols-1];    
    }
}

// Memoization
public class Solution {
    public int cntPaths(int m, int n, int[][] dp, int[][] obstacleGrid) {
        if(m < 0 || n < 0)              return 0;
        if(obstacleGrid[m][n] == 1)     return 0;
        if(m == 0 && n == 0)            return 1;

        if(dp[m][n] != -1)
            return dp[m][n];

        dp[m][n] = cntPaths(m-1, n, dp, obstacleGrid) + cntPaths(m, n-1, dp, obstacleGrid);
        return dp[m][n];
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if(m == 0)      return 0;
        int n =  obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)  
            return 0;
            
        int[][] dp = new int[m][n];

        for(int[] t : dp)
            Arrays.fill(t, -1);
        return cntPaths(m-1, n-1, dp, obstacleGrid);
    }
}

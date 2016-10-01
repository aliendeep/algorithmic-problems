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
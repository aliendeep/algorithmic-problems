class Solution {
public:
    // dp[i][j] = dp[i-1][j] + dp[i][j-1] 
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        if(m == 0)    return 0;
        int n = obstacleGrid[0].size();
        if(n == 0)    return 0;
        
        vector<vector<int>> dp(m+1, vector<int>(n+1, 0));
        if(obstacleGrid[0][0] || obstacleGrid[m-1][n-1])  
            return 0;
        
        // init
        dp[0][0] = 1;
        for(int i=1; i<m; i++){
            if(!obstacleGrid[i][0])
                dp[i][0] = dp[i-1][0];
        }
        
        for(int j=1; j<n; j++){
            if(!obstacleGrid[0][j])
                dp[0][j] = dp[0][j-1];
        }
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(!obstacleGrid[i][j])
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] ;
            }
        }
        return dp[m-1][n-1];        
    }
};
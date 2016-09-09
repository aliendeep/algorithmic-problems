class Solution {
public:
    // DP
    // Find the minimum number of steps required to convert word1 to word2
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        if(m == 0 && n == 0)        return 0;      
        if(m == 0)  return n;
        if(n == 0)  return m;
        vector<vector<int>> dp(m+1, vector<int>(n+1, INT_MAX));
        
        for(int i=0; i<=m; i++)
            dp[i][0] = i;
            
        for(int j=0; j<=n; j++)
            dp[0][j] = j;
            
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                // replace
                int x = dp[i-1][j-1] + (word1[i-1] != word2[j-1]);
                int y = dp[i-1][j] + 1;
                int z = dp[i][j-1] + 1;
                dp[i][j] = min(x, min(y, z));
            }
        }
        return dp[m][n];
    }
};
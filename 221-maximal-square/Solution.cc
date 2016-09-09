class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int r = matrix.size();
        if(r == 0)  return 0;
        int c = matrix[0].size();
        vector<vector<int>> dp(r, vector<int>(c, 0));

        int maxLen = 0;
        // first col
        for(int i=0; i<r; i++){
            dp[i][0] = matrix[i][0] - '0';
            maxLen = max(maxLen, dp[i][0]);
        }
        
        // first row
        for(int j=0; j<c; j++){
            dp[0][j] = matrix[0][j] - '0';
            maxLen = max(maxLen, dp[0][j]);
        }
        for(int i=1; i<r; i++){
            for(int j=1; j<c; j++){
                if(matrix[i][j] == '1')
                    dp[i][j] = min(dp[i-1][j], min(dp[i][j-1], dp[i-1][j-1])) + 1; 
                maxLen = max(maxLen, dp[i][j]);
            }            
        }
        // Return the area
        return maxLen*maxLen;
    }
};
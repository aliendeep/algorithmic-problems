/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's 
and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/
public class Solution {
    // dp[i][j] represents size of the maximal square sub-matrix with all 1s including matrix[i][j] 
    public int maximalSquare(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)
            return 0;
        int c = matrix[0].length;
        
        int maxLen = 0;            
        int[][] dp = new int[r][c];
        // init
        for(int j=0; j<c; j++){
            if(matrix[0][j] == '1'){
                dp[0][j] = 1;
                maxLen = 1;
            }
        }
        // first col
        for(int i=0; i<r; i++){
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                maxLen = 1;
            }
        }
        for(int i=1; i<r; i++){
            for(int j=1; j<c; j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);            
               }
            }
        }
        return maxLen*maxLen;
    }
}

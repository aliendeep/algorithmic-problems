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
// Time Complexity: O(n^2)
// Space: O(n^2)
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

// Time Complexity: O(n^2)
// Space: O(n)
class Solution2 {
    // Space reduced solution
    // Only keep current column and previous column
    public int maximalSquare(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)          return 0;
        int c = matrix[0].length;
        int maxLen = 0;
        // prev column
        int[] prev = new int[r+1];
        // current col
        int[] cur = new int[r+1];

        // first column
        for(int i=0; i<r; ++i){
            prev[i] = matrix[i][0] - '0';
            maxLen = Math.max(maxLen, prev[i]);  
        }
        
        // for all columns
        for(int j=1; j<c; ++j){
            cur[0] = matrix[0][j] - '0';
            maxLen = Math.max(maxLen, cur[0]);  
            for(int i=1; i<r; ++i){
                if(matrix[i][j] == '1'){
                    cur[i] = Math.min(cur[i-1], Math.min(prev[i], prev[i-1])) + 1;
                    maxLen = Math.max(maxLen, cur[i]);  
                }
            }
            prev = Arrays.copyOf(cur, cur.length);
            Arrays.fill(cur, 0);
        }
        return maxLen*maxLen;        
    }
}

// Time Complexity: O(n^2)
// Space: O(n)
class Solution3 {
    // Space reduced solution
    // Only keep current row and previous row
    public int maximalSquare(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)          return 0;
        int c = matrix[0].length;
        int maxLen = 0;
        // prev row
        int[] prev = new int[c+1];
        // current row
        int[] cur = new int[c+1];

        // first row
        for(int j=0; j<c; ++j){
            prev[j] = matrix[0][j] - '0';
            maxLen = Math.max(maxLen, prev[j]);  
        }
        
        // for all rows
        for(int i=1; i<r; ++i){
            cur[0] = matrix[i][0] - '0';
            maxLen = Math.max(maxLen, cur[0]);  
            for(int j=1; j<c; ++j){
                if(matrix[i][j] == '1'){
                    cur[j] = Math.min(cur[j-1], Math.min(prev[j], prev[j-1])) + 1;
                    maxLen = Math.max(maxLen, cur[j]);  
                }
            }
            prev = Arrays.copyOf(cur, cur.length);
            Arrays.fill(cur, 0);
        }
        return maxLen*maxLen;        
    }
}

/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character
http://www.lintcode.com/en/problem/edit-distance/

*/

public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m == 0 && n == 0)    return 0;
        // Insert n characters
        if(m == 0)              return n;

        // Delete m characters
        if(n == 0)              return m;
        
        int[][] dp = new int[m+1][n+1];
        for(int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);
        
        // Delete character from word1
        for(int i=0; i<=m; i++){
            dp[i][0] = i;
        }
        // Insert character to word1
        for(int j=0; j<=n; j++){
            dp[0][j] = j;
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                int x = dp[i-1][j-1];
                if(word1.charAt(i-1) != word2.charAt(j-1))
                    x++;
                int y = dp[i-1][j] + 1;
                int z = dp[i][j-1] + 1;
                dp[i][j] = Math.min(x, Math.min(y, z));
            }
        }
        
        return dp[m][n];
    }
}
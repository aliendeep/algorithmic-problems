public class Solution {
    // dp[i][j] = min(dp[i-1][j-1] + (word1[i] != word2[j]), dp[i-1][j] + 1, dp[i][j-1] + 1)
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
        
        // Delete character
        for(int i=0; i<=m; i++){
            dp[i][0] = i;
        }
        // Insert character
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
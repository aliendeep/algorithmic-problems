/*
Given two words word1 and word2, find the minimum number of steps required to 
convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/
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

// Memoized version of Edit distance
class Solution2 {
    int[][] dp;
    public int editDistance(String s1, int i, String s2, int j){
        if(i == 0 && j == 0)        return 0;
        // insert j characters
        if(i == 0)                  return j;
        // delete i characters
        if(j == 0)                  return i;
        if(dp[i][j] != -1)          return dp[i][j];
        
        int x = editDistance(s1, i-1, s2, j-1) + ((s1.charAt(i-1) != s2.charAt(j-1)) ? 1 : 0); 
        int y = editDistance(s1, i, s2, j-1) + 1;
        int z = editDistance(s1, i-1, s2, j) + 1;
        dp[i][j] = Math.min(x, Math.min(y, z));
        return dp[i][j]; 
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        dp = new int[n+1][m+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        
        return editDistance(word1, n, word2, m);
    }
}

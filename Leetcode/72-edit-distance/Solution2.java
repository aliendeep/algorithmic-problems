public class Solution {
    // Memoized version of Edit distance
    int[][] dp;
    public int getEditDistance(String word1, int i, String word2, int j){
        if(i == 0 && j == 0)        return 0;
        // insert j characters
        if(i == 0)                  return j;
        // delete i characters
        if(j == 0)                  return i;
        if(dp[i][j] != -1)          return dp[i][j];
        
        int x = getEditDistance(word1, i-1, word2, j-1);
        if(word1.charAt(i-1) != word2.charAt(j-1)) 
            x++;
        int y = getEditDistance(word1, i, word2, j-1) + 1;
        int z = getEditDistance(word1, i-1, word2, j) + 1;
        dp[i][j] = Math.min(x, Math.min(y, z));
        return dp[i][j];
        
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        dp = new int[n+1][m+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        
        return getEditDistance(word1, n, word2, m);
    }
}
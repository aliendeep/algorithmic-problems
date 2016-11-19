public class Solution {
    // DP
    // dp[i][j][n]: Returns whether s2.substring(j, n) is a scrambled string of s1.substring(i,n) or not.
    public boolean isScramble(String s1, String s2) {
       int n = s1.length();
       int nb = s2.length();
       if(n != nb)      return false;
       
       boolean[][][] dp = new boolean[n+1][n+1][n+1];
       // init
       for(boolean[][] a : dp){
           for(boolean[] b : a){
               Arrays.fill(b, false);
           }
       }

        for(int i=n-1; i>=0; i--){
           for(int j=n-1; j>=0; j--){
               dp[i][j][1] = (s1.charAt(i) == s2.charAt(j)); 
               for(int len=2; i+len <= n && j+len <= n; ++len){
                   for(int k=1; k<len; ++k){
                        //if(isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                        // isScramble(s1.substring(i), s2.substring(i)))
                       dp[i][j][len] = dp[i][j][len] || (dp[i][j][k] && dp[i+k][j+k][len-k]);
                       dp[i][j][len] = dp[i][j][len] || (dp[i][j+len-k][k]  && dp[i+k][j][len-k]);
                   }
               }
           }
        }
        return dp[0][0][n];
    }
}
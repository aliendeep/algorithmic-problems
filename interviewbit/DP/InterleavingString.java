/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example,
Given:

s1 = "aabcc",
s2 = "dbbca",
When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem
*/

public class InterleavingString {
  public int isInterleave(String a, String b, String c) {
      int la = a.length();
      int lb = b.length();
      int lc = c.length();
      
      if(lc != la + lb)
          return 0;
      
      boolean[][] dp = new boolean[la+1][lb+1];
      
      // init
      dp[0][0] = true;
      
      // use characters from a only
      for(int i=0; i<la; i++){
          if(a.charAt(i) == c.charAt(i)){
              dp[i+1][0] = true;
          }
          else
              break;
      }
      
      // use characters from b only
      for(int j=0; j<lb; j++){
          if(b.charAt(j) == c.charAt(j)){
              dp[0][j+1] = true;
          }
          else
              break;
      }
      
      for(int i=1; i<=la; i++){
          for(int j=1; j<=lb; j++){
                dp[i][j] = (dp[i-1][j] && a.charAt(i-1) == c.charAt(i+j-1)) || 
                           (dp[i][j-1] && b.charAt(j-1) == c.charAt(i+j-1));
          }
      }
      return dp[la][lb] ? 1 : 0;
  }
}

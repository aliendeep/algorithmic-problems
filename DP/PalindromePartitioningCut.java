/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example : 
Given 
s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

public class PalindromePartitioningCut {
  public int minCut(String s) {
      int n = s.length();
      boolean[][] dp = new boolean[n+1][n+1];

      // 1 length
      for(int i=0; i<n; i++)
          dp[i][i] = true;

      // 2 length
      for(int i=0; i<n-1; i++){
          if(s.charAt(i) == s.charAt(i+1)) 
              dp[i][i+1] = true;
      }
        
        // 3 length or more
        for(int l=3; l<=n; l++){
            for(int i=0; i<n-l+1; i++){
                // end index
                int j = i + l - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1])
                    dp[i][j] = true;
            }
        }
        
        // Now find the min cut
        // cut[i] = min cut needed to make string 0..i palindrome
        int[] cut = new int[n];
        Arrays.fill(cut, n+1);
        
        for(int i=0; i<n; i++){
            // s (0..i) is a palindrome, no cut needed
            if(dp[0][i]){
                cut[i] = 0;    
                continue;
            }
            // dp[0][i] is not a palindrome, find the min number of cut 
            for(int j=0; j<i; j++){
                // if right half is palindrome
                if(dp[j+1][i] && cut[j] + 1 < cut[i]){
                   cut[i] = cut[j] + 1;
                }
            }
        }
        return cut[n-1];
  }
}

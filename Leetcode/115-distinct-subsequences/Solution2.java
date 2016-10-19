public class Solution {
    // Time and Space: O(mn)
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        
        // empty string to empty string matching
        dp[0][0] = 1;
        
        // dp[j][0] = 1, since aligning t = "" with any substring of S
        // would have only one solution which is to delete all characters in s.
        for(int i=0; i<=s.length(); i++)
            dp[i][0] = 1;
            
        /*
            if S[i] != T[j], then the solution would be to ignore the character S[i] 
            and align substring  S[0..(i-1)] with T[0..j]. 
            dp[i][j] = dp[i-1][j].
    
            Otherwise, dp[i-1][j] + we could match the characters S[i] and T[j] and align the rest of them 
            (i.e. S[0..(i-1)] and T[0..(j-1)]
            So, dp[i][j] = dp[i-1][j] + d[i-1][j-1]
        */
        for(int i=1; i<=s.length(); i++){
            for(int j=1; j<=t.length(); j++){
                dp[i][j] = dp[i-1][j];
                if(s.charAt(i-1) == t.charAt(j-1))
                    dp[i][j] += dp[i-1][j-1]; 
            }
        }
        return dp[s.length()][t.length()];
    }
}
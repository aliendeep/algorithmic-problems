/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

public class DistinctSubsequences {
    // Time and Space: O(mn)
    public int numDistinct(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        int[][] dp = new int[nt+1][ns+1];
        
        // empty string to empty string matching
        dp[0][0] = 1;
        
        // dp[0][j] = 1, since aligning t = "" with any substring of S
        // would have only one solution which is to delete all characters in s.
        for(int j=0; j<=ns; j++)
            dp[0][j] = 1;
            
        /*
            if T[i] != S[j], then the solution would be to ignore the character S[j] 
            and align substring T[0..i] with S[0..(j-1)]. Therefore, dp[i][j] = dp[i][j-1].
    
            if T[i] == S[j], then first we could adopt the solution in case 1), 
            but also we could match the characters T[i] and S[j] and align the rest of them 
            (i.e. T[0..(i-1)] and S[0..(j-1)].
            So, dp[i][j] = dp[i][j-1] + d[i-1][j-1]
        */
        for(int i=1; i<=nt; i++){
            for(int j=1; j<=ns; j++){
                dp[i][j] = dp[i][j-1];
                if(t.charAt(i-1) == s.charAt(j-1))
                    dp[i][j] += dp[i-1][j-1]; 
            }
        }
        return dp[nt][ns];
    }
}

class DistinctSubsequencesMemoization {
    // Memoization
    String s, t;
    int[][] dp;

    public int numDistinctHelper(int i, int j) {
        if(i < 0 || j < 0)          return 0;
        // empty string
        if(i == 0 && j == 0)        return 1;
        // delete all charaters
        if(j == 0)                  return 1;
        if(i == 0)                  return 0;
        if(i < 0)                   return 0;
        
        if(dp[i][j] != -1)          return dp[i][j];

        int r = 0;
        if(s.charAt(i-1) == t.charAt(j-1))
            r = numDistinctHelper(i-1, j-1);
        
        r += numDistinctHelper(i-1, j);
        
        dp[i][j] = r;
        return dp[i][j];
    }
    
    public int numDistinct(String S, String T) {
        this.s = S;
        this.t = T;
        dp = new int[s.length()+1][t.length()+1];
        for(int[] a : dp)
            Arrays.fill(a, -1);
        return numDistinctHelper(s.length(), t.length());
    }
}

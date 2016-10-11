/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Link: http://www.lintcode.com/en/problem/longest-common-subsequence/
*/

// Time: O(mn)
public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public static final int CORNER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    int[][] dp;
    int[][] dir;     
   
    // Memoized version for LCS problem. 
    public int getLCS(String A, int n, String B, int m) {
        if(n == 0 || m == 0)
            return 0;
        if(dp[n][m] != -1)      
            return dp[n][m];
        if(A.charAt(n-1) == B.charAt(m-1)){ 
            dp[n][m] = getLCS(A, n-1, B, m-1) + 1;            
            dir[n][m] = CORNER;           
        }
        else{
            dp[n][m] = Math.max(getLCS(A, n-1, B, m), getLCS(A, n, B, m-1)); 
            if(dp[n][m] == dp[n-1][m])
                dp[n][m] = LEFT;
            else
                dp[n][m] = RIGHT;

        }
        return dp[n][m];
    }
    
    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        dp = new int[n+1][m+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return getLCS(A, n, B, m);
    }
}

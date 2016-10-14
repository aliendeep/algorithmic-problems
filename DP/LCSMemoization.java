/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Link: http://www.lintcode.com/en/problem/longest-common-subsequence/
*/

// Time complexity: O(mn)
// Space: O(mn)
public class LCSMemoization {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    int[][] dp;a
    
    // Memoized version for LCS problem. 
    public int getLCS(String A, int i, String B, int j) {
        if(i == A.length() || j == B.length())
            return 0;
        if(dp[i][j] != -1)      
            return dp[i][j];
        if(A.charAt(i) == B.charAt(j)){
            dp[i][j] = getLCS(A, i+1, B, j+1) + 1;            
        }
        else{
            dp[i][j] = Math.max(getLCS(A, i+1, B, j), getLCS(A, i, B, j+1)); 
        }
        return dp[i][j];
    }
    
    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        dp = new int[n+1][m+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return getLCS(A, 0, B, 0);
    }
}

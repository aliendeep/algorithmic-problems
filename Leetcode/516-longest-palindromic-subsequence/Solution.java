/*
Given a string s, find the longest palindromic subsequence's length in s. You may 
assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
*/
public class Solution {
    int[][] dp;
    
    int longestPalindromeSeq(String s, int i, int j){
        if(i == j)              return 1;
        if(j < i)               return 0;
        if(dp[i][j] != -1)      return dp[i][j];
        
        int maxLen = 0;
        if(s.charAt(i) == s.charAt(j)){
            maxLen = longestPalindromeSeq(s, i+1, j-1) + 2;
        }
        maxLen = Math.max(maxLen, longestPalindromeSeq(s, i+1, j));
        maxLen = Math.max(maxLen, longestPalindromeSeq(s, i, j-1));
        dp[i][j] = maxLen;
        return maxLen;
    }
    
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if(n <= 1)      return n;
        dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        return longestPalindromeSeq(s, 0, n-1);
        
    }
}

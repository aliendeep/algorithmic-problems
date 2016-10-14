// Given two strings str1 and str2, find the length of shortest string that has both str1 and str2 as subsequences.

import java.util.*;

public class ShortestCommonSupersequence{
  // Solution 1
  public int getLength(String s1, String s2){
    LCS lcs = new LCS();
    int lcsLength = lcs.longestCommonSubsequence(s1, s2);
    return s1.length() + s2.length() - lcsLength;
  }
  // Solution 2
  public int getSuperSeqLen(String s1, int n, String s2, int m, int[][] dp){
    if(n < 0)    return 0;
    if(m < 0)    return 0;
    if(n == 0)   return m;
    if(m == 0)   return n;
    if(dp[n][m] != -1)
        return dp[n][m];

    // Common Character
    if(s1.charAt(n-1) == s2.charAt(m-1)){
      dp[n][m] =  getSuperSeqLen(s1, n-1, s2, m-1, dp) + 1; 
    }
    else{
      //  Case 1: Remove last character from s1 and recur
      //  Case 2: Remove last character from Y and recur
      dp[n][m] =  Math.min(getSuperSeqLen(s1, n-1, s2, m, dp), getSuperSeqLen(s1, n, s2, m-1, dp)) + 1;       
    }
    return dp[n][m];
  }

  public int superSeqLen(String s1, String s2){
    int n = s1.length();
    int m = s2.length();
    if(n == 0)    return m;
    if(m == 0)    return n;

    int[][] dp = new int[n+1][m+1];
    for(int[] t : dp)
        Arrays.fill(t, -1);
    
    return getSuperSeqLen(s1, n, s2, m, dp);    
  }

  public static void main ( String args[] ) {
    ShortestCommonSupersequence s = new ShortestCommonSupersequence();
    System.out.println(s.getLength("AGGTAB", "GXTXAYB"));
    System.out.println(s.superSeqLen("AGGTAB", "GXTXAYB"));
  } 
}

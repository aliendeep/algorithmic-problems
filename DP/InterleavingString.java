// https://leetcode.com/problems/interleaving-string/

/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

public class InterleavingString {
    /*
        Let l1 =  length of string s1
        Let l2 =  length of string s2
        Let l3 =  length of string s3
        if l1 + l2 != l3, then return false
        if s1[l1-1] != s3[l3-1] then
            if s2[l2-1] == s3[l3-1] and s3[0:l1+l2-2] is an interleaving string of s1 and s2[0:l2-2]
                then return true
        Similarly,   
        if s2[l2-1] != s3[l3-1] then
            if s1[l1-1] == s3[l3-1] and s3[0:l1+l2-2] is an interleaving string of s1[0:l1-2] and s2
                then return true
        if s1[l1-1] == s3[l3-1] and s2[l2-1] == s3[l3-1] then
            if s3 is interleaving of either s1[0:l1-2] and s2 or s1 and s2[0:l2-2]
                return false;
    */
    // DP
    // Time complexity: O(l1*l2)
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if(l3 != l1 + l2)   return false;

        boolean[][] dp = new boolean[l1+1][l2+1];
        dp[0][0] = true;
        // Initialization
        // Use characters from s1 only to match s3
        for(int i=1; i<=l1; i++){
            if(s1.charAt(i-1) == s3.charAt(i-1)){
                dp[i][0] = true;        
            }
            else
                break;
        }

        // Use characters from s2 only to match s3
        for(int j=1; j<=l2; j++){
            if(s2.charAt(j-1) == s3.charAt(j-1)){
                dp[0][j] = true;        
            }
            else
                break;
        }
        
        for(int i=1; i<=l1; i++){
            for(int j=1; j<=l2; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) ||
                               (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[l1][l2];
    }
}
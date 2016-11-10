/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/
public class Solution {
    public int check(String s1, String s2, String s3, int i, int j, int[][] dp) {
        if(i <= 0 && j <= 0)          return 1;
        if(dp[i][j] != -1)            return dp[i][j];
        
        dp[i][j] = 0;
        if(i > 0 && j > 0){
            if(s1.charAt(i-1) == s3.charAt(i+j-1) && s2.charAt(j-1) == s3.charAt(i+j-1)){
                int x = check(s1, s2, s3, i-1, j, dp);
                if(x == 1){
                    dp[i][j] = x;
                    return x;
                }
                // consume jth of s2
                int y = check(s1, s2, s3, i, j-1, dp);
                dp[i][j] = y;
            }
            else if(s1.charAt(i-1) == s3.charAt(i+j-1)){
                dp[i][j] = check(s1, s2, s3, i-1, j, dp);
            }
            else if(s2.charAt(j-1) == s3.charAt(i+j-1)){
                dp[i][j] = check(s1, s2, s3, i, j-1, dp);
            }
            return dp[i][j];
        }
        // s2 empty
        else if(i > 0){
            if(s1.charAt(i-1) != s3.charAt(i+j-1))
                return 0;
            dp[i][j] = check(s1, s2, s3, i-1, j, dp);
        }
        // s1 empty
        else if(j > 0){
            if(s2.charAt(j-1) != s3.charAt(i+j-1))
                return 0;
             dp[i][j] = check(s1, s2, s3, i, j-1, dp);
            
        }
        return dp[i][j];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(c !=  a + b)
            return false;
        
        int[][] dp = new int[a+1][b+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return check(s1, s2, s3, a, b, dp) == 1 ? true : false;                
    }
}

// Backtracking (TLE)
public class SolutionRecursive {
    public boolean check(String s1, String s2, String s3) {
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(c !=  a + b)             return false;
        if(a == 0 && b == 0)        return true;
        
        if(a == 0 && s2.charAt(0) != s3.charAt(0))
            return false;
        else if(b == 0 && s1.charAt(0) != s3.charAt(0))
            return false;
        
        if(a > 0 && b > 0){
            if(s1.charAt(0) == s3.charAt(0) && s2.charAt(0) == s3.charAt(0)){
                boolean x = check(s1.substring(1), s2, s3.substring(1));
                if(x)
                    return true;
                boolean y = check(s1, s2.substring(1), s3.substring(1));
                return y;
            }
            else if(s1.charAt(0) == s3.charAt(0)){
                return check(s1.substring(1), s2, s3.substring(1));
            }
            else if(s2.charAt(0) == s3.charAt(0)){
                return check(s1, s2.substring(1), s3.substring(1));
            }
            else
                return false;
        }
        // s2 empty
        else if(a > 0){
            return check(s1.substring(1), s2, s3.substring(1));
        }
        // s1 empty
        else if(b > 0){
            return check(s1, s2.substring(1), s3.substring(1));
            
        }
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(c !=  a + b)
            return false;
        
        return check(s1, s2, s3);                
    }
}
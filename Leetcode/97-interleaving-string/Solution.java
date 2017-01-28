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
        if(l3 != l1 + l2)
            return false;
        boolean[][] dp = new boolean[l1+1][l2+1];
        // Base case
        dp[0][0] = true;
        
        // Initialization
        // Use characters from s1 only to match s3
        for(int i=0; i<l1; i++){
            if(s1.charAt(i) == s3.charAt(i)){
                dp[i+1][0] = true;        
            }
            else
                break;
        }

        // Use characters from s2 only to match s3
        for(int j=0; j<l2; j++){
            if(s2.charAt(j) == s3.charAt(j)){
                dp[0][j+1] = true;        
            }
            else
                break;
        }
        
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                dp[i+1][j+1] = (dp[i][j+1] && s1.charAt(i) == s3.charAt(i+j+1)) ||
                               (dp[i+1][j] && s2.charAt(j) == s3.charAt(i+j+1));
            }
        }
        return dp[l1][l2];
    }
}

class Solution2 {
    String s1;
    String s2;
    String s3;
    int[][] dp;
    public int check(int i, int j) {
        if(i == 0 && j == 0)          return 1;
        if(dp[i][j] != -1)            return dp[i][j];
        
        dp[i][j] = 0;
        // both non empty
        if(i > 0 && j > 0){
            // both matches
            if(s1.charAt(i-1) == s3.charAt(i+j-1) && s2.charAt(j-1) == s3.charAt(i+j-1)){
                if(check(i-1, j) == 1){
                    dp[i][j] = 1;
                    return 1;
                }
                // consume jth of s2
                dp[i][j] = check(i, j-1);
            }
            // only s1 matches
            else if(s1.charAt(i-1) == s3.charAt(i+j-1)){
                dp[i][j] = check(i-1, j);
            }
            else if(s2.charAt(j-1) == s3.charAt(i+j-1)){
                dp[i][j] = check(i, j-1);
            }
            return dp[i][j];
        }
        // s2 empty
        else if(i > 0){
            if(s1.charAt(i-1) != s3.charAt(i+j-1)){
                dp[i][j] = 0;
                return 0;
            }
            dp[i][j] = check(i-1, j);
        }
        // s1 empty
        else if(j > 0){
            if(s2.charAt(j-1) != s3.charAt(i+j-1)){
                dp[i][j] = 0;
                return 0;
            }
             dp[i][j] = check(i, j-1);
            
        }
        return dp[i][j];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(c !=  a + b)
            return false;
        
        dp = new int[a+1][b+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return check(a, b) == 1;            
    }
}
}

// Backtracking (TLE)
class SolutionRecursive {
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

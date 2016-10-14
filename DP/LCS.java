/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.
*/

// Time complexity: O(mn)
// Space: O(mn)

import java.util.*;

public class LCS {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public static final int CORNER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    int[][] dp;
    int[][] dir;     

    public void printLCS(String A, int i, String B, int j){
        if(i == 0 || j == 0)
            return;
        if(dir[i][j] == CORNER){
            printLCS(A, i-1, B, j-1);
            System.out.print(A.charAt(i-1));
        }
        else if(dir[i][j] == LEFT)
            printLCS(A, i-1, B, j);
        else
            printLCS(A, i, B, j-1);

    }   
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
            int l = getLCS(A, n-1, B, m);
            int r = getLCS(A, n, B, m-1);
            dp[n][m] = Math.max(l, r); 
            if(dp[n][m] == l)
                dir[n][m] = LEFT;
            else
                dir[n][m] = RIGHT;

        }
        return dp[n][m];
    }
    
    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        dp = new int[n+1][m+1];
        dir = new int[n+1][m+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        int l = getLCS(A, n, B, m);
        return l;
    }
}

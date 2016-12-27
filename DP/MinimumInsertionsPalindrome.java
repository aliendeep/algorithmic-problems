// http://www.practice.geeksforgeeks.org/problem-page.php?pid=557
/*
Given a string, find the minimum number of characters to be inserted to convert it to palindrome.
For Example:
ab: Number of insertions required is 1. bab or aba
aa: Number of insertions required is 0. aa
abcd: Number of insertions required is 3. dcbabcd
*/

import java.io.*;
import java.util.*;
// O(n^2)

public class Solution{
    // dp[i][j] - cost of coverting the substring i to j to palindrome 
    public static int getMinCount(String s, int start, int end, int[][] dp){
        if(start > end || start == end)       return 0;
        // Two char
        if(start == end -1)    return (s.charAt(start) == s.charAt(end) ? 0 : 1);
        if(dp[start][end] != -1)
            return dp[start][end];
        
        int minCost = Integer.MAX_VALUE;
        if(s.charAt(start) == s.charAt(end)){
            minCost = getMinCount(s, start+1, end-1, dp);
        }
        else{
            minCost = Math.min(getMinCount(s, start, end-1, dp),    
                               getMinCount(s, start+1, end, dp)) + 1;
        }
        dp[start][end] = minCost;
        return minCost;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);    
        int t = scan.nextInt();
        while(t-- > 0){
            String s = scan.next();
            int n = s.length();
            int[][] dp = new int[n][n];
            for(int[] x : dp)
                Arrays.fill(x, -1);
            System.out.println(getMinCount(s, 0, n-1, dp)); 
        }
    }
}
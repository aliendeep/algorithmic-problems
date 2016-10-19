/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example :

Input : 3
Return : 3

Steps : [1 1 1], [1 2], [2 1]
*/

public class Solution {
    public int getWays(int n, int[] dp){
        if(n < 0)   
            return 0;
        if(n == 0 || n == 1)
            return 1;
       
        if(dp[n] != -1)
            return dp[n];
            
        dp[n] = getWays(n-1, dp) + getWays(n-2, dp);
        return dp[n];
    }
    
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        
        return getWays(n, dp);
    }
}

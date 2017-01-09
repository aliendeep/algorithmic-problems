/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you 
climb to the top?

Note: Given n will be a positive integer.
*/
public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1];
            if(i-2 >= 0)
                dp[i] += dp[i-2];
        }
        return dp[n];
    }
}

// Memoization
class Solution2 {
    public int getWays(int n, int[] dp){
        if(n < 0)           return 0;
        if(n == 0)          return 1;
        if(n == 1)          return 1;
        if(dp[n] != -1)     return dp[n];
        dp[n] = getWays(n-1, dp) + getWays(n-2, dp);
        return dp[n];
    }
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return getWays(n, dp);
    }
}

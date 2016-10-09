/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

public class Solution {
    // Two cases:
    // Case 1: If we use same color as the previous post, [color of ith post == color of (i-1)], then color of i-1th post should be different than i-2th post. Number of ways to color ith post = dp[i-2]*(k-1)
    // Case 2: If we use different color than the previous post, [color of ith post != color of (i-1)]. Number of ways to color ith post = dp[i-1]*(k-1)
    public int numWays(int n, int k) {
        if(n == 0)  return 0;
        if(n == 1)  return k;
        if(n == 2)  return k*k;
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k*k;
        for(int i=2; i<n; i++){
            dp[i] = dp[i-1]*(k-1) + dp[i-2]*(k-1); 
        }   
        return dp[n-1];
    }
}
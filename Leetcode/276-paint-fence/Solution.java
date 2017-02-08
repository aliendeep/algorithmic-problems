/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence 
posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

public class Solution {
    /* Two cases:
        Case 1: If we use same color as the previous post, 
        [color of ith post == color of (i-1)], then color of i-1th post should be 
        different than i-2th post. Number of ways to color ith post = 
        dp[i-2]*(k-1)
        Case 2: If we use different color than the previous post, 
        [color of ith post != color of (i-1)]. 
        Number of ways to color ith post = dp[i-1]*(k-1)
    */
    public int numWays(int n, int k) {
        if(n == 0)  return 0;
        if(n == 1)  return k;
        if(n == 2)  return k*k;
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k*k;
        for(int i=2; i<n; i++){
            // Use different color or use the same color as the previous one
            dp[i] = dp[i-1]*(k-1) + dp[i-2]*(k-1); 
        }   
        return dp[n-1];
    }
}

// Memoization
class Solution2 {
    int[] dp;
    int k;
    public int ways(int n) {
        if(n <= 0)          return 0;
        if(n == 1)          return k;
        if(n == 2)          return k*k;
        if(dp[n] != -1)     return dp[n];
                 // same color       // different color than previous one
        dp[n] = ways(n-1)*(k-1) + ways(n-2)*(k-1);
        return dp[n];
    }
    public int numWays(int n, int k) {
        if(n == 0 || k == 0)        return 0;
        this.k = k;
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        return ways(n);
    }
}

class Solution3 {
    public int numWays(int n, int k) {
        if(n == 0)      return 0;    
        if(n == 1)      return k;
        int sameColorWays = k;
        int diffColorWays = k*(k-1);
        
        for(int i=2; i<n; ++i){
            int t = diffColorWays;
            diffColorWays = (sameColorWays + diffColorWays)*(k-1);
            sameColorWays = t;
        }
        return sameColorWays + diffColorWays;
    }
}

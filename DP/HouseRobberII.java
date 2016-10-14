/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

// [2,1,1,1]
class HouseRobberIIMemoization {
    public void print(int[] dp){
        for(int i=0; i<dp.length; i++)
            System.out.print(dp[i] + " ");
        System.out.println();
    }
    public int getAmount(int[] nums, int start, int i, int[] dp){
        if(i < start)           return 0;
        if(dp[i] != -1)         return dp[i];
        dp[i] = Math.max(getAmount(nums, start, i-1, dp), getAmount(nums, start, i-2, dp) + nums[i]);   
        return dp[i];
    }
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n < 0)       return 0;
        if(n == 1)      return nums[0];
        if(n == 2)      return Math.max(nums[0], nums[1]);

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int a = getAmount(nums, 1, n-1, dp);
        // reset dp
        Arrays.fill(dp, -1);
        int b = getAmount(nums, 0, n-2, dp);
        return Math.max(a, b);
    }
}
public class HouseRobberII{
    public int rob(int[] nums, int start, int n) {
        int[] dp = new int[n+1];
        dp[start] = nums[start];
        // At first house, robber can select either house 0 or house 1
        dp[start+1] = Math.max(nums[start], nums[start+1]);

        for(int i=start+2; i<n; i++){
            // decide to rob ith house or not
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[n-1];
    }   
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        if(n == 1)      return nums[0];
        if(n == 2)      return Math.max(nums[0], nums[1]);
        // two cases
        // 1st house is included and last house is not included
        // 1st house is not included and last house is included
        return Math.max(rob(nums, 0, n-1), rob(nums, 1, n));
    }
}

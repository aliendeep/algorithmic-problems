public class Solution {
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
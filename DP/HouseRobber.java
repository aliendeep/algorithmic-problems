/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
https://leetcode.com/problems/house-robber/
*/

import java.util.*;

class HouseRobberMemoization {
    int[] dp;
    public int getAmount(int[] nums, int i){
        if(i < 0)       return 0;
        if(dp[i] != -1)
            return dp[i];
        dp[i] = Math.max(getAmount(nums, i-1),  getAmount(nums, i-2) + nums[i]);
        return dp[i];
    }
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n < 0)
            return 0;
        dp = new int[n];
        Arrays.fill(dp, -1);
        
        return getAmount(nums, n-1);
    }
}

public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0)  
            return 0;
        if(n == 1)
            return nums[0];
            
        int[] dp = new int[n+1];
        dp[0] = nums[0];
        // At house 1, the robber can select either house 0 or house 1
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2; i<n; i++){
            // decide to rob ith house or not
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[n-1];
    }
}
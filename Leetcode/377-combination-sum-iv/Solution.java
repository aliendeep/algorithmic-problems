/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*/
public class Solution {
    // DP
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target+1];
        // init
        dp[0] = 1;
        for(int i=1; i<=target; i++){
            for(int num : nums){
                if(i-num >= 0)
                    dp[i] += dp[i-num];
            }
        }
        
        return dp[target];
    }
}

class Solution2 {
    // Alternative: Memoization
    public int combinationSum(int[] nums, int target, int[] dp) {
        if(target == 0)         return 1;
        if(dp[target] != -1)    return dp[target];
        
        int cnt = 0;
        for(int i=0; i<nums.length; ++i){
            if(nums[i] <= target){
                cnt += combinationSum(nums, target - nums[i], dp);
            }
        }
        dp[target] = cnt;
        return cnt;
    }
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        
        return combinationSum(nums, target, dp);
    }
}
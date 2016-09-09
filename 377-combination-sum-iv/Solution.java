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
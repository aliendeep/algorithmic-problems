public class Solution {
    // DP
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        dp[0] = nums[0];
        for(int i=1; i<n; i++){
            // if this index is reachable
            if(dp[i-1] >= i){
                dp[i] = Math.max(i + nums[i], dp[i-1]);
            }
        }

        for(int i=0; i<n; i++)
            if(dp[i] >= n-1)
                return true;
        return false;
    }
}
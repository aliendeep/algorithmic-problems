// Stack overflow
public class Solution {
    // Memoization
    public int jump(int i, int n, int[] nums, int[] dp){
        if(i >= n-1){
            return 1;
        }
        if(dp[i] != -1)
            return dp[i];
        
        int reach = i + nums[i];
        if(reach >= n-1){
            return 1;
        }

        int ans = 0;
        for(int t=i+1; t<=reach; ++t){
            ans = jump(t, n, nums, dp);
            if(ans == 1)
                return 1;
        }
        dp[i] = 0;
        return dp[i];
    }
    
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int r = jump(0, n, nums, dp);  
        return r == 1 ? true : false;
    }
}
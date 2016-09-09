class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if(n == 0)   return 0;
        if(n == 1)    return nums[0];
        vector<int> dp(n+1, 0);
        dp[0] = nums[0];
        // important: when we are at house 1, we can select either house 0 or house 1
        dp[1] = max(nums[0], nums[1]);
        for(int i=2; i<n; i++){
            // decide to rob ith house or not
            dp[i] = max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[n-1];
    }
};
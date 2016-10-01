class Solution {
public:
    int rob(vector<int>& nums, int start, int n){
        vector<int> dp(n+1, 0);
        dp[start] = nums[start];
        // important: when we are at house 1, we can select either house 0 or house 1
        dp[start+1] = max(nums[start], nums[start+1]);
        for(int i=start+2; i<n; i++){
            // decide to rob ith house or not
            dp[i] = max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[n-1];        
    }
    int rob(vector<int>& nums) {
        if(nums.size() == 0)    return 0;
        if(nums.size() == 1)    return nums[0];
        if(nums.size() == 2)    return max(nums[0], nums[1]);
        // two cases
        // 1st house is included and last house is not included
        // 1st house is not included and last house is included
        return max(rob(nums, 0, nums.size()-1), rob(nums, 1, nums.size()));
    }
};
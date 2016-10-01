class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        int n = nums.size();
        vector<int> dp(target+1, 0);
        
        // target of 0
        dp[0] = 1;
        
        for(int i=1; i<=target; i++){
            for(const auto& num : nums){
                if(i-num >= 0){
                    dp[i] += dp[i-num];
                }
            }
        }
        
        return dp[target];
    }
};
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        if(amount == 0)     return 0;
        vector<int> dp(amount+1, INT_MAX);
        for(const auto& denomination : coins)
            if(denomination <= amount)
                dp[denomination] = 1;
        
        dp[0] = 0;
        for(int i=1; i<=amount; i++){
            for(const auto& denomination : coins){
                if(i-denomination >= 0 && dp[i-denomination] < INT_MAX)
                    // use the denomination or not
                    dp[i]  = min(dp[i], dp[i-denomination] + 1);
            }
        }
        // check if it's possible to make the change
        return dp[amount] == INT_MAX ? -1 : dp[amount];
    }
};
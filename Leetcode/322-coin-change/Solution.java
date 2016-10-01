public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
            
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 1;
        for(int i=1; i<=amount; i++){
            for(int coin : coins){
                if(i - coin >= 0 && dp[i-coin] < Integer.MAX_VALUE){
                    // use the coin or not
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
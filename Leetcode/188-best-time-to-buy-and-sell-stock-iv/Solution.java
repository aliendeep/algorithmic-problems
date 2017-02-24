/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must 
sell the stock before you buy again).
*/
public class Solution {
    // test case:
    // 2
    //[3,2,6,5,0,3]
    // DP
    // Time: O(kn)
    // Space: O(k)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(k == 0 || n < 2)     return 0;
        if(k >= n/2){
            // Can make transactions as many times as possible
            return maxProfitSellBuyUnlimited(prices);
        }
        
        // Balance - Current Balance with at most j transactions (stock 0 .. i)
        int[] bal = new int[k+1];
        Arrays.fill(bal, Integer.MIN_VALUE);
        // Current Profit - Current Profit with at most j transactions (stock 0 .. i)
        int[] profit = new int[k+1];
        
        for(int i=0; i<n; i++){
            // for transactions
            for(int j=1; j<=k; j++){
                // Buy ith stock or not
                bal[j] = Math.max(profit[j-1] - prices[i], bal[j]); 
                // Sell ith stock or not
                profit[j] = Math.max(bal[j] + prices[i], profit[j]); 
            }
        }
        return profit[k];
    }
    public int maxProfitSellBuyUnlimited(int[] prices){
        int profit = 0;
        for(int i=1; i<prices.length; i++){
            int diff = prices[i] - prices[i-1];
            if(diff > 0)
                profit += diff;
        }
        return profit;
    }
}

class Solution2 {
    // Time: O(kn)
    // Space: O(kn)
    // dp[i][j] is the max profit using i transactions at time j
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(k == 0 || n < 2)     return 0;
        if(k >= n/2){
            // Can make transactions as many times as possible
            return maxProfitSellBuyUnlimited(prices);
        }
        
        int[][] dp = new int[k+1][n];
        for(int i=1; i<=k; ++i){
            // buy
            int t = -prices[0];
            for(int j=1; j<n; ++j){
                // sell 
                                 // don't sell or sell
                dp[i][j] = Math.max(dp[i][j-1], t + prices[j]);
                // buy at this time
                // t means the maximum profit of doing at most i-1 transactions, 
                // using at most first j-1 prices, and buying the stock at price[j] 
                t = Math.max(t, dp[i-1][j-1] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
    public int maxProfitSellBuyUnlimited(int[] prices){
        int profit = 0;
        for(int i=1; i<prices.length; i++){
            int diff = prices[i] - prices[i-1];
            if(diff > 0)
                profit += diff;
        }
        return profit;
    }
}

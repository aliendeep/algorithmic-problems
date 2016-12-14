/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions 
as you like (ie, buy one and sell one share of the stock multiple times) with 
the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell 
the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/

public class Solution {
    // DP
    // buy[i] = max(sell[i-2] - price[i], buy[i-1])
    // sell[i] = max(buy[i-1] + price[i], sell[i-1])
    // Time: O(n)
    // Space: O(n)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2)
            return 0;
            
        int[] buy = new int[n];
        Arrays.fill(buy, Integer.MIN_VALUE);
        int[] sell = new int[n];
        
        // initialization
        buy[0] = Math.max(- prices[0], Integer.MIN_VALUE);
        buy[1] = Math.max(- prices[1], buy[0]);
        sell[1] = Math.max(buy[0] + prices[1], 0);
        for(int i=2; i<n; i++){
            // After you sell your stock, you cannot buy stock on next day.
            buy[i] = Math.max(sell[i-2] - prices[i], buy[i-1]);
            sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]); 
        }
        return sell[n-1];
    }
}

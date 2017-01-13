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
    // https://discuss.leetcode.com/topic/30680/share-my-dp-solution-by-state-machine-thinking
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)
            return 0;
        
        int[] s0 = new int[n];
        int[] s1 = new int[n];
        int[] s2 = new int[n];
        
        s0[0] = 0;
        // buy
        s1[0] = -prices[0];
        // sell
        s2[0] = Integer.MIN_VALUE;
        for(int i=1; i<n; ++i){
            s0[i] = Math.max(s0[i-1], s2[i-1]);
                            // buy, stay
            s1[i] = Math.max(s0[i-1] - prices[i], s1[i-1]);
            // sell
            s2[i] = s1[i-1] + prices[i];
        }
        // rest or sell (final answer can't be in buy state)
        return Math.max(s0[n-1], s2[n-1]);
    }
}


class Solution2 {
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

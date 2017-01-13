/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell 
the stock before you buy again).
*/
public class Solution {
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // Forward & backward
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2)
            return 0;
        int profit = 0;
        // forward
        int minBuyingPrice = Integer.MAX_VALUE;
        int[] firstBuySell = new int[n];
        for(int i=0; i<n; i++){
            profit = Math.max(profit, prices[i] - minBuyingPrice);
            minBuyingPrice = Math.min(minBuyingPrice, prices[i]);
            firstBuySell[i] = profit;
        }
        
        // backward
        int maxSellingPrice = Integer.MIN_VALUE;
        for(int i=n-1; i>0; i--){
            maxSellingPrice = Math.max(maxSellingPrice, prices[i]);
            //  At most two transactions, you must sell the stock before you buy again
            profit = Math.max(profit, maxSellingPrice - prices[i] + firstBuySell[i-1]);
        }
        return profit;
    }
}

// Slight modification of the first solution
class Solution2 {
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // Forward & backward
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2)
            return 0;

        int[] firstBuySell = new int[n];
        int minBuy = prices[0];
        for(int i=1; i<n; ++i){
            firstBuySell[i] = Math.max(firstBuySell[i-1], prices[i] - minBuy);  
            minBuy = Math.min(minBuy, prices[i]);
        }
        
        int profit = firstBuySell[n-1];
        int maxSell = prices[n-1];
        for(int i=n-2; i>0; --i){
            profit = Math.max(profit, maxSell - prices[i] + firstBuySell[i-1]);
            maxSell = Math.max(maxSell, prices[i]);
        }
        return profit;
    }
}

// Solution 3
class Solution3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2)
            return 0;
        int[][] states = {{Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0}, 
                          {Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0}};
        int cur = 0;
        int next = 1;
        for(int price : prices){
            states[next][0] = Math.max(states[cur][0], -price);
            states[next][1] = Math.max(states[cur][1], states[cur][0] + price);
            states[next][2] = Math.max(states[cur][2], states[cur][1] - price);
            states[next][3] = Math.max(states[cur][3], states[cur][2] + price);
            // swap cur and next state
            cur = (cur + 1) % 2;
            next = (next + 1) % 2;
        }        
        return Math.max(states[cur][1], states[cur][3]);
    }
}

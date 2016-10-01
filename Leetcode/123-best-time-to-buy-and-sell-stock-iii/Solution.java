/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
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
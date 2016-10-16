/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

class Solution {
    // Memoization
    Map<Integer, Integer> dp;
    public int getMinCoin(int[] coins, int amount){
        if(amount == 0)                     return 0;
        if(dp.containsKey(amount))          return dp.get(amount);
            
        int r = Integer.MAX_VALUE;
        // Try all coins
        for(int i=0; i<coins.length; i++){
            if(coins[i] > amount)
                continue;
            int cnt = getMinCoin(coins, amount - coins[i]);
            if(r > cnt)
                r = cnt;
        }
        dp.put(amount, (r == Integer.MAX_VALUE) ? Integer.MAX_VALUE : r + 1);
        return dp.get(amount);
    }
    // Time: O(type of coins * amount)
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)         return 0;
        dp = new HashMap<>();        
        getMinCoin(coins, amount);
        return dp.get(amount) == Integer.MAX_VALUE ? -1 : dp.get(amount);
    }
}
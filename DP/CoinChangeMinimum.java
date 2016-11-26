/*
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be 
made up by any combination of the coins, return -1.

Link : https://leetcode.com/problems/coin-change/
*/

import java.util.*;

class CoinChangeMinimumMemoization{
    // Memoization
    public int getMinCoin(int[] coins, int amount, int[] dp ){
        if(amount == 0)                     return 0;
        if(dp[amount] != -1)                return dp[amount];
            
        int r = Integer.MAX_VALUE;
        // Try all coins
        for(int i=0; i<coins.length; i++){
            if(coins[i] > amount)
                continue;
            int cnt = getMinCoin(coins, amount - coins[i], dp);
            if(r > cnt)
                r = cnt;
        }
        dp[amount] = (r == Integer.MAX_VALUE) ? Integer.MAX_VALUE : r + 1;
        return dp[amount];
    }
    // Time: O(type of coins * amount)
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)         return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        
        getMinCoin(coins, amount, dp);
        return dp[amount]  == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
public class CoinChangeMinimum{
    // Time: O(type of coins * amount)
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if(n == 0)      return 0;
        int[] dp = new int[amount + 1];
        int[] used = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(used, -1);
        
        // Base case
        dp[0] = 0;
        for(int i=1; i<=amount; i++){
            for(int coin : coins){
                if(i - coin >= 0 && dp[i-coin] != Integer.MAX_VALUE){
                  if(dp[i] > dp[i-coin] + 1){
                    dp[i] = dp[i-coin] + 1;
                  }
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount] ;
    } 

    public static void main ( String args[] ) {
        int coins[] = {186,419,83, 408};
        CoinChangeMinimum s = new CoinChangeMinimum();
        System.out.println(s.coinChange(coins, 6249));
    }         
}
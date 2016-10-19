/*
You are given a set of coins S. In how many ways can you make sum N assuming you have infinite amount of each coin in the set.

Note : Coins in set S will be unique. Expected space complexity of this problem is O(N).

Example :

Input : 
    S = [1, 2, 3] 
    N = 4

Return : 4

Explanation : The 4 possible ways are
{1, 1, 1, 1}
{1, 1, 2}
{2, 2}
{1, 3}  
Note that the answer can overflow. So, give us the answer % 1000007
*/

import java.util.*;

class CoinWaysInfiniteTable {
    public final static int MOD = 1000007;
    public int coinchange2(ArrayList<Integer> coins, int amount) {
        if(amount == 0)
            return 0;
    
        int[] dp = new int[amount+1];
    
        // Base case: Number of ways to make 0 amount = 1 (No coins needed)
        dp[0] = 1;
        for(int coin : coins){
          for(int i=1; i<=amount; i++){
            if(i - coin >= 0){
              dp[i] += dp[i-coin] % MOD;
              dp[i] = dp[i] % MOD;
            }
          }
        }
        return dp[amount] % MOD;
    }
}

// Memoized version
public class CoinWaysInfinite {
    // space: O(n*Amount)
    // Include nth coin or not
    public final static int MOD = 1000007;
    public int getCount(ArrayList<Integer> coin, int n, int amount, int[][] dp){
        if(amount == 0)                 return 1;
        if(amount < 0)                  return 0;
        if(n < 0)                       return 0;
        if(dp[n][amount] != -1)         return dp[n][amount];
        
        int x = getCount(coin, n-1, amount, dp) % MOD;
        int y = 0;
        if(amount >= coin.get(n))
            y = getCount(coin, n, amount - coin.get(n), dp) % MOD;
        dp[n][amount] = (x + y) % MOD;      
        return dp[n][amount];
    }
    public int coinchange2(ArrayList<Integer> coins, int amount) {
        int n = coins.size();
        if(n == 0)          return 0;
        if(amount == 0)     return 1;
        // init
        int[][] dp = new int[n][amount+1];
        for(int[] t : dp)
           Arrays.fill(t, -1);

        return getCount(coins, n-1, amount, dp);
    }

    public static void main ( String args[] ) {
        Integer coins[] = {1, 2, 3};
        ArrayList<Integer> a = new ArrayList<Integer>(coins.length);
        Collections.addAll(a, coins);

        CoinWaysInfinite s = new CoinWaysInfinite();
        System.out.println(s.coinchange2(a, 4));
    }         
}


/*
Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} 
valued coins, how many ways can we make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
So output should be 4. 

For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. 
So the output should be 5.

Link: http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
*/

// Time: O(n*Amount) where n is the type of coins
import java.util.*;

class CoinChangeWaysMemoized {
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
}



class CoinChangeWays{
  // Space: O(n) Solution
  public int coinCount(int[] coins, int amount){
    if(amount == 0)
        return 0;

    int n = coins.length;
    int[] dp = new int[amount+1];

    // Base case: Number of ways to make 0 amount = 1 (No coins needed)
    dp[0] = 1;
    for(int coin : coins){
      for(int i=1; i<=amount; i++){
        if(i - coin >= 0){
          dp[i] += dp[i-coin];
        }
      }
    }
    return dp[amount];
  }
  public void print(List<Integer> cur){
    for(int i : cur)
        System.out.print(i + " ");
    System.out.println();
  }

  // print all solutions (backtracking)
  // O(2^n)
  public void bktk(int[] coins, int targetAmount, int curAmount, int prev, List<Integer> cur){
    if(curAmount == targetAmount){
      // print the solution
      print(cur);
      return;
    }

    if(curAmount > targetAmount)
        return;

    for(int i=prev; i<coins.length; i++){
      curAmount += coins[i];
      cur.add(coins[i]);
      // Same coin can be used multiple times
      bktk(coins, targetAmount, curAmount, i, cur);
      curAmount -= coins[i];
      cur.remove(cur.size()-1);
    }
  }

  public void printSolution(int[] coins, int amount){
    List<Integer> cur = new ArrayList<>();
    Arrays.sort(coins);
    bktk(coins, amount, 0, 0, cur);
  }

  public static void main(String args[]){
      CoinChangeWays ob = new CoinChangeWays();
      int coins[] = {2, 5, 3, 6};
      System.out.println("Number of solutions: " + ob.coinCount(coins, 10));
      System.out.println("Solutions:");  
      ob.printSolution(coins, 10);
   }  
}
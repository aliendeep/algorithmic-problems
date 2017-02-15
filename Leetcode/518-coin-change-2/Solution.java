/*
You are given coins of different denominations and a total amount of money. Write 
a function to compute the number of combinations that make up that amount. You may 
assume that you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10] 
Output: 1
*/

public class Solution {
    int[][] dp;
    
    public int cntWays(int i, int target, int[] coins) {
        if(i == 0)                  return target == 0 ? 1 : 0;
        if(target == 0)             return 1;
        if(dp[i][target] != -1)     return dp[i][target];
        
        int ways = 0;
        if(coins[i-1] <= target){
            ways += cntWays(i, target - coins[i-1], coins);
        }
        ways += cntWays(i-1, target, coins);
        
        dp[i][target] = ways;
        return ways;
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        if(n == 0)  return amount == 0 ? 1 : 0;

        dp = new int[n+1][amount+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        return cntWays(n, amount, coins);
    }
}

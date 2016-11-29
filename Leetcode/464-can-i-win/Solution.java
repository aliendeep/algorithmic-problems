/*
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
*/
public class Solution {
    int n;
    int target;
    
    public int getState(int sum, int pos, int[] dp){
        if(sum >= target)       return 0;
        if(dp[pos] != -1)       return dp[pos];
        // for all numbers
        int r = 0;
        for(int i=0; i<n; ++i){
            // if the number is not chosen yet
            if((pos & (1<<i)) != 0){
                // Add this number to the sum
                // toggle this pos as used
                if(getState(sum + (i+1), pos^(1<<i), dp) == 0){
                    r = 1;
                    break;
                }
            }
        }
        dp[pos] = r;
        return r;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.n = maxChoosableInteger;
        if(n <= 0)          return false;
        this.target = desiredTotal;
        if(n >= target)     
            return true;
        // if it's not possible to reach the target sum using the limit
        if((n*(n+1))/2 < target)
            return false;
            
        int[] dp = new int[(1<<n)];
        Arrays.fill(dp, -1);

        return getState(0, (1<<n)-1, dp) == 1;
    }
}
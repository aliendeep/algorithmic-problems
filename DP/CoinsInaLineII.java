/*
There are n coins with different value in a line. Two players take turns to 
take one or two coins from left side until there are no more coins left. 
The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?
*/

public class CoinsInaLineII {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public int getValue(int[] values, int i, int[] dp){
        int n = values.length;
        // Reached the right hand side
        if(i >= n)          return 0;
        // last coin
        if(i == n-1)        return values[n-1];
        // last two coins
        if(i == n-2)        return values[n-1] + values[n-2];
        if(i == n-3)        return values[n-2] + values[n-3];
        if(dp[i] != -1)
            return dp[i];
            
        // Select 1 coin, then next player will take the optimal option, so current
        // player will be able to pick up second optimal coin
        // getValue(values, i+2, dp) - if next player chooses 1 coin, then current
        // player will be able to pick up i+2 th coin
        // getValue(values, i+3, dp - if next player chooses 2 coins
        int x = values[i] + 
                Math.min(getValue(values, i+2, dp), getValue(values, i+3, dp));
        // Take Two coins
        int y = values[i] + values[i+1] + 
                Math.min(getValue(values, i+3, dp), getValue(values, i+4, dp));
        
        // Choose the optimal selection
        dp[i] = Math.max(x, y);
        return dp[i];
    }

    public boolean firstWillWin(int[] values) {
        int n = values.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        int firstPlayerCoinCount = getValue(values, 0, dp);
        int sum = 0;
        for(int x : values)
            sum += x;
        // The player who take the coins with the most value wins.
        return firstPlayerCoinCount > (sum - firstPlayerCoinCount) ? true: false;
    }
}


// http://www.lintcode.com/en/problem/coins-in-a-line/
/*
There are n coins in a line. Two players take turns to take one or two coins 
from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?
*/
public class CoinsInaLine {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    int[] move = {1, 2};
    
    public boolean isWinningState(int n, Map<Integer, Boolean> dp){
        if(n == 0)
            return false;
        if(n == 1 || n == 2)
            return true;
            
        if(dp.containsKey(n))
            return dp.get(n);
        
        // coin
        for(int i=0; i<2; ++i){
            if(move[i] <= n){            
                int next = n - move[i];
                if(!isWinningState(next, dp)){
                    dp.put(n, true);
                    return true;
                }
            }
        }
                
        dp.put(n, false);
        return false;
    } 
     
    public boolean firstWillWin(int n) {
        Map<Integer, Boolean> dp = new HashMap<>();
        // Find pattern using dp
        /*
            isWinningState(n, dp);
            for(int i=1; i<=n; ++i){
                if(dp.containsKey(i))
                    System.out.println(i + " " + dp.get(i));
            }
        */
        return n % 3 == 0 ? false : true;
    }
}

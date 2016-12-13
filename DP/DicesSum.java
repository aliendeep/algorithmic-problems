/*
Throw n dices, the sum of the dices' faces is S. Given n, find the all possible value of S along 
with its probability.
*/

/*
http://www.lintcode.com/en/problem/dices-sum/
Throw n dices, the sum of the dices' faces is S. Given n, find the all possible 
value of S along with its probability.
*/

public class Solution {
    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Write your code here
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair
        
        int targetSum = 6*n;
        double[][] dp = new double[n+1][targetSum+1];
        
        // i = number of throws
        // j = sum
        // Number of ways to make 0 sum thowing the dice 0 times
        dp[0][0] = 1;
        // For all throws
        for(int i=1; i<=n; ++i){
            for(int j=1; j<=targetSum; ++j){
                // For all faces [1..6]
                for(int k=1; k<=6; ++k){
                    // if target is greater than face value
                    if(j >= k){
                        // Reach the target sum j using i throws  
                        dp[i][j] = dp[i][j] + dp[i-1][j - k];
                    }
                }
            }        
        }
        double total = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> result = new ArrayList<>();
        for(int i=n; i<=targetSum; ++i){
            double probability = (dp[n][i]/total);
            result.add(new AbstractMap.SimpleEntry<Integer, Double>(i, probability));
        }
        return result;
 }
}

class Solution2 {
    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    // n - number of throws    
    public double getWays(int n, int target, double[][] dp){
        if(target < 0 || n < 0)
            return 0;
        if(n == 0){
            return target == 0 ? 1 : 0;
        }
        if(dp[n][target] != -1)
            return dp[n][target];
        
        double ways = 0;
        for(int face=1; face<=6; ++face){
            // Add the number of ways to reach the target - face using n-1 throws
            if(target >= face)
                ways += getWays(n-1, target - face, dp);
        }
        dp[n][target] = ways;
        return dp[n][target];
    }
    
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        int targetSum = 6*n;
        double[][] dp = new double[n+1][targetSum+1];
        for(double[] t : dp)
            Arrays.fill(t, -1);
        
        // All possibilities
        double total = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> result = new ArrayList<>();
        for(int i=n; i<=targetSum; ++i){
            double ways = getWays(n, i, dp);
            double probability = ways/total;
            result.add(new AbstractMap.SimpleEntry<Integer, Double>(i, probability));
        }
        return result;
 }
}
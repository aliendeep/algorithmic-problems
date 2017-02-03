/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the 
cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

// Time Complexity: O(nk^2)
// Space: O(nk)
// Memoization
public class Solution {
    int[][] dp;
    int[][] costs;
    int k;
    
    // Gives the minimum cost of painting houses 0...i with i-th having 'color'.
    public int getCost(int i, int color){
        if(i == 0)                  return costs[i][color];
        if(dp[i][color] != -1)      return dp[i][color];

        int cost = Integer.MAX_VALUE;
        for(int prevColor = 0; prevColor < k; prevColor++){
            // No two adjacent houses have the same color
            if(prevColor == color)  continue;
            cost = Math.min(cost, getCost(i-1, prevColor) + costs[i][color]);
        }
        dp[i][color] = cost;
        return cost;
    }
    // The cost of painting each house with a certain color is represented by a n x k cost matrix
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if(n == 0)          return 0;
        this.k = costs[0].length;
        dp = new int[n][k];
        this.costs = costs;
        
        // Initialize dp array
        for(int i=0; i<n; i++){
            for(int j=0; j<k; j++){
                dp[i][j] = -1;
            }
        }
        // K colors
        int minCost = Integer.MAX_VALUE;
        for(int color=0; color<k; color++){
            minCost = Math.min(minCost, getCost(n-1, color));
        }
        return minCost;        
        
    }
}

// Iterative
// Time Complexity: O(nk^2)
class Solution2 {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if(n == 0)      return 0;
        int k = costs[0].length;
        
        int[][] dp = new int[n][k];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        // init
        for(int color=0; color<k; ++color){
            dp[0][color] = costs[0][color];
        }
        
        for(int i=1; i<n; i++){
            for(int j=0; j<k; j++){
                int cost = Integer.MAX_VALUE;
                for(int t=0; t<k; t++){
                    if(t == j)  continue;
                    cost = Math.min(cost, dp[i-1][t]);
                }
                dp[i][j] = cost + costs[i][j];
            }
        }
        int minCost = Integer.MAX_VALUE;
        for(int j=0; j<k; j++)
            minCost = Math.min(minCost, dp[n-1][j]);
        
        return minCost;
    }
}

// O(nk) solution
public class Solution {
    // Keep track of the lowest two min cost options
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if(n == 0)      return 0;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        
        int minIndex1 = -1, minIndex2 = -1;
        for(int i=0; i<n; ++i){
            int prev1 = minIndex1, prev2 = minIndex2;
            minIndex1 = -1;
            minIndex1 = -1;
            for(int j=0; j<k; ++j){
                if(j != prev1){
                    dp[i][j] = (prev1 == -1 ? 0 : dp[i-1][prev1]) + costs[i][j]; 
                }
                // use the second lowest color
                else{
                    dp[i][j] = (prev2 == -1 ? 0 : dp[i-1][prev2]) + costs[i][j]; 
                }
                
                if(minIndex1 == -1 || dp[i][j] < dp[i][minIndex1]){
                    minIndex2 = minIndex1;
                    minIndex1 = j;
                }
                else if(minIndex2 == -1 || dp[i][j] < dp[i][minIndex2]){
                    minIndex2 = j;
                }
            }
        }
        return dp[n-1][minIndex1];
    }
}

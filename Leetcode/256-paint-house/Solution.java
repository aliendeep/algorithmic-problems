/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

/*
Sample Input:
[[5,8,6],[19,14,13],[7,5,12],[14,15,17],[3,20,10]]

Sample Output:
43
*/

// Memoization
public class Solution {
    int[][] dp;
    int[][] costs;

    // Gives the minimum cost of painting houses 0...i with i-th having 'color'.
    public int getCost(int i, int color){
        if(i < 0)                   return 0;
        if(dp[i][color] != -1)      return dp[i][color];

        int cost = Integer.MAX_VALUE;
        for(int prevColor = 0; prevColor < 3; prevColor++){
            // No two adjacent houses have the same color
            if(prevColor == color)  continue;
            cost = Math.min(cost, getCost(i-1, prevColor) + costs[i][color]);
        }
        dp[i][color] = cost;
        return cost;
    }
    
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0)          return 0;
        int k = costs[0].length;
        dp = new int[n][k];
        this.costs = costs;
        
        // Initialize dp array
        for(int i=0; i<n; i++){
            for(int j=0; j<k; j++){
                dp[i][j] = -1;
            }
        }
        // Three colors
        int minCost = Integer.MAX_VALUE;
        for(int color=0; color<3; color++){
            minCost = Math.min(minCost, getCost(n-1, color));
        }
        return minCost;
    }
}
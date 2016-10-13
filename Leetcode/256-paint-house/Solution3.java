/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0)      return 0;
        int k = costs[0].length;
        
        int[][] dp = new int[n][k];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        
        for(int i=0; i<n; i++){
            for(int j=0; j<3; j++){
                if(i == 0){
                    dp[0][j] = costs[0][j];
                }
                else{
                    if(j == 0)
                        dp[i][j] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][j];
                    else if(j == 1)
                        dp[i][j] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][j];
                    else
                        dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][j];
                }
            }            
        }
        return Math.min(Math.min(dp[n-1][0],dp[n-1][1]), dp[n-1][2]);

    }
}
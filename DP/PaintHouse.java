/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

public class PaintFence {
    int[][] costs;
    int[][] dp;
    
    // Cost of coloring ith house using color i
    public int getCost(int[][] dp, int i, int color){
        // Base Case
        if(i < 0)       return 0;
        if(dp[i][color] != -1)  return dp[i][color];
        
        int r = Integer.MAX_VALUE;    
        for(int c=0; c<3; c++){
            if(c == color)
                continue;
            // Use different color than color
            r = Math.min(r, getCost(dp, i-1, c) + costs[i][color]);
        }
        dp[i][color] = r;
        return r;
    }
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0)      return 0;
        int k = costs[0].length;
        
        this.costs = costs;
        dp = new int[n][k];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        int r = Integer.MAX_VALUE;    
        for(int c=0; c<k; c++){
            r = Math.min(r, getCost(dp, n-1, c));
        }
        return r;
    }
}
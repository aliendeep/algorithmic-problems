/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/
public class Solution {
    // Without Modifying the costs array
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0)
            return 0;
        int rPrev = costs[0][0];
        int gPrev = costs[0][1];
        int bPrev = costs[0][2];
        int r, g, b;
        for(int i=1; i<n; i++){
            r = Math.min(gPrev, bPrev) + costs[i][0];
            g = Math.min(rPrev, bPrev) + costs[i][1];
            b = Math.min(rPrev, gPrev) + costs[i][2];
            rPrev = r;
            gPrev = g;
            bPrev = b;
        }
        return Math.min(Math.min(rPrev, gPrev), bPrev);
    }
}
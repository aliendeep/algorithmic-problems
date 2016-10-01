/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

// [[2],[3,4],[6,5,7],[4,1,8,3]]

public class Solution {
    // dp[i][j] = min(triangle[i+1][j], triangle[i+1][j]);
    // Space: O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int nrow = triangle.size();
        if(nrow == 0)
            return 0;
            
        int[][] dp = new int[nrow][nrow];
        // for all rows
        for(int i=0; i<nrow; i++){
            List<Integer> row = triangle.get(i);
            if(i == 0){
                dp[0][0] = row.get(0);
                continue;
            }
            // only one option, first column
            dp[i][0] = dp[i-1][0] + row.get(0);
            for(int j=1; j<row.size()-1; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + row.get(j);
            }   
            // last column, only one option
            dp[i][row.size()-1] = dp[i-1][row.size()-2] + row.get(row.size()-1);
        }
        int minSum = Integer.MAX_VALUE;
        // last row
        for(int j=0; j<nrow; j++){
            minSum = Math.min(minSum, dp[nrow-1][j]);
        }
        return minSum;
    }
}
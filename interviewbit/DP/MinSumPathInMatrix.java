
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time. 
Example :

Input : 

    [  1 3 2
       4 3 1
       5 6 1
    ]

Output : 8
     1 -> 3 -> 2 -> 1 -> 1
*/
public class Solution {
  public int minPathSum(ArrayList<ArrayList<Integer>> a) {
      int r = a.size();
      if(r == 0)
          return 0;
      int c = a.get(0).size();
      int[][] dp = new int[r][c];
      
      // first column
      dp[0][0] = a.get(0).get(0);
      for(int i=1; i<r; ++i){
        dp[i][0] = dp[i-1][0] + a.get(i).get(0);          
      }
      // first row
      for(int j=1; j<c; ++j){
        dp[0][j] = dp[0][j-1] + a.get(0).get(j);          
      }

      for(int i=1; i<r; ++i){
          for(int j=1; j<c; ++j){
              dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + a.get(i).get(j);
          }
      }
      return dp[r-1][c-1];
  }
}

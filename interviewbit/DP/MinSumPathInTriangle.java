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

public class Solution {
  public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
      int r = a.size();
      if(r == 0)
          return 0;
      int[][] dp = new int[r][r];

      dp[0][0] = a.get(0).get(0);
      for(int i=1; i<r; ++i){
          List<Integer> t = a.get(i);
          for(int j=0; j<t.size(); ++j){
              int x = Integer.MAX_VALUE;
              if(j - 1 >= 0)
                  x = dp[i-1][j-1];

              int y = Integer.MAX_VALUE;
              if(j < i){
                  y = dp[i-1][j];
              }
              dp[i][j] = Math.min(x, y)+ t.get(j);
          }
      }
      
      int min = Integer.MAX_VALUE;
      for(int j=0; j<r; ++j){
          min = Math.min(dp[r-1][j], min);
      }
      return min;
  }
}

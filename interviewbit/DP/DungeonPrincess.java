public class Solution {
  public int calculateMinimumHP(ArrayList<ArrayList<Integer>> a) {
      int r = a.size();
      if(r == 0)
          return 0;
      int c = a.get(0).size();
      int[][] dp = new int[r][c];
      dp[r-1][c-1] = Math.max(1 - a.get(r-1).get(c-1), 1);
      // last row
      for(int j=c-2; j>=0; --j){
          dp[r-1][j] = Math.max(dp[r-1][j+1] - a.get(r-1).get(j), 1);
      }
      // last col
      for(int i=r-2; i>=0; --i){
          dp[i][c-1] = Math.max(dp[i+1][c-1] - a.get(i).get(c-1), 1);
      }
        
        for(int i=r-2; i>=0; --i){
          for(int j=c-2; j>=0; --j){
                int x = Math.max(dp[i+1][j] - a.get(i).get(j), 1);
                int y = Math.max(dp[i][j+1] - a.get(i).get(j), 1);
                dp[i][j] = Math.min(x, y);
          }        
        }     
      return dp[0][0];
  }
}

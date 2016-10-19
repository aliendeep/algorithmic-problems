/*
Given a 2 * N Grid of numbers, choose numbers such that the sum of the numbers
is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.

Example:

Grid:
  1 2 3 4
  2 3 4 5
so we will choose
3 and 5 so sum will be 3 + 5 = 8
*/

public class MaxSumWithoutAdjacentElements {
  public int adjacent(ArrayList<ArrayList<Integer>> a) {
      int r = a.size();
      if(r == 0)
          return 0;
      int n = a.get(0).size();
      int[] dp = new int[n];
      
      ArrayList<Integer> x = a.get(0);
      ArrayList<Integer> y = a.get(1);
      dp[0] = Math.max(x.get(0), y.get(0));
      if(n == 1)
          return dp[0];
        dp[1] = Math.max(dp[0], Math.max(x.get(1), y.get(1)));
        
        for(int i=2; i<n; i++){
            // include this column
            dp[i] = Math.max(x.get(i), y.get(i)) + dp[i-2];
            // Max of excluding this column and including this col 
            dp[i] = Math.max(dp[i-1], dp[i]);
        } 
        return dp[n-1];
  }
}

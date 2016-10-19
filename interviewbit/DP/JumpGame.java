/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return 1 ( true ).

A = [3,2,1,0,4], return 0 ( false ).

Return 0/1 for this problem
*/

public class JumpGame {
    // DP
  public int canJump(ArrayList<Integer> a) {
      int n = a.size();
        if(n <= 1)
            return 1;

      int[] dp = new int[n];
      Arrays.fill(dp, -1);
      
      dp[0] = a.get(0);
      for(int i=1; i<n; i++){
          if(dp[i-1] >= i){
              dp[i] = Math.max(i + a.get(i), dp[i-1]);
          }
      }
      
      for(int i=0; i<n; i++)
          if(dp[i] >= n-1)
              return 1;
      return 0;
  }
}


class JumpGameGreedy {
    // Greedy
  public int canJump(ArrayList<Integer> a) {
      int maxFurthestReach = 0;
      for(int i=0; i<=maxFurthestReach && i<a.size(); i++){
          maxFurthestReach = Math.max(maxFurthestReach, i+a.get(i));
      }
      return maxFurthestReach >= a.size()-1 ? 1 : 0;
  }
}

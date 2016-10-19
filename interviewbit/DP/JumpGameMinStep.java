public class JumpGameMinStep {
    // DP O(n^2)
     // dp[i] - minimum number of jumps needed to reach from 0 to i
  public int jump(ArrayList<Integer> a) {
      int n = a.size();
      int[] dp = new int[n];
      Arrays.fill(dp, n+1);
        
        dp[0] = 0;      
      for(int i=1; i<n; i++){
          for(int j=0; j<i; j++){
              if(dp[j] != n+1 && i <= j + a.get(j)){
                  dp[i] = Math.min(dp[i], dp[j] + 1);
                  break;
              }
          }
      }
      return dp[n-1] == n + 1 ? -1 : dp[n-1];
  }
}

class Solution {
    // Greedy
  public int jump(ArrayList<Integer> a) {
      int n = a.size();
        int furthestReach = 0;
        int minStep = 0;
        int maxReach = 0;

        for(int i=0; i<n-1; i++){
            furthestReach = Math.max(furthestReach, a.get(i) + i);
            if(i == maxReach){
                if(furthestReach <= maxReach)
                    return -1;
                    
                maxReach = furthestReach;
                minStep++;
            }
        }
        return minStep;     
  }
}

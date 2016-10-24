/*
https://leetcode.com/problems/jump-game-ii/

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
*/

public class JumpGameMinStep {
    // DP 
    // Time: O(n^2)
    public int jump(int[] A) {
        int n = A.length;
        if(n <= 1)
            return 0;
        // dp[i] - minimum number of jumps needed to reach from 0 to i
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                // index j is reachable
                if(i <= j + A[j] && dp[j] != Integer.MAX_VALUE){
                     dp[i] = Math.min(dp[i], dp[j]+1);
                     break;
                }
            }
        }
        return dp[n-1];
    }
}

public class JumpGameMinStepGreedy {
    // Greedy
    // O(n)
    public int jump(int[] A) {
        int n = A.length;
        if(n <= 1)      return 0;
            
        int furthestReach = 0;
        int minStep = 0;
        int maxReach = 0;

        for(int i=0; i<n-1; i++){
            furthestReach = Math.max(furthestReach, A[i] + i);
            if(i == maxReach){
                maxReach = furthestReach;
                minStep++;
            }
        }
        return minStep;
    }
}
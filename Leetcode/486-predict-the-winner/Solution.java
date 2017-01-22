/*
Given an array of scores that are non-negative integers. Player 1 picks one of 
the numbers from either end of the array followed by the player 2 and then player 
1 and so on. Each time a player picks a number, that number will not be available 
for the next player. This continues until all the scores have been chosen. 
The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume 
each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 
chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return 
True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.
*/
// DP
public class Solution {
    int[] cumSum;
    int[][] dp;
    int[] nums;
    
    int sum(int i, int j){
        return cumSum[j] - (i > 0 ? cumSum[i-1] : 0);         
    }
    
    public int getValue(int i, int j){
        if(i > j)           return 0;
        if(i == j)          return nums[i];
        if(dp[i][j] != -1)  return dp[i][j];
        
        int x = sum(i, j) - getValue(i+1, j);
        int y = sum(i, j) - getValue(i, j-1);
        dp[i][j] = Math.max(x, y);
        return dp[i][j];
    }

    
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        cumSum = new int[n];
        cumSum[0] = nums[0];
        for(int i=1; i<n; ++i){
            cumSum[i] = cumSum[i-1] + nums[i]; 
        }
        
        dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        int amt = getValue(0, n-1);
        int rem = cumSum[n-1] - amt;
        return amt >= rem;
    }
}

// DP 2
class Solution2 {
    int[] cumSum;
    int[][] dp;
    int[] nums;
    
    int sum(int i, int j){
        return cumSum[j] - (i > 0 ? cumSum[i-1] : 0);         
    }
    
    public int getValue(int i, int j){
        if(i > j)           return 0;
        if(i == j)          return nums[i];
        if(dp[i][j] != -1)  return dp[i][j];
        
        dp[i][j] = sum(i, j) - Math.min(getValue(i+1, j), getValue(i, j-1));
        return dp[i][j];
    }

    
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        cumSum = new int[n];
        cumSum[0] = nums[0];
        for(int i=1; i<n; ++i){
            cumSum[i] = cumSum[i-1] + nums[i]; 
        }
        
        dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        int amt = getValue(0, n-1);
        int rem = cumSum[n-1] - amt;
        return amt >= rem;
    }
}

// Alternative
class Solution3 {
    int[][] dp;
    int[] nums;
    
    public int getValue(int i, int j){
        if(i > j)           return 0;
        if(i == j)          return nums[i];
        if(dp[i][j] != -1)  return dp[i][j];
        
        // take ith coin
        int x = nums[i] + Math.min(getValue(i+2, j), getValue(i+1, j-1)); 
        // take jth coin
        int y = nums[j] + Math.min(getValue(i, j-2), getValue(i+1, j-1)); 
        dp[i][j] = Math.max(x, y);
        return dp[i][j];
    }

    
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        
        int total = 0;
        for(int num : nums){
            total += num;
        }
        
        dp = new int[n][n];
        for(int[] t : dp)
            Arrays.fill(t, -1);
            
        int amt = getValue(0, n-1);
        int rem = total - amt;
        return amt >= rem;
    }
}

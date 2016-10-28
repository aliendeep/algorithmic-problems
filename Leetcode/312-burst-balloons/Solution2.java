/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

public class Solution{
    // Time Complexity: O(n^3)
    // Divide & Conquer
    // Find the last balloon to burst
    // Then, the left and right side becomes independent
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // Add 1 at the beginning and end 
        int[] A = new int[n+2];
        A[0] = 1;
        int i = 1;
        // Burst all 0 ballons
        for(int num : nums){
            if(num > 0)
                A[i++] = num;
        }        

        A[i] = 1;

        n = A.length;
        int[][] dp = new int[n][n];
        for(int k = 2; k < n; ++k){
            for(int start = 0; start < n - k; ++start){
                int end = start + k;
                for(i=start+1; i<end; ++i){            
                    dp[start][end] = Math.max(dp[start][end], A[start]*A[i]*A[end] + dp[start][i] + dp[i][end]);
                }
            }
        }
        return dp[0][n-1];
    }
}
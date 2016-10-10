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

        int len = A.length;
        int[][] dp = new int[len][len];
        return getCoins(dp, A, 0, len - 1);
    }
    public int getCoins(int[][] dp, int[] A, int start, int end){
        if(start + 1 == end)        return 0;
        if(dp[start][end] > 0)      return dp[start][end]; 
        
        int r = 0;
        for(int i=start+1; i<end; i++){
            int cur = (A[start] * A[i] * A[end]) + getCoins(dp, A, start, i) + getCoins(dp, A, i, end);
            r = Math.max(r, cur);
        }
        dp[start][end] = r;
        return r;
    }
}
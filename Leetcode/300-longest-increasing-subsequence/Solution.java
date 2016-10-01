/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.
*/

public class Solution {
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1); 
            }
        }
        
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}
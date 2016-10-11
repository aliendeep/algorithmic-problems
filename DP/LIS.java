/*
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.
Link: http://www.lintcode.com/en/problem/longest-increasing-subsequence/
*/

public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
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

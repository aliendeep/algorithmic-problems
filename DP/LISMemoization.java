/*
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.
Link: http://www.lintcode.com/en/problem/longest-increasing-subsequence/
*/
// Time complexity: O(n^2)
// Space: O(n)

public class LISMemoization {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    int[] dp;
    
    public int getLIS(int[] nums, int n){
        if(n == 0){
            dp[0] = 1;
            return 1;
        }
        if(dp[n] != -1)
            return dp[n];
        
        int  maxEndingHere = 1;
        for(int i=0; i<n; i++){
            int r = getLIS(nums, i);
            if(nums[i] < nums[n])
                maxEndingHere = Math.max(maxEndingHere, r + 1);
        }

        // Compare local max with the global max
        dp[n] = maxEndingHere;
        return dp[n];
    }
    
    // Memoized version for LIS problem. 
    public int longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        
        dp = new int[n];
        Arrays.fill(dp, -1);
        getLIS(nums, n-1);
        
        int maxLength = 1;
        for(int i=0; i<n; i++)
            maxLength = Math.max(maxLength, dp[i]);
        return maxLength;
    }
}

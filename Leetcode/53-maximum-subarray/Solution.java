/*
Find the contiguous subarray within an array (containing at least one number) 
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
- If you have figured out the O(n) solution, try coding another solution using 
the divide and conquer approach, which is more subtle.
*/

public class Solution {
    // Kadene's algorithm
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for(int n : nums){
            if(curSum < 0)
                curSum = n;
            else
                curSum += n;
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}

public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for(int n : nums){
            curSum += n;
            maxSum = Math.max(maxSum, curSum);
            if(curSum < 0)
                curSum = 0;
        }
        return maxSum;
        
    }
}

// Straightforward DP
class Solution2 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // Maximum sum subarray ending at i
        int[] dp = new int[n];
        dp[0] = nums[0];
        int result = dp[0];
        for(int i=1; i<n; ++i){
            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}

// O(nlogn)
class Solution3 {
    public int helper(int[] nums, int l, int h){
        if(l > h)       return Integer.MIN_VALUE;
        if(l == h)      return nums[l];
        int mid = (l+h)/2;
        int left = helper(nums, l, mid-1);
        int right = helper(nums, mid+1, h);
        // Sub best
        int x = Math.max(left, right);

        int lsum = 0;
        int lmax = 0;
        for(int i=mid-1; i>=l; --i){
            lsum += nums[i];
            if(lsum > lmax)
                lmax = lsum;
        }

        int rsum = 0;
        int rmax = 0;
        for(int i=mid+1; i<=h; ++i){
            rsum += nums[i];
            if(rsum > rmax)
                rmax = rsum;
        }
        return Math.max(x, lmax + rmax + nums[mid]);
    }
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        return helper(nums, 0, n-1);
    }
}

class Solution4 {
    // O(n) Solution
    class Info{
        // sum starting from the start endpoint
        int startSum; 
        int endSum;
        // Maximum sum result
        int maxSum;
        // sum of values of the whole subarray
        int sum;
        public Info(int i, int j, int k, int s){
            startSum = i;
            endSum = j;
            maxSum = k;
            sum = s;
        }
    }
    
    public Info helper(int[] nums, int l, int h) {
        // one element
        if(l == h)  return new Info(nums[l], nums[l], nums[l], nums[l]);
        if(l > h)   return new Info(0, 0, 0, 0);
        int mid = (l+h)/2;
        Info left = helper(nums, l, mid);
        Info right = helper(nums, mid+1, h);
        // best sum starting from left endpoint
        int ss = Math.max(left.startSum, left.sum + right.startSum);
        // best sum starting from right endpoint
        int es = Math.max(right.endSum, right.sum + left.endSum);
        // sum of full array
        int sum = left.sum + right.sum;
        // maximum sum of individual part
        int ms = Math.max(left.maxSum, right.maxSum);
        ms = Math.max(ms, sum);
        ms = Math.max(ms, ss);
        ms = Math.max(ms, es);
        ms = Math.max(ms, left.endSum + right.startSum);
        return new Info(ss, es, ms, sum);        
    }
    
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0)  return 0;
        return helper(nums, 0, n-1).maxSum;
    }
}

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
/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.

Examples:

Input:
nums = [1,2,3,4,5]
m = 2

Output:
9

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5],
where the largest sum among the two subarrays is only 9.
*/

public class Solution {
    // Allowed maxSum in a bucket
    public int numberOfBucketsNeeded(int[] nums, long maxSum){
        long curSum = 0;
        int k = 1;
        for(int n : nums){
            curSum += n;
            if(curSum > maxSum){
                curSum = n;
                k++;
            }
        }
        return k; 
    }

    public int splitArray(int[] nums, int m) {
        if(nums.length == 0)
            return 0;
        // largest number
        long mi = nums[0];
        // sum of all elements
        long sum = nums[0];
        for(int i=1; i<nums.length; i++){
            mi = Math.max(mi, nums[i]);
            sum += nums[i];
        }
        // At least limit of MaxSum needed to be equal of the largest element
        long maxSumLow = mi;
        long maxSumHigh = sum;
        int k;
        while(maxSumHigh - maxSumLow > 5){
            long midSum = (maxSumHigh - maxSumLow)/2 + maxSumLow;
            k = numberOfBucketsNeeded(nums, midSum); 
            // Needed to increase the max sum limit
            if(k > m){
                maxSumLow = midSum + 1;
            }
            // Decrease the max sum limit
            else if(k <= m){
                maxSumHigh = midSum;
            }
        }
        
        for(long i=maxSumLow; i<=maxSumHigh; i++){
            k = numberOfBucketsNeeded(nums, i);
            if(k <= m)
                return (int)i;
        }
        return -1;
    }
}
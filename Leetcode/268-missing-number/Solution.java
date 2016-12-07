/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find 
the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it 
using only constant extra space complexity?
*/
public class Solution {
    public int missingNumber(int[] nums) {
        int r = 0;
        for(int i=0; i<nums.length; i++){
            r ^= (i+1);
            r ^= nums[i];
        }
        return r;
    }
}

class Solution2 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long sum = (long)(n*(n+1)/2.0);
        long nSum = 0;
        for(int i : nums)
            nSum += i;
        return (int)(sum - nSum);
    }
}
public class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long sum = (long)(n*(n+1)/2.0);
        long nSum = 0;
        for(int i : nums)
            nSum += i;
        return (int)(sum - nSum);
    }
}
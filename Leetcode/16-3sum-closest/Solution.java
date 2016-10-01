/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int curSum;
        int r = 0;
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<n-2; i++){
            int start = i + 1;
            int end = n-1;
            while(start < end){
                curSum = nums[i] + nums[start] + nums[end];                                  
                int diff = Math.abs(target - curSum);
                // Update mindiff if necessary
                if(diff < minDiff){
                    minDiff = diff;
                    r = curSum;
                }
                if(curSum == target)
                    return curSum;
                else if(curSum < target)
                    start++;    
                else
                    end--;
            }
        }
        return r;
    }
}
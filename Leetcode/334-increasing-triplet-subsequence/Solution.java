/*
Given an unsorted array return whether an increasing subsequence of length 3 
exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/

public class Solution {
    // the_smallest_so_far < the_second_smallest_so_far < current 
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3)     
            return false;
        int minValue = Integer.MAX_VALUE;
        int secondMinValue = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            if(minValue >= nums[i])
                minValue = nums[i];
            else if(secondMinValue >= nums[i])
                secondMinValue = nums[i];
            else
                return true;
        }
        return false;
    }
}

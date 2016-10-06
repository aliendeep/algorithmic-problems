/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

public class Solution {
    // Sort
    // O(nlogn) time

    public void swap(int[] nums, int i){
        int t = nums[i];
        nums[i] = nums[i+1];
        nums[i+1] = t;
    }
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        // Swap adjacent pairs
        for(int i=1; i<nums.length-1; i+=2){
            swap(nums, i);
        }
    }
}
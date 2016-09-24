/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while(h - l > 3){
            int mid = (l + h)/2;
            if(nums[mid] < target)
                l = mid;
            else if(nums[mid] >= target)
                h = mid;
        }
        
        for(int i=l; i<=h; i++){
            if(nums[i] >= target)
                return i;
        }
        return nums.length;
        
    }
}
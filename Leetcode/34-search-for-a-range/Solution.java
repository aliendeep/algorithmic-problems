/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    public int startPosition(int[] nums, int target){
        int l = 0, h = nums.length - 1;
        while(h - l > 3){
            int mid = (l + h)/2;
            if(nums[mid] < target)
                l = mid + 1;
            else if(nums[mid] > target)
                h = mid - 1;
            else
                h = mid;
        }
        
        for(int i=l; i<=h; i++)
            if(nums[i] == target)
                return i;
        return -1;
    }
    
    public int endPosition(int start, int[] nums, int target){
        int l = start, h = nums.length - 1;
        while(h - l > 3){
            int mid = (l + h)/2;
            if(nums[mid] < target)
                l = mid + 1;
            else if(nums[mid] > target)
                h = mid - 1;
            else
                l = mid;
        }
        
        for(int i=h; i>=l; i--)
            if(nums[i] == target)
                return i;
        return -1;
    }    
    
    public int[] searchRange(int[] nums, int target) {
        int[] r = new int[2];

        int low = startPosition(nums, target); 
        int high = -1;
        if(low != -1){
            high = endPosition(low, nums, target);
        }
        r[0] = low;
        r[1] = high;
        return r;
    }
}
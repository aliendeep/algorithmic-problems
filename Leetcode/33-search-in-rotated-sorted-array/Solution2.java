/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/
public class Solution {
    // Alternative
    public int search(int[] nums, int target) {
        // Find the index of the smallest number
        int n = nums.length;
        int l = 0, h = n - 1;
        while(l < h){
            int mid = (l + h)/2;
            if(nums[mid] > nums[h])
                l = mid + 1;
            else
                h = mid;
        }
        
        int rotations = l;
        l = 0;
        h = n - 1;
        while(l<=h){
            int mid = (l+h)/2;
            int realMid = (mid + rotations)%n;
            if(nums[realMid] == target)
                return realMid;
            else if(nums[realMid] < target)
                l = mid +1;
            else
                h = mid - 1;
        }
        return -1;
    }
}
/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/
public class Solution {
    public int findMin(int[] v) {
        int low = 0;
        int high = v.length - 1;
        
        while(high - low > 5){
            int mid = low + (high-low)/2;
            if(v[mid] > v[high])
                low = mid + 1;
            else if(v[mid] < v[high])
                high = mid;
            // A[mid] & A[high] same
            else 
                high = high - 1;
        }
        int r = v[low];
        for(int i=low+1; i<=high; i++)
            r = Math.min(r, v[i]);
        return r;
    }
}

public class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        
        while(h - l > 3){
            // This part of the subarray is sorted
            if(nums[l] < nums[h])
                return nums[l];
            int mid = l + (h-l)/2;
            if(nums[l] > nums[mid]){
                h = mid;
            }
            else if(nums[l] < nums[mid]){
                l = mid + 1;
            }
            // nums[l] == nums[mid]
            else
                l++;
        }
        int r = nums[l];
        for(int i=l+1; i<=h; i++)
            r = Math.min(r, nums[i]);
        return r;        
    }
}

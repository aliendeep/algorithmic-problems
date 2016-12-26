/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/
class Solution {
    // Binary Search
    // Time : O(logn)
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        while(high - low > 1){
            // If this portion of the array is not rotated at all
            if(nums[low] < nums[high])
                return nums[low];
                
            int mid = (low+high)/2;
            // left half is sorted
            if(nums[low] < nums[mid])
                low = mid + 1;
            // right half is sorted
            else 
                high = mid;
        }
        int r = nums[low];
        for(int i=low+1; i<=high; i++)
            r = Math.min(r, nums[i]);
        return r;
    }
}


public class Solution {
    // Binary Search
    // Time : O(logn)
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            // if only one element or Array is not rotated at all
            if(low == high || nums[low] < nums[high])
                return nums[low];
            if(high - low == 1)
                return Math.min(nums[low], nums[high]);

            int mid = (low+high)/2;
            // left half is sorted
            if(nums[low] < nums[mid])
                low = mid + 1;
            // right half is sorted
            else 
                high = mid;
        }
        return -1;
    }
}

// Cleaner
public class Solution {
    public int findMin(int[] a) {
        int l = 0, h = a.length - 1;
        while(h - l > 3){
            // Whole segment is sorted
            if(a[l] < a[h]){
                return a[l];
            }
            int mid = (l+h)/2;
            // min exists in the right half
            if(a[l] < a[mid]){
                l = mid;
            }
            // a[l] > mid
            else{
               h = mid; 
            }
        }
        
        int r = a[l];
        for(int i=l+1; i<=h; i++)
            r = Math.min(r, a[i]);
        return r;
    }
}

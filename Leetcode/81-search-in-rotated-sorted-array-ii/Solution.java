/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

public class Solution {
    // Recursive
    public boolean binarySearch(int[] a, int low, int high, int target){
        if(low > high)          
            return false;
        
        int mid = (low + high)/2;

        if(a[mid] == target)    
            return true;
        
        if(high - low <= 2){
            if(a[low] == target)
                return true;
            else if(a[high] == target)
                return true;
            else
                return false;
        }       
        // left side sorted
        if(a[low] < a[mid]){
            // if target falls into this range
            if(target >= a[low] && target < a[mid])
                return binarySearch(a, low, mid-1, target);
            return binarySearch(a, mid+1, high, target);
        }
        // right side sorted
        else if(a[mid] < a[low]){
            // if target falls into this range
            if(target > a[mid] && target <= a[high])
                return binarySearch(a, mid+1, high, target);
            return binarySearch(a, low, mid-1, target);
        }
        // left half is all repeats
        else if(a[low] == a[mid]){
            if(a[high] != a[mid])
                return binarySearch(a, mid+1, high, target);
            // needs to search both halves
            else{
                if(binarySearch(a, low, mid-1, target)) return true;
                return binarySearch(a, mid+1, high, target);
            }
        }
        return false;
    }
    
    public boolean search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }
}
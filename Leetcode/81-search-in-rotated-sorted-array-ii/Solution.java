/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to 
you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.
*/
// cleaner
public class Solution {
    public boolean search(int[] a, int target) {
        int n = a.length;
        int l = 0, h = n-1;
        while(h - l > 3){
            int mid = (l+h)/2;
            if(a[mid] == target)
                return true;
            // left side sorted
            if(a[l] < a[mid]){
                if(a[l] <= target && target < a[mid])
                    h = mid - 1;
                else
                    l = mid + 1;
            }
            // right half sorted
            else if(a[l] > a[mid]){
                if(a[mid] < target && target <= a[h])
                    l = mid + 1;
                else
                    h = mid - 1;
            }
            // a[l] == a[mid]
            else
                l++;
        }
        for(int i=l; i<=h; i++){
            if(a[i] == target)
                return true;
        }
        return false;
    }
}

class Solution2{
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

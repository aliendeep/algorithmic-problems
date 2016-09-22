public class Solution {
    // check which side is sorted
    int binarySearch(int[] a, int low, int high, int target){
        if(low > high)          
            return -1;
        int mid = (high - low)/2 + low;
        if(a[mid] == target)    
            return mid;
        
        if(high - low <= 2){
            if(a[low] == target)        return low;
            else if(a[high] == target)  return high;
            else                        return -1;
        }
        
        // left side sorted
        if(a[low] < a[mid]){
            // if target falls into this range
            if(target >= a[low] && target < a[mid])
                return binarySearch(a, low, mid-1, target);
            else
                return binarySearch(a, mid+1, high, target);
        }
        // right side sorted
        else if(a[mid] < a[high]){
            // if target falls into this range
            if(target > a[mid] && target <= a[high])
                return binarySearch(a, mid+1, high, target);
            else
                return binarySearch(a, low, mid-1, target);
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }
}
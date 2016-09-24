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
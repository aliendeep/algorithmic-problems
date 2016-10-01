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

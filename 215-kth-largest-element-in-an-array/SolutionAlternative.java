public class Solution {
    // Another implementation
    void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    int Partition(int[] nums, int left, int right){
        // select the first element as the pivot
        int pivotId = left;
        left++;
        while(left <= right){
            // Kth largest, so largest elements in the left side
            while(left <= right && nums[left] > nums[pivotId])
                left++;
            while(left <= right && nums[right] <= nums[pivotId])
                right--;
            if(left < right)
                swap(nums, left, right);
        }
        // Swap the pivot elements with right so that it lies between the two paritions
        swap(nums, pivotId, right);
        return right;
    }
    
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int newPivot = Partition(nums, left, right);
            if(newPivot == k-1)
                return nums[newPivot];
            else if(newPivot > k - 1)
                right = newPivot - 1;
            else 
                left = newPivot + 1;
        }
        return -1;
        
    }
}
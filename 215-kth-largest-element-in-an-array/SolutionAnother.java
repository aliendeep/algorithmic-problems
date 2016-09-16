public class Solution {
    // Another implementation
    void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    int Partition(int[] nums, int left, int right, int pivotId){
        int pivot = nums[pivotId];
        // swap the pivot with the rightmost element
        swap(nums, pivotId, right);

        int newPivot = left;
        for(int i=left; i<right; i++){
            if(nums[i] > pivot){
                swap(nums, i, newPivot);
                newPivot++;
            }
        }
        // Swap the pivot elements with right so that the pivot lies between the two paritions
        swap(nums, newPivot, right);
        return newPivot;
    }
    
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random rand = new Random();
        while(left <= right){
            // Generate a random number
            int pivotId = left + rand.nextInt(right - left + 1);
            int newPivot = Partition(nums, left, right, pivotId);
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
public class Solution {
    void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    int Partition(int[] nums, int left, int right, int pivotId, Comparator<Integer> comp){
        int pivot = nums[pivotId];
        int newPivotId = left;
        
        swap(nums, pivotId, right);
        for(int i=left; i<right; i++){
            if(comp.compare(nums[i], pivot) < 0){
                swap(nums, i, newPivotId);
                newPivotId++;
            }
        }
        swap(nums, right, newPivotId);
        return newPivotId;
    }

    // Selection Algorithm
    // Average time complexity: O(n)
    public int findKthLargest(int[] nums, int k) {
        Comparator<Integer> comp = new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                if(a > b)
                    return -1;
                if(a < b)
                    return 1;
                return 0;
            }
        };
        
        int left = 0, right = nums.length - 1;
        Random rand = new Random();
        while(left <= right){
            // Generate a random number between left & right to be the pivot
            int pivotId = left + rand.nextInt(right - left + 1);
            int newPivot = Partition(nums, left, right, pivotId, comp);
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
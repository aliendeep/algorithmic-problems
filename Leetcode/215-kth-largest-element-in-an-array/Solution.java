/*
Find the kth largest element in an unsorted array. Note that it is the kth largest 
element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/
// Quick Select O(n) implementation
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

// Another O(n) implementation
class Solution2 {
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

// O(nlogk) solution
class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0; i<k; ++i){
            minHeap.add(nums[i]);
        }
        int i = k;
        while(i < nums.length){
            int t = minHeap.peek();
            if(t < nums[i]){
                minHeap.poll();
                minHeap.add(nums[i]);
            }
            ++i;
        }
        return minHeap.poll();
    }
}


// O(klogn) solution
class Solution4 {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        for(int i=0; i<n; ++i){
            maxHeap.add(nums[i]);
        }
        int i = k;
        int r = 0;
        while(i-- > 0){
            r = maxHeap.poll();
        }
        return r;
    }
}
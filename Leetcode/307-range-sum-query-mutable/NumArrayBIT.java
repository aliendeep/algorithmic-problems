/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/

// Binary Indexed Tree Solution
class NumArray {
    int[] binaryIndexedTree;
    int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        // Construct Binary Indexed Tree from the original array
        binaryIndexedTree = new int[n+1];
        // 0th node contains the dummy node
        for(int i=1; i<=n; i++){
            updateBinaryIndexedTree(i, nums[i-1]);
        }
    }
    
    public int getNext(int index){
        return index + (index & -index);
    }
    
    public int getParent(int index){
        return index - (index & -index);
    }
    
    // index (1 indexing)
    void updateBinaryIndexedTree(int index, int val){
        // While in range
        while(index < binaryIndexedTree.length){
            binaryIndexedTree[index] += val;
            index = getNext(index);
        }
    }
    
    // Get sum of (0..i) 
    public int getSum(int index){
        index++;
        int sum = 0;
        
        while(index > 0){
            sum += binaryIndexedTree[index];
            index = getParent(index);
        }
        return sum;
    }
    
    void update(int i, int val) {
        int diff = val - nums[i];
        // Update value
        nums[i] = val;
        updateBinaryIndexedTree(i+1, diff);
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
}
// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
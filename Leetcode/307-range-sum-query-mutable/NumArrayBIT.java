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
class BinaryIndexedTree{
  int[] bit;
  int[] nums;

  public BinaryIndexedTree(int[] a){
    int n = a.length;
    this.nums = a;
    bit = new int[n+1];

    // construct the bit
    for(int i=1; i<=n; ++i){
      addDiff(i, nums[i-1]);
    }
  }
  
  public int lsb(int index){
    return index & -index;
  }

  // 1 indexing
  private void addDiff(int index, int diff){
    while(index < bit.length){
      bit[index] += diff;
      index = index + lsb(index);
    }    
  }

  // Get sum of (0..index)
  public int getSum(int index){
    index++;
    int sum = 0;
    while(index > 0){
      sum += bit[index];
      index = index - lsb(index);
    }
    return sum;
  }

  // i: 0 indexing. Set the value of a[i] to val
  public void set(int i, int val){
    int diff = val - nums[i];
    nums[i] = val;
    addDiff(i+1, diff);
  }
}

public class NumArrayBIT {
    BinaryIndexedTree bit;
    public NumArrayBIT(int[] nums) {
        bit = new BinaryIndexedTree(nums);
    }

    void update(int i, int val) {
        bit.set(i, val);
    }

    public int sumRange(int i, int j) {
        return bit.getSum(j) - bit.getSum(i-1);
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
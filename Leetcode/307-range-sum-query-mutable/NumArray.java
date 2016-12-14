/*
Given an integer array nums, find the sum of the elements between indices i 
and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is 
distributed evenly.
*/

class SegmentTree{
  // Number of leaves in the segment tree
  int n;
  // original number of nodes
  int _n;
  int[] node;  
  
  // Construct segment tree from an array
  public SegmentTree(int[] nums){
    _n = nums.length;
    // Find the closest power of 2
    n = 1;
    while(n < _n){
      n *= 2;
    }

    // Number of leaves = n
    // Number of internal nodes = n - 1
    // Total number of nodes = 2n - 1
    // 1 to 2n - 1 used
    // Space Complexity: 4*_n (Think about the case when _n = 33)
    // Number of leaves = 64 (closest power of 2)
    // Number of internal nodes = 63
    // Total number of nodes ~ 128 (Around 4 times of _n)
    node = new int[n*2];

    // Root index: 1
    buildSegmentTree(nums, 0, _n-1, 1);
  }

  // Build segment tree from the array : O(n) time
  // nodeIndex : Root 1
  public void buildSegmentTree(int[] nums, int lo, int hi, int nodeIndex){
    // leaf
    if(lo == hi){
      node[nodeIndex] = nums[lo];
      return;
    }

    int mid = (lo + hi)/2;
    // left
    buildSegmentTree(nums, lo, mid, 2*nodeIndex);
    // right
    buildSegmentTree(nums, mid+1, hi, 2*nodeIndex + 1);

    // Merge (Sum Operation)
    node[nodeIndex] = node[2*nodeIndex] +  node[2*nodeIndex + 1];
  }

  // O(logn)
  public void updateRecursive(int nodeIndex, int lower, int upper, int arrIndex, int val){
    // leaf
    if(lower == upper){
      node[nodeIndex] = val;
      return;
    }
    int mid = (lower + upper)/2;
    // right
    if(arrIndex > mid)
      updateRecursive(2*nodeIndex+1, mid+1, upper, arrIndex, val);
    else if(arrIndex <= mid)
      updateRecursive(2*nodeIndex, lower, mid, arrIndex, val);

    // Merge (Sum Operation)
    node[nodeIndex] = node[2*nodeIndex] +  node[2*nodeIndex + 1];  
  }

  public void update(int i, int val){
    updateRecursive(1, 0, _n-1, i, val);
  }

  public int rangeSumRecursive(int nodeIndex, int lower, int upper, int i, int j){
    // query for array[i..j]
    // Segment completely outside range
    if(lower > j || upper < i){
      return 0;
    }
    // Segment completely inside range
    if(lower >= i && upper <= j){
      return node[nodeIndex];
    }

    // Partial overlap
    int mid = (lower + upper)/2;

    // right 
    if(i > mid){
      return rangeSumRecursive(2*nodeIndex + 1, mid+1, upper, i, j);
    }
    if(j <= mid){
      return rangeSumRecursive(2*nodeIndex, lower, mid, i, j);
    }
    return rangeSumRecursive(2*nodeIndex, lower, mid, i, mid) + rangeSumRecursive(2*nodeIndex + 1, mid+1, upper, mid+1, j);
    /*
    Will work too
    // return rangeSumRecursive(2*nodeIndex, lower, mid, i, j) + rangeSumRecursive(2*nodeIndex + 1, mid+1, upper, i, j);
    */
  }

  public int rangeSum(int i, int j){
    return rangeSumRecursive(1, 0, _n-1, i, j);
  }
}

class NumArray {
    SegmentTree sTree;
    
    public NumArray(int[] nums) {
        if(nums.length == 0)
            return;
        sTree = new SegmentTree(nums);    
    }

    void update(int i, int val) {
        sTree.update(i, val);
    }

    public int sumRange(int i, int j) {
        return sTree.rangeSum(i, j);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);

// Solution 2
// BIT Solution
class BinaryIndexedTree{
  int[] bit;
  // original numbers
  int[] nums;

  public BinaryIndexedTree(int[] a){
    int n = a.length;
    this.nums = a;
    bit = new int[n+1];

    // construct the bit (1 indexing)
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

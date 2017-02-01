/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is 
the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/

// Segment Tree
import java.util.*;

class SegmentTree{
  // Number of leaves in the segment tree
  int n;
  // original number of nodes
  int _n;
  int[] node;  
  
  // Construct segment tree from an array
  public SegmentTree(int _n){
    this._n = _n;
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
  }

  public void addRecursive(int nodeIndex, int lower, int upper, int arrIndex, int diff){
    // leaf
    if(lower == upper){
      node[nodeIndex] += diff;
      return;
    }
    int mid = (lower + upper)/2;
    // right
    if(arrIndex > mid)
      addRecursive(2*nodeIndex+1, mid+1, upper, arrIndex, diff);
    else if(arrIndex <= mid)
      addRecursive(2*nodeIndex, lower, mid, arrIndex, diff);

    // Merge (Sum Operation)
    node[nodeIndex] = node[2*nodeIndex] + node[2*nodeIndex + 1];  
  }

  public void addDiff(int i, int diff){
    addRecursive(1, 0, _n-1, i, diff);
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
  }

  public int rangeSum(int i, int j){
    return rangeSumRecursive(1, 0, _n-1, i, j);
  }
}

public class Solution{
  public List<Integer> countSmaller(int[] nums) {
      int n = nums.length;
      int[] t = nums.clone();
      Arrays.sort(t);

      SegmentTree st = new SegmentTree(n);

      Integer[] result = new Integer[n];  
      for(int i=n-1; i>=0; --i){
        int pos = Arrays.binarySearch(t, nums[i]);
        int sum = st.rangeSum(0, pos-1);
        result[i] = sum;
        // Set the value at pos
        st.addDiff(pos, 1);                
      }
      return Arrays.asList(result);
  }
}

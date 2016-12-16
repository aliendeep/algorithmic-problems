// Segment Tree
import java.util.*;

class SegmentTree{
  // Number of nodes in the segment tree
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
    node[nodeIndex] = node[2*nodeIndex] +  node[2*nodeIndex + 1];  
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
      
      int[] posArray = new int[n];
      for(int i=0; i<n; ++i){
        int pos = Arrays.binarySearch(t, nums[i]);
        st.addDiff(pos, 1);
        posArray[i] = pos;
      }

      List<Integer> result = new ArrayList<>();
      for(int i=0; i<n; ++i){
        result.add(st.rangeSum(0, posArray[i]-1));
        // Decrement the value at pos (Set it to 0)
        st.addDiff(posArray[i], -1);                
      }
      return result;
  }
}

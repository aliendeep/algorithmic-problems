// Range Minimum Query
class SegmentTreeRMQ{
  // Number of nodes in the tree
  int n;

  // original array size (leaf nodes: 0 to _n-1)
  int _n;

  // Root Node Index: 1. Left child 2*NodeIndex. Right child: 2*NodeIndex + 1
  int[] nodes;
  
  public SegmentTreeRMQ(int[] nums){
    _n = nums.length;
    if(_n == 0)
        return;

    // Find the closest power of 2
    n = 1;
    while(n < _n){
      n *= 2;
    }

    nodes = new int[2*n];
    buildSegmentTree(nums, 0, _n-1, 1);
  }

  private void buildSegmentTree(int[] nums, int low, int high, int nodeIndex){
    // leaf
    if(low == high){
      nodes[nodeIndex] = nums[low];
      return;
    }
    int mid = (low+high)/2;
    // left node index: 2*nodeIndex
    buildSegmentTree(nums, low, mid, 2*nodeIndex);
    // right node index: 2*nodeIndex + 1
    buildSegmentTree(nums, mid+1, high,  2*nodeIndex + 1);
    // merge operation (minimum)
    nodes[nodeIndex] = Math.min(nodes[2*nodeIndex], nodes[2*nodeIndex + 1]);
  }

  private int queryRangeMinRecursive(int nodeIndex, int lower, int upper, int i, int j){
    // Find arr min in arr[i..j]

    // if outside the range
    if(lower > j || upper < i)
        return Integer.MAX_VALUE;

    // if complete overlap
    if(lower >= i && upper <= j)
        return nodes[nodeIndex];

    int mid = (lower + upper)/2;
    if(i > mid){
      return queryRangeMinRecursive(2*nodeIndex + 1, mid+1, upper, i, j);
    }
    else if(i <= mid){
      return queryRangeMinRecursive(2*nodeIndex, lower, mid, i, j);      
    }

    return Math.min(queryRangeMinRecursive(2*nodeIndex, lower, mid, i, mid), queryRangeMinRecursive(2*nodeIndex + 1, mid+1, upper, mid+1, j));
  }

  public int rangeMin(int i, int j){
    return queryRangeMinRecursive(1, 0, _n-1, i, j);
  }

  public static void main(String[] args){
    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    SegmentTreeRMQ st = new SegmentTreeRMQ(nums);
    System.out.println(st.rangeMin(0, 7)); 

    int[] a = {10, 20, 30, 40, 11, 22, 33, 44, 15, 5};
    SegmentTreeRMQ st1 = new SegmentTreeRMQ(a);
    System.out.println(st1.rangeMin(0, 5)); 
    System.out.println(st1.rangeMin(1, 2)); 
    System.out.println(st1.rangeMin(8, 9)); 
    System.out.println(st1.rangeMin(0, 9)); 
    System.out.println(st1.rangeMin(4, 6));     
  }
}
class SegmentTree{
    // no of leaves in the segment tree
    int n;
    int _n;
    int[] node;
    
    // Root Node Index : 1
    public SegmentTree(int _n){
        this._n = _n;
        // Find the closest power of 2
        n = 1;
        while(n < _n){
          n *= 2;
        }
        node = new int[n*2];
    }

    public void addDiffRecursive(int nodeIndex, int low, int high, int arrIndex, int diff){
        if(low == high){
            // leaf
            node[nodeIndex] += diff;
            return;
        }
        int mid = (int)(low+high)/2;
        if(arrIndex > mid)
            addDiffRecursive(2*nodeIndex+1, mid+1, high, arrIndex, diff);
        if(arrIndex <= mid)
            addDiffRecursive(2*nodeIndex, low, mid, arrIndex, diff);
    
        node[nodeIndex] = node[2*nodeIndex] + node[2*nodeIndex+1];
    }
    // Add difference
    public void addDiff(int i, int diff){
        addDiffRecursive(1, 0, _n-1, i, diff);
    }
    
    public int rangeSumRecursive(int nodeIndex, int low, int high, int i, int j){
        // return range sum of a[i...j]
        // outside range
        if(high < i || low > j)
            return 0;
            
        // completely within range
        if(low >= i && high <= j)
            return node[nodeIndex];
            
        int mid = (int)(low+high)/2;
        if(i > mid){
            // right 
            return rangeSumRecursive(nodeIndex*2 + 1, mid+1, high, i, j);
        }        
        if(j <= mid){
            // left
            return rangeSumRecursive(nodeIndex*2, low, mid, i, j);
        }
        return rangeSumRecursive(nodeIndex*2, low, mid, i, mid) + 
                            rangeSumRecursive(nodeIndex*2+1, mid+1, high, mid+1, j);
    }
    public int rangeSum(int i, int j){
        return rangeSumRecursive(1, 0, _n-1, i, j);
    }
}

public class Solution{
  // Find first occurrence of target
  public int lowerBound(long[] nums, long target){
      int index = Arrays.binarySearch(nums, target);
      if(index < 0){
        index =  -index - 1;
      }
      return index;
  }

  public int countRangeSum(int[] nums, int lowerRange, int upperRange) {
    int n = nums.length;
    if(n == 0)
        return 0;
    // cum sum
    long[] sums = new long[n];
    sums[0] = nums[0];
    for(int i=1;i<n; ++i){
      sums[i] = sums[i-1] + nums[i];
    }

    Set<Long> set = new HashSet<>();
    for(int i=0;i<n; ++i){
      set.add(sums[i]);
    }    

    long[] t = new long[set.size()];
    int in = 0;
    Iterator it = set.iterator();
    while(it.hasNext()){
      t[in++] = (long)it.next();
    }
    Arrays.sort(t);

    // Build the segment tree
    SegmentTree st = new SegmentTree(n);
    for(int i=0; i<n; ++i){
      int pos = Arrays.binarySearch(t, sums[i]);
      st.addDiff(pos, 1);
    }

    long cnt = 0;
    long lower = lowerRange;
    long upper = upperRange;

    for(int i=0; i<n; ++i){
      // Find the range of lower and upper in the sorted cum sum array
      int lowerIndex = lowerBound(t, lower);
      int upperIndex = lowerBound(t, upper+1) - 1;

      int ssum = st.rangeSum(lowerIndex, upperIndex);

      cnt += ssum;
      // update range
      lower += nums[i];
      upper += nums[i];
      int pos = Arrays.binarySearch(t, sums[i]);
      st.addDiff(pos, -1);
    }
    return (int)cnt;
  }
}
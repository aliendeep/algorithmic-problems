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

  public static void main(String[] args){
    int[] nums = {1, 3, 5};
    BinaryIndexedTree bit = new BinaryIndexedTree(nums);
    // 0 .. i
    System.out.println(bit.getSum(2));
  }
}
import java.util.*;

class Main{
  public static boolean isFeasible(long[] nums, long k){
    long cur = k;
    long rem;
    long curPos = 0;
    for(long a : nums){
      rem = a - curPos;
      if(rem > cur)   return false;
      if(rem == cur)    cur--;
      curPos = a;
    }
    return true;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int x=1; x<=t; ++x){
      int n = in.nextInt();
      long[] nums = new long[n];
      long max = Integer.MIN_VALUE;
      // Sorted
      for(int i=0; i<n; ++i){
        nums[i] = in.nextLong();
        max = Math.max(max, nums[i]);
      }

      long l = nums[0];
      long h = max;
      while(h - l > 5){
        long mid = (h - l)/2 + l;
        // increase strength
        if(!isFeasible(nums, mid)){
          l = mid + 1;
        }
        else{
          h = mid;
        }
      }
      long resultStrength = -1;
      for(long i=l; i<=h; ++i){
        if(isFeasible(nums, i)){
          resultStrength = i;
          break;
        }
      }
      System.out.println("Case " + x + ": " + resultStrength);
    }
  }  
}

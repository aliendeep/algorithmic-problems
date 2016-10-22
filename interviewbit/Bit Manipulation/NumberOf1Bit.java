// Count number of 1 bits

public class NumberOf1Bit {
  public int numSetBits(long n) {
      int cnt = 0;
      while(n > 0){
          if((n & 1) != 0)
              cnt++;
         n = n >> 1;
      }
      return cnt;
  }
}

public class Solution2 {
  public int numSetBits(long n) {
      int cnt = 0;
      while(n > 0){
          // Clear the last set bit
          n &= (n-1);
          cnt++;
      }
      return cnt;
  }
}

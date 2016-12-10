/*
Write a function that takes an unsigned integer and returns the number of ’1' bits 
it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
so the function should return 3.
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        while(n != 0){
            // Clear the lowest set bit
            n = n & (n-1);
            cnt++;
        }
        return cnt;
    }
}

class Solution2 {
  public int hammingWeight(int n) {
      int cnt = 0;
      for(int i=31; i>=0; i--){
          if((n & (1<<i)) != 0)
              cnt++;
      }
      return cnt;
  }
}
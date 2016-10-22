public class Solution {
  public long reverse(long a) {
      long rev = 0;
      for(int i=0; i<=31; i++){
          rev <<= 1;
          if((a & (1<<i)) != 0){
                rev |= 1;                     
          }
      }
      return rev;
  }
}

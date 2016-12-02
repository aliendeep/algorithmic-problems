// Greedy
/*
How many minimum numbers from fibonacci series are required such that sum of numbers should be equal to a given Number N?
Note : repetition of number is allowed.

Example:

N = 4
Fibonacci numbers : 1 1 2 3 5 .... so on
here 2 + 2 = 4 
so minimum numbers will be 2 
*/
public class Solution {
    public final static int N = 50;
    long[] f;
    public Solution(){
        f = new long[N+1];
        f[0] = 1;
        f[1] = 1;
        for(int i=2; i<=N; ++i){
            f[i] = f[i-1] + f[i-2]; 
        }
    }
  // find largest number smaller than a
    public int getMaxIndex(int a, int end){
      int maxIndex = 0;
      for(int i=end; i>=0; i--){
          if(f[i] <= a){
              maxIndex = i;
              break;
          }
      }
      return maxIndex;
    }
  public int fibsum(int a) {
      int cnt = 0;
      int i;
      int end = N;
      while(a > 0){        
          i = getMaxIndex(a, end);
          end = i;
          a = a - (int)f[i];
          cnt++;
      }
      return cnt;
  }
}

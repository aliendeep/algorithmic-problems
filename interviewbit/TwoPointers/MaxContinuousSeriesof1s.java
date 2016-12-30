/* 
You are given with an array of 1s and 0s. And you are given with an integer M, 
which signifies number of flips allowed.
Find the position of zeros which when flipped will produce maximum continuous 
series of 1s.

For this problem, return the indices of maximum continuous series of 1s in order.

Example:

Input : 
Array = {1 1 0 1 1 0 0 1 1 1 } 
M = 1

Output : 
[0, 1, 2, 3, 4] 

If there are multiple possible solutions, return the sequence which has the 
minimum start index.
*/
public class Solution {
    // number of flips allowed
  public ArrayList<Integer> maxone(ArrayList<Integer> a, int m) {
      int n = a.size();
      int l = 0, r = 0;
      int maxLen = -1;
      int rL = -1, rR = -1;
      int cnt0 = 0;
      // Count number of 0s
      while(r < n){
          // Extend range
          if(cnt0 <= m){
              if(a.get(r) == 0)
                  cnt0++;
              r++;
          }
          // Reduce range
          if(cnt0 > m){
              if(a.get(l) == 0)
                  cnt0--;
              l++;
          }
          if(r - l > maxLen){
              maxLen = r-l;
              rL = l;
              rR = r;
          }
      }
        ArrayList<Integer> result = new ArrayList<Integer>();      
      for(int i=rL; i<rR; ++i)
          result.add(i);
      return result;
  }
}

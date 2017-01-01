/*
For Given Number N find if its COLORFUL number or not

Return 0/1

COLORFUL number:

A number can be broken into different contiguous sub-subsequence parts. 
Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245. 
And this number is a COLORFUL number, since product of every digit of a 
contiguous subsequence is different
Example:

N = 23
2 3 23
2 -> 2
3 -> 3
23 -> 6
this number is a COLORFUL number since product of every digit of a sub-sequence are different. 

Output : 1
*/
public class Solution {
  public int colorful(int a) {
      // Single digitis
      if(a < 10)  return 1;
      String s = Integer.toString(a);
      int n = s.length();
      Set<Long> products = new HashSet<>();
      // For all length of substring
      for(int l=1; l<=n; ++l){
          for(int i=0; i<n-l+1; ++i){
              String p = s.substring(i, i+l); 
              long product = 1;
              for(int j=0; j<p.length(); ++j){
                  product *=(p.charAt(j) - '0');
              }
              if(!products.add(product))
                  return 0;
          }
      }
      return 1;
  }
}

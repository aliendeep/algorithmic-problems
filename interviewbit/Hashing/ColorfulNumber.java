/*
For Given Number N find if its COLORFUL number or not

Return 0/1

COLORFUL number:

A number can be broken into different contiguous sub-subsequence parts. 
Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245. 
And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different
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
    public int computeProduct(String sub){
        int num = Integer.parseInt(sub);
      if(num < 10) return num;
    int product = 1;
    while(num > 0){
      product *= num % 10;
      num = num / 10;
    }
    return product;        
    }
    
  public int colorful(int a) {
      if(a < 10)  
          return 1;
      String num = String.valueOf(a);
      int n = num.length();
      Set<Integer> set = new HashSet<>();

        for(int i=1; i<=n; ++i){
          for(int j=0; i+j<=n; ++j){
              String sub = num.substring(j, i+j); 
              int product = computeProduct(sub);
              if(set.contains(product))
                  return 0;
              set.add(product);
          }
      }
      return 1;
  }
}

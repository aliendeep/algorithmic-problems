// https://www.interviewbit.com/problems/colorful-number/
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

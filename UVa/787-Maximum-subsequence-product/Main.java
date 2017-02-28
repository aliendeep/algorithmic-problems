import java.util.*;
import java.math.BigInteger;

class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int x = 0;
    while(in.hasNext()){
      long[] nums = new long[150];
      int n = 0;
      while((x = in.nextInt()) != -999999){
        nums[n++] = x;
      }
      BigInteger pos = BigInteger.valueOf(nums[0]);
      BigInteger neg = BigInteger.valueOf(nums[0]);
      BigInteger maxProd = pos;
      for(int i=1; i<n; ++i){
        BigInteger num = BigInteger.valueOf(nums[i]);
        BigInteger px = num.multiply(pos);
        BigInteger nx = num.multiply(neg);
        BigInteger posNext = px.max(nx).max(num);
        BigInteger negNext = px.min(nx).min(num);
        maxProd = maxProd.max(posNext);
        pos = posNext;
        neg = negNext;
     }
    System.out.println(maxProd);
    }
  }  
}

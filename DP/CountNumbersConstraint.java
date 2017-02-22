/*
Find how many numbers of length n are there such that each number is at least 4 
smaller/greater than the number before and after it.  Eg: if n = 5, such numbers 
are 39518, 15951, etc.
*/
import java.util.*;

#TOOD
class CountNumbersConstraint{
  // d = cur digits
  public void getCount(int n, int d){
    if(n == 0)  return 0;
    if(n == 1)  return 1;

  }

  public int count(int n){
    // n digits
    // 0 - 9
    // d - current digits
    int[][] dp = new int[n][10];
  }
  public static void main(String[] args){
    CountNumbersConstraint ob = new CountNumbersConstraint();
    System.out.println(ob.count(5));
  }  
}
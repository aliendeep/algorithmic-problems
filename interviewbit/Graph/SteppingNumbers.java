/*
Given N and M find all stepping numbers in range N to M

The stepping number:

A number is called as a stepping number if the adjacent digits have a difference of 1.
e.g 123 is stepping number, but 358 is not a stepping number

Example:

N = 10, M = 20
all stepping numbers are 10 , 12 
Return the numbers in sorted order.
*/

public class Solution {
    public boolean isStep(int n){
        if(n <= 9)      return true;
        int prev = n%10;
        n = n/10;
        int cur;
        while(n > 0){
            cur = n % 10;
            if(Math.abs(cur - prev) != 1)
                return false;
            prev = cur;
            n = n/10;
        }
        return true;
    }
  public ArrayList<Integer> stepnum(int a, int b) {
      ArrayList<Integer> r = new ArrayList<>();
      for(int i=a; i<=b; ++i){
          if(isStep(i))
              r.add(i);
      }
      return r;
  }
}

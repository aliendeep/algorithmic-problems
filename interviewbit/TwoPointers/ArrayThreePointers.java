/*
You are given 3 arrays A, B and C. All 3 of the arrays are sorted.

Find i, j, k such that :
max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))

**abs(x) is absolute value of x and is implemented in the following manner : **

      if (x < 0) return -x;
      else return x;
Example :

Input : 
        A : [1, 4, 10]
        B : [2, 15, 20]
        C : [10, 12]

Output : 5 
         With 10 from A, 15 from B and 10 from C. 
*/

// O(m + n + k)
public class Solution {
  public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
      int i = 0, j = 0, k = 0;
      int r = Integer.MAX_VALUE;
      int min, max;
      while(i < a.size() && j < b.size() && k < c.size()){
          int x = a.get(i);
          int y = b.get(j);
          int z = c.get(k);
          min = Math.min(Math.min(x, y), z);
          max = Math.max(Math.max(x, y), z);
          
            r = Math.min(r, max - min);
            if(x == min){
                i++;
            }
            else if(y == min){
                j++;
            }
            else if(z == min)
                k++;
      } 
      return r;
  }
}


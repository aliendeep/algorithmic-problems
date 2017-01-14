/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of 
its factors.
Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/

/* Testcase:
Sample Input:
12
32
23848713

Sample Output:
[[2,6],[3,4],[2,2,3]]
[[2,16],[4,8],[2,2,8],[2,4,4],[2,2,2,4],[2,2,2,2,2]]
[[3,7949571],[7,3406959],[9,2649857],[3,3,2649857],[21,1135653],[3,7,1135653],
[63,378551],[3,21,378551],[7,9,378551],[3,3,7,378551]]
*/

import java.util.*;

public class Solution {
  public void bktk(int n, int start, List<Integer> cur, List<List<Integer>> r){
      if(n == 1){
          if(cur.size() > 1){
            System.out.println(cur);
            r.add(new ArrayList<Integer>(cur));
          }
          return;
      }

      System.out.println("n "+n);
      for(int i=start; i*i<=n; i++){
          System.out.println(i);
          if(n % i == 0) {
            cur.add(i);
            bktk(n/i, i, cur, r);
            cur.remove(cur.size()-1);
          }
      }
      System.out.println("Adding "+n);
      cur.add(n);
      bktk(1, -1, cur, r);
      cur.remove(cur.size()-1);
  }
  
  public List<List<Integer>> getFactors(int n) {
      List<List<Integer>> r = new ArrayList<>();
      List<Integer> cur = new ArrayList<>();
      bktk(n, 2, cur, r);
      return r;
  }
  public static void main(String[] args){
    Solution ob = new Solution();
    System.out.println(ob.getFactors(12));
  }        
}

class Solution2 {
  public void bktk(int n, int start, List<Integer> cur, List<List<Integer>> r){
      if(n == 1 && cur.size() > 1){
          r.add(new ArrayList<Integer>(cur));
          return;
      }

      for(int i=start; i<=n; i++){
          if(n % i == 0) {
              cur.add(i);
              bktk(n/i, i, cur, r);
              cur.remove(cur.size()-1);
          }
      }
  }
  
  public List<List<Integer>> getFactors(int n) {
      List<List<Integer>> r = new ArrayList<>();
      List<Integer> cur = new ArrayList<>();
      bktk(n, 2, cur, r);
      return r;
  }
}

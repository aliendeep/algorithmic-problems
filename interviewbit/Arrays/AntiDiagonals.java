/*
Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.

Example:

    
Input:  

1 2 3
4 5 6
7 8 9

Return the following :

[ 
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]


Input : 
1 2
3 4

Return the following  : 

[
  [1],
  [2, 3],
  [4]
]
*/
public class Solution {
  public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
      int n = a.size();
      ArrayList<ArrayList<Integer>> result = new ArrayList<>();
      for(int i=0; i<2*n-1; ++i){
          result.add(new ArrayList<>());
      }
      
      for(int i=0; i<n; ++i){
          for(int j=0; j<n; ++j){
              int index = i+j;
              result.get(index).add(a.get(i).get(j));
          }
      }
      return result;
  }
}
/*
Given a list of array, return a list of arrays, each array is a combination of 
one element in each given array.
Let me give you an example to help you understand the question Suppose the input 
is [[1, 2, 3], [4], [5, 6]], the output should be [[1, 4, 5], [1, 4, 6], 
[2, 4, 5], [2, 4, 6], [3, 4, 5], [3, 4, 6]].
*/
import java.util.*;

public class PermutationsOfArrayofArrays{
  public void print(List<List<Integer>> a){
    for(List<Integer> t : a){
      for(int x : t)
        System.out.print(x + " ");
      System.out.println();
    }
  }

  List<List<Integer>> result;
  List<List<Integer>> nums;
  int n;
  boolean[][] taken;

  public void bktk(int prev, List<Integer> cur){
    if(cur.size() == n){
      result.add(new ArrayList<>(cur));
      return;
    }

    for(int i=prev; i<n; ++i){
      List<Integer> a = nums.get(i);
      for(int j=0; j<a.size(); ++j){
        if(taken[i][j])
          continue;

        taken[i][j] = true;
        cur.add(a.get(j));

        bktk(i+1, cur);

        taken[i][j] = false;
        cur.remove(cur.size()-1);
      }
    }
  }

  public ArrayList<Integer> get(int[] a){
    ArrayList<Integer> arr = new ArrayList<>();
    for(int t : a)
      arr.add(t);  
    return arr;  
  } 

  public void perm(){
    result = new ArrayList<>();
    nums = new ArrayList<>();
    int[] a = {1, 2, 3};
    int[] b = {4};
    int[] c = {5, 6};
    nums.add(get(a));
    nums.add(get(b));
    nums.add(get(c));
    n = 3;
    taken = new boolean[n][n];

    bktk(0, new ArrayList<>());
    print(result);
  }

  public static void main(String[] args){
    PermutationsOfArrayofArrays ob = new PermutationsOfArrayofArrays();
    ob.perm();
  }
}

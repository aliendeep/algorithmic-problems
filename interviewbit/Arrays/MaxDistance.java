import java.util.*;
// https://www.interviewbit.com/problems/max-distance/

// O(n) Solution
class Solution {
  // DO NOT MODIFY THE LIST
  public int maximumGap(final List<Integer> a) {
      int n = a.size();
      int[] min = new int[n];
      int[] max = new int[n];

      min[0] = a.get(0);
      for(int i=1; i<n; ++i){
          min[i] = Math.min(min[i-1], a.get(i));
      }

      max[n-1] = a.get(n-1);
      for(int i=n-2; i>=0; --i){
          max[i] = Math.max(max[i+1], a.get(i)); 
      }
      
      int result = -1;
      int i = 0, j = 0;
      while(i < n && j < n){
          if(min[i] <= max[j]){
              result = Math.max(result, j - i);
              j++;
          }
          // min[i] > max[j]
          else{
              i++;
          }
      }
      return result;
  }
}

public class MaxDistance {
  // DO NOT MODIFY THE LIST
  class Pair{
      int val;
      int index;
      public Pair(int v, int i){
          val = v;
          index = i;
      }
  }
  public int maximumGap(final List<Integer> a) {
      int n = a.size();
      Pair[] arr = new Pair[n];
      for(int i=0; i<n; ++i)
          arr[i] = new Pair(a.get(i), i);
         
      Arrays.sort(arr, new Comparator<Pair>(){
          @Override
          public int compare(Pair a, Pair b){
              return Integer.compare(a.val, b. val);
          }
      });

      int maxIndex = arr[n-1].index;
      int result = 0;
      for(int i=n-2; i>=0; --i){
        result = Math.max(result, maxIndex - arr[i].index);
        maxIndex = Math.max(maxIndex, arr[i].index);
      }
      return result;      
  }
    public static void main(String[] args){
      int[] t = {3, 5, 4, 2};
      List<Integer> a = new ArrayList<>();
      for(int n : t)
          a.add(n);
      MaxDistance ob = new MaxDistance();
      System.out.println(ob.maximumGap(a));
    }
}

// left to right
public class Solution2 {
  // DO NOT MODIFY THE LIST
  class Pair{
      int val;
      int index;
      public Pair(int v, int i){
          val = v;
          index = i;
      }
  }
  public int maximumGap(final List<Integer> a) {
      int n = a.size();
      Pair[] arr = new Pair[n];
      for(int i=0; i<n; ++i)
          arr[i] = new Pair(a.get(i), i);
         
      Arrays.sort(arr, new Comparator<Pair>(){
          @Override
          public int compare(Pair a, Pair b){
              return Integer.compare(a.val, b. val);
          }
      });
        int minIndex = arr[0].index;
        int result = 0;
        for(int i=1; i<n; ++i){
            result = Math.max(result, arr[i].index - minIndex);
            minIndex = Math.min(minIndex, arr[i].index);
        }
        return result;      
  }
}



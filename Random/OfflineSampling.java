import java.util.*;
// EPI
/*
Input: Set of Distinct Elements
Output: Returns a subset of size k. All subsets should be equally likely
Idea: Build a random subset of size k-1 and add one element from the rest of the subset randomly to create random subset of size k

Follow up: If k is greater than n/2, then we can minimize the number of calls to the random number generator by removing n-k elements from the set.
*/
// Time Complexity: O(k)
public class OfflineSampling{
  public static void swap(int[] a, int i, int j){
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
  // Modify the original array itself
  public static void offlineRandomSampling(int[] a, int k){
    int n = a.length;
    Random rand = new Random();
    for(int i=0; i<k; ++i){
      // Generate a random number between [i, a.length - 1]
      swap(a, i, i + rand.nextInt(n  - i));
    }
  }

  // Solution 2: Return result (Simialr to online sampling approach)
  public static void offlineRandomSampling2(int[] a, int k){
    int[] subset = new int[k];
    for(int i=0; i<k; ++k)
      subset[i] = a[i];

    int n = a.length;
    Random rand = new Random();

    for(int i=k; i<n; ++i){
      int randIndex = rand.nextInt(i+1);
      if(randIndex < k){
        subset[randIndex] = a[i];
      }
    }
  }


  public static void print(int[] a, int k){
    for(int i=0; i<k; ++i){
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args){
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    offlineRandomSampling(a, 4);
    print(a, 4);
  }
}
import java.util.*;
// EPI
/*
Return a random k-sized subset of {0, 1, ..., n - 1}.
Approach 1: (Similar to Uniform Random Number Generation)

Approach 2:
Offline Sampling
*/
public class RandomSubset{
  // Approach 1: O(n) time
  public static int[] randomSubsetGeneration(int n, int k){
    int[] a = new int[n];
    for(int i=0; i<n; ++i){
      a[i] = i;
    }
    OfflineSampling of = new OfflineSampling();
    of.offlineRandomSampling(a, k);

    // Create the result array
    int[] r = new int[k];
    for(int i=0; i<k; ++i)
      r[i] = a[i];    
    return r;
  }

  // Approach 2: O(k) time
  public static int[] randomSubsetGenerationK(int n, int k){
    Map<Integer, Integer> changed = new HashMap<>();
    Random rand = new Random();

    for(int i=0; i<n; ++i){
      // Generate a random number between (i, n-1)
      int randIndex = i + rand.nextInt(n - i);
      Integer p1 = changed.get(randIndex);
      Integer p2 = changed.get(i);
      if(p1 == null && p2 == null){
        changed.put(randIndex, i);
        changed.put(i, randIndex);
      }
      else if(p1 == null && p2 != null){
        changed.put(randIndex, p2);
        changed.put(i, randIndex);        
      }
      else if(p1 != null && p2 == null){
        changed.put(i, p1);        
        changed.put(randIndex, i);        
      }
      else{
        // update the change
        changed.put(randIndex, p1);
        changed.put(i, p2);        
      }
    }
    int[] r = new int[k];
    for(int i=0; i<k; ++i)
      r[i] = map.get(i);    
    return r;
  }  
}
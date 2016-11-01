import java.util.*;
// Reservoir Sampling
/*
Input: Generate a uniform random subset of size k from a stream

Idea:
Suppose, we have a random subset of size k out of n packets. When we read the (n+1)th packet, probability of having this packet in the subset is k/(n+1).
If we choose a random number from the subset and replace it with (n+1)th packet, the resulting subset will be a random subset of size k out of (n+1) packet.
*/

// Time complexity: O(1)
// Spece complexity: O(k)
public class OnlineSampling{
  public static List<Integer> onlineSampling(Iterator<Integer> iterator, int k){
    Random rand = new Random();

    List<Integer> runningSample = new ArrayList<>();
    for(int i=0; i<k && iterator.hasNext(); ++i){
      runningSample.add(iterator.next());
    }

    // running sample now contains k packets
    int numSeenSoFar = k;
    while(iterator.hasNext()){
      Integer x = iterator.next();
      // Generate a random number between [0...numSeenSoFar]. If this number is between [0..k-1], then we replace that element by x
      int id = rand.nextInt(numSeenSoFar);
      if(numSeenSoFar < k){
        runningSample.set(id, x);
      }
    }
    return runningSample;
  }
}
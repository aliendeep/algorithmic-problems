import java.util.*;
// EPI
/*
Generate uniformly random permutation of [0, 1, ..., n-1]

Approach 1:
(Similar to Uniform Random Number Generation Approach) 
Iterative pick numbers betweem 0 to n-1 (inclusive). If a number repeats, discard. Use a hashset to keep track of the currently selected n
number.
Time complexity of this approach: O(nlogn)
Additional storage for the hashset needed.

Approach 2:
Create an array of n numbers and use the offline sampling to find the subset.
Time Complexity: O(n)
*/
public class RandomPermutation{
  public static int[] computeRandomPermutation(int n){
    int[] a = new int[n];
    for(int i=0; i<n; ++i){
      a[i] = i;
    }
    OfflineSampling of = new OfflineSampling();
    of.offlineRandomSampling(a, n);
    return a;
  }
}

/* Variant:
/*
Shuffle: Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, 
each of the 52! permutations of the deck has to be equally likely. Assume that you are given a random number generator which is perfect.
*/

// Similar to offlineSampling
class ShuffleCard{
  public static void swap(int[] a, int i, int j){
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static void shuffleCard(int[] cards){
    Random rand = new Random();
    int n = cards.length;
    for(int i=0; i<n; ++i){
      // Generate a random number between [i, ... n]
      int randIndex = i + rand.nextInt(n - i);
      swap(cards, i, randIndex);
    }
  } 
  
  // Alterntive  
  public static void shuffleCard2(int[] cards){
    Random rand = new Random();
    int n = cards.length;
    for(int i=0; i<n; ++i){
      // Generate a random number between [0..i]
      int randIndex = rand.nextInt(i+1);
      swap(cards, i, randIndex);
    }
  }  
}
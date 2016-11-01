import java.util.*;
// EPI
/*
Given a random number generator that produces 0 and 1 with equal probability, generate a random number between a and b inclusive.
*/
// Bit 
// Time complexity: O(log(b-a+1))
public class GenerateUniformRandomNumber{
  // a is the lower bound and b is the upper bound
  public static int uniformRandom(int a, int b){
    Random rand = new Random();

    int numberOfOutcomes = b - a + 1;
    int random;
    while(true){
      random = 0;
      // Generate a random number between [0, b-a+1)
      for(int i=0; (1<<i) < numberOfOutcomes; ++i){
        // rand.getNextInt(2) generates a 0 and 1 with equal probability
        random = (random << 1) | (rand.nextInt(2));
      }
      if(random < numberOfOutcomes)
        break;
    }
    return a + random;
  }

  // Alternative
  public static double log2(int x){
    return Math.log10(x)/Math.log10(2);
  }
  // Number of bits needed to represent n
  public static int uniformRandom2(int a, int b){
    Random rand = new Random();

    int numberOfOutcomes = b - a + 1;
    int random;
    // Number of bits needed to represent the highest number b - a (in the spectrum [0..b-a])
    int numberOfBitsNeeded = (int)Math.floor(log2(b-a))+1;

    while(true){
      random = 0;
      // Generate a random number between [0, b-a+1)
      for(int i=0; i<numberOfBitsNeeded; ++i){
        // rand.getNextInt(2) generates a 0 and 1 with equal probability
        random = (random << 1) | (rand.nextInt(2));
      }
      if(random < numberOfOutcomes)
        break;
    }
    return a + random;
  }

  public static void main(String[] args){
    int t = uniformRandom2(0, 100);
    System.out.println(t);
  }
}
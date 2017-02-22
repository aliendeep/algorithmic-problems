import java.util.*;
// EPI
/*
Input: 
Numbers: n0, n1, n2 .. (N numbers)
Associated Probability: p0, p1, p2 ..
Random Number Generator that generates number between 0 and 1 uniformly
Output: Return one random number according to the probability distribution
*/
public class GenerateNonUniformRandomNumber{
  public static int binarySearch(double[] cum, double uniform){
    int low = 0, high = cum.length - 1;
    while(high - low > 3){
      int mid = (low + high)/2;
      if(cum[mid] < uniform)
        low = mid + 1;
      else
        high = mid;
    }

    for(int i=high; i>=low; --i){
      if(cum[i] <= uniform)
        return i;
    }
    return 0;
  }

  public static int nonuniformRandom(int[] values, double[] prob){
    int n = values.length;
    // CumSum of probabilities
    // p0, p0+p1, p0+p1+p2, ...
    double[] cumProb = new double[n];
    cumProb[0] = prob[0];
    for(int i=1; i<n; ++i){
      cumProb[i] = cumProb[i-1] + prob[i]; 
    }

    Random rand = new Random();
    // random number in [0.0,1.0).
    double uniform = rand.nextDouble();

    // Find the index where this probability lies in the cumSum array
    // Binary Search
    int index = binarySearch(cumProb, uniform);
    return values[index];
  }
}

/* Variant:
Given an array of integers, return a random element from it on the basis of its value.
*/
class RandomGenerator{
  int[] cumSum;
  int sum;
  int[] nums;

  public RandomGenerator(int a[]) {
    this.nums = a;
    int n = a.length;
    cumSum = new int[n];
    cumSum[0] = a[0];
    for(int i=1; i<n; ++i){
      cumSum[i] = cumSum[i-1] + a[i];
    }
    sum = cumSum[n-1];
  }

  // Returns a random value between [0,1)
  private double getRandom() {
    Random r = new Random();
    return r.nextDouble();
  }  

  public int getRandomInt() {
    int rand = (int)Math.floor(getRandom()*sum);

    // Binary Search
    int n = nums.length;
    int low = 0, high = n-1;
    while(high - low > 3){
      int mid = (low+high)/2;
      if(nums[mid] < rand)
        low = mid + 1;
      else
        high = mid;
    } 

    for(int i=high; i>=low; --i){
      if(nums[i] <= rand)
          return nums[i];
    }
    return -1;
  }
}

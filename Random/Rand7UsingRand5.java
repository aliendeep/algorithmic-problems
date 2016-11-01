import java.util.*;

// http://www.csinterview.com/design-rand7-using-rand5/

/*
General:
Approach 1. If we have a rand() that generates number between 0 to k-1, we can easily generate a rand k^d() – Range Expanding 
To generate rand k^d(), we need to pick d number of digits by using randk() for each digit.
rand k^d() = k^(d-1)*rand() + ... + k^0*rand()

Approach 2: Reservoir Sampling
We can throw away a range of samples (rejection) without changing the uniform nature of the random number generator. 

Mod:
If we can make use of a larger percentage of the samples, if we use mod technique

We are given a random integer generator rand5() that generates 0, 1, 2, 3, or 4 with equal probabilities. 
Can you design a rand7() that generates an integer value 0 through 6 with uniform distribution?
*/
public class Rand7UsingRand5{
  public static int rand5(){
    Random rand = new Random();
    return rand.nextInt(5);
  }

  // Solution 1: Use base5 to represent the number
  public static int rand7(){
    int result = 0;
    while(true){
      result = 5*rand5() + rand5();
      if(result <= 20)
        break;
    }
    return result%7;
  }
  
  // Solution 2 Use base2 to represent the number
  // Generate rand2() using rand5()
  public static int rand2(){
    int result = 0;
    while(true){
      result = rand5();
      if(result <= 4)
        break;
    }
    return result % 2;
  }  

  // 3 bits needed
  public static int rand7Solution2(){
    int result = 0;
    while(true){
      result = (1<<2)*rand2() + (1<<1)*rand2() + (1<<0)*rand2();
      if(result != 7)
        break;
    }
    return result;
  }

}

// http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
// What is the least number of egg-droppings that is guaranteed to work in all cases?
import java.util.*;

public class EggDroppingPuzzle {
  public int eggDropCount(int eggs, int floors, int[][] dp){
    // base case
    if(eggs < 1)        return Integer.MAX_VALUE/2;
    if(eggs == 1)       return floors;
    if(floors == 0)     return 0;

    if(dp[eggs][floors] != -1)
        return dp[eggs][floors];

    int minValue = Integer.MAX_VALUE;
    for(int f=1; f<=floors; f++){
      int val = 1 + Math.max(eggDropCount(eggs, floors - f, dp), eggDropCount(eggs - 1, f - 1, dp));
      minValue = Math.min(minValue, val);
    }
    dp[eggs][floors] = minValue;
    return minValue;
  }

  public int eggDropCountMain(int eggs, int floors){
    int[][] dp = new int[eggs+1][floors+1];
    for(int[] t : dp)
        Arrays.fill(t, -1);
    return eggDropCount(eggs, floors, dp);
  }

  public static void main(String args[]){
      EggDroppingPuzzle ob = new EggDroppingPuzzle();
      int r = ob.eggDropCountMain(2, 10);
      System.out.println(r);
  }  
}
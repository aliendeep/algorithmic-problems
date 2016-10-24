// http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
/*
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
*/

// Time complexity: O(n^2)
import java.util.*;

public class RodCut{
  // Let dp[n] : best possible price for a rod of length n

  public int getProfit(int[] price, int len, int[] dp){
    if(len <= 0)
        return 0;

    if(dp[len] != -1)
      return dp[len];

    int maxValue = Integer.MIN_VALUE;
    for(int i=0; i<len; i++){
      // cut at ith point or not (i is 0 indexing)
      maxValue = Math.max(maxValue, getProfit(price, len - i - 1, dp) + price[i]);
    }

    dp[len] = maxValue;
    return dp[len];
  }

  public static void main(String args[]){
    int price[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
    RodCut s = new RodCut();

    int len = price.length;

    int[] dp = new int[len+1];
    Arrays.fill(dp, -1);

    System.out.println(s.getProfit(price, len, dp));
  }  
}
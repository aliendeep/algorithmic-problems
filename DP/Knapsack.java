import java.util.*;

class KnapsackMemoization{
  public int getValue(int[] values, int weights[], int capacity, int i, int[][] dp){
    if(values.length == 0 || capacity == 0 || i < 0)
        return 0;
    if(dp[i][capacity] != -1)
        return dp[i][capacity];

    int r = Integer.MIN_VALUE;
    // Don't take the item
    int a = getValue(values, weights, capacity, i-1, dp);

    int b = Integer.MIN_VALUE;
    if(weights[i] <= capacity){
        // take the item
        b = getValue(values, weights, capacity - weights[i], i-1, dp) + values[i];
    }

    dp[i][capacity] = Math.max(a, b);
    return dp[i][capacity];
  }

  public int getMaxValue(int[] values, int weights[], int capacity){
    int n = values.length;
    if(n == 0 || capacity == 0)       return 0;

    int[][] dp = new int[n][capacity+1];
    for(int[] t : dp)
        Arrays.fill(t, -1);

    return getValue(values, weights, capacity, n-1, dp);
  }
}

// 0-1 Knapsack
// Time Complexity: O(n*Capacity)
public class Knapsack{
  public static int getMaxValue(int[] values, int weights[], int capacity){
    int n = values.length;
    if(n == 0 || capacity == 0)       return 0;

    int[][] dp = new int[n+1][capacity+1];
    for(int i=0; i<=n; i++){
      for(int w=0; w<=capacity; w++){
        if(i == 0 || w == 0){
          dp[i][w] = 0;
        }
        else{
          if(weights[i-1] <= w){
            // include the element or not
            dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i-1]] + values[i-1]);
          }
          else{
            dp[i][w] = dp[i-1][w];            
          }
        }
      }
    }
    return dp[n][capacity];
  }

  public static void main(String args[]){
    int val[] = {60, 100, 120};
    int wt[] = {10, 20, 30};
    System.out.println(getMaxValue(val, wt, 50));

    KnapsackMemoization ks = new KnapsackMemoization();
    System.out.println(ks.getMaxValue(val, wt, 50));

  }
}
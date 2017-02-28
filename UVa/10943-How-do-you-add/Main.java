import java.util.*;

class Main{
  public static long countWays(int n, int k, long[][] dp){
    if(k < 0 || n < 0)    return 0;
    if(n == 0 && k == 0)  return 1;
    if(dp[n][k] != -1)
      return dp[n][k];

    dp[n][k] = 0;
    for(int i=0; i<=n; ++i)
      dp[n][k] = (dp[n][k] + countWays(n-i, k-1, dp)) % 1000000;
    return dp[n][k];
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    while(true) {
      int n = in.nextInt();
      int k = in.nextInt();
      if(n == 0 && k == 0)
        break;
      long[][] dp = new long[n+1][k+1];
      for(long[] t : dp)
        Arrays.fill(t, -1);
      System.out.println(countWays(n, k, dp));
    }
  }  
}

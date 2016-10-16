// Count the number of possible Binary Search Trees with n keys 
// Catalan Number
// https://leetcode.com/problems/unique-binary-search-trees/

public class CountBinarySearchTreeHelper {
  public int numTreesHelper(int n, int[] dp){
    if(n == 0)      return 1;
    if(dp[n] != 0)
        return dp[n];
    int sum = 0;
    for(int i=1; i<=n; i++){
      int left = numTreesHelper(i-1, dp);
      int right = numTreesHelper(n-i, dp);
      sum += left * right;
    }
    dp[n] = sum;
    return dp[n];
  }
  
  public int numTrees(int n){
    int[] dp = new int[n+1];
    return numTreesHelper(n, dp);
  }
}

class CountBinarySearchTree{
  public int numTreesRecursive(int n){
    if(n == 0)
        return 1;
    int sum = 0;
    for(int i=1; i<=n; i++){
      int left = numTreesRecursive(i-1);
      int right = numTreesRecursive(n-i);
      sum += left * right;
    }
    return sum;
  }
    // http://www.geeksforgeeks.org/program-nth-catalan-number/
    // Catalan Number
    public int numTrees(int n) {
        if(n <= 1)  
            return 1;
        int[] cnt = new int[n+1];
        cnt[0] = 1;
        cnt[1] = 1;
        for(int i=2;i<=n; i++){
            for(int j=0; j<i; j++)
                cnt[i] += cnt[j] * cnt[i-j-1];
        }
        return cnt[n];
    }
}
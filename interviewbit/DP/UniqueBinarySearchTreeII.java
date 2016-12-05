/*
Given A, how many structurally unique BST’s (binary search trees) that store values 1...A?

Example :

Given A = 3, there are a total of 5 unique BST’s.


   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/
public class Solution {
    public int numBSTRootedAt(int n, int max, int[] dp){
        if(n < 0)       return 0;
        if(n <= 1)      return 1;
        if(dp[n] != -1)
            return dp[n];
        
        int sum = 0;
        for(int i=1; i<=n; i++){
            sum += numTrees(i-1)*numTrees(n-i);
        }
        dp[n] = sum;
        return dp[n];
    }    
  public int numTrees(int n) {
      int[] dp = new int[n + 1];
      Arrays.fill(dp, -1);
      return numBSTRootedAt(n, n, dp);
  }
}

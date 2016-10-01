public class Solution {
    public int numTreeMemoization(int n, int[] dp){
        if(dp[n] != 0)
            return dp[n];
        
        int sum = 0;
        for(int i=1; i<=n; i++){
            // left subtree
            if(dp[i-1] == 0)
                dp[i-1] = numTrees(i-1);
            // right subtree
            if(dp[n-i] == 0)
                dp[n-i] = numTrees(n-i);
            sum += dp[i-1]*dp[n-i];
        }
        dp[n] = sum;
        return dp[n];
    }

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        // empty or 1 node tree
        dp[0] = 1;
        dp[1] = 1;
        return numTreeMemoization(n, dp);
    }
}
public class Solution {
    /*
             1, 2, 3, 4, 5, 6,  7,   8,  9, 10, 11
    Result:  1, 1, 2, 4, 6, 9, 12,  18, 27, 36, 54
    */
    public int integerBreak(int n) {
        if(n == 0)
            return 0;
        int[] dp = new int[Math.max(n+1, 7)];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        
        for(int i=7; i<=n; i++){
            dp[i] = dp[i-3]*3;
        }
        return dp[n];
    }
}
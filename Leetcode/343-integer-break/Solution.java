/*
Given a positive integer n, break it into the sum of at least two positive integers and 
maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover 
the regularities.
*/

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

// Alternative
class Solution2 {
   public int integerBreak(int n) {
        if(n == 0)
            return 0;
        int[] dp = new int[n+1];
        
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            for(int j=1; j<i; j++){
                dp[i] = Math.max(dp[i], Math.max(dp[j], j)*Math.max(dp[i-j], i-j));
            }
        }
        return dp[n];
    }
}

class Solution3 {
    // O(n^2) Solution
    // Memoization
    public int getProduct(int n, int[] dp){
        if(n == 1)  return 1;
        if(n == 2)  return 1;

        if(dp[n] != -1)
            return dp[n];
        
        int maxProd = 0;
        for(int i=2; i<n; ++i){
            // use both numbers
            maxProd = Math.max(maxProd, i*(n-i));
            maxProd = Math.max(maxProd, getProduct(i, dp) * getProduct(n-i, dp));
            maxProd = Math.max(maxProd, getProduct(i, dp) * (n-i));
            maxProd = Math.max(maxProd, i * getProduct(n-i, dp));
        }        
        dp[n] = maxProd;
        return maxProd;
    }    
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return getProduct(n, dp);
    }
}

class Solution4 {
    // https://discuss.leetcode.com/topic/42991/o-log-n-time-solution-with-explanation    
    // O(logn) Solution
    public int integerBreak(int n) {
       if(n == 2)           return 1;
       if(n == 3)           return 2;
       if(n % 3 == 0)       return (int)Math.pow(3, n/3);
       if(n % 3 == 1)       return 2*2*(int)Math.pow(3, (n-4)/3);
       return 2*(int)Math.pow(3, (n-2)/3);
    }
}
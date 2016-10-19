int Solution::WaysStairs(int A) {
    // Do not write main() function.
    // Do not read input, instead use the arguments to the function.
    // Do not print the output, instead return values as specified
    // Still have a doubt. Checkout www.interviewbit.com/pages/sample_codes/ for more details
    vector<int> dp(A+1, 0);
    dp[0] = 1;
    dp[1] = 1;
    for(int i=1; i<=A; i++){
        dp[i] = dp[i-1];
        if(i-2 >= 0)
            dp[i] += dp[i-2];
    }
    
    return dp[A];
}

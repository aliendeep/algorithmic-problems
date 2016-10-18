/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
*/

public class CountNumberWithUniqueDigits {
    // Number of numbers containing unique digits
    // f(1) = 9 = 9
    // f(2) = 81 = f(1)*9 = 9*9
    // f(3) = 576 = f(2)*8 = 9*9*8
    // f(4) = 4536 = f(3)*7 = 9*9*8*7;
    // f(5) = 27216 = f(4)*6 = 9*9*8*7*6;
    //  0 ≤ x < 10^n
    int[] dp;
    public final static int MAX_DIGITS = 11;
    public Solution(){
        dp = new int[MAX_DIGITS];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        dp[1] = 9;
        int start = 9;
        for(int i=2; i<MAX_DIGITS; i++){
          dp[i] *= dp[i-1]*start;            
          start--;
      }
    }
    public int countNumbersWithUniqueDigits(int n) {
        int r = 0;
        for(int i=0; i<=n; i++)
            r += dp[i];
        return r;
    }
}
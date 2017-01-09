/*
A message containing letters from A-Z is being encoded to numbers using the 
following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways 
to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/
public class Solution {
    // dp[i] : Number of ways to decode a string of size 1
    public int numDecodings(String s) {
        int n = s.length();
        if(n == 0)
            return 0;

        int[] dp = new int[n+1];
        // Number of ways to decode an empty string = 1
        dp[0] = 1;
        // Number of ways to decode a string of size 1
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i=2; i<=n; i++){
            int first = s.charAt(i-1) - '0';
            int second = (s.charAt(i-2) - '0')*10 + (s.charAt(i-1) - '0');
            if(first >= 1 && first <= 9)
                dp[i] = dp[i-1];
            if(second >= 10 && second <= 26)
                dp[i] += dp[i-2];
        }
        return dp[n];
    }
}

// Memoization
class Solution2 {
    int getCount(String s, int n, int[] dp){
        // empty string
        if(n == 0)  return 1;
        
        if(dp[n] != -1)
            return dp[n];
        
        int last = s.charAt(n-1) - '0';
        dp[n] = 0;
        if(last >= 1 && last <= 9)
            dp[n] += getCount(s, n-1, dp);
        
        if(n >= 2){
            last += (s.charAt(n-2) - '0')*10;
            if(last >= 10 && last <= 26)
                dp[n] += getCount(s, n-2, dp);
        }        
        return dp[n];
    }

    public int numDecodings(String s) {
        int n = s.length();
        if(n == 0)  return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return getCount(s, n, dp);
    }
}

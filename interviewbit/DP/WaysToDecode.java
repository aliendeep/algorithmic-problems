/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Example :

Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/*
Lets first look at the bruteforce solution. 
It only makes sense to look at 1 digit or 2 digit pairs ( as 3 digit sequence will be greater than 26 ).

So, when looking at the start of the string, we can either form a one digit code, and then look at the ways of forming the rest of the string of length L - 1, or we can form 2 digit code if its valid and add up the ways of decoding rest of the string of length L - 2.
This obviously is exponential.
*/

public class WaysToDecode {
    // dp[i] : Number of ways to decode a string of size 1
  public int numDecodings(String s) {
      int n = s.length();
      if(n == 0)
          return 0;
      int[] dp = new int[n+1];
        if(n  == 1 && s.charAt(0) == '0')
          return 0;
      dp[0] = 1;
      // if the last character of the is '0', 0 should be a part of another code. 
      dp[1] = (s.charAt(0) == '0') ? 0 : 1;
      
      for(int i=2; i<=n; ++i){
          int first = (s.charAt(i-1) - '0');
          int second = (s.charAt(i-2) - '0')*10 + first;
          if(first >= 1 && first <= 9)
              dp[i] = dp[i-1];
          // 10 to 19 or 20 to 26
          if(second >= 10 && second <= 26) {
              dp[i] += dp[i-2];
          }
      }
      return dp[n];
  }
}

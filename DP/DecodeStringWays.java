public class DecodeStringWays {
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
                dp[i] += dp[i-1];
            if(second >= 10 && second <= 26)
                dp[i] += dp[i-2];
        }
        return dp[n];
    }
}
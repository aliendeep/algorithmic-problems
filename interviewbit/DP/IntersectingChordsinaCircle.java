/*
Given a number N, return number of ways you can draw N chords in a circle with 
2*N points such that no 2 chords intersect.
Two ways are different if there exists a chord which is present in one way and 
not in other.

For example,

N=2
If points are numbered 1 to 4 in clockwise direction, then different ways to 
draw chords are:
{(1-2), (3-4)} and {(1-4), (2-3)}

So, we return 2.
Notes:

1 ≤ N ≤ 1000
Return answer modulo 10^9+7.

https://www.interviewbit.com/problems/intersecting-chords-in-a-circle/
Solution Approach: (2*N points)
Think in terms of DP.
Can we relate answer for N with smaller answers.

If we draw a chord between any two points, can you observe current set of points 
getting broken into two smaller sets S_1 and S_2? Can a chord be drawn between 
two points where each point belong to different set?

If we draw a chord from a point in S_1 to a point in S_2, it will surely 
intersect the chord we’ve just drawn.

So, we can arrive at a recurrence that Ways(n) = sum[i = 0 to n-1] { Ways(i)*Ways(n-i-1) }.
Here we iterate over i, assuming that size of one of the sets is i and size of 
other set automatically is (n-i-1) since we’ve already used a pair of points and 
i pair of points in one set.
*/

public class Solution {
                                 //123456789  
    public static final int MOD = 1000000007;
    // Catalan Number
    public int chordCnt(int A) {
        long[] dp = new long[A+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=A; ++i){
            for(int j=0; j<i; ++j){
                dp[i] = (dp[i] + dp[j]*dp[i-j-1] % MOD) % MOD;
            }
        }
        return (int)dp[A] % MOD;
    }
}

// Memoization
class Solution2 {
                                 //123456789  
    public static final int MOD = 1000000007;
    public long getCnt(int n, long[] dp){
        // no coord
        if(n == 0)
            return 1;
            
        // n=1 => two points, one chord possible
        if(n == 1)
            return 1;
        if(dp[n] != -1)
            return dp[n];
        
        long ways = 0;
        for(int i=0; i<n; ++i){
            ways = (ways + (getCnt(i, dp)*getCnt(n-i-1, dp)) % MOD) % MOD;
        }
        dp[n] = ways;
        return dp[n];
    }   
    
    // Catalan Number
    public int chordCnt(int A) {
        long[] dp = new long[A+1];
        Arrays.fill(dp, -1);
        return (int)getCnt(A, dp);
    }
}

// Points
class Solution3 {
                                 //123456789  
    public static final int MOD = 1000000007;
    public long getCnt2(int n, long[] dp){
        // no coord
        if(n == 0)
            return 1;

        if(n%2 == 1) return 0;
            
        // two points, one chord possible
        if(n == 2)
            return 1;
        if(dp[n] != -1)
            return dp[n];
        
        long ways = 0;
        for(int i=0; i<n; i+= 2){
            ways = (ways + (getCnt2(i, dp)*getCnt2(n-i-2, dp)) % MOD) % MOD;
        }
        dp[n] = ways;
        return dp[n];
    }   
    
    
    // Catalan Number
    public int chordCnt(int A) {
        long[] dp = new long[2*A+1];
        Arrays.fill(dp, -1);
        return (int)getCnt2(2*A, dp);
    }
}

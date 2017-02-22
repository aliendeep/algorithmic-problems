/*
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an 
array that is constructed by these N numbers successfully if one of the following 
is true for the ith position (1 ≤ i ≤ N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
Note:
N is a positive integer and will not exceed 15.
*/
// Bitmask DP
public class Solution {
    int N;
    // Number of 1's in the mask
    // Mask - which numbers have been used so far
    int count(int k, int mask, int[] dp){
        // mask = 0
        if(k == 0)              return 1;    
        if(dp[mask] != -1)      return dp[mask];
        dp[mask] = 0;
        for(int i=0; i<N; ++i){
            if((mask & (1<<i)) == 0) 
                continue;
            if((i+1) % k == 0 || k % (i+1) == 0)
                dp[mask] += count(k-1, mask ^ (1<<i), dp);
        }
        return dp[mask];
    }
    
    public int countArrangement(int N) {
        this.N = N;
        int[] dp = new int[1<<N];
        Arrays.fill(dp, -1);
        return count(N, (1<<N) - 1, dp);
    }
}

// Bitmask DP - Solution 2
public class Solution {
    int N;
    // Mask - which numbers have been used so far
    int count(int mask, int[] dp){
        if(mask == 0)              return 1;    
        if(dp[mask] != -1)      return dp[mask];
        int k = 0;
        // Count number of 1's
        for(int i=0; i<N; ++i){
            if((mask & (1<<i)) == 0) 
                continue;
            k++;
        }
        
        dp[mask] = 0;
        for(int i=0; i<N; ++i){
            if((mask & (1<<i)) == 0) 
                continue;
            if((i+1) % k == 0 || k % (i+1) == 0)
                dp[mask] += count(mask ^ (1<<i), dp);
        }
        return dp[mask];
    }
    
    public int countArrangement(int N) {
        this.N = N;
        int[] dp = new int[1<<N];
        Arrays.fill(dp, -1);
        return count((1<<N) - 1, dp);
    }
}

// Backtracking
public class Solution {
    int cnt;
    int N;

    public void bktk(int lev, boolean[] taken){
        if(lev == N+1){
            cnt++;
            return;
        }
        for(int i=1; i<=N; ++i){
            if(taken[i])                        continue;
            if(i % lev != 0 && lev % i != 0)    continue;
            taken[i] = true;
            bktk(lev+1, taken);
            taken[i] = false;
        }
    }
    
    public int countArrangement(int N) {
        cnt = 0;
        this.N = N;
        boolean[] taken = new boolean[N+1];
        bktk(1, taken);
        return cnt;
    }
}

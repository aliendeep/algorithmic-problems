/*
We define f(X, Y) as number of different corresponding bits in binary representation of X and Y. For example, f(2, 7) = 2, since binary representation of 2 and 7 are 010 and 111, respectively. The first and the third bit differ, so f(2, 7) = 2.

You are given an array of N positive integers, A1, A2 ,…, AN. Find sum of f(Ai, Aj) for all pairs (i, j) such that 1 ≤ i, j ≤ N. Return the answer modulo 109+7.

For example,

A=[1, 3, 5]

We return

f(1, 1) + f(1, 3) + f(1, 5) + 
f(3, 1) + f(3, 3) + f(3, 5) +
f(5, 1) + f(5, 3) + f(5, 5) =

0 + 1 + 1 +
1 + 0 + 2 +
1 + 2 + 0 = 8
*/

public class DifferentBitsSumPairwise {
    // Logic: Solve this for only 1 bit
    // Time: O(32*N)
    public final static long MOD = 1000000007;
    public int cntBits(ArrayList<Integer> A) {
        int n = A.size();
        long r = 0;
        for(int i=31; i>=0; --i){
            // For ith bit, count number of 1s in all index
            long cnt = 0;
            for(int num : A){
                if((num & (1<<i)) != 0)
                    cnt++;
            }
            // For all numbers, number of set bit (1) in ith position = cnt
            // For all numbers, number of not set bit (0) in ith position = n - cnt
            // Count of differences for ith bit = cnt*(n - cnt)
            // Each difference contributes twice
            r += cnt*(n-cnt)*2;
            r = r % MOD;
        }
        return (int)r;
    }
}

/*
Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
*/

public class Solution {
    // n = 999 Prefix 1: 1 + 10 (10 to 19) + 100 (100 to 199) and so on
    long countNumStartWithPrefix(int n, long prefix){
        long cnt = 0;
        int power = 1;
        while(prefix <= n){
            cnt += Math.min(n - prefix + 1, power);
            power *= 10;
            prefix *= 10;
        }
        return cnt;
    }

    public long findKthNumber(int n, long skipBlock, long prefix) {
        if(prefix > 0){
            if(skipBlock == 0)
                return prefix;
            skipBlock--;
        }
        
        int start = (prefix == 0) ? 1 : 0;
        for(int i=start; i<=9; ++i){
            long cnt = countNumStartWithPrefix(n, prefix*10 + i);
            if(cnt > skipBlock)
                return findKthNumber(n, skipBlock, prefix*10 + i);
            // Skip this part
            skipBlock -= cnt;
        }
        return -1;        
    }
    
    public int findKthNumber(int n, int k) {
        return (int)findKthNumber(n, k-1, 0);
    }
}
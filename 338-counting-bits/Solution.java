/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/

public class Solution {
    // Memoization
    public static int[] dp;
    
    public int getCount(int n) {
        if(n == 0){
            dp[0] = 0;
            return 0;
        }          
        if(dp[n] != -1)     return dp[n];
            
        int lowestSetBit = n & ~(n-1);
        dp[n] =  getCount(n - lowestSetBit) + 1;
        return dp[n];
    }
    
    public int[] countBits(int num) {
        dp = new int[num+1];
        Arrays.fill(dp, -1);
        
        for (int n = 0; n <= num; ++n) {
            getCount(n);
        }
        return dp;
    }
}
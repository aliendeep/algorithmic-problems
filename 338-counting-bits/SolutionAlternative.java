/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/
class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        if(num == 0)
            return dp;
        dp[1] = 1;
        for(int n=2; n<=num; n++){
            // Find the lowest set bit (and the value)
            int lowestSetBitVal = n & ~(n-1);
            dp[n] = dp[n - lowestSetBitVal] + 1; 
        }
        return dp;
    }
}
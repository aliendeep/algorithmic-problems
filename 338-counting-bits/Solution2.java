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
    public final static int NDIGIT = 100000;
    int[] dp = new int[NDIGIT];
    public Solution(){
        dp[0] = 0;
        dp[1] = 1;
        compute(dp, 2, NDIGIT);
    }
    
    public void compute(int[] dp, int start, int end){
        for(int n=start; n<end; n++){
            // n is even
            if(n % 2 == 0){
                // n is power of 2
                if((n & (n-1)) == 0)
                    dp[n] = 1;
                else{
                    // Find the lowest set bit (and the value)
                    int lowestSetBitVal = n & ~(n-1);
                    dp[n] = dp[lowestSetBitVal] + dp[n - lowestSetBitVal]; 
                }
            }
            // n is odd
            else{
                dp[n] = dp[n-1] + 1; 
            }
        }
    }    
    
    public int[] countBits(int num) {
        int[] cnt = new int[num+1];
        if(num < NDIGIT){
            System.arraycopy(dp, 0, cnt, 0, num+1);
        }
        else{        
            System.arraycopy(dp, 0, cnt, 0, NDIGIT);
            compute(cnt, NDIGIT, num+1);
        }
        return cnt;
    }
}
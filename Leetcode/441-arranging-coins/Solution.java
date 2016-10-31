/*
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
*/
public class Solution {
    public int arrangeCoins(int n) {
        long low = 0;
        long high = 100000;
        // Find the largest index in nsum that is smaller than n
        while(high - low > 5){
            long mid = (low + high)/2;
            long cumSum = (mid*(mid+1))/2;
            if(cumSum > n){
                high = mid - 1;
            }
            else{
                low = mid;
            }
        }   
        
        for(long i=high; i>=low; i--){
            long cumSum = (i*(i+1))/2;
            if(cumSum <= n)
                return (int)i;
        }
        return -1;
    }
}
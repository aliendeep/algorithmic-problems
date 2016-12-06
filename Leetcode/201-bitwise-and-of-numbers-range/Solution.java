/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of 
all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Idea: bitwise AND of the range is keeping the common bits of m and n from lsb to 
msb until the first bit that are different.
*/
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        // Shift m and n to the right until they are equal. 
        int nthBit = 0;
        while(m != n){
            m >>>= 1;
            n >>>= 1;
            nthBit++;
        }
        return m << nthBit;
    }
}
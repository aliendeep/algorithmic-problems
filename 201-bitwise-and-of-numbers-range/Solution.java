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
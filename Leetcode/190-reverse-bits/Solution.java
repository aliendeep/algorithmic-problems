public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        int j = 0;
        for(int i=31; i>=0; i--){
            if((n & (1<<i)) != 0)
                rev |= (1<<j);
            j++;
        }
        return rev;
    }
}
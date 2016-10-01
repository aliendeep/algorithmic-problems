public class Solution {
    public int trailingZeroes(int n) {
        // 10 = 5*2, number of multiples of 2 > number of multiples of 5
        // Number of multiples of 5 between 1 and n = n/5, n/25, n/125 and so on = n/5^1, n/5^2, n/5^3
        int cnt = 0;
        // use long because i*=5 may exceed the range of int
        for(long i=5; n/i > 0; i*=5){
            cnt += n/i;
        }
        return cnt;
    }
}
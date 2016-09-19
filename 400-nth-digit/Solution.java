public class Solution {
    // Simulation
    // 1*9, 2*90, 3*900
    public int findNthDigit(int n) {
        // digitLen = 1, 2, 3, ...
        int digitLen = 1;
        // cnt: 9, 90, 900, ..
        // long: to avoid overflow
        long cnt = 9;
        // RangeStart: 1, 100, 1000, ..
        int rangeStart = 1;
        
        while(n > digitLen*cnt){
            n -= digitLen*cnt;
            cnt *= 10;
            rangeStart *= 10;
            digitLen++;
        }
        
        rangeStart += (n-1)/digitLen;
        return Integer.toString(rangeStart).charAt((n-1)%digitLen) - '0';
    }
}
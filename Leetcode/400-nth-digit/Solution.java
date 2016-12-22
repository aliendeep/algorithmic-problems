public class Solution {
    // Simulation
    // 1 length digit - 9 (1 to 9)
    // 2 lengths digit - 2*90 = 90 (10 .. 99)
    // 3 lengths digit - 3*90*10 = 900 (100 .. 1000)
    // 4 lengths digit - 4*900*10 
    public int findNthDigit(int n) {
        // length of the digit
        int len = 1;
        // long: to avoid overflow
        long cnt = 9;
        
        int number = 1;
        while(n > len*cnt){
            n -= len*cnt;
            cnt *= 10;
            number *= 10;
            len++;
        }
        
        number += (n - 1)/len;
        String num = Integer.toString(number);
        return num.charAt((n - 1) % len) - '0';
    }
}
/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 1)    
            return dividend;
        if(dividend == Integer.MIN_VALUE && divisor == -1)    
            return  Integer.MAX_VALUE;
        boolean negSign = (dividend < 0) ^ (divisor < 0);
        // make both positive
        long x = Math.abs((long)dividend);
        long y = Math.abs((long)divisor);
        
        if(x < y)
            return 0;
        // find largest 2^power * y that is greater than x
        int power = 0;
        long yPow = y;
        while(x > yPow){
            // multiply by 2
            yPow <<= 1;
            power++;
        }
        
        int r = 0;
        while(x >= y){
            while(yPow > x){
                yPow >>=1;
                power--;
            }
            r += (1<<power);
            x -= yPow;
        }
        return negSign == true ? -r : r;
    }
}

class Solution2 {
    public int divide(int dividend, int divisor) {
        if(divisor == 1)    
            return dividend;
        if(dividend == Integer.MIN_VALUE && divisor == -1)    
            return  Integer.MAX_VALUE;
        boolean negSign = (dividend < 0) ^ (divisor < 0);
        // make both positive
        long x = Math.abs((long)dividend);
        long y = Math.abs((long)divisor);
        
        if(x < y)
            return 0;
        
        int power = 32;
        long yPower = y << power;
        int result = 0;
        while(x >= y){
            while(yPower > x){
                // divide by two
                yPower >>= 1;
                power--;
            }
            result += 1<<power;
            x -= yPower;
        }
        return negSign == true ? -result : result;
    }
}
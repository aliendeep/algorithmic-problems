public class Solution {
    public double myPow(double x, int power) {
        long n = (long)power;
        // Handle negative number
        if(n < 0){
            n = -n;
            x = 1.0/x;
        }
        double r = 1;
        while(n > 0){
            // odd
            if((n & 1) != 0)
                r *= x;    
            x *= x;
            n >>>= 1;
        }
        return r;
    }
}
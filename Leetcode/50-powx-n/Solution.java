/*
Implement pow(x, n).
*/

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

public class Solution {
    // recursive
    public double pow(double x, long n) {
        if(n == 0)
            return 1;
        double t = (double)pow(x, n/2);
        if((n & 1) == 1)
            return x*t*t;
        return t*t;
    }

    public double myPow(double x, int n) {
        // Handle negative number
        if(n < 0){
            n = -n;
            x = 1.0/x;
        } 
        
        return pow(x, (long)n);
    }
}

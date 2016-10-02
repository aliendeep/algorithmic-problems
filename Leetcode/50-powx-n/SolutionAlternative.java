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
public class Solution {
    /*
        n = 2^x
    =>  log n = x log 2
    =>      x = log n / log 2
    */    
    public boolean isPowerOfTwo(int n) {
        if(n == 0)
            return false;
        double x = Math.round((Math.log10(n) / Math.log10(2)));  
        return (Math.pow(2, x) == n);        
    }
}
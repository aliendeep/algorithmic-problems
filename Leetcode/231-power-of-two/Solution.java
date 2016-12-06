// Given an integer, write a function to determine if it is a power of two.

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

class Solution2 {
    // Recursive
    public boolean isPowerOfTwo(int n) {
        if(n == 0)  return false;
        if(n == 1)  return true;
        return (n%2 == 0) && isPowerOfTwo(n/2);
    }
}

class Solution3 {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0)      return false;
        return (n & (n-1)) == 0;
    }
}
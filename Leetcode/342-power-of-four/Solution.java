/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
*/

public class Solution {
    public boolean isPowerOfFour(int n) {
        if(n == 0)
            return false;
        double x = Math.round((Math.log10(n) / Math.log10(4)));  
        return (Math.pow(4, x) == n);          
    }
}

class Solution2 {
    public boolean isPowerOfFour(int n) {
        if(n == 0)  return false;
        if(n == 1)  return true;
        return (n%4 == 0) && isPowerOfFour(n/4);        
    }
}

class Solution3 {
    /*
        if n is power of 4
        - n should be > 0
        - n & (n-1) clears the lowest set bit. There should be only one 1 bit if n is power of 4. 
          So, n & (n-1) should be 0 after clearing the last bit
        - The 1 bit should be in odd position
    */
    public boolean isPowerOfFour(int n) {
        return (n > 0 && (n & (n-1)) == 0 && (n & 0x55555555) != 0);
    }
}

// Base 4 number (Interesting)
class Solution4 {
    public boolean isPowerOfFour(int num) {
        return Integer.toString(num, 4).matches("10*");
    }
}
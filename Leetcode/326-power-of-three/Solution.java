/*
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
*/

public class Solution {
    /*
        n = 3^x
    =>  log n = x log 3
    =>      x = log n / log 3
    */
    public boolean isPowerOfThree(int n){
        if(n == 0)
            return false;
        double x = Math.round((Math.log10(n) / Math.log10(3)));  
        return (Math.pow(3, x) == n);
    }
}

class Solution2{
    // Recursive
    public boolean isPowerOfThree(int n) {
        if(n == 0)
            return false;
        if(n == 1)
            return true;
        return n%3 == 0 && isPowerOfThree(n/3);
    }
}

// Interesting Idea
class Solution3 {
    // https://discuss.leetcode.com/topic/33595/ternary-number-solution
    // Alternative: Base 3 number
    // Power of 3 will be something like 10,100,1000, etc, analogous to binary numbers that are powers of 2.
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("10*");
    }
}
/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 6, 8 are ugly while 14 is not ugly since it includes another 
prime factor 7.

Note that 1 is typically treated as an ugly number.
*/
public class Solution {
    public boolean isUgly(int num) {
        if(num == 0)
            return false;
        if(num == 1)
            return true;
        if(num%2 == 0)
            return isUgly(num/2);
        if(num%3 == 0)
            return isUgly(num/3);
        if(num%5 == 0)
            return isUgly(num/5);
        return false;
    }
}

// Iterative
class Solution2 {
    public boolean isUgly(int n) {
        if(n == 0)
            return false;
            
        if(n == 1)
            return true;

        while(n%2 == 0)
            n = n/2;

        while(n%3 == 0)
            n = n/3;
            
        while(n%5 == 0)
            n = n/5;
        
        return (n==1);
    }
}
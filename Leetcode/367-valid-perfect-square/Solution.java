/*
Given a positive integer num, write a function which returns True if num is a 
perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
*/

public class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        int l = 0;
        int h = num/2;
        while(h - l > 3){
            double mid = (h-l)/2+l;
            if(mid == (num*1.0)/mid)
                return true;
            else if(mid < (num*1.0)/mid){
                l = (int)mid+1;
            }
            else{
                h = (int)mid-1;
            }
        }
        
        for(int i=l; i<=h; i++){
            if(i == (num*1.0)/i)
                return true;
        }
        return false;
    }
}

class Solution2 {
    // Alternative
    // Square number follows the series: 1 + 3 + 5 + 7 + ..
    public boolean isPerfectSquare(int num) {
        for(int i=1; num > 0; i+=2){
            num -= i;
        }
        return num == 0;
    }
}

class Solution3 {
    // Alternative: Newton method to calculate the sqr of the num
    // https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
    public boolean isPerfectSquare(int num) {
        long x = num/2 + 1;
        while(x*x > num){
            // right shift: divide by 2
            x = (x + num/x) >> 1;
        }
        return x*x == num;
    }
}

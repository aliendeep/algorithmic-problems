/*
Determine whether an integer is a palindrome. Do this without extra space.
*/

// If n has d digits; then 10^d −1  ≤ n < 10^d 
public class Solution {
    public boolean isPalindrome(int x) {
        if(x == 0)
            return true;
        if(x < 0)
            return false;
        // Find the number of digits needed to represent the number
        int nDigits = (int)(Math.floor(Math.log10(x)) + 1);          
        int div  = (int)Math.pow(10, nDigits-1);
        for(int i=0; i<nDigits/2; i++){
            if(x/div != x%10)
                return false;
            x = x % div;
            x = x/10;
            div /= 100;
        }
        return true;
    }
}
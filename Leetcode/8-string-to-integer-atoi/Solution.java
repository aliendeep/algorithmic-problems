/*
Implement atoi to convert a string to an integer.
*/
public class Solution {
    public int myAtoi(String str) {
        if(str.length() == 0)
            return 0;
        
        // Remove all spaces infront of the number
        int i = 0;
        while(str.charAt(i) == ' ')
            i++;
            
        // Handle if it contains sign of the number 
        // Also, check if the given number is a negative number
        int signFlag = 1;
        if(i < str.length() && (str.charAt(i)  == '+' || str.charAt(i)  == '-')){
            if(str.charAt(i) == '-') 
                signFlag = -1;
            i++;
        }

        double n = 0;
        while(i<str.length()){
            if(Character.isDigit(str.charAt(i)) == true)
                n = n*10 + (str.charAt(i) - '0');
            else
                break;

            // If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
            // Handle Overflow
            if(n*signFlag >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(n*signFlag <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;
        }
        
        return (int)(signFlag*n);
    }
}
/*
Given a non-negative integer represented as a non-empty array of digits, plus one 
to the integer.

You may assume the integer do not contain any leading zero, except the number 0 
itself.

The digits are stored such that the most significant digit is at the head of the 
list.
*/
public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n-1]++;
        int i;
        for(i=n-1; i>0 && digits[i]==10;i--){
            digits[i] = 0;
            digits[i-1]++;
        }
        if(i == 0 && digits[i] == 10){
            int[] r =  new int[n+1];
            digits[0] = 0;
            System.arraycopy(digits, 0, r, 1, n);
            r[0] = 1;
            return r;
        }
        return digits;
    }
}

// Cleaner
class Solution2 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--){
            if(digits[i] < 9){
               digits[i]++;
               return digits;
            }
            digits[i] = 0;
        }
        int[] r = new int[n+1];
        r[0] = 1;
        return r;
    }
}

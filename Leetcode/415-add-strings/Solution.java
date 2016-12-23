/*
Given two non-negative numbers num1 and num2 represented as string, 
return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

public class Solution {
    public String addStrings(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int a, b;
        StringBuilder x = new StringBuilder(num1);
        x.reverse();
        StringBuilder y = new StringBuilder(num2);
        y.reverse();
        
        int carry = 0;
        int i = 0, j = 0;
        StringBuilder sum = new StringBuilder();
        while(i<n || j<m || carry != 0){
            a = 0;
            if(i < n){
                a = x.charAt(i) - '0';
                i++;
            }
            b = 0;
            if(j < m){
                b = y.charAt(j) - '0';
                j++;
            }
            int t = a + b + carry;
            char p = (char)(t%10 + '0');
            sum.append(p);
            carry = t/10;
        }
        sum.reverse();
        return sum.toString();
    }
}
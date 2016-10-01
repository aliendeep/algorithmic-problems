/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Hint:

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";
        StringBuilder result = new StringBuilder();
        if(((numerator < 0) ^ (denominator < 0)) != false)
            result.append('-');

        Long n = new Long(numerator);  
        Long d = new Long(denominator); 
        
        n = Math.abs(n);
        d = Math.abs(d);
        result.append(Long.toString(n/d));
        
        Long remainder = n%d;
        if(remainder == 0)
            return result.toString();
        
        // (remainder, position)
        Map<Long, Integer> mapping = new HashMap<>();
        result.append(".");
        
        while(remainder != 0){
            // found a remainder that occurred previously
            if(mapping.containsKey(remainder)){
                int offset = mapping.get(remainder);
                String t = result.substring(0, offset) + '(' + result.substring(offset) + ')';
                return t;
            }
            
            mapping.put(remainder, result.length());
            remainder *= 10;
            result.append(Long.toString(remainder/d));
            remainder = remainder % d;
        }  
        return result.toString();
    }
}
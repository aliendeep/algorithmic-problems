/*
Given an integer n, count the total number of digit 1 appearing in all non-negative 
integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/
public class Solution {
    public int countDigitOne(int n) {
        if(n <= 0)  return 0;
        
        int cnt = 0;
        int x = n;
        long multiplier = 1;
        while(x > 0){
            int digit = x % 10;
            x /= 10;
            cnt += x*multiplier;
            
            if(digit == 1){
                cnt += n % multiplier + 1;
            }
            else if(digit > 1)
                cnt += multiplier;
            
            multiplier *= 10;
        }
        return cnt;
    }
}
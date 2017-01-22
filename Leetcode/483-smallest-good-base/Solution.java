/*
For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n 
in string format. 

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.
Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.
Example 3:
Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.
Note:
The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.
*/
/* 
Solution Approach:
For a base b:
We can write 1 + b + b^2 + b^3 + ... + b^m = n
Since n can be as large as 10^18: we can the following case separately:

- Try to find a base that satisfies the constraint given:
1 + b + b^2 + b^3 <= n 
- If not found, 1 + b + b^2 = n, quadratic equation. Solve it and check if it
satisfies the constraint
- Otherwise, 1 + b = n
*/

public class Solution {
    public long compute(long base){
        long bs = base * base;
        long cs = base * bs;
        return 1 + base + bs + cs;
    }
    
    public boolean canConvert(long base, long n){
        while(n > 0){
            if(n % base != 1)
                return false;
            n /= base;
        }    
        return true;
    }
    
    public long sqrt(long n){
        long l = 0; 
        long h = Math.min(n, 2000000001);
        while(h - l > 3){
            long mid = (l+h)/2;
            long sqr = mid*mid;
            if(sqr == n)
                return mid;
            else if(sqr < n){
                l = mid;
            }    
            else
                h = mid;
        }
        for(long i=l; i<=h; ++i){
            if(i*i == n)
                return i;
        }
        
        return -1;
    }    
    public String smallestGoodBase(String num) {
        long n = Long.parseLong(num);
        long base = 2;
        while(compute(base) <= n){
            if(canConvert(base, n))
                return Long.toString(base);
            base++;
        }
        
        long sq = sqrt(4*n-3);
        if(sq != -1){
            if(sq % 2 == 1){
                return Long.toString((sq - 1)/2);
            }
        }
        return Long.toString(n - 1);
    }
}

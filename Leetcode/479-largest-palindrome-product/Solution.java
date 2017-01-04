/*
Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].
*/
public class Solution {
    // Generate candidate palindomes and see if it has two n digit factors
    long[] largest = {0, 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999};    
    boolean isPalindrome(long p){
        String s = Long.toString(p);
        int i = 0, j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    public int largestPalindrome(int n) {
        if(n == 1)
            return 9;
        // Largest number
        long l = largest[n];
        long s = largest[n-1];
        
        boolean found = false;
        long firstHalf = (l*l)/(int)Math.pow(10, n);
        long maxProduct = 0;

        while(!found){
            StringBuilder secondHalf = new StringBuilder().append(firstHalf).reverse();
            String c = firstHalf + secondHalf.toString();
            long candidate = Long.parseLong(c);
            for(long factor=l; factor>s; --factor){
                if(candidate / factor > l)
                    break;
                if(candidate % factor == 0){
                    maxProduct = candidate;
                    found = true;
                    break;
                }
            }
            firstHalf--;
        }

        maxProduct = maxProduct % 1337;
        return (int)maxProduct;
    }
}

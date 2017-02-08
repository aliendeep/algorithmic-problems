/*
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
*/
public class Solution {
    // Greedy
    public int integerReplacement(int a) {
        int cnt = 0;
        long n = a;
        while(n != 1){
            if(n % 2 == 0){
                n = n/2;
            }
            else{
                if(n == 3 || (Long.bitCount(n-1) < Long.bitCount(n+1))){
                    n = n - 1;
                }
                else{
                    n = n + 1;
                }
            }
            cnt++;
        }
        return cnt;
    }
}

public class Solution {
    // Greedy
    public int integerReplacement(int a) {
        int cnt = 0;
        long n = a;
        while(n != 1){
            if(n % 2 == 0){
                n = n/2;
            }
            else{
                // odd bit
                // Check out the last two bits
                // If a number ends with 01, then certainly decrementing is the way to go. 
                // 11 - increment
                if(n == 3 || ((n >> 1) & 1) == 0){
                    n = n - 1;
                }
                else{
                    n = n + 1;
                }
            }
            cnt++;
        }
        return cnt;
    }
}

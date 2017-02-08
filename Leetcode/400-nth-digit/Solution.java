/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, 
which is part of the number 10.
*/
public class Solution {
    // Simulation
    // 1 length digit - 9 (1 to 9)
    // 2 lengths digit - 2*90 = 90 (10 .. 99)
    // 3 lengths digit - 3*90*10 = 900 (100 .. 1000)
    // 4 lengths digit - 4*900*10 
    public int findNthDigit(int n) {
        // length of the digit
        int len = 1;
        // long: to avoid overflow
        long cnt = 9;
        
        int number = 1;
        while(n > len*cnt){
            n -= len*cnt;
            cnt *= 10;
            number *= 10;
            len++;
        }
        
        number += (n - 1)/len;
        String num = Integer.toString(number);
        return num.charAt((n - 1) % len) - '0';
    }
}

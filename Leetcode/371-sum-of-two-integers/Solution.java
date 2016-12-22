/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.
*/

public class Solution {
    public int getSum(int a, int b) {
        while(b != 0){
            int carry = a & b;
            a = a ^ b;
            b = carry<<1;
        }
        return a;
    }
}

class Solution2 {
    // Time: O(n)
    public int getSum(int a, int b) {
        int sum = 0, carryin = 0, k = 1;
        long tA = a, tB = b;
        while(tA != 0 || tB != 0){
            // Get the kth bit
            int ak = a & k, bk = b & k;
            int carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            k <<= 1;
            // unsigned bit operation is necessary (Example: -1 + 1)
            tA >>>= 1;
            tB >>>= 1;
        }
        return sum | carryin;
    }
}
/*
Your task is to calculate ab mod 1337 where a is a positive integer and b is an 
extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
*/

// x mod y = (mn) mod y = (m mod y) (n mod y)
// x^325 = x^300 x^20 x^5 = (x^(100))^3 (x^(10))^2 x^5. at each bit of 325, 
// the base is actually x^10 of previous base.
public class Solution {
    public static final int kMod = 1337;
    public int pow(int x, int y){
        x = x%kMod;
        int r = 1;
        while(y > 0){
            if((y & 1) != 0)
                r = (r*x)%kMod;
            x = (x*x) % kMod;
            y >>= 1;
        }
        return r;
    }

    public int superPow(int a, int[] b) {
        if(b.length == 0)
            return 1;
        int r = 1;
        for(int i=b.length-1; i>=0; i--){
            r = (r*pow(a, b[i]))%kMod;
            a = pow(a, 10);
        }
        return r;
    }
}

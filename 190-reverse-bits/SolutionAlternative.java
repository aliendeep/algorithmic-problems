// Follow up: If this function is called many times, how would you optimize it?
public class Solution {
    // precomputed reverse of all 8 bit number
    public static int[] computedReverse;
    public Solution(){
        computedReverse = new int[256];
        for(int i=1; i<(1<<8); i++){
            computedReverse[i] = reverse(i);
            //System.out.println(computedReverse[i]);
        }
    }
    
    // n - 8 bit number
    public static int reverse(int n){
        int rev = 0;
        for(int i=7; i>=0; i--){
            if((n & (1<<i)) != 0)
                rev |= 1 << (7-i);            
        }
        return rev;
    }    
    
    public static void printBinary(int n){
        for(int i=7; i>=0; i--){
            if((n & (1<<i)) != 0)
                System.out.print(1);
            else                    
                System.out.print(0);
        }   
        System.out.println();    
    }
    // 32 bit integer
    public  static int reverseBits(int n) {
        int bitMask = 0xFF;
        int wordSize = 8;
        long x3 = (long)computedReverse[(n >>> 3*wordSize) & bitMask];
        long x2 = (long)computedReverse[(n >>> 2*wordSize) & bitMask] << wordSize;
        long x1 = (long)computedReverse[(n >>> wordSize) & bitMask] << (2*wordSize);
        long x0 = (long)computedReverse[(int)(n & bitMask)] << (3*wordSize); 
        printBinary((int)x0);

        return (int)(x3 | x2 | x1 | x0);
    }
}
public class Solution {
    public int[] singleNumber(int[] nums) {
        int aXorB = 0;
        for(int n : nums)
            aXorB ^= n;
        
        // Find the lowest set bit in aXorB
        int lowestDifferentBit = aXorB & ~(aXorB-1);
        int a = 0, b = 0;
        for(int n : nums){
            if((lowestDifferentBit & n) != 0)
                a ^= n;
            else
                b ^= n;
        }
        int[] t = new int[2];
        t[0] = a;
        t[1] = b;
        return t;
    }
}
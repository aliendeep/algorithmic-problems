/*
Given an array of numbers nums, in which exactly two elements appear only once and 
all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
1. The order of the result is not important. So in the above example, [5, 3] is also correct.
2. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/
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

class Solution2 {
    public int[] singleNumber(int[] nums) {
        int aXorB = 0;
        for(int n : nums)
            aXorB ^= n;
        
        // Find the lowest set bit in aXorB
        int lowestDifferentBit = aXorB & -aXorB;
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
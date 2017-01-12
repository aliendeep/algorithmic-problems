/*
Given an array of integers, every element appears three times except for one, 
which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it 
without using extra memory?
*/
// Extra memory
public class Solution {
    public int singleNumber(int[] nums) {
        int[] bitCnt = new int[32];
        
        for(int n : nums){
            for(int i=31; i>=0; i--){
                if((n & (1<<i)) != 0)
                    bitCnt[i]++;
            }    
        }
        int r = 0;
        for(int i=31; i>=0; i--){
            r |= (bitCnt[i] % 3) << i;
        }
        return r;
    }
}

// No Extra memory
class Solution2 {
    // Ones denotes whether a bit position has been set once (module 3)
    // Twos denotes whether a bit position has been set twice (module 3)
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        int nextOnes = 0, nextTwos = 0;
        for(int n : nums){
            nextOnes = (~n & ones) | (n & ~ones & ~twos);
            nextTwos = (~n & twos) | (n & ones & ~twos);
            ones = nextOnes;
            twos = nextTwos;
       }
       return ones;
    }
}

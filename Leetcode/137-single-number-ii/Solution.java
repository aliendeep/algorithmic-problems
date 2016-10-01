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
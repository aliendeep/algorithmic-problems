public class Solution {
    public int missingNumber(int[] nums) {
        int r = 0;
        for(int i=0; i<nums.length; i++){
            r ^= (i+1);
            r ^= nums[i];
        }
        return r;
    }
}
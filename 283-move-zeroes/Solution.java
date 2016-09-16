public class Solution {
    public void moveZeroes(int[] nums) {
        int w = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0)
                nums[w++] = nums[i];
        }
        // write 0s
        while(w < nums.length){
            nums[w++] = 0;
        }        
    }
}
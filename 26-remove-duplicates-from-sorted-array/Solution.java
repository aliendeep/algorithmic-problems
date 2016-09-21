public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 1)
            return nums.length; 
        int w = 1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] != nums[w-1])
                nums[w++] = nums[i];
        }
        return w;
    }
}
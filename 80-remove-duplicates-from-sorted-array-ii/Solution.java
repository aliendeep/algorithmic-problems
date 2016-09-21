public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 2)
            return nums.length; 
        int w = 2;
        for(int i=2; i<nums.length; i++){
            if(nums[i] != nums[w-2])
                nums[w++] = nums[i];
        }
        return w;        
    }
}
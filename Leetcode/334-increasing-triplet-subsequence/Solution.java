public class Solution {
    // the_smallest_so_far < the_second_smallest_so_far < current 
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3)     
            return false;
        int minValue = Integer.MAX_VALUE;
        int secondMinValue = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            if(minValue >= nums[i])
                minValue = nums[i];
            else if(secondMinValue >= nums[i])
                secondMinValue = nums[i];
            else
                return true;
        }
        return false;
    }
}
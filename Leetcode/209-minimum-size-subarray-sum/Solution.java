public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int i = 0;
        int sum = 0;
        int n = nums.length;
        while(i < n){
            if(sum < target)
                sum += nums[i++];
            if(sum >= target && start < n){
                minLength = Math.min(minLength, i-start);
                sum -= nums[start++];
            }
        }
        
        // Last window        
        while(sum >= target && start < n){
            minLength = Math.min(minLength, i-start);
            sum -= nums[start++];
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength; 
    }
}
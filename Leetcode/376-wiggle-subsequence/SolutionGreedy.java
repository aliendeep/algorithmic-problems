public class Solution {
    // Greedy
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n <= 1)      return n;
        
        int prevDiff = (nums[1] - nums[0]);
        // Starts with the same number
        System.out.println(nums[0] + " " + prevDiff);
        int len = (prevDiff == 0) ? 1 : 2;
        for(int i=2; i<n; ++i){
            int diff = nums[i] - nums[i-1];
            if((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)){
                len++;
                prevDiff = diff;
            }
        }
        return len;
    }
}
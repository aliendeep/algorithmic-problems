public class Solution {
    // DP:  O(n) (Space Optimized)
    // https://leetcode.com/articles/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        
        // Prev values
        int up = 1;
        int down = 1;
        // next values
        int up1, down1;
        for(int i=1; i<n; ++i){
            // increasing
            if(nums[i] > nums[i-1]){
                up1 = down + 1;
                down1 = down;
            }
            // decreasing
            else if(nums[i] < nums[i-1]){
                down1 = up + 1;
                up1 = up;
            }
            // equal, same as the before
            else{
                up1 = up;
                down1 = down;
            }
            // ready for next iteration
            up = up1;
            down = down1;
        }
        return Math.max(up, down);
    }
}

// Cleaner version
public class Solution {
    // DP:  O(n) (Space Optimized)
    // https://leetcode.com/articles/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n == 0)      return 0;
        
        // values
        int up = 1;
        int down = 1;
        for(int i=1; i<n; ++i){
            // increasing
            if(nums[i] > nums[i-1]){
                up = down + 1;
            }
            // decreasing
            else if(nums[i] < nums[i-1]){
                down = up + 1;
            }
            // equal, same as the before
        }
        return Math.max(up, down);
    }
}
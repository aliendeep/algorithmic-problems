/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/
public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] pos = new int[n];
        Arrays.fill(pos, 1);
        
        int[] neg = new int[n];
        Arrays.fill(neg, Integer.MAX_VALUE);
        
        pos[0] = nums[0];
        neg[0] = nums[0];
        int r = pos[0];
        for(int i=1; i<n; ++i){
            pos[i] = Math.max(Math.max(pos[i-1]*nums[i], neg[i-1]*nums[i]), nums[i]);    
            neg[i] = Math.min(Math.min(pos[i-1]*nums[i], neg[i-1]*nums[i]), nums[i]);  
            r = Math.max(r, pos[i]);
       }
       return r;
    }
}
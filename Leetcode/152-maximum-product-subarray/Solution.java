public class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 1)
            return nums[0];            
        
        int curMaxProduct = 1;
        int curMinProduct = 1;
        int result = Integer.MIN_VALUE;
        for(int n : nums){
            int x = curMaxProduct;
            int y = curMinProduct;
            curMaxProduct = Math.max(n*x, Math.max(n*y, n));
            curMinProduct = Math.min(n*x, Math.min(n*y, n));
            result = Math.max(result, curMaxProduct);
        }
        return result;
    }
}
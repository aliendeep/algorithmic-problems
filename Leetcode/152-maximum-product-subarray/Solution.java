/*
Find the contiguous subarray within an array (containing at least one number) 
which has the largest product.

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

// O(1) Space
class Solution2 {
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

class Solution3 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int pos = nums[0];
        int neg = nums[0];
        int r = pos;
        for(int i=1; i<n; ++i){
            int posNext = Math.max(Math.max(pos*nums[i], neg*nums[i]), nums[i]);    
            int negNext = Math.min(Math.min(pos*nums[i], neg*nums[i]), nums[i]);  
            r = Math.max(r, posNext);
            pos = posNext;
            neg = negNext;
       }
       return r;
    }
}

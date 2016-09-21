public class Solution {
    // Derive from the example given
    // Time:  O(n)
    public int trap(int[] height) {
        if(height.length <= 2)
            return 0;

        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0; 
        int left = 0, right = height.length - 1;
        while(left < right){
            if(height[left] <=  height[right]){
                // left to right pass
                if(height[left] >= maxLeft)
                    maxLeft = height[left];        
                else
                    sum += maxLeft - height[left];
                left++;
            }
            else{
                // right to left pass
                if(height[right] >= maxRight)
                    maxRight = height[right];        
                else
                    sum += maxRight - height[right];
                right--;
            }
        }
        return sum;
    }
}
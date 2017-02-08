/*
Given n non-negative integers representing an elevation map where the width of 
each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/
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
            if(height[left] <= height[right]){
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

class Solution2 {
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0)  return 0;
        int maxHeight = 0;
        int h = 0;
        for(int i=0; i<n; ++i){
            if(maxHeight < height[i]){
                maxHeight = height[i];
                h = i;
            }
        }
        int r = 0;
        int maxLeft = height[0];
        for(int i=1; i<h; ++i){
            if(height[i] > maxLeft){
                maxLeft = height[i];
            }
            else{
                r += maxLeft - height[i];
            }
        }
        int maxRight = height[n-1];
        for(int i=n-2; i>h; --i){
            if(height[i] > maxRight){
                maxRight = height[i];
            }
            else{
                r += maxRight - height[i];
            }
        }
        return r;
    }
}

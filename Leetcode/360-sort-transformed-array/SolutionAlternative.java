/*
Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]
*/

public class Solution {
    // Alternative: Differentiate to find maxima or minima
    double f(double x, int a, int b, int c){
        return a*x*x + b*x + c;
    }
    
    double minimaOrMaxima(int a, int b){
        /* 
        2ax + b = 0
        => x =  -b/(2a);
        */
        if(a == 0)
            return 0;
        return (double)(-b / (2*a));
    }

    public int[] sortPositiveTransform(int[] nums, int a, int b, int c){
        int n = nums.length;
        int[] r = new int[n];
        
        double minX = minimaOrMaxima(a, b);
        int closestToMinimaIndex = 0;
        double closestToMinimaDiff = f(nums[0], a, b, c) - f(minX, a, b, c);
        for(int i=1; i<nums.length; ++i){
            if(closestToMinimaDiff > f(nums[i], a, b, c) - f(minX, a, b, c)){
                closestToMinimaDiff = f(nums[i], a, b, c) - f(minX, a, b, c);    
                closestToMinimaIndex = i;
            }
        }
        
        r[0] = (int)f(nums[closestToMinimaIndex], a, b, c);
        int left = closestToMinimaIndex - 1;
        int right = closestToMinimaIndex + 1;

        int index = 1;
        while(left >= 0 || right <= n-1){
            int p = left >= 0 ? (int)f(nums[left], a, b, c) : Integer.MAX_VALUE;
            int q = right <= n-1 ? (int)f(nums[right], a, b, c) : Integer.MAX_VALUE;
            if(right == n || p < q){
                r[index++] = p;
                left--;
            }
            else{
                r[index++] = q;
                right++;
            }
        }
        return r;
    } 
    
    public int[] sortNegativeTransform(int[] nums, int a, int b, int c){
        int n = nums.length;
        int[] r = new int[n];
        
        double maxX = minimaOrMaxima(a, b);
        int closestToMaximaIndex = 0;
        double closestToMaximaDiff = f(maxX, a, b, c) - f(nums[0], a, b, c);
        for(int i=1; i<nums.length; ++i){
            if(closestToMaximaDiff > f(maxX, a, b, c) - f(nums[i], a, b, c)){
                closestToMaximaDiff = f(maxX, a, b, c) - f(nums[i], a, b, c);    
                closestToMaximaIndex = i;
            }
        }
        
        r[n-1] = (int)f(nums[closestToMaximaIndex], a, b, c);
        int left = closestToMaximaIndex - 1;
        int right = closestToMaximaIndex + 1;

        int index = n-2;
        while(left >= 0 || right <= n-1){
            int p = left >= 0 ? (int)f(nums[left], a, b, c) : Integer.MIN_VALUE;
            int q = right <= n-1 ? (int)f(nums[right], a, b, c) : Integer.MIN_VALUE;
            if(right == n || p > q){
                r[index--] = p;
                left--;
            }
            else{
                r[index--] = q;
                right++;
            }
        }
        return r;
    }      
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(a > 0){
            return sortPositiveTransform(nums, a, b, c); 
        }
        else{
            return sortNegativeTransform(nums, a, b, c);    
        }
    }
}